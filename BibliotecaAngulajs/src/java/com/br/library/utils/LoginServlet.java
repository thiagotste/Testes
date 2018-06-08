package com.br.library.utils;

import com.br.inova.classes.utils.EfetuaMd5;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.br.library.sql.sqlComamnds;
import java.sql.*;
import java.util.ArrayList;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        EfetuaMd5 md5 = new EfetuaMd5();
        String email = request.getParameter("email");
        String password = md5.hashMD5(request.getParameter("password") + "&bis");
        System.out.println(email);
        System.out.println(password);
        sqlComamnds s = new sqlComamnds();
        try {
//            int i;
//            String query = "insert into access.user(name, email, pass, rg, cpf, phone, address, is_super_user) values(?,?,?,?,?,?,?,?)";
//            i = s.executeUpdate(query, new Object[]{"admin", "admin@admin", md5.hashMD5("123456" + "&bis"), "","","","", Boolean.parseBoolean("true")});
//            System.out.println(i);
            String query = "Select * from access.user";
            ArrayList<Object[]> rows = s.executeQuery(query, null, "Login");
                       System.out.println(rows);
        } catch (Exception e) {
            System.out.println(e);
        }
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"code\":1}");
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
