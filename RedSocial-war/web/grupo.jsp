<%@page import="java.util.Collections.list(Enumeration)"%>
<%@page import="ea.entity.Grupo"%>
<%@page import="java.util.Enumeration"%>
<%@page import="ea.entity.Usuario"%>
<%@page import="ea.entity.Post"%>
<%@page import="java.util.List"%>
<%@page errorPage="ShowError.jsp" %>
<%-- 
    Document   : grupo
    Created on : 25-mar-2015, 14:27:55
    Author     : fran
--%>
<%

Usuario usuario=(Usuario)sesion.getAttribute("usuario");
Usuario usuarioMuro=(Usuario) request.getAttribute("usuarioMuro");


List<Grupo> listaGruposUsuario = null;

Grupo grupo = null;
List<Post> listaPostGrupo = null;
List<Usuario> listaMiembrosGrupo = null;

Boolean tieneGrupos = (Boolean)request.getAttribute("tieneGrupos");
if (tieneGrupos){
    // Lista de Grupos donde usuario es miembro
    listaGruposUsuario = (List)request.getAttribute("listaGruposUsuario");
    
    // primer grupo de la lista
    grupo =(Grupo) request.getAttribute("grupo");
    
    // Lista de post
    listaPostGrupo = (List<Post>)request.getAttribute("listaPostGrupo");
    
    // Lista de miembros
    listaMiembrosGrupo = (List<Usuario>)request.getAttribute("listaMiembrosGrupo");
}

//listaPost = (List) request.getAttribute("postGrupo");

//listaMiembros = (List) request.getAttribute("miembrosGrupo");

//List<Grupo> listaGruposUsuario;
//listaGruposMiembro = (List) request.getAttribute("listaGruposMiembro");


%>


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

                    <!-- LISTADO GRUPOS -->
                    <div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">

                        <ul class="nav hidden-xs" id="lg-menu">
                            <li class="active"><a href="crearGrupo.jsp?usurioMuro=<%=usuarioMuro.getIdUsuario()%>"><i class="glyphicon glyphicon-list-alt"></i> + Crear grupo </a></li>
                            <%if(tieneGrupos){
                                for(Grupo g : listaGruposUsuario){ %>
                                    <li class="active list-group">
                                        <a href="#featured"><i class="glyphicon glyphicon-list-alt"></i> <%=g.getNombre()%> </a>
                                        <form class="pull-right col-xs-offset-1" method="post" action="AbandonarGrupoServlet">
                                            <input type="hidden" name="idGrupoAbandonar" value="<%=g.getIdGrupo()%>"></input>
                                            <input class="btnEliminar botonEliminar" type="submit" name="abandonar" value="Abandonar" href=""></input>
                                        </form>
                                        <% if (g.getIdAdministrador().equals(usuario.getIdUsuario())){ %>    
                                            <form class="pull-right col-xs-offset-1" method="post" action="EliminarGrupoServlet" class="pull-right">
                                                <input type="hidden" name="idGrupoEliminar" value="<%=g.getIdGrupo()%>"></input>
                                                <input class="btnEliminar botonEliminar" type="submit" name="eliminiar" value="Borrar" href=""></input>
                                            </form>
                                        <% } %>
                                    </li>
                            <% }
                            }%>
                         </ul>
                    </div> <!-- fin LISTADO GRUPOS -->
                    <!-- /sidebar -->

                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main">

                       <!-- top nav -->
                        <%@include file="/navBarServlet?usuarioMuro=<%=usuarioMuro.getIdUsuario()%>" %>

                        <div class="padding">
                            <div class="full col-sm-9">

                                <!-- content -->                      
                                <div class="row">

                                    <!-- main col left --> 
                                    <div class="col-sm-5">
                                        <%if(tieneGrupos){ %>
                                        
                                            <!-- INFO DEL GRUPO -->
                                            <div class="panel panel-default">
                                                <div class="panel-thumbnail"><img src="assets/img/bg_5.jpg" class="img-responsive"></div>
                                                <div class="panel-body">
                                                        <p class="lead"><%= grupo.getNombre()%></p>
                                                        <p><%= listaMiembrosGrupo.size()%> Miembros, <%= listaPostGrupo.size()%> Posts</p>
                                                        <form method="POST" action="EliminarGrupoServlet?idGrupo=<%=grupo.getIdGrupo().toString()%>">
                                                            <button class="btn btn-danger btn-block" type="submit">Eliminar</button>   
                                                        </form>
                                                </div>
                                            </div> <!-- fin INFO DEL GRUPO -->
                                            
                                            <!-- MIEMBROS DEL GRUPO -->
                                            <div class="panel panel-default">
                                                <div class="panel-heading"> <h4>Miembros</h4></div>
                                                <% for (Usuario m : listaMiembrosGrupo) {%>
                                                <div class="panel-body">
                                                    <img src="assets/img/150x150.gif" class="img-circle pull-right"> <a href="MuroServlet?usuarioMuro=<%=m.getIdUsuario() %>"><%= m.getNombre()%></a>
                                                    <div class="clearfix"></div>
                                                    <hr>
                                                </div>
                                                <% }%>
                                            </div> <!-- fin MIEMBROS DEL GRUPO -->
                                        <%}else{%>
                                            <p class="lead">NO HAY GRUPOS</p>
                                        <%}%>
                                    </div>

                                    <!-- main col right -->
                                    
                                    
                                    <div class="col-sm-7">
                                        <%if(tieneGrupos){ %>
                                        
                                        <!-- CREAR POST GRUPO -->
                                            <div class="well"> 
                                                <form action="./GrupoCrearPostServlet" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
                                                    <h4>¿Qué te cuentas?</h4>
                                                    <div class="form-group" style="padding:14px;">
                                                        <input class="form-control" name="id_grupo" type="hidden" value="<%=grupo.getIdGrupo()%>"/>
                                                        <textarea class="form-control" name="description_post_grupo" placeholder="Actualiza tu estado" ></textarea>
                                                        <input class="filestyle" data-input="false" data-buttonText="Buscar Archivo" name="image_post_grupo" type="file" />
                                                        <!--<button class="btn btn-primary pull-right" type="submit">Post</button>-->
                                                        <input type="submit" class="btn btn-primary pull-right" name="btnPost" value="Post"/>
                                                    </div>

                                                </form>
                                            </div> <!-- fin CREAR POST GRUPO -->
                                            
                                        <!-- LISTAR POST GRUPO -->
                                            <%for (Post p : listaPostGrupo) {%>
                                                <div class="panel panel-default">
                                                <% if (p.getImagen() != null) {%>
                                                    <div class="panel-thumbnail"><img src="<%=p.getImagen()%>" class="img-responsive"></div>
                                                    <div class="panel-body">
                                                <% }%>

                                                        <p><%=p.getDescripcion()%></p>

                                                        <form name="delete" action="PostDeleteServlet" method="post">                                                                   
                                                            <input type="hidden" name="idGuardada" value="<%=p.getIdPost()%>"/> <!--Guardamos la id para recuperarla al borrar post-->
                                                            <input type="hidden" name="tipo_borrado" value="from_grupo"/>
                                                            <input href class="btnEliminar botonEliminar" type="submit" value="Eliminar" name="eliminar" />
                                                        </form>

                                                    </div>
                                                </div>
                                            <% } // fin LISTAR POST GRUPO
                                        }// fin tieneGrupos%>
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

