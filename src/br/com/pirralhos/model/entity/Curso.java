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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcurso")
    private Integer idcurso;
    @Basic(optional = false)
    @Column(name = "desc_curso")
    private String descCurso;
    @Basic(optional = false)
    @Column(name = "qtd_aluno")
    private int qtdAluno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCurso")
    private List<Serie> serieList;

    public Curso() {
    }

    public Curso(Integer idcurso) {
        this.idcurso = idcurso;
    }

    public Curso(Integer idcurso, String descCurso, int qtdAluno) {
        this.idcurso = idcurso;
        this.descCurso = descCurso;
        this.qtdAluno = qtdAluno;
    }

    public Integer getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }

    public String getDescCurso() {
        return descCurso;
    }

    public void setDescCurso(String descCurso) {
        this.descCurso = descCurso;
    }

    public int getQtdAluno() {
        return qtdAluno;
    }

    public void setQtdAluno(int qtdAluno) {
        this.qtdAluno = qtdAluno;
    }

    public List<Serie> getSerieList() {
        return serieList;
    }

    public void setSerieList(List<Serie> serieList) {
        this.serieList = serieList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcurso != null ? idcurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idcurso == null && other.idcurso != null) || (this.idcurso != null && !this.idcurso.equals(other.idcurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.Curso[ idcurso=" + idcurso + " ]";
    }
    
}
