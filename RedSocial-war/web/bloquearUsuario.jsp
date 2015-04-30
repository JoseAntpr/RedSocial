<%-- 
    Document   : bloquearUsuario
    Created on : 30-abr-2015, 17:25:01
    Author     : Azahar
--%>

<%@page import="ea.ejb.UsuarioFacade"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="ea.entity.Usuario"%>

<%
    HttpSession sesion = request.getSession(); 
    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
    Usuario usuarioMuro = (Usuario)sesion.getAttribute("usuarioMuro");
    
    List<Usuario> listaUsuario = (List) request.getAttribute("listaUsuarios");
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
                                    <div class="col-sm-12">

                                        <!-- POST -->
                                        <%  
                                            for (int i = 0; i < listaUsuario.size(); i++) {
                                                    Usuario u = listaUsuario.get(i);   
                                        %> 
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <%
                                                if(!u.getIdUsuario().equals(usuario.getIdUsuario())){                                                 
                                                    //  if(u.getEstado()){ 
                                                %>
                                                <form action="BloquearUsuarioServlet" method="post">  
                                                    <input type="hidden" value="<%=u.getIdUsuario() %>" name="idUser" >
                                                    <input type="hidden" value="Bloqueado" name="button" >
                                                    <input  class="btn btn-danger pull-right" type="submit" value="Bloqueado">
                                                </form><!--
                                                <%   // }else{%>
                                                <form action="BloquearUsuarioServlet" method="post">
                                                    <input type="hidden" value="<%=u.getIdUsuario() %>" name="idUser" >
                                                     <input type="hidden" value="Bloquear" name="button" >
                                                    <input  class="btn btn-primary pull-right" type="submit" value="Bloquear">
                                                </form>
                                                <%    //}%>-->
                                                <h4><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28px" width="28px"> <a href="MuroServlet?usuarioMuroGet=<%= u.getIdUsuario() %>">  <%= u.getNombre() + " " + u.getApellidos()%></a></h4>
                                                    <%
                                                }else{ %>
                                                <h4><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28px" width="28px"> <a href="MuroServlet">  <%= u.getNombre() + " " + u.getApellidos()%></a></h4>
                                                <%}%>
                                            </div>
                                            <div class="panel-body">
                                                <p>Descripcion... </p> 
                                            </div>
                                        </div> 
                                        <%  
                                           }%> 
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