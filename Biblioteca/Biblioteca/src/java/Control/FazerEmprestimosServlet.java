package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;

import Model.Etidade.Cliente;
import Model.Etidade.Funcionario;
import Model.Etidade.emprestimoFuncionario;
import Model.DAO.EmprestimoFuncionarioDAO;
import Model.Etidade.Emprestimo;
import Model.DAO.EmprestimoDAO;
import Model.DAO.LivroDAO;

public class FazerEmprestimosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";

        HttpSession session = request.getSession();
        Cliente c = (Cliente) session.getAttribute("cliente");
        Funcionario f = (Funcionario) session.getAttribute("fun");

        int liId = Integer.parseInt(request.getParameter("liId"));
        int escId = Integer.parseInt(request.getParameter("escId"));

        GregorianCalendar greHoje = new GregorianCalendar();
        DateFormat dtFormat = DateFormat.getDateInstance();

        Date dHoje = greHoje.getTime();
        String sHoje = dtFormat.format(dHoje);

        if (greHoje.get(Calendar.DAY_OF_WEEK) == 7) {
            greHoje.add(Calendar.DAY_OF_MONTH, 9);
        } else if (greHoje.get(Calendar.DAY_OF_WEEK) == 1) {
            greHoje.add(Calendar.DAY_OF_MONTH, 8);
        } else {
            greHoje.add(Calendar.DAY_OF_MONTH, 7);
        }
        Date dEntrega = greHoje.getTime();
        String sEntrega = dtFormat.format(dEntrega);

        if (c != null) {
            int clid = Integer.parseInt(request.getParameter("clid"));
            
            Emprestimo emp = new Emprestimo(clid, liId, escId, sHoje, sEntrega, "1");
            EmprestimoDAO empDao = new EmprestimoDAO();
            empDao.adicionarEntidade(emp);

            LivroDAO liDao = new LivroDAO();
            liDao.atualizarSituacao("1", liId, escId);
            session.setAttribute("sEntrega", sEntrega);

            url = "/concluirEmprestimo.jsp";            
            
        }else if(f != null){
            int funid = Integer.parseInt(request.getParameter("funid"));
            
            emprestimoFuncionario empFun = new emprestimoFuncionario(funid, liId, escId, sHoje, sEntrega, "1");
            EmprestimoFuncionarioDAO empFunDao = new EmprestimoFuncionarioDAO();
            empFunDao.adicionarEntidade(empFun);
            
            LivroDAO liDao = new LivroDAO();
            liDao.atualizarSituacao("1", liId, escId);
            session.setAttribute("sEntrega", sEntrega);

            url = "/concluirEmprestimo.jsp";
            
        }else {
            request.setAttribute("mensagem", "È preciso fazer login.");
            url = "/controCliente.jsp";
        }

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
