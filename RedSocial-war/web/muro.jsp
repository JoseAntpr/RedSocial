<%-- 
    Document   : index
    Created on : 19-mar-2015, 11:02:07
    Author     : Azahar
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="ea.entity.Usuario"%>
<%@page import="ea.entity.Post"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Post> lista;
    
    HttpSession sesion = request.getSession();
    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
    Usuario usuarioMuro = (Usuario) sesion.getAttribute("usuarioMuro");
//  Usuario usuarioMuro = (Usuario) request.getAttribute("usuarioMuro");

    lista = (List) request.getAttribute("listaPost");
    
    String mensaje=(String)request.getAttribute("mensajeErrorMuroOtro");
    
    SimpleDateFormat format = new SimpleDateFormat("EEE dd MMM yyyy - HH:mm:ss");
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
                            <%  if(mensaje!=null){  %>
                                   <p><%=mensaje%></p>
                            <%  } %>
                            <!-- content -->                      
                            <div class="row">
				<!-- main col left --> 
                                <div class="col-sm-5">
                                    <%for (int i=0;i<lista.size();i++) { 
                                        Post p=lista.get(i);
                                     %>        
                                     <div class="panel panel-default">
                                        <div class="panel-thumbnail">
                                            <% if (p.getImagen() != null) {%>
                                            <img src="<%= p.getImagen()%>" class="img-responsive">
                                            <% }%>
                                        </div>
                                        <div class="panel-body">
                                            <p><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28px" width="28px">
                                                <%= usuarioMuro.getNombre() + " " + usuarioMuro.getApellidos()%></p>
                                            <p><%= format.format(p.getFecha()) %></p>
                                            <p><%= p.getDescripcion()%></p>
                                                
                                            <p>
                                                <% if(usuario.equals(usuarioMuro)){%>
                                                <form name="delete" action="PostDeleteServlet" method="post">    
                                                    <input type="hidden" value="usuario" name="tipo_borrado"/>
                                                    <input type="hidden" value="<%=p.getIdPost()%>" name="idGuardada"/> <!--Guardamos la id para recuperarla al borrar post-->
                                                    <input href class="btnEliminar botonEliminar" type="submit" value="Eliminar" name="eliminar" />
                                                </form>
                                                <%} %>
                                            </p>
                                        </div>
                                    </div>
                                    <% } %>  
                                </div>
                            </div>
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
