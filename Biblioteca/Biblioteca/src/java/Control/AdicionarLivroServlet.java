package Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Etidade.Livro;
import Model.DAO.LivroDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AdicionarLivroServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codLivro = null;
        String codEscritor = null;
        String codTipo = null;
        String codBiblioteca = null;
        String nome = null;
        String editora = null;
        String vol = null;
        String edicao = null;
        String idioma = null;
        String numPag = null;
        String anoLancamento = null;
        String dataCompra = null;
        String descricao = null;
        String formato = null;
        String situaco = "0";
        
        String url = "/Admin/adLivro.jsp";
        int linhasAdicionadas = 0;
        Livro livro;

        LivroDAO liDao = new LivroDAO();

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Parse the request
                List<FileItem> items = upload.parseRequest(request);

                // Process the uploaded items
                Iterator<FileItem> iter = items.iterator();

                while (iter.hasNext()) {
                    FileItem item = iter.next();

                    if (item.isFormField()) {
                        switch (item.getFieldName()) {
                            case "codLivro":
                                codLivro = item.getString();
                                break;
                            case "codEscritor":
                                codEscritor = item.getString();
                                break;
                            case "codTipo":
                                codTipo = item.getString();
                                break;
                            case "codBiblioteca":
                                codBiblioteca = item.getString();
                                break;
                            case "nome":
                                nome = item.getString();
                                break;
                            case "editora":
                                editora = item.getString();
                                break;
                            case "vol":
                                vol = item.getString();
                                break;
                            case "edicao":
                                edicao = item.getString();
                                break;
                            case "idioma":
                                idioma = item.getString();
                                break;
                            case "numPagina":
                                numPag = item.getString();
                                break;
                            case "anoLancamento":
                                anoLancamento = item.getString();
                                break;
                            case "dataCompra":
                                dataCompra = item.getString();
                                break;
                            case "descricao":
                                descricao = item.getString().trim();
                                break;
                            case "formato":
                                formato = item.getString();
                                break;
                        }
                    } else {
                        isAdicionarImagem(item);

//                            System.out.println("**** Falha ao adicionar imagem. ****");
                    }
                }

            } catch (FileUploadException ex) {
                ex.printStackTrace();
                System.out.println("\\\\\\ " + ex.getMessage() + " \\\\\\");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("\\\\\\ " + e.getMessage() + " \\\\\\");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("\\\\\\ " + e.getMessage() + " \\\\\\");
            } catch (SecurityException e) {
                e.printStackTrace();
                System.out.println("\\\\\\ " + e.getMessage() + " \\\\\\");
            }
        }
        
        if (liDao.isPesquisarCodigo(Integer.parseInt(codLivro),
                Integer.parseInt(codEscritor))) {
            livro = new Livro(Integer.parseInt(codLivro), Integer.parseInt(codEscritor),
                    Integer.parseInt(codTipo), Integer.parseInt(codBiblioteca),
                    nome, editora, vol, edicao, idioma, numPag, anoLancamento,
                    dataCompra, formatarDescricao(descricao), formato, situaco);
            request.setAttribute("livro", livro);
            request.setAttribute("descricao", formatarDescricao(descricao).split("%"));
            request.setAttribute("liMensagem", "O código " + livro.getCodLivro()
                    + ", para o escritor " + livro.getCodEscritor() + ", já está cadastrado.");

        } else {
            livro = new Livro(Integer.parseInt(codLivro), Integer.parseInt(codEscritor),
                    Integer.parseInt(codTipo), Integer.parseInt(codBiblioteca),
                    nome, editora, vol, edicao, idioma, numPag, anoLancamento,
                    dataCompra, formatarDescricao(descricao), formato, situaco);

            liDao.adicionarEntidade(livro);

        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    private String formatarDescricao(String aux) {
        StringBuilder desc = new StringBuilder(aux);
        boolean a = false;
        int b = 0;
        do {
            int i = desc.indexOf("\n", b);
            if (i != -1) {
                desc.insert(i - 1, "%");
                a = true;
                b = i + 2;
            } else {
                a = false;
            }
        } while (a);

        return desc.toString();
    }

    private boolean isAdicionarImagem(FileItem item)
            throws IOException {
        boolean adicionado = false;

        String fileNome = item.getName();
        String path = "C:\\Users\\thiago-pc\\Desktop\\Biblioteca\\Biblioteca\\web\\imagens\\Livros";
        File uplaoded = new File(path, fileNome);

        if (uplaoded.exists()) {
            uplaoded.delete();
        } else {
            uplaoded.createNewFile();
        }

        InputStream input = item.getInputStream();
        FileOutputStream output = new FileOutputStream(uplaoded);

        int i = input.read();
        while (i != -1) {
            output.write(i);
            i = input.read();
        }
        input.close();
        output.close();

        adicionado = true;

        return adicionado;
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
