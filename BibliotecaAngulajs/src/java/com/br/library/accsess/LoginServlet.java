package com.br.library.accsess;

import com.br.inova.classes.utils.EfetuaMd5;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.br.library.sql.SqlComamnds;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        EfetuaMd5 md5 = new EfetuaMd5();
        String email = request.getParameter("email");
        String password = md5.hashMD5(request.getParameter("password") + "&bis");
        SqlComamnds sql = new SqlComamnds();
        ArrayList<Object[]> rows;
        Object[] row;
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            String query = "SELECT id, name, email, is_enabled, rg, cpf, phone, address, is_super_user, (SELECT CASE WHEN count(*) = 0 THEN false WHEN count(*) = 1 THEN true END FROM access.librarian WHERE user_id = au.id) FROM access.user au WHERE email = ? AND pass = ?";
            rows = sql.executeQuery(query, new Object[]{email, password}, "Login Servlet");
            if (rows == null) {
                out.print("{\"code\":0}");
            } else if (rows.isEmpty()) {
                out.print("{\"code\":1}");
            } else {
                row = rows.get(0);
                if ((boolean) row[3]) {
                    User user = new User();
                    user.setId((long) row[0]);
                    user.setName((String) row[1]);
                    user.setEmail((String) row[2]);
                    user.setEnabled((boolean) row[3]);
                    user.setRg((String) row[4]);
                    user.setCpf((String) row[5]);
                    user.setPhone((String) row[6]);
                    user.setAddress((String) row[7]);
                    user.setSuperUser((boolean) row[8]);
                    session.setAttribute("user", user);

                    if ((boolean) row[9]) {
                        session.setAttribute("isLibrarian", true);
                    }
                    out.print("{\"code\":2}");
                } else {
                    out.print("{\"code\":3}");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            out.print("{\"code\":4}");
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
