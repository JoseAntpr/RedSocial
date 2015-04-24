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
    Usuario uMuro;
    Usuario u;

    u = (Usuario) request.getAttribute("usuarioSesion");
    uMuro = (Usuario) request.getAttribute("usuarioMuro");

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
							<ul class="nav navbar-nav navbar-right">
							 <li class="dropdown" id="fat-menu"> <a data-toggle="dropdown" class="dropdown-toggle" role="button" id="drop3" href="#"><%= u.getNombre()+" "+u.getApellidos() %> <b class="caret"></b></a>
                                                                <ul aria-labelledby="drop3" role="menu" class="dropdown-menu">
                                                                    <li role="presentation">
                                                                        <form method="POST" action="EditarUsuarioServlet"> 
                                                                            <input type='hidden' name="idUser" placeholder="Fecha" value="<%= uMuro.getIdUsuario() %>"/>                                                                                                                                                   
                                                                            <button class="btn btn-link" type="submit">Editar perfil</button>
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
                                                            <%    
                                                            if(mensaje!=null){                                             
                                                            %>
                                                            
                                                                <p><%=mensaje%></p>
                                                            
                                                            <%    
                                                            }     
                                                            %>
								<!-- content -->                      
								<div class="row">
								  
								 <!-- main col left --> 
								 <div class="col-sm-5">
								   
                                                                     
                                                                     
                                                                     <%for (int i=0;i<lista.size();i++) { 
                                                                         Post p=lista.get(i);
                                                                         
                                                                     %>        
                                                                    <div class="panel panel-default">
                                                                        <div class="panel-thumbnail"><img src="<%= p.getImagen()%>" class="img-responsive"></div>
                                                                        <div class="panel-body">
                                                                            <p><%= uMuro.getNombre() + " " + uMuro.getApellidos()%></p>
                                                                            <p><%= p.getFecha()%></p>
                                                                            <p><%= p.getDescripcion()%></p>


                                                                            <p>
                                                                                <img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28px" width="28px">

                                                                            <form name="delete" action="PostDeleteServlet" method="post">    
                                                                                <input type="hidden" value="usuario" name="tipo_borrado"/>
                                                                                <input type="hidden" value="<%=p.getIdPost()%>" name="idGuardada"/> <!--Guardamos la id para recuperarla al borrar post-->
                                                                                <input href class="btnEliminar botonEliminar" type="submit" value="Eliminar" name="eliminar" />
                                                                            </form>
                                                                            </p>
                                                                        </div>

                                                                    </div>
                                        <%
                                            }
                                        %>  
                                        <!--<div class="panel panel-default">
                                              <div class="panel-thumbnail"><img src="assets/img/bg_5.jpg" class="img-responsive"></div>
                                              <div class="panel-body">
                                                <p class="lead">Urbanization</p>
                                                <p>45 Followers, 13 Posts</p>
                                                
                                                <p>
                                                      <img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28px" width="28px">
                                                </p>
                                              </div>
                                              
                                        </div>-->







                                        <!--<div class="panel panel-default">
                                              <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Bootstrap Examples</h4></div>
                                                <div class="panel-body">
                                                      <div class="list-group">
                                                        <a href="http://usebootstrap.com/theme/facebook" class="list-group-item">Modal / Dialog</a>
                                                        <a href="http://usebootstrap.com/theme/facebook" class="list-group-item">Datetime Examples</a>
                                                        <a href="http://usebootstrap.com/theme/facebook" class="list-group-item">Data Grids</a>
                                                      </div>
                                                </div>
                                        </div>-->

                                        <!--<div class="well"> 
                                                 <form class="form-horizontal" role="form">
                                                      <h4>What's New</h4>
                                                       <div class="form-group" style="padding:14px;">
                                                        <textarea class="form-control" placeholder="Update your status"></textarea>
                                                      </div>
                                                      <button class="btn btn-primary pull-right" type="button">Post</button><ul class="list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>
                                                </form>
                                        </div>-->

                                        <!--<div class="panel panel-default">
                                               <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>More Templates</h4></div>
                                                <div class="panel-body">
                                                      <img src="assets/img/150x150.gif" class="img-circle pull-right"> <a href="#">Free @Bootply</a>
                                                      <div class="clearfix"></div>
                                                      
                                                      <hr>
                                                      <ul class="list-unstyled"><li><a href="http://usebootstrap.com/theme/facebook">Dashboard</a></li><li><a href="http://usebootstrap.com/theme/facebook">Darkside</a></li><li><a href="http://usebootstrap.com/theme/facebook">Greenfield</a></li></ul>
                                                </div>
                                        </div>-->

                                        <!--<div class="panel panel-default">
                                              <div class="panel-heading"><h4>What Is Bootstrap?</h4></div>
                                              <div class="panel-body">
                                                                                </div>
                                        </div>-->



                                    </div>

                                    <!-- main col right -->
                                    <!--  <div class="col-sm-7">
                                               
                                                    <div class="well"> 
                                                       <form class="form">
                                                            <h4>Sign-up</h4>
                                                            <div class="input-group text-center">
                                                            <input class="form-control input-lg" placeholder="Enter your email address" type="text">
                                                              <span class="input-group-btn"><button class="btn btn-lg btn-primary" type="button">OK</button></span>
                                                            </div>
                                                      </form>
                                                    </div>-->

                                    <!--<div class="panel panel-default">
                                          <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Bootply Editor &amp; Code Library</h4></div>
                                           <div class="panel-body">
                                                 <p><img src="assets/img/150x150.gif" class="img-circle pull-right"> <a href="#">The Bootstrap Playground</a></p>
                                                 <div class="clearfix"></div>
                                                 <hr>
                 
                                           </div>
                                    </div>-->

                                    <!--<div class="panel panel-default">
                                          <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Stackoverflow</h4></div>
                                           <div class="panel-body">
                                                 <img src="assets/img/150x150.gif" class="img-circle pull-right"> <a href="#">Keyword: Bootstrap</a>
                                                 <div class="clearfix"></div>
                                                 <hr>
                                                 
                                                 <p>If you're looking for help with Bootstrap code, the <code>twitter-bootstrap</code> tag at <a href="http://stackoverflow.com/questions/tagged/twitter-bootstrap">Stackoverflow</a> is a good place to find answers.</p>
                                                 
                                                 <hr>
                                                 <form>
                                                 <div class="input-group">
                                                   <div class="input-group-btn">
                                                   <button class="btn btn-default">+1</button><button class="btn btn-default"><i class="glyphicon glyphicon-share"></i></button>
                                                   </div>
                                                   <input class="form-control" placeholder="Add a comment.." type="text">
                                                 </div>
                                                 </form>
                                                 
                                           </div>
                                    </div>-->

                                    <!--<div class="panel panel-default">
                                          <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Portlet Heading</h4></div>
                                           <div class="panel-body">
                                                 <ul class="list-group">
                                                 <li class="list-group-item">Modals</li>
                                                 <li class="list-group-item">Sliders / Carousel</li>
                                                 <li class="list-group-item">Thumbnails</li>
                                                 </ul>
                                           </div>
                                    </div>-->

                                    <!-- <div class="panel panel-default">
                                          <div class="panel-thumbnail"><img src="assets/img/bg_4.jpg" class="img-responsive"></div>
                                          <div class="panel-body">
                                            <p class="lead">Social Good</p>
                                            <p>1,200 Followers, 83 Posts</p>
                                            
                                            <p>
                                                  <img src="assets/img/photo.jpg" height="28px" width="28px">
                                                  <img src="assets/img/photo.png" height="28px" width="28px">
                                                  <img src="assets/img/photo_002.jpg" height="28px" width="28px">
                                            </p>
                                          </div>
                                    </div>-->

                                </div>
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
