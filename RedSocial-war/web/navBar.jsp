<%-- 
    Document   : navBar
    Created on : 24-abr-2015, 17:43:24
    Author     : Azahar
--%>
  

<div class="navbar navbar-blue navbar-static-top">  
    <div class="navbar-header">
	<a  class="navbar-brand logo">Rs</a>
    </div>
    <nav class="collapse navbar-collapse" role="navigation">
        <form class="navbar-form navbar-left">
            <div class="input-group input-group-sm" style="max-width:360px;">
                <input class="form-control" placeholder="Search" name="buscar" id="srch-term" type="text">
                <div class="input-group-btn">
                    <a href="#" class="btn btn-default" ><i class="glyphicon glyphicon-search"></i></a>
                </div>
            </div>
	</form>
	<ul class="nav navbar-nav">
            <li>
                <a href="MuroServlet?usuarioMuro=<%= usuario.getIdUsuario()  %>"><i class="glyphicon glyphicon-home"></i> Inicio</a>
            </li>
            <li>
                <% if(usuario.getIdUsuario().equals(usuarioMuro.getIdUsuario())){ %>
                    <a href="postAddIntermedioServlet?usuarioMuro=<%=usuarioMuro.getIdUsuario()%>" role="button" ><i class="glyphicon glyphicon-plus"></i> Post</a>
                <% }else{}%>
            </li>
            <li>
                <a href="ListarSeguidoresServlet?x=usuariosSeguir&usuarioMuro=<%= usuario.getIdUsuario() %>"><span class="badge">Usuarios</span></a>
            </li>
            <li>
		<a href="#"><span class="badge">Grupos</span></a>
            </li>
	</ul>
	<ul class="nav navbar-nav navbar-right">
            <li class="dropdown" id="fat-menu"> <a data-toggle="dropdown" class="dropdown-toggle" role="button" id="drop3" href="#"><%= usuario.getNombre()+" "+usuario.getApellidos() %> <b class="caret"></b></a>
                <ul aria-labelledby="drop3" role="menu" class="dropdown-menu">
                  <!--  <li role="presentation">
                        
                        <form method="POST" action="EditarUsuarioServlet"> 
                            <input type='hidden' name="idUsuarioMuro" placeholder="Fecha" value="<%= usuarioMuro.getIdUsuario()%>"/>                                                                                                                                                   
                            <button class="btn btn-link" type="submit">Editar perfil</button>
                        </form>
                       
                    </li> -->
                    <li role="presentation"><a href="editarUsuario.jsp" tabindex="-1" role="menuitem">Editar Perfil</a></li>
                    <li role="presentation"><a href="editarPassword.jsp" tabindex="-1" role="menuitem"</a>Cambiar Contrase�a</li>
                    <li class="divider" role="presentation"></li>
                    <li role="presentation"><a href="LogoutServlet" tabindex="-1" role="menuitem">Cerrar Sesi�n</a></li>
                </ul>
           </li>	
	</ul>
    </nav>
</div>
