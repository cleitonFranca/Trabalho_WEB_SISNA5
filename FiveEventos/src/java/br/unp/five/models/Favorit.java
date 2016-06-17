/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unp.five.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Cleiton França
 */
@Entity
@Table(name = "Favorit")
@NamedQueries({
    @NamedQuery(name = "Favorit.findAll", query = "SELECT f FROM Favorit f"),
    @NamedQuery(name = "Favorit.findByIdUsuario", query = "SELECT f FROM Favorit f WHERE f.idUsuario = :idUsuario"),
    @NamedQuery(name = "Favorit.findByIdPeriodico", query = "SELECT f FROM Favorit f WHERE f.idPeriodico = :idPeriodico")})
public class Favorit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Column(name = "idPeriodico")
    private Integer idPeriodico;
    
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Favorit() {
    }

    public Favorit(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPeriodico() {
        return idPeriodico;
    }

    public void setIdPeriodico(Integer idPeriodico) {
        this.idPeriodico = idPeriodico;
    }

   

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favorit)) {
            return false;
        }
        Favorit other = (Favorit) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "br.unp.five.models.Favorit[ idUsuario=" + idUsuario + " ]";
//    }

    @Override
    public String toString() {
        return "Favorit{" + "idUsuario=" + idUsuario + ", idPeriodico=" + idPeriodico + ", usuario=" + usuario + '}';
    }
    
    
    
    
}
