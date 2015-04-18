<%-- 
    Document   : index
    Created on : 19-mar-2015, 11:02:07
    Author     : Azahar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <meta charset="utf-8">
        <title>Red Social</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="assets/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/facebook.css" rel="stylesheet">
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
                                <a href="#" class="navbar-brand logo">Rs</a>
                            </div>
                            <nav class="collapse navbar-collapse" role="navigation">							
                                <ul class="nav navbar-nav">						
                                    <li>
                                        <a href="#">Red Social - Tecnología de aplicaciones web</a>
                                    </li>	                                                       
                                </ul>							
                            </nav>
                        </div>

                    <!-- /top nav -->

                        <div class="padding">
                            <div class="full col-sm-4">
                                <!-- content -->                      
                                <div class="row">                                   
                                    <form method="POST" action="LoginServlet">
                                        <h4 class="form-signin-heading">Iniciar sesión:</h4> 
                                        <input type="text" name="email" placeholder="email"  class="form-control" required autofocus></br>
                                        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required></br>
                                        <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
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
