package ma.fstt.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import ma.fstt.entities.*;
import ma.fstt.service.IDao;

@ApplicationScoped
public class CommandeDao implements IDao<Commande> {

    @Override
    public void create(Commande c) throws Exception {
        String sql = "INSERT INTO commande(date_commande,client_id) VALUES(?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDate(1, c.getDateCommande());
            ps.setInt(2, c.getClient().getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Commande c) throws Exception {
        String sql = "UPDATE commande SET date_commande=?,client_id=? WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setDate(1, c.getDateCommande());
            ps.setInt(2, c.getClient().getId());
            ps.setInt(3, c.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM commande WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Commande findById(int id) throws Exception {
        String sql = "SELECT c.id, c.date_commande, cl.id AS cid, cl.nom, cl.prenom " +
                     "FROM commande c JOIN client cl ON c.client_id = cl.id WHERE c.id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                Commande c = map(rs);
                c.setLignes(findLignesByCommande(id));
                return c;
            }
        }
    }

    @Override
    public List<Commande> findAll() throws Exception {
        List<Commande> list = new ArrayList<>();
        String sql = "SELECT c.id, c.date_commande, cl.id AS cid, cl.nom, cl.prenom " +
                     "FROM commande c JOIN client cl ON c.client_id = cl.id ORDER BY c.id";
        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        }
        for (Commande c : list) c.setLignes(findLignesByCommande(c.getId()));
        return list;
    }

    // ---------- Lignes de commande ----------

    public List<LigneCommande> findLignesByCommande(int commandeId) throws Exception {
        List<LigneCommande> list = new ArrayList<>();
        String sql = "SELECT l.id, l.quantite, p.id AS pid, p.designation, p.prix, p.stock " +
                     "FROM ligne_commande l JOIN produit p ON l.produit_id = p.id " +
                     "WHERE l.commande_id=? ORDER BY l.id";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, commandeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LigneCommande l = new LigneCommande();
                    l.setId(rs.getInt("id"));
                    l.setQuantite(rs.getInt("quantite"));
                    l.setProduit(new Produit(rs.getInt("pid"), rs.getString("designation"),
                            rs.getDouble("prix"), rs.getInt("stock")));
                    list.add(l);
                }
            }
        }
        return list;
    }

    public void addLigne(int commandeId, int produitId, int quantite) throws Exception {
        String sql = "INSERT INTO ligne_commande(commande_id,produit_id,quantite) VALUES(?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, commandeId);
            ps.setInt(2, produitId);
            ps.setInt(3, quantite);
            ps.executeUpdate();
        }
    }

    public void updateLigne(int ligneId, int produitId, int quantite) throws Exception {
        String sql = "UPDATE ligne_commande SET produit_id=?, quantite=? WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, produitId);
            ps.setInt(2, quantite);
            ps.setInt(3, ligneId);
            ps.executeUpdate();
        }
    }

    public void deleteLigne(int ligneId) throws Exception {
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM ligne_commande WHERE id=?")) {
            ps.setInt(1, ligneId);
            ps.executeUpdate();
        }
    }

    private Commande map(ResultSet rs) throws SQLException {
        Commande c = new Commande();
        c.setId(rs.getInt("id"));
        c.setDateCommande(rs.getDate("date_commande"));
        Client cl = new Client();
        cl.setId(rs.getInt("cid"));
        cl.setNom(rs.getString("nom"));
        cl.setPrenom(rs.getString("prenom"));
        c.setClient(cl);
        return c;
    }
}