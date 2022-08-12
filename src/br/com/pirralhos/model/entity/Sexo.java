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
@Table(name = "sexo")
public class Sexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsexo")
    private Integer idsexo;
    @Basic(optional = false)
    @Column(name = "desc_sexo")
    private String descSexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSexo")
    private List<Autorizado> autorizadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSexo")
    private List<Aluno> alunoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSexo")
    private List<Professor> professorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSexo")
    private List<Responsavel> responsavelList;

    public Sexo() {
    }

    public Sexo(Integer idsexo) {
        this.idsexo = idsexo;
    }

    public Sexo(Integer idsexo, String descSexo) {
        this.idsexo = idsexo;
        this.descSexo = descSexo;
    }

    public Integer getIdsexo() {
        return idsexo;
    }

    public void setIdsexo(Integer idsexo) {
        this.idsexo = idsexo;
    }

    public String getDescSexo() {
        return descSexo;
    }

    public void setDescSexo(String descSexo) {
        this.descSexo = descSexo;
    }

    public List<Autorizado> getAutorizadoList() {
        return autorizadoList;
    }

    public void setAutorizadoList(List<Autorizado> autorizadoList) {
        this.autorizadoList = autorizadoList;
    }

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
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
        hash += (idsexo != null ? idsexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexo)) {
            return false;
        }
        Sexo other = (Sexo) object;
        if ((this.idsexo == null && other.idsexo != null) || (this.idsexo != null && !this.idsexo.equals(other.idsexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.Sexo[ idsexo=" + idsexo + " ]";
    }
    
}
