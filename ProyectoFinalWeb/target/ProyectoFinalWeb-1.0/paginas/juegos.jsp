<%-- 
    Document   : juegos
    Created on : 24/10/2021, 01:40:27 PM
    Author     : mack
--%>

<%@page import="DAO.JuegosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameTopStore</title>
  <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <style type="text/css">
    body {
	background-color: #000;
	background-repeat: no-repeat;
	background-size: 100% 100%;
	background-attachment: fixed;
	background-image: url(../img/fondos/fond6.jpg);
	color: #333;
	position: relative;
	text-align: justify;
}
    .form {
	text-align: left;
}
    </style>
</head>

<body>
<div >
<nav #000000 black>
<div class="nav-wrapper #000000 black"><a href="../index.jsp">
<img  src="../img/fondos/logo1.png" alt="logo" width="384" height="162" class="img-responsive" /></a><a href="../index.jsp" class="brand-logo "><i class="material-icons "></i>Inicio</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="catalogo.jsp"><i class="material-icons">Galeria</i></a></li>
        <li><a href="juegos.jsp"><i class="material-icons">Ingreso Juegos</i></a></li>
        <li><a href="rentas.jsp"><i class="material-icons">Rentas</i></a></li>
        <li><a href="devoluciones.jsp"><i class="material-icons">Devoluciones</i></a></li>
        <li><a href="clientes.jsp"><i class="material-icons">Registro Clientes</i></a></li>
        <li><a href="reportes.jsp"><i class="material-icons">Reportes</i></a></li>
      </ul>
    </div>
    </div>
    </div>
    
    
      <p>&nbsp;</p>
      <p>&nbsp;</p>
<p>&nbsp;</p>
      <p>
        
        <!-- Formulario -->
        
      </p>
     
    <form>
      <div class="row ">
      </span>
        <div class="input-field col s5"> 
          
          <div align="center"><span class="form">
          <input placeholder="Codigo Juego " id="cod_juego" name="cod_juego" type="text" class="validate">
          </span></div>
          <span class="form">
          <label for="cod_juego">
            <div align="center"><span class="black-text">Codigo</div>
          </label>
        </span></div>
        
        <div align="center"><span class="form">
        <div class="row">
        </span>
        </div>
        <div class="input-field col s5">
          <div align="center"><span class="form">
          <input placeholder="Titulo del Juego" id="titulo" name="titulo" type="text" class="validate">
          </span></div>
          <span class="form">
          <label for="titulo">
            <div align="center"><span class="black-text">Titulo</div>
          </label>
        </span></div>
        
        <div align="center"><span class="form">
        <div class="row">
        </span>
        </div>
        <div class="input-field col s5">
          <div align="center"><span class="form">
          <input placeholder="Genero" id="genero" name="genero" type="text" class="validate">
          </span></div>
          <span class="form">
          <label for="genero">
            <div align="center"><span class="black-text">Genero</div>
          </label>
        </span></div>
        
      
       
          <div align="center"><span class="form">
          <div class="row">
          </span>
          </div>
        <div class="input-field col s5">
          <div align="center"><span class="form">
          <input placeholder="Plataforma" id="plataforma" name="plataforma" type="text" class="validate">
          </span></div>
          <span class="form">
          <label for="plataforma">
            <div align="center"><span class="black-text">Ingrese plataforma</div>
          </label>
        </span></div>
       <div class="row">
        <div class="input-field col s5">
          <div align="center"><span class="form">
          <input placeholder="Precio" id="precio" name="precio" type="text" class="validate">
          </span></div>
          <span class="form">
          <label for="precio">
            <div align="center"><span class="black-text">Ingrese precio</div>
          </label>
         </span></div>
          
      </div>
          <div id="success"></div>
         <button type="submit" class="btn btn-xl">Registrar</button>
    </form>
<!-- Fin formulario -->
                
        <%
            String codigoJuego     = request.getParameter("cod_juego");
            String tituloJuego     = request.getParameter("titulo");
            
            String generoJuego     = request.getParameter("genero");
            
            // String unidadesJuego   = request.getParameter("unidades");
            
            String unidadesJuego   = "1";
            
            String plataformaJuego = request.getParameter("plataforma");
            String precioJuego     = request.getParameter("precio");
            
            boolean resultado = false;
            
            
            if(codigoJuego!=null){
            
                resultado=true;
                
                JuegosDAO juego = new JuegosDAO();
                String grabados = juego.crear(codigoJuego, tituloJuego, generoJuego, unidadesJuego, plataformaJuego, precioJuego);
                
                out.println("informe de grabacion "+grabados);
                
            }
         %>

<!-- Dropdown Structure -->    
    
        <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>




