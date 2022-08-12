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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "serie")
@NamedQueries({
    @NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s")})
public class Serie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idserie")
    private Integer idserie;
    @Basic(optional = false)
    @Column(name = "desc_serie")
    private String descSerie;
    @Basic(optional = false)
    @Column(name = "idade_minima_meses")
    private int idadeMinimaMeses;
    @Basic(optional = false)
    @Column(name = "idade_maxima_meses")
    private int idadeMaximaMeses;
    @Basic(optional = false)
    @Column(name = "qtd_min_prof")
    private int qtdMinimaProfessores;
    @Basic(optional = false)
    @Column(name = "qtd_max_prof")
    private int qtdMaximaProfessores;
    @Basic(optional = false)
    @Column(name = "qtd_alunos_prof")
    private int qtdAlunosProf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSerie")
    private List<Turma> turmaList;
    @JoinColumn(name = "fk_curso", referencedColumnName = "idcurso")
    @ManyToOne(optional = false)
    private Curso fkCurso;

    public Serie() {
    }

    public Serie(Integer idserie) {
        this.idserie = idserie;
    }

    public Serie(Integer idserie, String descSerie, int qtdAlunosProf) {
        this.idserie = idserie;
        this.descSerie = descSerie;
        this.qtdAlunosProf = qtdAlunosProf;
    }

    public Integer getIdserie() {
        return idserie;
    }

    public void setIdserie(Integer idserie) {
        this.idserie = idserie;
    }

    public String getDescSerie() {
        return descSerie;
    }

    public void setDescSerie(String descSerie) {
        this.descSerie = descSerie;
    }

    public int getQtdAlunosProf() {
        return qtdAlunosProf;
    }

    public void setQtdAlunosProf(int qtdAlunosProf) {
        this.qtdAlunosProf = qtdAlunosProf;
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        this.turmaList = turmaList;
    }

    public Curso getFkCurso() {
        return fkCurso;
    }

    public void setFkCurso(Curso fkCurso) {
        this.fkCurso = fkCurso;
    }

    public int getIdadeMinimaMeses() {
		return idadeMinimaMeses;
	}

	public void setIdadeMinimaMeses(int idadeMinimaMeses) {
		this.idadeMinimaMeses = idadeMinimaMeses;
	}

	public int getIdadeMaximaMeses() {
		return idadeMaximaMeses;
	}

	public void setIdadeMaximaMeses(int idadeMaximaMeses) {
		this.idadeMaximaMeses = idadeMaximaMeses;
	}
	
	public int getQtdMinimaProfessores() {
		return qtdMinimaProfessores;
	}

	public void setQtdMinimaProfessores(int qtdMinimaProfessores) {
		this.qtdMinimaProfessores = qtdMinimaProfessores;
	}

	public int getQtdMaximaProfessores() {
		return qtdMaximaProfessores;
	}

	public void setQtdMaximaProfessores(int qtdMaximaProfessores) {
		this.qtdMaximaProfessores = qtdMaximaProfessores;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idserie != null ? idserie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serie)) {
            return false;
        }
        Serie other = (Serie) object;
        if ((this.idserie == null && other.idserie != null) || (this.idserie != null && !this.idserie.equals(other.idserie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.Serie[ idserie=" + idserie + " ]";
    }
    
}
