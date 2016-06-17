/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unp.five.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Cleiton França
 */
@Entity
@Table(name = "Periodicos")
@NamedQueries({
    @NamedQuery(name = "Periodicos.findAll", query = "SELECT p FROM Periodicos p"),
    @NamedQuery(name = "Periodicos.findByIdPeriodico", query = "SELECT p FROM Periodicos p WHERE p.idPeriodico = :idPeriodico"),
    @NamedQuery(name = "Periodicos.findByIssn", query = "SELECT p FROM Periodicos p WHERE p.issn = :issn"),
    @NamedQuery(name = "Periodicos.findByTitulo", query = "SELECT p FROM Periodicos p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Periodicos.findByPais", query = "SELECT p FROM Periodicos p WHERE p.pais = :pais"),
    @NamedQuery(name = "Periodicos.findByTipo", query = "SELECT p FROM Periodicos p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Periodicos.findByInicioprazo", query = "SELECT p FROM Periodicos p WHERE p.inicioprazo = :inicioprazo"),
    @NamedQuery(name = "Periodicos.findByFimprazo", query = "SELECT p FROM Periodicos p WHERE p.fimprazo = :fimprazo"),
    @NamedQuery(name = "Periodicos.findByStatus", query = "SELECT p FROM Periodicos p WHERE p.status = :status")})
public class Periodicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPeriodico")
    private Integer idPeriodico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "issn")
    private String issn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pais")
    private String pais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inicioprazo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioprazo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fimprazo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fimprazo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;
    //@OneToMany(mappedBy = "idPeriodico2")
    //private List<Favorit> favoritList;
    @JoinColumn(name = "idArea", referencedColumnName = "idArea")
    @ManyToOne(optional = false)
    private Area idArea;
    @JoinColumn(name = "idClassificacao", referencedColumnName = "idClassificacao")
    @ManyToOne(optional = false)
    private Classificacao idClassificacao;
    @JoinColumn(name = "idPeso", referencedColumnName = "idPeso")
    @ManyToOne(optional = false)
    private Peso idPeso;

    public Periodicos() {
    }

    public Periodicos(Integer idPeriodico) {
        this.idPeriodico = idPeriodico;
    }

    public Periodicos(Integer idPeriodico, String issn, String titulo, String pais, String tipo, Date inicioprazo, Date fimprazo, String status) {
        this.idPeriodico = idPeriodico;
        this.issn = issn;
        this.titulo = titulo;
        this.pais = pais;
        this.tipo = tipo;
        this.inicioprazo = inicioprazo;
        this.fimprazo = fimprazo;
        this.status = status;
    }

    public Integer getIdPeriodico() {
        return idPeriodico;
    }

    public void setIdPeriodico(Integer idPeriodico) {
        this.idPeriodico = idPeriodico;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getInicioprazo() {
        return inicioprazo;
    }

    public void setInicioprazo(Date inicioprazo) {
        this.inicioprazo = inicioprazo;
    }

    public Date getFimprazo() {
        return fimprazo;
    }

    public void setFimprazo(Date fimprazo) {
        this.fimprazo = fimprazo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Classificacao getIdClassificacao() {
        return idClassificacao;
    }

    public void setIdClassificacao(Classificacao idClassificacao) {
        this.idClassificacao = idClassificacao;
    }

    public Peso getIdPeso() {
        return idPeso;
    }

    public void setIdPeso(Peso idPeso) {
        this.idPeso = idPeso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodico != null ? idPeriodico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodicos)) {
            return false;
        }
        Periodicos other = (Periodicos) object;
        if ((this.idPeriodico == null && other.idPeriodico != null) || (this.idPeriodico != null && !this.idPeriodico.equals(other.idPeriodico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.unp.five.models.Periodicos[ idPeriodico=" + idPeriodico + " ]";
    }
    
}
