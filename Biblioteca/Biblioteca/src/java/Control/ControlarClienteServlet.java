package Control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.DAO.ClienteDAO;
import Model.Etidade.Cliente;
import Model.DAO.FuncionarioDAO;
import Model.Etidade.Funcionario;


public class ControlarClienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email_cpf = request.getParameter("email_cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha_cpf");
        int controle = -1;
        try {
            controle = Integer.parseInt(request.getParameter("controle"));
        } catch (NumberFormatException e) {
            
        }
        

        String resEmail = "";
        String url = "/controCliente.jsp";

        HttpSession sesseion = request.getSession();
        
        Cliente c = null;
        ClienteDAO clieDao = new ClienteDAO();

        Funcionario f = null;
        FuncionarioDAO fDao = new FuncionarioDAO();

        switch (controle) {
            case 0:
                if (fDao.isPesquisarEmail(email_cpf)
                        && isDecomporCodigo(fDao.pesquisarTipo(email_cpf), "administrador")) {
                    String s = email_cpf + senha;
                    if (fDao.isPequisarSenha(s)) {
                        f = fDao.selecionarEntidade(email_cpf);
                        sesseion.setAttribute("fun", f);
                        url = "/Admin/painelFuncionario.jsp";
                    } else {
                        request.setAttribute("mensagem", "login ou senha incorretos.");
                        url = "/controCliente.jsp";
                    }

                } else if (fDao.isPesquisarEmail(email_cpf)
                        && isDecomporCodigo(fDao.pesquisarTipo(email_cpf), "funcionario")) {
                    String s = email_cpf + senha;
                    if (fDao.isPequisarSenha(s)) {
                        f = fDao.selecionarEntidade(email_cpf);
                        sesseion.setAttribute("fun", f);
                        url = "/Admin/painelFuncionario.jsp";
                    } else {
                        request.setAttribute("mensagem", "login ou senha incorretos.");
                        url = "/controCliente.jsp";
                    }

                } else {
                    String s = email_cpf + senha;
                    if (clieDao.isPequisarSenha(s)) {
                        c = clieDao.selecionarEntidade(email_cpf);
                        sesseion.setAttribute("cliente", c);
                        url = "/painelCliente.jsp";
                    } else {
                        request.setAttribute("mensagem", "login ou senha incorretos.");
                        url = "/controCliente.jsp";
                    }
                }
                break;
            case 1:
                resEmail = clieDao.pesquisarEmail(email);

                if (resEmail.equals("") || resEmail == null) {
                    sesseion.setAttribute("email", email);
                    url = "/Admin/adCliente.jsp";
                } else {
                    request.setAttribute("email", email);
                    url = "/controCliente.jsp";
                }
                break;
        }

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    private boolean isDecomporCodigo(String c, String c2) {
        boolean booCod = false;

        if (c.equalsIgnoreCase(c2)) {
            booCod = true;
        }

        return booCod;
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
