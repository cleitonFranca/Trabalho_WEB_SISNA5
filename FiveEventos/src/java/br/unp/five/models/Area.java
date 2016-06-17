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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "Area")
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a"),
    @NamedQuery(name = "Area.findByIdArea", query = "SELECT a FROM Area a WHERE a.idArea = :idArea"),
    @NamedQuery(name = "Area.findByDescricao", query = "SELECT a FROM Area a WHERE a.descricao = :descricao")})
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idArea")
    private Integer idArea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @JoinTable(name = "ClassificacaoArea", joinColumns = {
        @JoinColumn(name = "idArea", referencedColumnName = "idArea")}, inverseJoinColumns = {
        @JoinColumn(name = "idClassificacao", referencedColumnName = "idClassificacao")})
    @ManyToMany
    private List<Classificacao> classificacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private List<Periodicos> periodicosList;

    public Area() {
    }

    public Area(Integer idArea) {
        this.idArea = idArea;
    }

    public Area(Integer idArea, String descricao) {
        this.idArea = idArea;
        this.descricao = descricao;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Classificacao> getClassificacaoList() {
        return classificacaoList;
    }

    public void setClassificacaoList(List<Classificacao> classificacaoList) {
        this.classificacaoList = classificacaoList;
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
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
