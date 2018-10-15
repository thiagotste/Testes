package Filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Model.Etidade.Livro;

public class DownloadFilter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig fc) {
        filterConfig = fc;
    }

    @Override
    public void doFilter(ServletRequest sr,
            ServletResponse sr1,
            FilterChain fc)
            throws IOException, ServletException {
        
        

        HttpServletRequest httpRequest = (HttpServletRequest) sr;
        HttpServletResponse httpResponse = (HttpServletResponse) sr1;
        ServletContext sc = filterConfig.getServletContext();
        HttpSession session = httpRequest.getSession();

        Livro l = (Livro) session.getAttribute("livro");
        
        String path = sc.getRealPath("/Livros/" + l.getCodLivro() + ".epub");

        httpResponse.setContentType("application/octet-stream");
        httpResponse.setHeader("content-disposition",
                "attachment; filename=" + l.getNome() + ".epub");

        FileInputStream in = new FileInputStream(path);
        PrintWriter out = httpResponse.getWriter();

        int i = in.read();
        while (i != -1) {
            out.write(i);
            i = in.read();
        }
        
        in.close();
        out.close();
        
        fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

}
