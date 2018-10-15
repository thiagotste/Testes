package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.LivroDAO;
import Model.Etidade.Livro;
import javax.servlet.RequestDispatcher;


public class livroServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codLivro = Integer.parseInt(request.getParameter("codLivro"));
        int codEscritor = Integer.parseInt(request.getParameter("codEscritor"));
        String url = "";
        LivroDAO liDao = new LivroDAO();
        Livro l = liDao.selecionarEntidade(codLivro, codEscritor);
        
        HttpSession session = request.getSession();
        
        
        String [] splitDescricao = l.getDescricao().split("%");
        
        session.setAttribute("livro", l);
        session.setAttribute("descricao", splitDescricao);
        
        if(l.getSituacao().equals("1")){
            request.setAttribute("mensagem", "O livro nâo está disponível.");
        }
        
        url = "/livro.jsp";
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
