package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.DAO.TipoLivroDAO;
import Model.Etidade.TipoLivro;
import javax.servlet.RequestDispatcher;

public class AdicionarTipoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        TipoLivro tp = null;
        TipoLivroDAO tpDao = new TipoLivroDAO();
        String url = "/Admin/adTipo.jsp";
        
        if (!nome.equals("")) {
            try {
                if (tpDao.isPesquisarNome(nome)) {
                request.setAttribute("message", "Tipo ja existe.");
                request.setAttribute("nome", nome);
            } else {                
                tp = new TipoLivro(nome);
                tpDao.adicionarEntidade(tp);
            }
            } catch (Exception e) {
                System.out.println("deu merda.");
            }          
        }
        else{
            request.setAttribute("message", "Você precisa digitar um nome.");
        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
