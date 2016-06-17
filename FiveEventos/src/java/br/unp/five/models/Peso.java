/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unp.five.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cleiton França
 */
@Entity
@Table(name = "Peso")
@NamedQueries({
    @NamedQuery(name = "Peso.findAll", query = "SELECT p FROM Peso p"),
    @NamedQuery(name = "Peso.findByIdPeso", query = "SELECT p FROM Peso p WHERE p.idPeso = :idPeso"),
    @NamedQuery(name = "Peso.findByDescricao", query = "SELECT p FROM Peso p WHERE p.descricao = :descricao")})
public class Peso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPeso")
    private Integer idPeso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeso")
    private List<Periodicos> periodicosList;

    public Peso() {
    }

    public Peso(Integer idPeso) {
        this.idPeso = idPeso;
    }

    public Peso(Integer idPeso, String descricao) {
        this.idPeso = idPeso;
        this.descricao = descricao;
    }

    public Integer getIdPeso() {
        return idPeso;
    }

    public void setIdPeso(Integer idPeso) {
        this.idPeso = idPeso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Periodicos> getPeriodicosList() {
        return periodicosList;
    }

    public void setPeriodicosList(List<Periodicos> periodicosList) {
        this.periodicosList = periodicosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeso != null ? idPeso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peso)) {
            return false;
        }
        Peso other = (Peso) object;
        if ((this.idPeso == null && other.idPeso != null) || (this.idPeso != null && !this.idPeso.equals(other.idPeso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
