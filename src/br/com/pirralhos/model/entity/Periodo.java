package br.com.pirralhos.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "periodo")
public class Periodo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperiodo")
    private Integer idperiodo;
    @Basic(optional = false)
    @Column(name = "desc_periodo")
    private String descPeriodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPeriodo")
    private List<Turma> turmaList;

    public Periodo() {
    }

    public Periodo(Integer idperiodo) {
        this.idperiodo = idperiodo;
    }

    public Periodo(Integer idperiodo, String descPeriodo) {
        this.idperiodo = idperiodo;
        this.descPeriodo = descPeriodo;
    }

    public Integer getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(Integer idperiodo) {
        this.idperiodo = idperiodo;
    }

    public String getDescPeriodo() {
        return descPeriodo;
    }

    public void setDescPeriodo(String descPeriodo) {
        this.descPeriodo = descPeriodo;
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        this.turmaList = turmaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperiodo != null ? idperiodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.idperiodo == null && other.idperiodo != null) || (this.idperiodo != null && !this.idperiodo.equals(other.idperiodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.Periodo[ idperiodo=" + idperiodo + " ]";
    }
    
}
