/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Post;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fran
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> {
    @PersistenceContext(unitName = "RedSocial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
        public List<Post> findByMuroIdUsuario (String id_usuario) {
        Query q;
        List<Post> listaPost;        
        
        q = em.createQuery("SELECT p FROM Post p, Muro m WHERE p.idPost=m.idPost AND m.idUsuario= :ID");
        // Mirar lo del Muro ya que no está la clase en ea.entity
        q.setParameter("ID", id_usuario);
        listaPost = q.getResultList();
        return listaPost;
        
    }
    
}
