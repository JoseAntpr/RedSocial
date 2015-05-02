<%-- 
    Document   : addMiembroGrupo
    Created on : 02-may-2015, 18:14:25
    Author     : Jose Sánchez Aranda
--%>

<%@page import="ea.entity.Grupo"%>
<%@page import="java.util.List"%>
<%@page import="ea.entity.Usuario"%>
<%
Usuario usuario=(Usuario)session.getAttribute("usuario");
Usuario usuarioMuro=(Usuario) session.getAttribute("usuarioMuro");
%>

<%
    Grupo grupo = (Grupo) request.getAttribute("grupo");
    List<Usuario> listaUsuario;
    
//    Usuario usuarioMuro=(Usuario) request.getAttribute("usuarioMuro");
    String datos=(String)request.getAttribute("datos");
    
    listaUsuario = (List) request.getAttribute("listaUsuarios");
    
    List<Usuario> listaMiembrosGrupo = (List<Usuario>) request.getAttribute("listaMiembrosGrupo");
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir miembro a grupo</title>
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
                            <li class="active"><a href="#"><i class="glyphicon glyphicon-list-alt"></i> Miembros del grupo </a></li> 
                            <%if(listaMiembrosGrupo!=null){
                                for(Usuario u : listaMiembrosGrupo){ %>
                                    <li class="active list-group">
                                        <a href="#"><i class="glyphicon glyphicon-list-alt"></i> <%=u.getNombre()%> </a>
                                    </li>
                            <% }
                            }%>
                         </ul>
                    </div> <!-- fin LISTADO GRUPOS -->
                    <!-- /sidebar -->
                    <!-- /sidebar -->
				  
                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main">
                        
                        <!-- top nav -->
                        <%@include file="/navBar.jsp" %>

                    <!-- /top nav -->

                        <div class="padding">
                            
                        <div class="panel panel-default full wrapper">
                            <div class="col-sm-6">
                                <!-- content -->                      
                                <div class="row">                                   
                                    <form method="POST" action="AddMiembroGrupoServlet?idGrupo=<%=grupo.getIdGrupo().toString()%>" id="miFormulario">
                                        <h4 class="form-signin-heading">Buscar usuarios:</h4> 
                                        <div class="col-sm-8">
                                        <input type="text" name="buscar" placeholder="Nombre"  class="form-control" autofocus></br>
                                        </div>
                                        <div class="col-sm-2">
                                           <button class="btn btn-success btn-block" type="submit">Buscar</button> 
                                        </div>
                                        
                                    </form>
                                    <a class="btn btn-primary col-sm-2" href="GrupoServlet">Salir</a>
                                </div><!--/row-->
                            </div><!-- /col-94-->
                            <div class="col-sm-6">
                                <h3>Grupo <%=grupo.getNombre()%></h3>  
                            </div>
                        </div>
                            
                                                                    <%                                          
                                            String s=null;
                                            for (int i = 0; i < listaUsuario.size(); i++) {
                                                    Usuario u = listaUsuario.get(i);
                                                    if(grupo.getUsuarioCollection().contains(u)){
                                                        s="si";
                                                    }else{
                                                        s="no";
                                                    }                                     
                                        %> 
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <%
                                                if(!u.getIdUsuario().equals(usuario.getIdUsuario())){                                                 
                                                      if(s.equals("si")){ %>
                                                      <form action="AddOrNotMiembroGrupoServlet?idGrupo=<%=grupo.getIdGrupo().toString()%>" method="post">  
                                                        <input type="hidden" value="<%=u.getIdUsuario() %>" name="idUsuario" >
                                                        <input type="hidden" value="<%= usuarioMuro.getIdUsuario() %>" name="usuariomuro" >
                                                        <input type="hidden" value="hola" name="datos" >
                                                        <input type="hidden" value="busquedaUsuarios" name="ruta" >
                                                        <input  class="btn btn-success pull-right" type="submit" name="boton" value="Bloquear">
                                                        </form>
                                                      <% }else{%>
                                                        <form action="AddOrNotMiembroGrupoServlet?idGrupo=<%=grupo.getIdGrupo().toString()%>" method="post">
                                                        <input type="hidden" value="<%=u.getIdUsuario() %>" name="idUsuario" >
                                                        <input type="hidden" value="<%= usuarioMuro.getIdUsuario() %>" name="usuariomuro" >
                                                        <input type="hidden" value="Hola" name="datos" >
                                                        <input type="hidden" value="busquedaUsuarios" name="ruta" >
                                                        <input  class="btn btn-primary pull-right" type="submit" name="boton" value="Añadir">
                                                        </form>
                                                <%      }%>
                                                      <h4> <a href="MuroServlet?usuarioMuroGet=<%= u.getIdUsuario() %>">  <%= u.getNombre() + " " + u.getApellidos()%></a></h4>
                                                <%
                                                }else{ %>
                                                    <h4><a href="MuroServlet">  <%= u.getNombre() + " " + u.getApellidos()%></a></h4>
                                                <%}%>
                                                
                                                

                                            </div>
                                            <div class="panel-body">
                                                <p>Descripcion... </p> 

                                            </div>
                                        </div> 
                                        <%  
                                                
                                            }%>
                            
                        </div><!-- /padding -->
                    </div>
                <!-- /main -->				  
                </div>
            </div>
        </div>
        
        <script type="text/javascript" src="assets/js/jquery.js"></script>
        <script type="text/javascript" src="assets/js/bootstrap.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
			$('[data-toggle=offcanvas]').click(function() {
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
