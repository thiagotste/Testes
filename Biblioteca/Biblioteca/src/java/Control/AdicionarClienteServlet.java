package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Etidade.Cliente;
import Model.DAO.ClienteDAO;
import javax.servlet.RequestDispatcher;

public class AdicionarClienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String url = "";
        //HttpSession session = request.getSession();

        ClienteDAO cDAO = new ClienteDAO();

        if (cDAO.pesquisarEmail(email).equals(email)) {
            request.setAttribute("email", email);
            request.setAttribute("emailMensagem", "Email já existe.");
            url = "/Admin/adCliente.jsp";
        } else {
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String cpf = request.getParameter("cpf");
            String sexo = request.getParameter("sexo");
            String dtNascimento = request.getParameter("dtNascimento");
            String telefone = request.getParameter("telefone");
            String celular = request.getParameter("celular");
            String senha = request.getParameter("senha");
            String confSenha = request.getParameter("confsenha");
            String cep = request.getParameter("cep");
            String endereco = request.getParameter("endereco");
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String uf = request.getParameter("uf");
            
            String emailSenha = email + senha;
            
            Cliente c = new Cliente(nome, sobrenome, email, cpf, sexo,
                        dtNascimento, endereco, complemento, cep, cidade, uf, telefone, celular, emailSenha);
            request.setAttribute("cliente", c);

            if (cDAO.isPesquisarCpf(cpf) == false) {
                cDAO.adicionarEntidade(c);                
            }else{
                request.setAttribute("cpfMensagem", "Cpf já existe.");
            }

            url = "/controCliente.jsp";

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
