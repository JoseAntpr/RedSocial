/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fran
 */
@Entity
@Table(name = "GRUPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByIdGrupo", query = "SELECT g FROM Grupo g WHERE g.idGrupo = :idGrupo"),
    @NamedQuery(name = "Grupo.findByIdAdministrador", query = "SELECT g FROM Grupo g WHERE g.idAdministrador = :idAdministrador"),
    @NamedQuery(name = "Grupo.findByNombre", query = "SELECT g FROM Grupo g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "Grupo.findByPrivacidad", query = "SELECT g FROM Grupo g WHERE g.privacidad = :privacidad"),
    @NamedQuery(name = "Grupo.findByImagen", query = "SELECT g FROM Grupo g WHERE g.imagen = :imagen"),
    @NamedQuery(name = "Grupo.findByNombreBuscar", query = "SELECT g FROM Grupo g WHERE UPPER(g.nombre) LIKE UPPER(:datos)")
})
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GRUPO")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "secuencia_grupo")
    @SequenceGenerator(name="secuencia_grupo", sequenceName = "GRUPO_SEQ", allocationSize=1)
    private BigDecimal idGrupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ADMINISTRADOR")
    private BigInteger idAdministrador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIVACIDAD")
    private BigInteger privacidad;
    @Size(max = 256)
    @Column(name = "IMAGEN")
    private String imagen;
    @JoinTable(name = "MIEMBRO", joinColumns = {
        @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")})
    @ManyToMany
    private Collection<Usuario> usuarioCollection;
    @OneToMany(mappedBy = "idGrupo")
    private Collection<Post> postCollection;

    public Grupo() {
    }

    public Grupo(BigDecimal idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Grupo(BigDecimal idGrupo, BigInteger idAdministrador, String nombre, BigInteger privacidad) {
        this.idGrupo = idGrupo;
        this.idAdministrador = idAdministrador;
        this.nombre = nombre;
        this.privacidad = privacidad;
    }

    public BigDecimal getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(BigDecimal idGrupo) {
        this.idGrupo = idGrupo;
    }

    public BigInteger getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(BigInteger idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(BigInteger privacidad) {
        this.privacidad = privacidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ea.entity.Grupo[ idGrupo=" + idGrupo + " ]";
    }
    
}
