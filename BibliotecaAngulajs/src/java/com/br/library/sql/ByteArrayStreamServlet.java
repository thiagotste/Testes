package com.br.library.sql;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.br.library.accsess.User;
import java.io.BufferedOutputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ByteArrayStreamServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        String origin = request.getParameter("origin");
//        int id = Integer.parseInt(request.getParameter("id"));

        SqlMethodInterface sql = new SqlCommands();
        String query = "";
        List<Object[]> rows = new ArrayList<>();
        byte[] image = null;

        try (BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            switch (origin) {
                case "userArea":
                    if (u != null) {
                        query = "SELECT image FROM access.user WHERE id = ?  AND image is not null";
                        rows = sql.executeQuery(query, new Object[]{u.getId()}, "ByteArrayStram");
                        if (!rows.isEmpty()) {
                            image = (byte[]) rows.get(0)[0];
                            out.write(image);
                            out.close();
                        } else {
                            String stringPath = getServletContext().getRealPath("/res/images/silhueta.png");
                            defaultImage(out, stringPath);
                        }
                    }
                    break;
            }
        } catch (IOException ioe) {
            System.out.println("ByteArrayStreamServlet: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("ByteArrayStreamServlet: " + e.getMessage());
        }
    }

    private void defaultImage(BufferedOutputStream out, String stringPath) {
        try {
            Path path = Paths.get(stringPath);
            if (Files.exists(path)) {
                out.write(Files.readAllBytes(path));
                out.close();
            }
        } catch (IOException ioe) {
            System.out.println("defaultImage: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("defaultImage: " + e.getMessage());
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
