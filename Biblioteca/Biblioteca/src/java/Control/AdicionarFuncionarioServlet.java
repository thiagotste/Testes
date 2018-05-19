package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import Model.DAO.FuncionarioDAO;
import Model.Etidade.Funcionario;
import Model.DAO.BibliotecaDAO;

public class AdicionarFuncionarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String url = "/Admin/adFuncionario.jsp";
        
        HttpSession session = request.getSession();

        FuncionarioDAO fDao = new FuncionarioDAO();
        BibliotecaDAO bDao = new BibliotecaDAO();

        if (fDao.isPesquisarEmail(email)) {
            request.setAttribute("email", email);
            request.setAttribute("emailMensagem", "Email já está cadastrado.");
        } else if (fDao.isPequisarCPF(cpf)) {
            request.setAttribute("cpf", cpf);
            request.setAttribute("cpfMensagem", "CPF já está cadastrado.");
        } else {
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String sexo = request.getParameter("sexo");
            String dtNascimento = request.getParameter("dtNascimento");
            String telefone = request.getParameter("telefone");
            String celular = request.getParameter("celular");
            int codBiblioteca = Integer.parseInt(request.getParameter("codbiblioteca"));
            String senha = request.getParameter("senha");
            String confSenha = request.getParameter("confsenha");
            String cep = request.getParameter("cep");
            String endereco = request.getParameter("endereco");
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            String tipoFuncionario = request.getParameter("tipoFuncionario");

            String emailSenha = email + senha;
            Funcionario f = new Funcionario(codBiblioteca, nome, sobrenome, email, 
                    cpf, sexo, dtNascimento, endereco, complemento, cep, cidade,
                    uf, telefone, celular, emailSenha, tipoFuncionario);
            
            if(bDao.isPequisarCodBiblioteca(codBiblioteca)){
                fDao.adicionarEntidade(f);
            }else{
                session.setAttribute("f", f);
                request.setAttribute("mensagem", "Código de biblioteca não está cadastrado.");                
            }
            
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
