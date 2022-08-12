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
@Table(name = "estado")
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado")
    private Integer idestado;
    @Basic(optional = false)
    @Column(name = "desc_estado")
    private String descEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idestado")
    private List<Cidade> cidadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstado")
    private List<Professor> professorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstado")
    private List<Responsavel> responsavelList;

    public Estado() {
    }

    public Estado(Integer idestado) {
        this.idestado = idestado;
    }

    public Estado(Integer idestado, String descEstado) {
        this.idestado = idestado;
        this.descEstado = descEstado;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public List<Cidade> getCidadeList() {
        return cidadeList;
    }

    public void setCidadeList(List<Cidade> cidadeList) {
        this.cidadeList = cidadeList;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Responsavel> getResponsavelList() {
        return responsavelList;
    }

    public void setResponsavelList(List<Responsavel> responsavelList) {
        this.responsavelList = responsavelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestado != null ? idestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.idestado == null && other.idestado != null) || (this.idestado != null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.Estado[ idestado=" + idestado + " ]";
    }
    
}
