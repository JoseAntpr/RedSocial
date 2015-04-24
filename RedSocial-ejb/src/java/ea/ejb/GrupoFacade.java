/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.ejb;

import ea.entity.Grupo;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fran
 */
@Stateless
public class GrupoFacade extends AbstractFacade<Grupo> {
    @PersistenceContext(unitName = "RedSocial-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Grupo nuevoGrupo(BigDecimal administrador,String nombre,String privacidad){
        Grupo grupo = new Grupo();
        
        grupo.setIdAdministrador(new BigInteger(administrador.toString()));
        grupo.setNombre(nombre);
        grupo.setImagen("imagen");
        
        if(privacidad.toUpperCase().equals("PUBLICO")){
            grupo.setPrivacidad(BigInteger.ZERO);
        }else{
            grupo.setPrivacidad(BigInteger.ONE);
        }
        create(grupo);
        return grupo;
    }
    
    public void eliminarGrupo(Grupo grupo){
        System.err.println("El id de grupo es " + grupo.getIdGrupo());
        remove(grupo);
    }

    public GrupoFacade() {
        super(Grupo.class);
    }
    
}
