<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset=UTF-8>
        <link rel="stylesheet" href="styles/listaMenu.css">
        <link rel="stylesheet" href="styles/footer.css">
        <title>JSP Page</title>
    </head>
    
    <jsp:include page="listaMenu.jsp"/>
    
    <section>
        <form action="Teste" method="post" enctype="multipart/form-data">
            <label for="imagem">Adicionar imagem</label><br>
            <input type="file" name="imagem" id="imagem" accept="image/*"><br>
            <input type="submit" value="enviar">
        </form>
        
    </section>
    
    <jsp:include page="footer.jsp"/>
