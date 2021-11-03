<%-- 
    Document   : rentas
    Created on : 24/10/2021, 01:41:30 PM
    Author     : mack
--%>

<%@page import="DAO.ReportesDAO"%>
<%@page import="DAO.RentaDAO"%>
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
	background-image: url(../img/fondos/fond8.jpg);
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
<div class="nav-wrapper #000000 black"> <a href="../Index.jsp"><img  src="../img/fondos/logo1.png" alt="logo" width="384" height="162" class="img-responsive" ></a>
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
     
     
     <form>
   
    
      <p>
        <label>
          <input type="checkbox" value="casilla de verificación " id="Reportes_0" name="reporte_prestamos" />
          <span class="black-text"> Imprimir Stock de videojuegos</label>
        <br />
        <label>
          <input type="checkbox" value="casilla de verificación " id="Reportes_1" name="reporte_stock" align="center" />
          <span class="black-text"> Imprimir Prestamos Realizados</label>
        <br />
        <label>
          <input type="checkbox" value="casilla de verificación " id="Reportes_2" name="reporte_top10" align="center" />
          <span class="black-text"> Imprimir Usuarios con multa</label>
        <br />
        <label>
          <input type="checkbox" value="casilla de verificación " id="Reportes_3" name="reporte_multas" />
          <span class="black-text"> Imprimir Videojuegos mas rentados</label>
        <br />
       <div align="center"><span class="form">
         <div class="input-field col s12">
          <span class="form">
          <input placeholder="Filtrado por:"  id="filtrado" name="filtrado" type="text" class="validate">
          <label for="filtrado"><span class="black-text">Filtrado por: </label>
        </span></div>
        
      </p>
<div id="success"></div>
         <button type="submit" class="btn btn-xl">Imprimir</button>
    </form>
</div>
        
  
                           
<!-- Fin formulario -->
   
        <%
            
            
            boolean myCheck1 = false;
            boolean myCheck2 = false;
            boolean myCheck3 = false;
            boolean myCheck4 = false;
            
            if (request.getParameter("reporte_prestamos") != null){
               myCheck1 = true;
            }

            if (request.getParameter("reporte_stock") != null){
               myCheck2 = true;
            }

            if (request.getParameter("reporte_top10") != null){
               myCheck3 = true;
            }

            if (request.getParameter("reporte_multas") != null){
               myCheck4 = true;
            }
            
            
            if(myCheck1 || myCheck2 || myCheck3 || myCheck4){
                
                //out.println("informe de impresion, antes de imprimir ");
                boolean resultado = false;
                resultado=true;
                String grabados = "";
                
                //out.println("informe de impresion, antes cargar control ");
                ReportesDAO reporte = new ReportesDAO();
            
                if(myCheck1){
                    grabados = reporte.imprimeReporte1();
                    
                    
                } else if(myCheck2){
                    grabados = reporte.imprimeReporte2();
                    
                    
                } else if(myCheck3) {
                
                    grabados = reporte.imprimeReporte3();
                    
                } else if(myCheck4){
                    grabados = reporte.imprimeReporte4();
                
                }
                

                ///out.println("informe de impresion, antes de llamar el metdo de impresion ");


                // String grabados = reporte.imprimeReporte3();
            
                // String grabados = reporte.imprimeReporte4();
            
                out.println("informe de impresion: "+grabados);
            
            } else {
                // out.println("Seleccione un reporte para imprimir");
            }
                
        %>

    
<!-- Dropdown Structure -->    
    
        <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>     
</body>
</html>
