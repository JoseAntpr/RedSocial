<%-- 
    Document   : sidebar
    Created on : 30-abr-2015, 17:07:03
    Author     : Azahar
--%>

<%@page import="java.text.SimpleDateFormat"%>
<!-- sidebar -->
<div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">
    <ul class="nav">
        <li><a href="#" data-toggle="offcanvas" class="visible-xs text-center"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
    </ul>
    <ul class="nav hidden-xs" id="lg-menu"></br></br>
        <li><img src="assets/img/Avatar.jpg" class="img-responsive"></li></br>							
        <li class="active"><%= usuarioMuro.getNombre() + " " + usuarioMuro.getApellidos()%></li></br>
        <li class="active">Vive en <%= usuarioMuro.getLocalidad() + ", " + usuarioMuro.getProvincia() + ", " + usuarioMuro.getPais()%></li></br>
        <%!
        SimpleDateFormat formatSidebar = new SimpleDateFormat("EEE dd MMM yyyy");
        %>
        <li class="active">Fecha ingreso:  <%= formatSidebar.format(usuarioMuro.getFechaIngreso()) %></li></br>
        <li>Descripción desdes desdes desdesdesdes desdesdesdes desdesdesdes</li></br>
        <li><a href="GrupoServlet"><i class="glyphicon glyphicon-align-right"></i> Grupos</a></li>                       
        <li><a href="ListarSeguidoresServlet?x=seguidores"><i class="glyphicon glyphicon-list"></i> Seguidores <b><%=usuarioMuro.getUsuarioCollection1().size()%></b>  </a></li>
        <li><a href="ListarSeguidoresServlet?x=Seguir" name="Seguir" ><i class="glyphicon glyphicon-list"></i> Siguiendo <b><%=usuarioMuro.getUsuarioCollection().size()%> </b></a></li></br>

        <!--
        <li><a href="GrupoServlet?usuarioMuro=<%= usuarioMuro.getIdUsuario()%>"><i class="glyphicon glyphicon-align-right"></i> Grupos</a></li>
        <li><a href="ListarSeguidoresServlet?x=seguidores&usuarioMuro=<%= usuarioMuro.getIdUsuario()%>"><i class="glyphicon glyphicon-list"></i> Seguidores <b><%=usuarioMuro.getUsuarioCollection1().size()%></b>  </a></li>
        <li><a href="ListarSeguidoresServlet?x=Seguir&usuarioMuro=<%= usuarioMuro.getIdUsuario()%>" name="Seguir" ><i class="glyphicon glyphicon-list"></i> Siguiendo <b><%=usuarioMuro.getUsuarioCollection().size()%> </b></a></li></br>
        -->  
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