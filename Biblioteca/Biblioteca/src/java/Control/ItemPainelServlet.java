package Control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import Model.DAO.EmprestimoDAO;
import Model.Etidade.Emprestimo;
import Model.DAO.EmprestimoFuncionarioDAO;
import Model.Etidade.emprestimoFuncionario;
import Model.Etidade.Cliente;
import Model.Etidade.Funcionario;
import Model.DAO.TipoLivroDAO;
import Model.Etidade.TipoLivro;
import Model.DAO.EscritorDAO;
import Model.Etidade.Escritor;
import Model.DAO.BibliotecaDAO;
import Model.Etidade.Biblioteca;

public class ItemPainelServlet extends HttpServlet {

    String url = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int controle = Integer.parseInt(request.getParameter("controle"));
        String cod = request.getParameter("cod");
        String de = request.getParameter("de");
        String ate = request.getParameter("ate");

        HttpSession session = request.getSession();

        if (session.getAttribute("cliente") == null && session.getAttribute("fun") == null) {
            url = "/controCliente.jsp";
        } else {
            if (session.getAttribute("cliente") != null) {
                controleCliente(controle, session, cod, de, ate);
            } else if (session.getAttribute("fun") != null) {
                controleFuncionario(controle, session, cod, de, ate);
            }
        }

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    private void controleCliente(int controle, HttpSession session, String cod,
            String de, String ate) {
        EmprestimoDAO emDao = new EmprestimoDAO();
        ArrayList<Emprestimo> e;

        Cliente c = (Cliente) session.getAttribute("cliente");

        switch (controle) {
            case 0:
                e = emDao.selecionarTodasEntidades(c.getCodCliente());
                session.setAttribute("arrayEmprestimo", e);
                session.setAttribute("fudeu", "0");
                url = "/ultEmprestimos.jsp";
                break;
            case 1:
                e = emDao.selecionarAbertos(c.getCodCliente());
                session.setAttribute("arrayEmprestimo", e);
                session.setAttribute("fudeu", "1");
                url = "/ultEmprestimos.jsp";
                break;
            case 2:
                e = emDao.selecionarFechados(c.getCodCliente());
                session.setAttribute("arrayEmprestimo", e);
                session.setAttribute("fudeu", "2");
                url = "/ultEmprestimos.jsp";
                break;
            case 3:
                if (cod == "33") {
                    e = emDao.pesquidarData(de, ate, c.getCodCliente());
                    session.setAttribute("arrayEmprestimo", e);
                    session.setAttribute("fudeu", "3");
                    url = "/ultEmprestimos.jsp";
                } else {
                    url = "/empPorData.jsp";
                }
                break;
            case 4:
                e = emDao.selecionarTodasEntidades(c.getCodCliente());
                session.setAttribute("arrayEmprestimo", e);
                session.setAttribute("fudeu", "4");
                url = "/ultEmprestimos.jsp";
                break;
            case 5:
                url = "/alterarEmail.jsp";
                break;
            case 6:
                session.removeAttribute("cliente");
                url = "/controCliente.jsp";
                break;
            case 7:
                url = "/alterarSenha.jsp";
                break;
            case 8:
                url = "/alterarCadastro.jsp";
                break;
            case 9:

                break;
        }
    }

    private void controleFuncionario(int controle, HttpSession session, String cod,
            String de, String ate) {
        EmprestimoFuncionarioDAO emFunDao = new EmprestimoFuncionarioDAO();
        ArrayList<emprestimoFuncionario> ef;

        Funcionario f = (Funcionario) session.getAttribute("fun");

        switch (controle) {
            case 0:
                ef = emFunDao.selecionarTodasEntidades(f.getCodFuncionario());
                session.setAttribute("arrayEmprestimoFuncionario", ef);
                session.setAttribute("fudeu", "0");
                url = "/ultEmprestimos.jsp";
                break;
            case 1:
                ef = emFunDao.selecionarAbertos(f.getCodFuncionario());
                session.setAttribute("arrayEmprestimoFuncionario", ef);
                session.setAttribute("fudeu", "1");
                url = "/ultEmprestimos.jsp";
                break;
            case 2:
                ef = emFunDao.selecionarFechados(f.getCodFuncionario());
                session.setAttribute("arrayEmprestimoFuncionario", ef);
                session.setAttribute("fudeu", "2");
                url = "/ultEmprestimos.jsp";
                break;
            case 3:
                if (cod == "33") {
                    ef = emFunDao.pesquidarData(de, ate, f.getCodFuncionario());
                    session.setAttribute("arrayEmprestimoFuncionario", ef);
                    session.setAttribute("fudeu", "3");
                    url = "/ultEmprestimos.jsp";
                } else {
                    url = "/empPorData.jsp";
                }
                break;
            case 4:
                ef = emFunDao.selecionarTodasEntidades(f.getCodFuncionario());
                session.setAttribute("arrayEmprestimoFuncionario", ef);
                session.setAttribute("fudeu", "4");
                url = "/ultEmprestimos.jsp";
                break;
            case 5:
                url = "/alterarEmail.jsp";
                break;
            case 6:
                session.removeAttribute("fun");
                url = "/controCliente.jsp";
                break;
            case 7:
                url = "/alterarSenha.jsp";
                break;
            case 8:
                url = "/alterarCadastro.jsp";
                break;
            case 9:
                url = "/Admin/adTipo.jsp";
                break;
            case 10:
                url = "/Admin/adEscritor.jsp";
                break;
            case 11:
                url = "/Admin/adBiblioteca.jsp";
                break;
            case 12:
                url = "/Admin/adFuncionario.jsp";
                break;
            case 13:
                url = "/Admin/adCliente.jsp";
                break;
            case 14:
                EscritorDAO eDao = new EscritorDAO();
                ArrayList<Escritor> escritores = eDao.selecionarTodasEntidades();
                session.setAttribute("escritores", escritores);

                TipoLivroDAO tDao = new TipoLivroDAO();
                ArrayList<TipoLivro> tipos = tDao.selecionarTodasEntidades();
                session.setAttribute("tipos", tipos);

                BibliotecaDAO bDao = new BibliotecaDAO();
                ArrayList<Biblioteca> bibliotecas = bDao.selecionarTodasEntidades();
                session.setAttribute("bibliotecas", bibliotecas);

                url = "/Admin/adLivro.jsp";
                break;
            case 15:
                
                break;
            case 16:
                url = "/Admin/realizarEntrega.jsp";
                break;
        }
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
