package ma.fstt.servlets;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ma.fstt.dao.ClientDao;
import ma.fstt.entities.Client;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private ClientDao dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "new":
                    req.getRequestDispatcher("/client/form.jsp").forward(req, resp);
                    break;
                case "edit":
                    req.setAttribute("client", dao.findById(Integer.parseInt(req.getParameter("id"))));
                    req.getRequestDispatcher("/client/form.jsp").forward(req, resp);
                    break;
                case "delete":
                    dao.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect("clients");
                    break;
                default:
                    req.setAttribute("clients", dao.findAll());
                    req.getRequestDispatcher("/client/list.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            Client c = new Client();
            c.setNom(req.getParameter("nom"));
            c.setPrenom(req.getParameter("prenom"));
            c.setEmail(req.getParameter("email"));
            c.setTelephone(req.getParameter("telephone"));
            c.setAdresse(req.getParameter("adresse"));

            String id = req.getParameter("id");
            if (id == null || id.isEmpty()) {
                dao.create(c);
            } else {
                c.setId(Integer.parseInt(id));
                dao.update(c);
            }
            resp.sendRedirect("clients");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}