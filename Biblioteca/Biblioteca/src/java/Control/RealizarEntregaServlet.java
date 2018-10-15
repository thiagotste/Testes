package Control;

import java.io.IOException;
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
import Model.DAO.LivroDAO;
import javax.servlet.RequestDispatcher;


public class RealizarEntregaServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        String tipo = request.getParameter("tipo");
        String situacao = request.getParameter("situacao");
        String url = "";
        
        HttpSession session = request.getSession();
        EmprestimoDAO emDao = new EmprestimoDAO(); 
        EmprestimoFuncionarioDAO emFunDao = new EmprestimoFuncionarioDAO();
        LivroDAO lDao = new LivroDAO();
        
        if(situacao.equals("1")){
            int cod = Integer.parseInt(request.getParameter("cod"));
            int codL = Integer.parseInt(request.getParameter("codL"));
            int codE = Integer.parseInt(request.getParameter("codE"));
            String t = (String) session.getAttribute("t");
            
            if(t.equals("cliente")){
                emDao.atualizarSituacao(cod, codL, codE);
                lDao.atualizarSituacao("0", codL, codE);
                url = budcarEmprestAberto(t, session, cod);
            }else{
                emFunDao.atualizarSituacao(cod, codL, codE);
                lDao.atualizarSituacao("0", codL, codE);
                url = budcarEmprestAberto(t, session, cod);
            }            
        }else{
            int pesEmpre = Integer.parseInt(request.getParameter("pesEmpes"));
            url = budcarEmprestAberto(tipo, session, pesEmpre);
        }
                
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
    }
    
    private String budcarEmprestAberto(String tipo, HttpSession session, int pesEmpre){
        if(tipo.equals("cliente")){
             EmprestimoDAO emDao = new EmprestimoDAO();
             ArrayList<Emprestimo> arrayEmp = emDao.selecionarAbertos(pesEmpre);
             session.setAttribute("arrayEmprestimoFun", arrayEmp);
             session.setAttribute("t", "cliente");             
             
        }else if(tipo.equals("funcionario")){
            EmprestimoFuncionarioDAO emFunDao = new EmprestimoFuncionarioDAO();
            ArrayList<emprestimoFuncionario> arrayEmFun = emFunDao.selecionarAbertos(pesEmpre);
            session.setAttribute("arrayEmprestimoFun", arrayEmFun);
            session.setAttribute("t", "funcionario");
        }
        
        return "/Admin/realizarEntrega.jsp";
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
