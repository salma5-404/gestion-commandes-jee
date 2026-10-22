package ma.fstt.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int id;
    private Date dateCommande;
    private Client client;
    private List<LigneCommande> lignes = new ArrayList<>();

    public Commande() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getDateCommande() { return dateCommande; }
    public void setDateCommande(Date dateCommande) { this.dateCommande = dateCommande; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public List<LigneCommande> getLignes() { return lignes; }
    public void setLignes(List<LigneCommande> lignes) { this.lignes = lignes; }

    public double getTotal() {
        double t = 0;
        for (LigneCommande l : lignes) t += l.getSousTotal();
        return t;
    }
}