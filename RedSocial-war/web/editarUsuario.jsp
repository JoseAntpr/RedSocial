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
    Usuario uMuro;
    
    uMuro = (Usuario) request.getAttribute("user");

%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <meta charset="utf-8">
        <title>Red Social</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="assets/css/bootstrap.css" rel="stylesheet">       
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
			<!--		   
			<ul class="nav hidden-xs" id="lg-menu"></br></br>
                            <li><img src="assets/img/bg_5.jpg" class="img-responsive"></li></br>							
                            <li class="active"><%= uMuro.getNombre()+" "+uMuro.getApellidos() %></li></br>
                            <li class="active">Vive en <%= uMuro.getLocalidad()+", "+uMuro.getProvincia()+", "+uMuro.getPais() %></li></br>
                            <li class="active">Fecha ingreso:  <%= uMuro.getFechaIngreso() %></li></br>
                            <li>Descripción desdes desdes desdesdesdes desdesdesdes desdesdesdes</li></br>
                            <li><a href="GrupoServlet"><i class="glyphicon glyphicon-list"></i> Grupos</a></li>
                            <li><a href="ListarSeguidoresServlet?x=seguidores&uMuro=<%= uMuro.getIdUsuario() %>"><i class="glyphicon glyphicon-list"></i> Seguidores <b><%=uMuro.getUsuarioCollection1().size() %></b>  </a></li>
                            <li><a href="ListarSeguidoresServlet?x=Seguir&uMuro=<%= uMuro.getIdUsuario() %>" name="Seguir" ><i class="glyphicon glyphicon-list"></i> Siguiendo <b><%=uMuro.getUsuarioCollection().size() %> </b></a></li></br>
                        </ul>
			<ul class="list-unstyled hidden-xs" id="sidebar-footer">
			</ul>
				-->	  
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
						
                    <!-- top nav -->
                    <div class="navbar navbar-blue navbar-static-top">  
                        <div class="navbar-header">
                            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                                <span class="sr-only">Toggle</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
                            </button>
                            <a  class="navbar-brand logo">Rs</a>
			</div>
			<nav class="collapse navbar-collapse" role="navigation">
                            <form class="navbar-form navbar-left">
				<div class="input-group input-group-sm" style="max-width:360px;">
                                    <input class="form-control" placeholder="Search" name="buscar" id="srch-term" type="text">
                                    <div class="input-group-btn">
                                        <a href="#" class="btn btn-default" ><i class="glyphicon glyphicon-search"></i></a>
                                        <!--<button  class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button> -->
                                    </div>
                                </div>
                            </form>
                            <ul class="nav navbar-nav">
                                <!--
                                <li>
                                    <a href="MuroServlet?usuarioMuro=<%=u.getIdUsuario() %>"><i class="glyphicon glyphicon-home"></i> Inicio</a>
                                </li>
				<li>
                                    <% if(u.getIdUsuario().equals(uMuro.getIdUsuario())){ %>
                                    <a href="postAdd.jsp?idUsuario=<%=u.getIdUsuario()%>" role="button" ><i class="glyphicon glyphicon-plus"></i> Post</a>
                                    <% }else{%>
                                    <%}%>
				</li>
				<li>
                                    <a href="ListarSeguidoresServlet?x=usuariosSeguir&uMuro=<%= u.getIdUsuario()%>"><span class="badge">Usuarios</span></a>
				</li>
                                <li>
                                    <a href="#"><span class="badge">Grupos</span></a>
				</li>
                            </ul>
                                -->
                            <ul class="nav navbar-nav navbar-right">
                                
                                <li class="dropdown" id="fat-menu"> <a data-toggle="dropdown" class="dropdown-toggle" role="button" id="drop3" href="#"><%= u.getNombre()+" "+u.getApellidos() %> <b class="caret"></b></a>
                                    <ul aria-labelledby="drop3" role="menu" class="dropdown-menu">
                                        <li role="presentation">
                                            <form method="POST" action="EditarUsuarioServlet">                                                
                                                <input type="hidden" name="idUser" placeholder="<%= uMuro.getIdUsuario()%>" value="">
                                                <a href="EditarUsuarioServlet" tabindex="-1" role="menuitem" type="submit">Editar Perfil</a>
                                            </form>
                                        </li>
                                        <li class="divider" role="presentation"></li>
                                        <li role="presentation"><a href="LogoutServlet" tabindex="-1" role="menuitem">Cerrar Sesión</a></li>
                                    </ul>
                                </li>						
                            </ul>
			</nav>
                    </div>
                    <!-- /top nav -->
					  
                    <div class="padding">
                        <div class="full col-sm-9"> 
                            <!-- content -->                      
                            <div class="row">                                   
                                    <form method="POST" action="GuardarUsuarioServlet">
                                        <h4 class="form-signin-heading">Editar Usuario:</h4> 
                                        <div class="col-sm-4">
                                        <input type="text" name="nombre" placeholder="<%= uMuro.getNombre() %>"  class="form-control" required autofocus></br>
                                        </div>
                                        <div class="col-sm-6">
                                        <input type="text" name="apellidos" placeholder="<%= uMuro.getApellidos()%>"  class="form-control" required autofocus></br>
                                        </div>
                                        <div class="col-sm-6">
                                        <input type="text" name="direccion" placeholder="<%= uMuro.getDireccion()%>"  class="form-control" required autofocus></br>
                                        </div>
                                        <div class="col-sm-4">
                                        <input type="text" name="localidad" placeholder="<%= uMuro.getLocalidad()%>"  class="form-control" required autofocus></br>
                                        </div>
                                        <div class="col-sm-4">
                                        <input type="text" name="provincia" placeholder="<%= uMuro.getProvincia()%>"  class="form-control" required autofocus></br>
                                        </div>
                                        <div class="col-sm-4">
                                        <input type="text" name="pais" placeholder="<%= uMuro.getPais()%>"  class="form-control" required autofocus></br>
                                        </div>
                                        <div class="col-sm-6">
                                            <input type="text" name="email" placeholder="<%= uMuro.getEmail()%>"  class="form-control" required autofocus></br>
                                        </div>
                                        <div class="col-sm-6">
                                        <input type="password" id="password" name="<%= uMuro.getPassword()%>" class="form-control" placeholder="Password" required></br>
                                        </div>
                                        <div class="col-sm-4">
                                        <button class="btn btn-success btn-block" type="submit">Guardar</button>
                                        </div>
                                    </form>                                    
                                </div><!--/row-->
                        </div><!--/row-->
                        <div class="row" id="footer">    
                            <div class="col-sm-6">

                            </div>

                        </div>

                    </div><!-- /col-9 -->
                </div><!-- /padding -->
            </div>
                <!-- /main -->

            </div>
        </div>
    </div>


    <!--post modal-->
    <div id="postModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
                    Update Status
                </div>
                <div class="modal-body">
                    <form class="form center-block">
                        <div class="form-group">
                            <textarea class="form-control input-lg" autofocus="" placeholder="What do you want to share?"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div>
                        <button class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true">Post</button>
                        <ul class="pull-left list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>
                    </div>	
                </div>
            </div>
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

