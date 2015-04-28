/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Usuario;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fran
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "RedSocial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    //Azahar: Método para buscar usuarios en el login.
    //Devuelve el usuario si lo encuentra, y si no devuelve null.
    public Usuario login(String email, String password){
        
        Usuario user = null;
        
        //Creamos una consulta que busque usuarios por email y contraseña.
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email=:email AND u.password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);
        
        try{
            user = (Usuario) query.getSingleResult();  
        }catch ( Exception ex){}
                    
        
        return user;
    } 
    
    public Usuario buscarEmail (String email){
    
        Query query = em.createNamedQuery("Usuario.findByEmail");
        query.setParameter("email", email);
        Usuario user = null;
        
        try{
         user = (Usuario) query.getSingleResult();
        }catch ( Exception ex){}
         
        return user;
    } 
    
    //Azahar: Método para cuando se registra un nuevo usuario.
     public Usuario nuevoUser(String nombre, String apellidos, String direccion, String localidad, String provincia, String pais, String email, String password) {
       
       
//             BigDecimal bg = new BigDecimal(11.0);
                
             Usuario user = new Usuario();         
             
              user.setNombre(nombre);
              user.setApellidos(apellidos);
              user.setDireccion(direccion);
              user.setLocalidad(localidad);
              user.setProvincia(provincia);
              user.setPais(pais);
              user.setEmail(email);
              user.setPassword(password);
//              user.setIdUsuario(bg);
              user.setFechaIngreso(new Date());  

              create(user);  
             return user;
         
    }
     //Azahar: Método para cuando el usuario cambia sus datos.
     public void editarUsuario(Usuario usuario, String nombre, String apellidos, String direccion, String localidad, String provincia, String pais, String email) {

            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            usuario.setDireccion(direccion);
            usuario.setLocalidad(localidad);
            usuario.setProvincia(provincia);
            usuario.setPais(pais);
            usuario.setEmail(email);

            edit(usuario);               
         
    }
     //Azahar: Método para cuando el usuario quiera cambiar su contraseña.
     public void editarPass(Usuario usuario, String pass) {
    
            usuario.setPassword(pass);
            edit(usuario);               
         
    }
    
}
