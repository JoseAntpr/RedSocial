<%-- 
    Document   : index
    Created on : 19-mar-2015, 11:02:07
    Author     : Azahar
--%>


<%@page import="java.math.BigDecimal"%>
<%@page import="ea.entity.Usuario"%>
<%@page import="ea.entity.Post"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Post> lista;
    Usuario usuarioMuro;
    Usuario usuario;
    

    usuario = (Usuario) session.getAttribute("usuario");
    usuarioMuro = (Usuario) request.getAttribute("usuarioMuro");

    lista = (List) request.getAttribute("listaPost");
    
    String mensaje=(String)request.getAttribute("mensajeErrorMuroOtro");
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
                
		<!-- sidebar -->
		<div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">
                    <ul class="nav">
                        <li><a href="#" data-toggle="offcanvas" class="visible-xs text-center"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
                    </ul>
                    <ul class="nav hidden-xs" id="lg-menu"></br></br>
                        <li><img src="assets/img/bg_5.jpg" class="img-responsive"></li></br>							
                        <li class="active"><%= usuario.getNombre()+" "+usuario.getApellidos() %></li></br>
                        <li class="active">Vive en <%= usuario.getLocalidad()+", "+usuario.getProvincia()+", "+usuario.getPais() %></li></br>
                        <li class="active">Fecha ingreso:  <%= usuario.getFechaIngreso() %></li></br>
			<li>Descripción desdes desdes desdesdesdes desdesdesdes desdesdesdes</li></br>
                        <li><a href="GrupoServlet"><i class="glyphicon glyphicon-list"></i> Grupos</a></li>
                        <li><a href="ListarSeguidoresServlet?x=seguidores&usuarioMuro=<%= usuario.getIdUsuario() %>"><i class="glyphicon glyphicon-list"></i> Seguidores <b><%=usuario.getUsuarioCollection1().size() %></b>  </a></li>
                        <li><a href="ListarSeguidoresServlet?x=Seguir&usuarioMuro=<%= usuario.getIdUsuario() %>" name="Seguir" ><i class="glyphicon glyphicon-list"></i> Siguiendo <b><%=usuario.getUsuarioCollection().size() %> </b></a></li></br>
                    </ul>
                    <ul class="list-unstyled hidden-xs" id="sidebar-footer"></ul>
                    <!-- tiny only nav-->
                    <ul class="nav visible-xs" id="xs-menu">
			<li><a href="#featured" class="text-center"><i class="glyphicon glyphicon-list-alt"></i></a></li>
			<li><a href="#stories" class="text-center"><i class="glyphicon glyphicon-list"></i></a></li>
			<li><a href="#" class="text-center"><i class="glyphicon glyphicon-paperclip"></i></a></li>
			<li><a href="#" class="text-center"><i class="glyphicon glyphicon-refresh"></i></a></li>
                    </ul>
                </div>
		<!-- /sidebar -->
                
		<!-- main right col -->
		<div class="column col-sm-10 col-xs-11" id="main">
                    
                    <!-- Include de la barra de navegación -->
                    <%@include file="navBar.jsp" %>
                    <!-- /Include -->
                    
                    <div class="padding">
			<div class="full col-sm-9">
                            <%  if(mensaje!=null){  %>
                                   <p><%=mensaje%></p>
                            <%  } %>
                            <!-- content -->                      
                                <div class="col-sm-8">                                   
                                <form method="POST" action="GuardarUsuarioServlet">
                                    <h4 class="form-signin-heading">Editar perfil:</h4> 
                                    <div class="col-sm-4">
                                    Nombre <input type="text" name="nombre" placeholder="<%= usuario.getNombre() %>"  class="form-control" required autofocus></br>
                                    </div>
                                    <div class="col-sm-6">
                                    Appelidos <input type="text" name="apellidos" placeholder="<%= usuario.getApellidos()%>"  class="form-control" required autofocus></br>
                                    </div>
                                    <div class="col-sm-6">
                                    Direccion <input type="text" name="direccion" placeholder="<%= usuario.getDireccion()%>"  class="form-control" required autofocus></br>
                                    </div>
                                    <div class="col-sm-4">
                                    Localidad <input type="text" name="localidad" placeholder="<%= usuario.getLocalidad()%>"  class="form-control" required autofocus></br>
                                    </div>
                                    <div class="col-sm-4">
                                    Provincia <input type="text" name="provincia" placeholder="<%= usuario.getProvincia()%>"  class="form-control" required autofocus></br>
                                    </div>
                                    <div class="col-sm-4">
                                    Pais <input type="text" name="pais" placeholder="<%= usuario.getPais()%>"  class="form-control" required autofocus></br>
                                    </div>
                                    <div class="col-sm-10">
                                    Email <input type="text" name="email" placeholder="<%= usuario.getEmail()%>"  class="form-control" required autofocus></br>
                                    </div>                                                                    
                                    <div class="col-sm-4">
                                    <button class="btn btn-success btn-block" type="submit">Guardar</button>
                                    </div>
                                </form>                                    
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
