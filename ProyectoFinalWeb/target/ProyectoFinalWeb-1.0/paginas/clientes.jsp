<%-- 
    Document   : clientes
    Created on : 24/10/2021, 01:39:01 PM
    Author     : mack
--%>

<%@page import="DAO.ClientesDAO"%>
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
	background-image: url(../img/fondos/fond3.jpg);
	color: #000;
	text-decoration: underline;
}
    .Form {
	text-align: center;
}
    .btn.btn-xl {
	text-align: center;
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
<p>&nbsp;</p>
    <p>
      
<!-- Formulario -->
</p>
<div align="right"><span class="form">
 <form>
    <div class="row ">
       <div class="input-field col s6"> 
          
          
         <input placeholder="Ingreso de nombre " id= "first_name" name="nombre_cliente" type="text" class="validate">
          <label for="first_name"><span class="black-text">	Nombre y Apellido</label>
</div>
        <div align="left">
        <div class="row">
        <div class="input-field col s6">
          <input placeholder="Ingrese DPI" id="dpi" name="dpi_cliente" type="text" class="validate">
          <label for="dpi"><span class="black-text">DPI</label>
        </div>
        <div align="left">
        <div class="row">
        <div class="input-field col s6">
            <input type="text" class="validate" id="direccion" name="direccion_cliente" placeholder="Direccion" size="20" maxlength="20">
          <label for="direccion"><span class="black-text">Domicilio</label>
        </div>
        <div align="left">
        <div class="row">
        <div class="input-field col s6">
          <input placeholder="Ingreso de Telefono" id="tel"name="telefono_cliente" type="text" class="validate">
          <label for="tel"><span class="black-text">Telefono</label>
        </div>
  <div align="left">
   
      <div class="row">
        <div class="input-field col s5">
          <input id="email" name="email_cliente" type="email" class="validate">
          <label for="email"><span class="black-text">Ingrese Correo</label>
          <span class="helper-text" data-error="Error" data-success="valido">Escriba un correo valido</span>
        </div>
      </div>
      <div align="center"><span class="form">
<div id="success"></div>
         <button class="waves-effect waves-light btn"type="submit" class="btn btn-xl">Registrar</button>
      
    </form>
</div>
        
           <%
            String codigoCliente     = "";
            String nombreCliente     = request.getParameter("nombre_cliente");
            String dpiCliente        = request.getParameter("dpi_cliente");
            
            String direccionCliente  = request.getParameter("direccion_cliente");
            String telefonoCliente   = request.getParameter("telefono_cliente");
            String emailCliente      = request.getParameter("email_cliente");
            
            boolean resultado = false;
            
            if(nombreCliente!=null){
                
                resultado=true;
                
                ClientesDAO cliente = new ClientesDAO();
                String grabados = cliente.crear(codigoCliente, nombreCliente, direccionCliente, dpiCliente, telefonoCliente, emailCliente);
                
                out.println("informe de grabacion "+grabados);
                
            }
         %>
  
                           

<!-- Dropdown Structure -->    
    
        <!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>     
</body>
</html>






          
