<%-- 
    Document   : cambiarPassError
    Created on : 28-abr-2015, 20:09:20
    Author     : Azahar
--%>


<%@page import="java.math.BigDecimal"%>
<%@page import="ea.entity.Usuario"%>
<%@page import="ea.entity.Post"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //List<Post> lista;
    
    /*En teoría para la página de editar no haría falta tener el usuario muro, por ahora le voy a pasar el mismo
    para que no falle el navBar. 
    Tampoco me hace falta el mensaje de error, ni las listas del post. 
    Y así me ahorro el servlet intermedio para mostar la info y la cojo de la sesión solo.    
    */
    Usuario usuarioMuro  = (Usuario) session.getAttribute("usuario");
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    String mensaje = (String) request.getAttribute("mensaje");

    //lista = (List) request.getAttribute("listaPost");
    
   // String mensaje=(String)request.getAttribute("mensajeErrorMuroOtro");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <meta charset="utf-8">
        <title>Red Social</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="assets/css/bootstrap.css" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <link href="assets/css/facebook.css" rel="stylesheet">
    </head>

    <body>
     <div class="wrapper">
	<div class="box">
            <div class="row row-offcanvas row-offcanvas-left">
                
		<!-- Include sidebar -->
                    <%@include file="sidebar.jsp" %>                    
		<!-- /Include sidebar -->
                
		<!-- main right col -->
		<div class="column col-sm-10 col-xs-11" id="main">
                    
                    <!-- Include de la barra de navegación -->
                    <%@include file="navBar.jsp" %>
                    <!-- /Include -->
                    
                    <div class="padding">
			<div class="full col-sm-9">                            
                            <%-- if(mensaje!=null){  %>
                                   <p><%=mensaje%></p>
                            <%  } --%>
                            <!-- content -->                      
                                <div class="col-sm-8">                                   
                                <div class="row">                                   
                                    <% out.println(mensaje); %></br>
                                    <a href="editarPassword.jsp">Volver.</a>
                                </div><!--/row-->                                   
                            </div><!--/row-->
                        </div><!-- /col-9 -->  
                    </div><!-- /padding -->
                </div><!-- /main right col -->
            </div>
           <!-- /main -->
        </div>
    </div>
    <script type="text/javascript" src="assets/js/jquery.js"></script>
    <script type="text/javascript" src="assets/js/bootstrap.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('[data-toggle=offcanvas]').click(function () {
                $(this).toggleClass('visible-xs text-center');
                $(this).find('i').toggleClass('glyphicon-chevron-right glyphicon-chevron-left');
                $('.row-offcanvas').toggleClass('active');
                $('#lg-menu').toggleClass('hidden-xs').toggleClass('visible-xs');
                $('#xs-menu').toggleClass('visible-xs').toggleClass('hidden-xs');
                $('#btnShow').toggle();
            });
        });

    </script>
</body>
</html>
