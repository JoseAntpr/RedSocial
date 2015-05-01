<%-- 
    Document   : editarGrupo
    Created on : 30-abr-2015, 19:40:05
    Author     : Jose Sánchez Aranda
--%>
<%@page import="ea.entity.Grupo"%>
<%Grupo grupo =(Grupo) request.getAttribute("grupo");
    
  String idGrupo = grupo.getIdGrupo().toString();
  String nombreGrupo = grupo.getNombre();
  String privacidadGrupo = grupo.getPrivacidad().toString();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <meta charset="utf-8">
        <title>Editar Grupo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="assets/css/bootstrap.css" rel="stylesheet"/>
        <link href="assets/css/facebook.css" rel="stylesheet"/>
    </head>
    
    <body>
        
        <div class="wrapper">
            <div class="box">
		<div class="row row-offcanvas row-offcanvas-left">
					
                    <!-- sidebar -->
                    <div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">
                    </div>
                    <!-- /sidebar -->
				  
                    <!-- main right col -->
                    <div class="column col-sm-10 col-xs-11" id="main">
                        
                        <!-- top nav -->
                        <div class="navbar navbar-blue navbar-static-top">  
                            <div class="navbar-header">
                                <a  class="navbar-brand logo">Rs</a>
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
                                        <a href="MuroServlet?usuarioMuro=1"><i class="glyphicon glyphicon-home"></i> Inicio</a>
                                    </li>
                                   <li>
                                        <a href="ListarSeguidoresServlet?x=usuariosSeguir&uMuro=1"><span class="badge">Usuarios</span></a>
                                    </li>
                                    <li>
                                        <a href="#"><span class="badge">Grupos</span></a>
                                    </li>	                                                       
                                </ul>							
                            </nav>
                        </div>

                    <!-- /top nav -->

                        <div class="padding">
                            
                            <div class="full col-sm-6">
                                <!-- content -->                      
                                <div class="row">                                   
                                    <form method="POST" action="EditarGrupoServlet?grupoEditado=1&idGrupo=<%=idGrupo%>" id="miFormulario">
                                        <h4 class="form-signin-heading">Editar grupo:</h4> 
                                        <div class="col-sm-8">
                                        <input type="text" name="nombreGrupo" value="<%=nombreGrupo%>"  class="form-control" required autofocus></br>
                                        </div>
                                        <div class="col-sm-4">
                                        <select form="miFormulario" name="privacidad" class="form-control ">
                                            <%if(privacidadGrupo=="0"){%>
                                            <option value="publico">Público</option>
                                            <option value="privado">Privado</option>  
                                            <%}else{%>
                                            <option value="privado">Privado</option>
                                            <option value="publico">Público</option>
                                            <%}%>
                                        </select>
                                        </div>
                                        <br/>
                                        <br/>
                                        <div class="col-sm-5">
                                        <button class="btn btn-success btn-block" type="submit">Editar</button>
                                        </div>
                                    </form>                                    
                                </div><!--/row-->
                            </div><!-- /col-94-->
                            
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


