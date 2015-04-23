<%-- 
    Document   : seguidores.jsp
    Created on : 22-abr-2015, 17:03:46
    Author     : Joseantpr
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="ea.entity.Usuario"%>
<%
    List<Usuario> listaUsuario;
   
    Usuario usuarioPropio;
    
    usuarioPropio=(Usuario) request.getAttribute("usuario");
    
    
    listaUsuario = (List) request.getAttribute("listaSeguidores");
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

                    <!-- sidebar -->
                    <div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">
					  
						<ul class="nav">
							<li><a href="#" data-toggle="offcanvas" class="visible-xs text-center"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
						</ul>
					   
						<ul class="nav hidden-xs" id="lg-menu"></br></br>
                                                    <li><img src="assets/img/bg_5.jpg" class="img-responsive"></li></br>							
                                                        <li class="active">Nombre Apellido</li></br>
                                                        
							<li>Descripción desdes desdes desdesdesdes desdesdesdes desdesdesdes</li></br>
                                                        <li><a href="GrupoServlet"><i class="glyphicon glyphicon-list"></i> Grupos</a></li>
                                                        <li>
                                                                <a href="ListarSeguidoresServlet?x=seguidores"><i class="glyphicon glyphicon-list"></i> Seguidores</a></li>
                                                        <li><a href="ListarSeguidoresServlet?x=Seguir" name="Seguir" ><i class="glyphicon glyphicon-list"></i> Siguiendo</a></li></br>
                                                     
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
                                    <!--<li>
                                          <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Post</a>
                                    </li>-->
                                    <li>
                                        <a href="#"><span class="badge">Notificación</span></a>
                                    </li>
                                </ul>
                                <ul class="nav navbar-nav navbar-right">
                                    <li>
                                        <a href="#"><span class="glyphicon glyphicon-off"></span></a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                        <!-- /top nav -->

                        <div class="padding">
                            <div class="full col-sm-9">

                                <!-- content -->                      
                                <div class="row">

                                    <!-- main 100% page --> 
                                    <div class="col-sm-12">


                                        <!-- POST -->



                                        <%
                                            BigDecimal idUsuario = new BigDecimal(3.0);
                                            String s=null;
                                            for (int i = 0; i < listaUsuario.size(); i++) {
                                           
                                                if (!listaUsuario.get(i).getIdUsuario().equals(usuarioPropio.getIdUsuario()) ) {
                                                    Usuario u = listaUsuario.get(i);
                                                
                                                    s=usuarioPropio.siguesUsuario(u);
                                                
                                                    
                                                    
                                                    
                                        %> 
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <%if(s.equals("siguiendo")){ %>
                                                <button  class="btn btn-primary pull-right" type="submit">Siguiendo</button>
                                                <% }else{%>
                                                <button  class="btn btn-primary pull-right" type="submit">Seguir</button>
                                                <%}%>
                                                <h4><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28px" width="28px"> <%= u.getNombre() + " " + u.getApellidos()%></h4>

                                            </div>
                                            <div class="panel-body">
                                                <p>Descripcion... </p> 

                                            </div>
                                        </div> 
                                        <%  
                                                }
                                            }%> 
                                            






                                        <!--<ul class="list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-film"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>-->




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