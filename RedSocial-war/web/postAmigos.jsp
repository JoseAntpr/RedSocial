<%-- 
    Document   : postAmigos
    Created on : 30-abr-2015, 18:50:52
    Author     : Azahar
--%>

      <div class="col-sm-6">
                                    <%for (int i=0;i<lista.size();i++) { 
                                        Post p=lista.get(i);
                                     %>        
                                     <div class="panel panel-default">
                                        <div class="panel-thumbnail">
                                            <img src="<%= p.getImagen()%>" class="img-responsive">
                                        </div>
                                        <div class="panel-body">
                                            <p><%= usuarioMuro.getNombre() + " " + usuarioMuro.getApellidos()%></p>
                                            <p><%= p.getFecha()%></p>
                                            <p><%= p.getDescripcion()%></p>
                                                
                                            <p><img src="assets/img/uFp_tsTJboUY7kue5XAsGAs28.png" height="28px" width="28px">
                                                <% if(usuario.equals(usuarioMuro)){%>
                                                <form name="delete" action="PostDeleteServlet" method="post">    
                                                    <input type="hidden" value="usuario" name="tipo_borrado"/>
                                                    <input type="hidden" value="<%=p.getIdPost()%>" name="idGuardada"/> <!--Guardamos la id para recuperarla al borrar post-->
                                                    <input href class="btnEliminar botonEliminar" type="submit" value="Eliminar" name="eliminar" />
                                                </form>
                                                <%} %>
                                            </p>
                                        </div>
                                    </div>
                                    <% } %>  
                                </div>