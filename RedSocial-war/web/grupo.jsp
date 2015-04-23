<%@page import="ea.entity.Grupo"%>
<%@page import="java.util.Enumeration"%>
<%@page import="ea.entity.Usuario"%>
<%@page import="ea.entity.Post"%>
<%@page import="java.util.List"%>
<%@page errorPage="ShowError.jsp" %>
p<%-- 
    Document   : grupo
    Created on : 25-mar-2015, 14:27:55
    Author     : fran
--%>

<%-- declaracion  y asignación de variables --%>
<%!List<Post> listaPost;%>
<% listaPost = (List) request.getAttribute("postGrupo");%>

<%!List<Usuario> listaMiembros;%>
<% listaMiembros = (List) request.getAttribute("miembrosGrupo");%>

<%!String nombreGrupo;%>
<% nombreGrupo = (String) request.getAttribute("nombreGrupo");%>

<%!List<Grupo> listaGrupo;%>
<% listaGrupo = (List) request.getAttribute("listaGrupo");%>


<%-- Enumeration atributosSesion = session.getAttributeNames(); --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <meta charset="utf-8">
        <title>Grupo</title>
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
                        <ul class="nav hidden-xs" id="lg-menu">
                            <li class="active"><a href="#featured"><i class="glyphicon glyphicon-list-alt"></i> + Crear grupo </a></li>
                            <% for(Grupo g : listaGrupo){ %>
                                <li class="active"><a href="#featured"><i class="glyphicon glyphicon-list-alt"></i> <%=g.getNombre()%> </a></li>
                            <% } %>
                            
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
                                <a class="navbar-brand logo">Rs</a>
                            </div>
                            <nav class="collapse navbar-collapse" role="navigation">
                                <form class="navbar-form navbar-left">
                                    <div class="input-group input-group-sm" style="max-width:360px;">
                                        <input class="form-control" placeholder="Search" name="srch-term" id="srch-term" type="text">
                                        <div class="input-group-btn">
                                            <a href="ListarSeguidoresServlet?x=usuariosSeguir" class="btn btn-default" ><i class="glyphicon glyphicon-search"></i></a>
                                            <!-- <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>-->
                                        </div>
                                    </div>
                                </form>
                                <ul class="nav navbar-nav">
                                    <li>
                                        <a href="MuroServlet"><i class="glyphicon glyphicon-home"></i> Inicio</a>
                                    </li>
                                    <li>
                                        <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Post</a>
                                    </li>
                                    <li>
                                        <a href="#"><span class="badge">badge</span></a>
                                    </li>
                                </ul>
                                <ul class="nav navbar-nav navbar-right">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="">More</a></li>
                                            <li><a href="">More</a></li>
                                            <li><a href="">More</a></li>
                                            <li><a href="">More</a></li>
                                            <li><a href="">More</a></li>
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

                                    <!-- main col left --> 
                                    <div class="col-sm-5">

                                        <div class="panel panel-default">
                                            <div class="panel-thumbnail"><img src="assets/img/bg_5.jpg" class="img-responsive"></div>
                                            <div class="panel-body">
                                                <p class="lead"><%= nombreGrupo%></p>
                                                <p><%= listaMiembros.size()%> Miembros, <%= listaPost.size()%> Posts</p>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading"> <h4>Miembros</h4></div>
                                            <% for (Usuario m : listaMiembros) {%>
                                            <div class="panel-body">
                                                <img src="assets/img/150x150.gif" class="img-circle pull-right"> <a href="#"><%= m.getNombre()%></a>
                                                <div class="clearfix"></div>
                                                <hr>
                                            </div>
                                            <% }%>
                                        </div>
                                    </div>

                                    <!-- main col right -->
                                    <div class="col-sm-7">

                                        <div class="well"> 
                                            <form action="./GrupoCrearPostServlet" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
                                                <h4>¿Que te cuentas?</h4>
                                                <div class="form-group" style="padding:14px;">
                                                    <textarea class="form-control" name="description_post_grupo" placeholder="Actualiza tu estado" ></textarea>
                                                    <input class="form-control" name="image_post_grupo" type="file" />
                                                </div>
                                                <!--<button class="btn btn-primary pull-right" type="submit">Post</button>-->
                                                <input type="submit" class="btn btn-primary pull-right" name="btnPost" value="Post"/>
                                                <ul class="list-inline">
                                                    <li>
                                                        <a href=""><i class="glyphicon glyphicon-upload" ></i></a>
                                                    </li>
                                                </ul>
                                            </form>
                                        </div>
                                        <% for (int i = listaPost.size() - 1; i >= 0; i--) {
                                                Post p = listaPost.get(i);
                                                //for (Post p : listaPost) {
                                        %>
                                        <div class="panel panel-default">
                                            <% if (p.getImagen() != null) {%>
                                            <div class="panel-thumbnail"><img src="<%=p.getImagen()%>" class="img-responsive"></div>
                                            <div class="panel-body">
                                                <% }%>

                                                <p><%=p.getDescripcion()%></p>

                                                <form name="delete" action="PostDeleteServlet" method="post">                                                                   
                                                    <input type="hidden" name="idGuardada" value="<%=p.getIdPost()%>"/> <!--Guardamos la id para recuperarla al borrar post-->
                                                    <input type="hidden" name="tipo_borrado" value="grupo"/>
                                                    <input href class="btnEliminar botonEliminar" type="submit" value="Eliminar" name="eliminar" />
                                                </form>

                                                <!--<p>
                                                      <img src="assets/img/photo.jpg" height="28px" width="28px">
                                                      <img src="assets/img/photo.png" height="28px" width="28px">
                                                      <img src="assets/img/photo_002.jpg" height="28px" width="28px">
                                                </p>-->
                                            </div>
                                        </div>
                                        <% }%>
                                    </div>
                                </div><!--/row-->
                                <div class="row" id="footer">    
                                    <div class="col-sm-6">

                                    </div>
                                </div>

                                <hr>
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

