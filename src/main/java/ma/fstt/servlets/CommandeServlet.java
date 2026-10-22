package ma.fstt.servlets;

import java.io.IOException;
import java.sql.Date;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ma.fstt.dao.ClientDao;
import ma.fstt.dao.CommandeDao;
import ma.fstt.dao.ProduitDao;
import ma.fstt.entities.Client;
import ma.fstt.entities.Commande;

@WebServlet("/commandes")
public class CommandeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private CommandeDao dao;
    @Inject
    private ClientDao clientDao;
    @Inject
    private ProduitDao produitDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "new":
                    req.setAttribute("clients", clientDao.findAll());
                    req.getRequestDispatcher("/commande/form.jsp").forward(req, resp);
                    break;
                case "edit":
                    req.setAttribute("commande", dao.findById(Integer.parseInt(req.getParameter("id"))));
                    req.setAttribute("clients", clientDao.findAll());
                    req.getRequestDispatcher("/commande/form.jsp").forward(req, resp);
                    break;
                case "delete":
                    dao.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("commandes");
                    break;
                case "detail":
                    req.setAttribute("commande", dao.findById(Integer.parseInt(req.getParameter("id"))));
                    req.setAttribute("produits", produitDao.findAll());
                    req.getRequestDispatcher("/commande/detail.jsp").forward(req, resp);
                    break;
                case "deleteLigne": {
                    int cid = Integer.parseInt(req.getParameter("commandeId"));
                    dao.deleteLigne(Integer.parseInt(req.getParameter("ligneId")));
                    resp.sendRedirect("commandes?action=detail&id=" + cid);
                    break;
                }
                default:
                    req.setAttribute("commandes", dao.findAll());
                    req.getRequestDispatcher("/commande/list.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "save";
        try {
            switch (action) {
                case "addLigne": {
                    int cid = Integer.parseInt(req.getParameter("commandeId"));
                    dao.addLigne(cid,
                            Integer.parseInt(req.getParameter("produitId")),
                            Integer.parseInt(req.getParameter("quantite")));
                    resp.sendRedirect("commandes?action=detail&id=" + cid);
                    break;
                }
                case "updateLigne": {
                    int cid = Integer.parseInt(req.getParameter("commandeId"));
                    dao.updateLigne(Integer.parseInt(req.getParameter("ligneId")),
                            Integer.parseInt(req.getParameter("produitId")),
                            Integer.parseInt(req.getParameter("quantite")));
                    resp.sendRedirect("commandes?action=detail&id=" + cid);
                    break;
                }
                default: {
                    Commande c = new Commande();
                    c.setDateCommande(Date.valueOf(req.getParameter("dateCommande")));
                    Client cl = new Client();
                    cl.setId(Integer.parseInt(req.getParameter("clientId")));
                    c.setClient(cl);

                    String id = req.getParameter("id");
                    if (id == null || id.isEmpty()) {
                        dao.create(c);
                    } else {
                        c.setId(Integer.parseInt(id));
                        dao.update(c);
                    }
                    resp.sendRedirect("commandes");
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}