package Control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Etidade.Biblioteca;
import Model.DAO.BibliotecaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

public class AdicionarBibliotecaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Biblioteca b = null;
        BibliotecaDAO bDao = new BibliotecaDAO();
        String url = "";

        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String cep = request.getParameter("cep");
        String cidade = request.getParameter("cidade");
        String email = request.getParameter("email");
        String uf = request.getParameter("uf");
        String telefone = request.getParameter("telefone");
        
        if (nome != null) {
            b = new Biblioteca(nome, endereco, cep, cidade, uf, telefone, email);
            bDao.adicionarEntidade(b);
        }
        url = "/Admin/adBiblioteca.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
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
