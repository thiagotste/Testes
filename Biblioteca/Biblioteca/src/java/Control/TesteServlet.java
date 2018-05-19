package Control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class TesteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/teste.jsp";

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repositry = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repositry);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Parse the request
                List<FileItem> items = upload.parseRequest(request);

                // Process the uploaded items
                Iterator<FileItem> iter = items.iterator();

                if (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (!item.isFormField()) {
                        String fileNome = item.getName();                        
                        String path = "C:\\Users\\thiago-pc\\Desktop\\Biblioteca\\Biblioteca\\web\\imagens\\Livros";
                        File uplaoded = new File(path, fileNome);
                        
                        if(uplaoded.exists()){
                            uplaoded.delete();
                        }else
                            uplaoded.createNewFile();
                        
                        
                        InputStream input = item.getInputStream();
                        FileOutputStream output = new FileOutputStream(uplaoded);
                                                                       
                        int i = input.read();
                        while(i != -1){
                            output.write(i);
                            i = input.read();
                        }
                        input.close();
                        output.close();
                    }
                }
            } catch (FileUploadException ex) {
                ex.printStackTrace();
                System.out.println("\\\\\\ " + ex.getMessage() + " \\\\\\");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("\\\\\\ " + e.getMessage() + " \\\\\\");
            } catch (SecurityException e) {
                e.printStackTrace();
                System.out.println("\\\\\\ " + e.getMessage() + " \\\\\\");
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
