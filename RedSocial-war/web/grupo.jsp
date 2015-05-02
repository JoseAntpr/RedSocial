<%@page import="java.math.BigDecimal"%>
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

Usuario usuario=(Usuario)session.getAttribute("usuario");
Usuario usuarioMuro=(Usuario) session.getAttribute("usuarioMuro");


List<Grupo> listaGruposUsuarioMuro = null;

Grupo grupo = null;
List<Post> listaPostGrupo = null;
List<Usuario> listaMiembrosGrupo = null;

String idPostEditarString = null;
BigDecimal idPostEditar = null;

String imagenPost = null;

Boolean muroDeOtro = (Boolean) request.getAttribute("muroDeOtro");

Boolean tieneGrupos = (Boolean)request.getAttribute("tieneGrupos");
if (tieneGrupos){
    // Lista de Grupos donde usuario es miembro
    listaGruposUsuarioMuro = (List)request.getAttribute("listaGruposUsuarioMuro");
    
    // primer grupo de la lista
    //grupo = listaGruposUsuarioMuro.get(0);
    grupo = (Grupo) request.getAttribute("grupo");
    
    // Lista de post
    listaPostGrupo = (List)request.getAttribute("listaPostGrupo");
    
    // Lista de miembros
    listaMiembrosGrupo = (List)request.getAttribute("listaMiembrosGrupo");
    
    // Recuperamos el post a editar de grupo.jsp si es que lo hay
    idPostEditarString = (String)request.getAttribute("idPostEditar");
    if(idPostEditarString != null){
        idPostEditar = new BigDecimal(idPostEditarString);
    }
}

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
                            <%if(usuario.getIdUsuario().equals(usuarioMuro.getIdUsuario())){%>
                            <li class="active"><a href="crearGrupo.jsp?usurioMuro=<%=usuarioMuro.getIdUsuario()%>"><i class="glyphicon glyphicon-list-alt"></i> + Crear grupo </a></li> 
                            <%}%>
                            <%if(tieneGrupos){
                                for(Grupo g : listaGruposUsuarioMuro){ %>
                                    <li class="active list-group">
                                        <a href="GrupoServlet?idGrupoElegido=<%=g.getIdGrupo()%>"><i class="glyphicon glyphicon-list-alt"></i> <%=g.getNombre()%> </a>
                                        <form action="GrupoUnirAbandonarServlet" method="post">
                                            <input type="hidden" name="accion" value="abandonar"/>
                                            <input type="hidden" name="idGrupo" value="<%=g.getIdGrupo()%>"/>
                                            <input  class="btn btn-danger btn-block" type="submit" name="boton" value="Abandonar">
                                        </form>
                                    </li>
                            <% }
                            }%>
                         </ul>
                    </div> <!-- fin LISTADO GRUPOS -->
                    <!-- /sidebar -->

                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main">

                       <!-- top nav -->
                        <%@include file="/navBar.jsp" %>

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
                                                        
                                                        <form method="POST" action="EditarGrupoServlet?idGrupo=<%=grupo.getIdGrupo().toString()%>">
                                                            <button class="btn btn-warning btn-block" type="submit">Editar grupo</button>   
                                                        </form>
                                                            
                                                        <form method="POST" action="EliminarGrupoServlet?idGrupo=<%=grupo.getIdGrupo().toString()%>">
                                                            <button class="btn btn-danger btn-block" type="submit">Eliminar grupo</button>   
                                                        </form>
                                                </div>
                                            </div> <!-- fin INFO DEL GRUPO -->
                                            
                                            <!-- MIEMBROS DEL GRUPO -->
                                            <div class="panel panel-default">
                                                <div class="panel-heading"> <h4>Miembros</h4></div>
                                                <div class="panel panel-body">
                                                    <form method="POST" action="editarGrupo.jsp?grupo=<%=grupo%>">
                                                        <button class="btn btn-success  btn-group col-sm-8" type="submit">Añadir miembro</button>   
                                                    </form>

                                                    <form method="POST" action="EliminarGrupoServlet">
                                                        <button class="btn btn-danger btn-group col-sm-4" type="submit">Eliminar miembro</button>   
                                                    </form>
                                                </div>
                                                    
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
                                        <%if(tieneGrupos){ 
                                            if(!muroDeOtro){ %>
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
                                            <% } %> 
                                        <!-- LISTAR POST GRUPO -->
                                            <%for (Post p : listaPostGrupo) {
                                                imagenPost = p.getImagen();
                                                 if (idPostEditarString != null){ 
                                                    if(p.getIdPost().equals(idPostEditar)){ %>
                                                        <div class="panel panel-default">
                                                        <% if (imagenPost != null) {%>
                                                            <div class="panel-thumbnail"><img src="<%=imagenPost%>" class="img-responsive"></div>
                                                        <% } %>
                                                        <div class="well"> 
                                                            <form action="GrupoEditarPostServlet?idPostEditar=<%=p.getIdPost()%>" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
                                                                <h4>¡Editando Post!</h4>
                                                                <div class="form-group" style="padding:14px;">
                                                                    <input class="form-control" name="id_grupo" type="hidden" value="<%=grupo.getIdGrupo()%>"/>
                                                                    <textarea class="form-control" name="description_post_grupo" ><%=p.getDescripcion()%></textarea>
                                                                    <input class="filestyle" data-input="false" data-buttonText="Buscar Archivo" name="image_post_grupo" type="file"/>
                                                                    <!--<button class="btn btn-primary pull-right" type="submit">Post</button>-->
                                                                    <input type="submit" class="btn btn-primary pull-right" name="btnPost" value="Actualiza"/>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <%}else{%>
                                                        <div class="panel panel-default">
                                                            <% if (imagenPost != null) {%>
                                                                <div class="panel-thumbnail"><img src="<%=imagenPost%>" class="img-responsive"></div>
                                                            <% } %>
                                                            <div class="panel-body">
                                                            <p><%=p.getDescripcion()%></p>
                                                            <% if(p.getIdUsuario().getIdUsuario().equals(usuario.getIdUsuario())){ %>
                                                                <form name="delete" action="PostDeleteServlet?idGrupoElegido=<%=grupo.getIdGrupo()%>" method="post">                                                                   
                                                                    <input type="hidden" name="idPost" value="<%=p.getIdPost()%>"/> <!--Guardamos la id para recuperarla al borrar post-->
                                                                    <input type="hidden" name="tipo_borrado" value="from_grupo"/>
                                                                    <input class="btn btn-danger pull-right" type="submit" value="Eliminar" name="eliminar" />
                                                                </form>
                                                                <form name="edit" action="GrupoServlet?idPostEditar=<%=p.getIdPost()%>&idGrupoElegido=<%=grupo.getIdGrupo()%>" method="post">                                                                   
                                                                    <input type="hidden" name="idPost" value="<%=p.getIdPost()%>"/> <!--Guardamos la id para recuperarla al editar post-->
                                                                    <input type="hidden" name="from" value="from_grupo"/>
                                                                    <input class="btn btn-warning pull-right" type="submit" value="Editar" name="editar" />
                                                                </form>
                                                            <% }%>
                                                            </div>
                                                        </div>
                                                    <%}
                                                }else{ %>
                                                    <div class="panel panel-default">
                                                    <% if (imagenPost != null) {%>
                                                        <div class="panel-thumbnail"><img src="<%=imagenPost%>" class="img-responsive"></div>
                                                    <% } %>
                                                        <div class="panel-body">
                                                        <p><%=p.getDescripcion()%></p>
                                                        <% if(p.getIdUsuario().getIdUsuario().equals(usuario.getIdUsuario())){ %>
                                                            <form name="delete" action="PostDeleteServlet?idGrupoElegido=<%=grupo.getIdGrupo()%>" method="post">                                                                   
                                                                <input type="hidden" name="idPost" value="<%=p.getIdPost()%>"/> <!--Guardamos la id para recuperarla al borrar post-->
                                                                <input type="hidden" name="tipo_borrado" value="from_grupo"/>
                                                                <input class="btn btn-danger pull-right" type="submit" value="Eliminar" name="eliminar" />
                                                            </form>
                                                            <form name="edit" action="GrupoServlet?idPostEditar=<%=p.getIdPost()%>&idGrupoElegido=<%=grupo.getIdGrupo()%>" method="post">                                                                   
                                                                <input type="hidden" name="idPost" value="<%=p.getIdPost()%>"/> <!--Guardamos la id para recuperarla al editar post-->
                                                                <input type="hidden" name="from" value="from_grupo"/>
                                                                <input class="btn btn-warning pull-right" type="submit" value="Editar" name="editar" />
                                                            </form>
                                                        <% }%>
                                                        </div>
                                                    </div>
                                                <% } %>
                                            <% } // fin LISTAR POST GRUPO
                                        }// fin tieneGrupos%>
                                    </div>
                                </div><!--/row-->
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

