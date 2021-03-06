<%-- 
    Document   : buscar
    Created on : 30-abr-2015, 19:27:15
    Author     : Jesus
--%>

<%@page import="java.math.BigInteger"%>
<%@page import="ea.entity.Grupo"%>
<%@page import="ea.ejb.UsuarioFacade"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="ea.entity.Usuario"%>


<%
    
    List<Usuario> listaUsuario;
    List<Grupo> listaGrupos;
    
    HttpSession sesion = request.getSession(); 
    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
    Usuario usuarioMuro = (Usuario)sesion.getAttribute("usuarioMuro");
    
//    Usuario usuarioMuro=(Usuario) request.getAttribute("usuarioMuro");
    String datos=(String)request.getAttribute("datos");
    
    listaUsuario = (List) request.getAttribute("listaUsuarios");
    listaGrupos=(List) request.getAttribute("listaGrupos");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

                        <!-- top nav -->
                        <%@include file="navBar.jsp" %>
                        
                        <!-- /top nav -->

                        <div class="padding">
                            <div class="full col-sm-9">

                                <!-- content -->                      
                                <div class="row">

                                    <!-- main 100% page --> 
                                    <div class="col-sm-6">


                                        <!-- POST -->
                                        <h3> Usuarios</h3>


                                        <%                                          
                                            String s=null;
                                            for (int i = 0; i < listaUsuario.size(); i++) {
                                                    Usuario u = listaUsuario.get(i);
                                                    s=usuario.siguesUsuario(u);                                            
                                        %> 
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <%
                                                if(!u.getIdUsuario().equals(usuario.getIdUsuario())){                                                 
                                                      if(s.equals("si")){ %>
                                                        <form action="SeguirNoSeguirServlet" method="post">  
                                                        <input type="hidden" value="<%=u.getIdUsuario() %>" name="usuarioDejarSeguir" >
                                                        <input type="hidden" value="Siguiendo" name="botonSeguir" >
                                                        <input type="hidden" value="<%= usuarioMuro.getIdUsuario() %>" name="usuariomuro" >
                                                        <input type="hidden" value="<%= datos %>" name="datos" >
                                                        <input type="hidden" value="busquedaUsuarios" name="ruta" >
                                                        <input  class="btn btn-success pull-right" type="submit" name="boton" value="Siguiendo">
                                                        </form>
                                                      <% }else{%>
                                                        <form action="SeguirNoSeguirServlet" method="post">
                                                        <input type="hidden" value="<%=u.getIdUsuario() %>" name="usuarioSeguir" >
                                                        <input type="hidden" value="Seguir" name="botonSeguir" >
                                                        <input type="hidden" value="<%= usuarioMuro.getIdUsuario() %>" name="usuariomuro" >
                                                        <input type="hidden" value="<%= datos %>" name="datos" >
                                                        <input type="hidden" value="busquedaUsuarios" name="ruta" >
                                                        <input  class="btn btn-primary pull-right" type="submit" name="boton" value="Seguir">
                                                        </form>
                                                <%      }%>
                                                      <h4><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28" width="28"> <a href="MuroServlet?usuarioMuroGet=<%= u.getIdUsuario() %>">  <%= u.getNombre() + " " + u.getApellidos()%></a></h4>
                                                <%
                                                }else{ %>
                                                    <h4><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28" width="28"> <a href="MuroServlet">  <%= u.getNombre() + " " + u.getApellidos()%></a></h4>
                                                <%}%>
                                                
                                                

                                            </div>
                                            <div class="panel-body">
                                                <p>Descripcion... </p> 

                                            </div>
                                        </div> 
                                        <%  
                                                
                                            }%> 
                                            

                                        <!--<ul class="list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-film"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>-->

                                    </div>

                                       <div class="col-sm-6">


                                        <!-- POST -->
                                        <h3> Grupos</h3>


                                        <%                                          
                                            
                                            for (int i = 0; i < listaGrupos.size(); i++) {
                                                    Grupo g = listaGrupos.get(i);
                                                                                                
                                        %> 
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <% // GRUPO PUBLICO
                                                    if (g.getPrivacidad().equals(BigInteger.ZERO)){ 
                                                        if (usuario.getGrupoCollection().contains(g)){ %>
                                                            <p>Grupo p&uacute;blico, eres miembro.</p>
                                                            <form action="GrupoUnirAbandonarServlet" method="post">
                                                                <input type="hidden" name="accion" value="abandonar"/>
                                                                <input type="hidden" name="idGrupo" value="<%=g.getIdGrupo()%>"/>
                                                                <input type="hidden" name="datosBusqueda" value="<%=datos%>"/>
                                                                <input  class="btn btn-danger pull-right" type="submit" name="boton" value="Abandonar">
                                                            </form>
                                                            <h4><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28" width="28"> <a href="./GrupoServlet?idGrupoElegido=<%=g.getIdGrupo()%>">  <%= g.getNombre() %></a></h4>
                                                        <% }else{ %>
                                                            <p>Grupo p&uacute;blico, no eres miembro.</p>
                                                            <form action="GrupoUnirAbandonarServlet" method="post">
                                                                <input type="hidden" name="accion" value="unirse"/>
                                                                <input type="hidden" name="idGrupo" value="<%=g.getIdGrupo()%>"/>
                                                                <input type="hidden" name="datosBusqueda" value="<%=datos%>"/>
                                                                <input  class="btn btn-success pull-right" type="submit" name="boton" value="Unirse">
                                                            </form>
                                                            <h4><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28" width="28"> <a href="./GrupoServlet?idGrupoElegido=<%=g.getIdGrupo()%>">  <%= g.getNombre() %></a></h4>
                                                        <% }
                                                    }else { // GRUPO PRIVADO 
                                                        if (usuario.getGrupoCollection().contains(g)){ %>
                                                            <p>Grupo privado, eres miembro.</p>
                                                            <form action="GrupoUnirAbandonarServlet" method="post">
                                                                <input type="hidden" name="accion" value="abandonar"/>
                                                                <input type="hidden" name="idGrupo" value="<%=g.getIdGrupo()%>"/>
                                                                <input type="hidden" name="datosBusqueda" value="<%=datos%>"/>
                                                                <input  class="btn btn-danger pull-right" type="submit" name="boton" value="Abandonar">
                                                            </form>
                                                            <h4><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28" width="28"> <a href="./GrupoServlet?idGrupoElegido=<%=g.getIdGrupo()%>">  <%= g.getNombre() %></a></h4>
                                                        <% }else{ %>
                                                            <p>Grupo privado, no eres miembro.</p>
                                                            <h4><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28" width="28"> <a href="#">  <%= g.getNombre() %></a></h4>
                                                        <%}
                                                    } %>
                                                </div>
                                                <div class="panel-body">
                                                    <p>Descripcion... </p> 

                                                </div>
                                            </div> 
                                        <%  
                                                
                                            }%> 
                                            

                                        <!--<ul class="list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-film"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>-->

                                    </div>

                                </div><!--/row-->

                            </div><!-- /col-9 -->
                        </div><!-- /padding -->
                    </div>
                    <!-- /main -->

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
