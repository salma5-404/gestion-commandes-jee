package ma.fstt.entities;

public class LigneCommande {
    private int id;
    private Commande commande;
    private Produit produit;
    private int quantite;

    public LigneCommande() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }
    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public double getSousTotal() {
        return produit == null ? 0 : produit.getPrix() * quantite;
    }
}