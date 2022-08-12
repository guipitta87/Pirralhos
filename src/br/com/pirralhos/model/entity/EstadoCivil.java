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
@Table(name = "estado_civil")
public class EstadoCivil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado_civil")
    private Integer idestadoCivil;
    @Basic(optional = false)
    @Column(name = "desc_estado_civil")
    private String descEstadoCivil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstadoCivl")
    private List<Professor> professorList;

    public EstadoCivil() {
    }

    public EstadoCivil(Integer idestadoCivil) {
        this.idestadoCivil = idestadoCivil;
    }

    public EstadoCivil(Integer idestadoCivil, String descEstadoCivil) {
        this.idestadoCivil = idestadoCivil;
        this.descEstadoCivil = descEstadoCivil;
    }

    public Integer getIdestadoCivil() {
        return idestadoCivil;
    }

    public void setIdestadoCivil(Integer idestadoCivil) {
        this.idestadoCivil = idestadoCivil;
    }

    public String getDescEstadoCivil() {
        return descEstadoCivil;
    }

    public void setDescEstadoCivil(String descEstadoCivil) {
        this.descEstadoCivil = descEstadoCivil;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadoCivil != null ? idestadoCivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCivil)) {
            return false;
        }
        EstadoCivil other = (EstadoCivil) object;
        if ((this.idestadoCivil == null && other.idestadoCivil != null) || (this.idestadoCivil != null && !this.idestadoCivil.equals(other.idestadoCivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.EstadoCivil[ idestadoCivil=" + idestadoCivil + " ]";
    }
    
}
