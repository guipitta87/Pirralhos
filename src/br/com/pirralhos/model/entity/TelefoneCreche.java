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
@Table(name = "telefone_creche")
public class TelefoneCreche implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtelefone_creche")
    private Integer idtelefoneCreche;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTelefoneCreche")
    private List<Professor> professorList;

    public TelefoneCreche() {
    }

    public TelefoneCreche(Integer idtelefoneCreche) {
        this.idtelefoneCreche = idtelefoneCreche;
    }

    public TelefoneCreche(Integer idtelefoneCreche, String numero) {
        this.idtelefoneCreche = idtelefoneCreche;
        this.numero = numero;
    }

    public Integer getIdtelefoneCreche() {
        return idtelefoneCreche;
    }

    public void setIdtelefoneCreche(Integer idtelefoneCreche) {
        this.idtelefoneCreche = idtelefoneCreche;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
        hash += (idtelefoneCreche != null ? idtelefoneCreche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TelefoneCreche)) {
            return false;
        }
        TelefoneCreche other = (TelefoneCreche) object;
        if ((this.idtelefoneCreche == null && other.idtelefoneCreche != null) || (this.idtelefoneCreche != null && !this.idtelefoneCreche.equals(other.idtelefoneCreche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.TelefoneCreche[ idtelefoneCreche=" + idtelefoneCreche + " ]";
    }
    
}
