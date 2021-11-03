<%-- 
    Document   : devoluciones
    Created on : 24/10/2021, 01:39:39 PM
    Author     : mack
--%>

<%@page import="DAO.DevolucionDAO"%>
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
	background-image: url(../img/fondos/fond5.jpg);
	text-align: left;
}
    .form {
	text-align: justify;
}
    </style>
</head>

<body tracingsrc="../img/fondos/fond2.jpg" tracingopacity="100">
<div >
<nav #000000 black>
<div class="nav-wrapper #000000 black"> <a href="../index.jsp"><img  src="../img/fondos/logo1.png" alt="logo" width="384" height="162" class="img-responsive" ></a>
      <a href="../index.jsp" class="brand-logo "><i class="material-icons "></i>Inicio</a>
      
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
           <p>
             <!-- Formulario -->
             
           </p>
     <div class="row ">
     
     <form>
     <div class="row">
      <div class="input-field col s6">
          <span class="form">
  <input placeholder="Numero Solicitud"  id="nsolicitudo" name="nsolicitud" type="text" class="validate">
          <label for="nsolicitud"><span class="black-text">Numero Solicitud</label>
      </span>      </div>
      
    <form class="col med s6">
      <div class="row ">
        <div class="input-field col s6" >
        
          <span class="form">
          <input placeholder="ID Cliente " id="idcliente" name="idcliente" type="text" class="validate">
          <label for="idcliente"><span class="black-text">IdCliente</label>
        </span></div>
        
        <span class="form">
        <div class="row">
        </span>
        <div class="input-field col s6">
          <span class="form">
          <input placeholder="ID Juego" id="idjuego" name="idjuego" type="text" class="validate">
          <label for="idjuego"><span class="black-text">idjuego </label>
        </span></div>
   
      
   <div id="success"></div>
         <button type="submit" class="btn btn-xl">Registrar</button>
    </form>
</div>
        
  
                           
<!-- Fin formulario -->
        
        <%
            String numeroSolicitud  = request.getParameter("nsolicitud");
            String clienteSolicitud = request.getParameter("idcliente");
            String juegoSolicitud   = request.getParameter("idjuego");

            boolean resultado = false;
            
            if(clienteSolicitud!=null && juegoSolicitud!=null){
                
                resultado=true;
                
                DevolucionDAO devolucion = new DevolucionDAO();
                String grabados = devolucion.crear(numeroSolicitud, clienteSolicitud, juegoSolicitud);
                
                out.println("renta grabada: "+grabados);
                
            }
         %>


<!-- Dropdown Structure -->    
    
        <!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>     
</body>
</html>
