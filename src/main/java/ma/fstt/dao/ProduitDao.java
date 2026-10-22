package ma.fstt.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import ma.fstt.entities.Produit;
import ma.fstt.service.IDao;

@ApplicationScoped
public class ProduitDao implements IDao<Produit> {

    @Override
    public void create(Produit p) throws Exception {
        String sql = "INSERT INTO produit(designation,prix,stock) VALUES(?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getDesignation());
            ps.setDouble(2, p.getPrix());
            ps.setInt(3, p.getStock());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Produit p) throws Exception {
        String sql = "UPDATE produit SET designation=?,prix=?,stock=? WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getDesignation());
            ps.setDouble(2, p.getPrix());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM produit WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Produit findById(int id) throws Exception {
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM produit WHERE id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    @Override
    public List<Produit> findAll() throws Exception {
        List<Produit> list = new ArrayList<>();
        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM produit ORDER BY id")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    private Produit map(ResultSet rs) throws SQLException {
        return new Produit(rs.getInt("id"), rs.getString("designation"),
                rs.getDouble("prix"), rs.getInt("stock"));
    }
}