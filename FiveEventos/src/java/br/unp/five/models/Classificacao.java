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
@Table(name = "Classificacao")
@NamedQueries({
    @NamedQuery(name = "Classificacao.findAll", query = "SELECT c FROM Classificacao c"),
    @NamedQuery(name = "Classificacao.findByIdClassificacao", query = "SELECT c FROM Classificacao c WHERE c.idClassificacao = :idClassificacao"),
    @NamedQuery(name = "Classificacao.findByDescricao", query = "SELECT c FROM Classificacao c WHERE c.descricao = :descricao")})
public class Classificacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idClassificacao")
    private Integer idClassificacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @ManyToMany(mappedBy = "classificacaoList")
    private List<Area> areaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClassificacao")
    private List<Periodicos> periodicosList;

    public Classificacao() {
    }

    public Classificacao(Integer idClassificacao) {
        this.idClassificacao = idClassificacao;
    }

    public Classificacao(Integer idClassificacao, String descricao) {
        this.idClassificacao = idClassificacao;
        this.descricao = descricao;
    }

    public Integer getIdClassificacao() {
        return idClassificacao;
    }

    public void setIdClassificacao(Integer idClassificacao) {
        this.idClassificacao = idClassificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
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
        hash += (idClassificacao != null ? idClassificacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classificacao)) {
            return false;
        }
        Classificacao other = (Classificacao) object;
        if ((this.idClassificacao == null && other.idClassificacao != null) || (this.idClassificacao != null && !this.idClassificacao.equals(other.idClassificacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
}
