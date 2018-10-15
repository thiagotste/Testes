package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import javax.servlet.ServletContext;

import Model.Etidade.Cliente;
import Model.Etidade.Funcionario;
import Model.Etidade.Livro;


public class DownloadServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente c = null;
        Funcionario f = null;
        Livro l = null;

        HttpSession session = request.getSession();
        c = (Cliente) session.getAttribute("cliente");
        f = (Funcionario) session.getAttribute("fun");

        if (c != null || f != null) {
            ServletContext sc = getServletContext();
            PrintWriter out = response.getWriter();

            l = (Livro) session.getAttribute("livro");
            String path = sc.getRealPath("/Livros/" + l.getCodLivro() + ".epub");

            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition",
                    "attachment; filename=" + l.getNome() + ".epub");

            FileInputStream in = new FileInputStream(path);
            int i = in.read();
            while (i != -1) {
                out.write(i);
                i = in.read();
            }
            
            in.close();
            out.close();
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
