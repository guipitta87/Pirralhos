package br.com.pirralhos.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "turma")
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idturma")
    private Integer idturma;
    @Basic(optional = false)
    @Column(name = "desc_turma")
    private String descTurma;
    @JoinTable(name = "turma_professor", joinColumns = {
        @JoinColumn(name = "idturma", referencedColumnName = "idturma")}, inverseJoinColumns = {
        @JoinColumn(name = "idprofessor", referencedColumnName = "idprofessor")})
    @ManyToMany(fetch=FetchType.EAGER)
    private List<Professor> professorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTurma")
    @LazyCollection(LazyCollectionOption.FALSE)    
    private List<LctoAgenda> lctoAgendaList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkTurma")
    @LazyCollection(LazyCollectionOption.FALSE)    
    private List<Matricula> matriculaList;
    @JoinColumn(name = "fk_periodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodo fkPeriodo;
    @JoinColumn(name = "fk_serie", referencedColumnName = "idserie")
    @ManyToOne(optional = false)
    private Serie fkSerie;

    public Turma() {
    }

    public Turma(Integer idturma) {
        this.idturma = idturma;
    }

    public Turma(Integer idturma, String descTurma) {
        this.idturma = idturma;
        this.descTurma = descTurma;
    }

    public Integer getIdturma() {
        return idturma;
    }

    public void setIdturma(Integer idturma) {
        this.idturma = idturma;
    }

    public String getDescTurma() {
        return descTurma;
    }

    public void setDescTurma(String descTurma) {
        this.descTurma = descTurma;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<LctoAgenda> getLctoAgendaList() {
        return lctoAgendaList;
    }

    public void setLctoAgendaList(List<LctoAgenda> lctoAgendaList) {
        this.lctoAgendaList = lctoAgendaList;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public Periodo getFkPeriodo() {
        return fkPeriodo;
    }

    public void setFkPeriodo(Periodo fkPeriodo) {
        this.fkPeriodo = fkPeriodo;
    }

    public Serie getFkSerie() {
        return fkSerie;
    }

    public void setFkSerie(Serie fkSerie) {
        this.fkSerie = fkSerie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idturma != null ? idturma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.idturma == null && other.idturma != null) || (this.idturma != null && !this.idturma.equals(other.idturma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.Turma[ idturma=" + idturma + " ]";
    }
    
    public int getQtdAlunos()
    {
    	return matriculaList.size();
    }
}
