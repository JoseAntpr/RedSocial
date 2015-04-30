
<%@page import="ea.entity.Usuario"%>
66<%-- 
    Document   : postAdd
    Created on : 21-mar-2015, 13:39:52
    Author     : Jesus
--%>
<%
    HttpSession sesion = request.getSession();
    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
    Usuario usuarioMuro = (Usuario) session.getAttribute("usuarioMuro");

    //Usuario usuarioMuro = (Usuario)request.getAttribute("usuarioMuro");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir Post</title>
        <link href="assets/css/bootstrap.css" rel="stylesheet">
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
                                    <div class="col-sm-12">


                                        <!-- POST --> 
                                        <div class="well"> 
                                            <form class="form-horizontal" role="form" name="add" action="PostAddServlet" method="post" enctype="multipart/form-data">
                                                <h4>¿Qué estás pensando?</h4>
                                                <div class="form-group" style="padding:14px;">
                                                    <textarea class="form-control" name="descripcion" placeholder="Escriba el contenido del post"></textarea>

                                                </div>
                                                <button  class="btn btn-primary pull-right" type="submit">Post</button>
                                                <input type="file" name="imagen" value="Introduzca"/>

                                                <!--<ul class="list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-film"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>-->
                                            </form>
                                        </div>

                                    </div>


                                </div><!--/row-->



                                <!--<div class="row" id="footer">    
                                    <div class="col-sm-6">

                                    </div>

                                </div>-->

                            </div> <!--/col-9 -->
                       </div> <!-- /padding -->
                    </div>
                    <!-- /main -->

                </div>
            </div>
        </div>


        <!--post modal-->
        <!--
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
        </div> -->

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
