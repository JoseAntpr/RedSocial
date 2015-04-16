/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public Usuario login(String email, String pass){
        Usuario user = null;
              
        Query query = em.createNamedQuery("Usuario.findByEmailAndPassword");
        query.setParameter("email", email);
        query.setParameter("password", pass);
          
        try{
            user = (Usuario) query.getSingleResult();                  
        }
        catch(NoResultException e){}
        return user;
    }    
}
