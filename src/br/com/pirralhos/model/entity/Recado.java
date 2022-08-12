package br.com.pirralhos.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recado")
public class Recado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
    @Column(name = "idrecado")
    private Integer idrecado;
    @Basic(optional = false)
    @Column(name = "desc_recado")
    private String descRecado;
    @Basic(optional = false)
    @Column(name = "quem_enviou")
    private Character quemEnviou;
    @Basic(optional = false)
    @Column(name = "data_recado")
    private Date dataRecado;
    @JoinColumn(name = "fk_professor", referencedColumnName = "idprofessor")
    @ManyToOne(optional = false)
    private Professor fkProfessor;
    @JoinColumn(name = "fk_responsavel", referencedColumnName = "idresponsavel")
    @ManyToOne(optional = false)
    private Responsavel fkResponsavel;
    @JoinColumn(name = "fk_aluno", referencedColumnName = "ra")
    @ManyToOne(optional = false)
    private Aluno fkAluno;

    public Recado() {
    }

    public Recado(Integer idrecado) {
        this.idrecado = idrecado;
    }

    public Recado(Integer idrecado, String descRecado) {
        this.idrecado = idrecado;
        this.descRecado = descRecado;
    }

    public Integer getIdrecado() {
        return idrecado;
    }

    public void setIdrecado(Integer idrecado) {
        this.idrecado = idrecado;
    }

    public String getDescRecado() {
        return descRecado;
    }

    public void setDescRecado(String descRecado) {
        this.descRecado = descRecado;
    }

    public Responsavel getFkResponsavel() {
        return fkResponsavel;
    }

    public void setFkResponsavel(Responsavel fkResponsavel) {
        this.fkResponsavel = fkResponsavel;
    }

    public Aluno getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(Aluno fkAluno) {
        this.fkAluno = fkAluno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecado != null ? idrecado.hashCode() : 0);
        return hash;
    }

    public Character getQuemEnviou() {
		return quemEnviou;
	}

	public void setQuemEnviou(Character quemEnviou) {
		this.quemEnviou = quemEnviou;
	}
	
	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recado)) {
            return false;
        }
        Recado other = (Recado) object;
        if ((this.idrecado == null && other.idrecado != null) || (this.idrecado != null && !this.idrecado.equals(other.idrecado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.Recado[ idrecado=" + idrecado + " ]";
    }

	public Date getDataRecado() {
		return dataRecado;
	}

	public void setDataRecado(Date dataRecado) {
		this.dataRecado = dataRecado;
	}

	public Professor getFkProfessor() {
		return fkProfessor;
	}

	public void setFkProfessor(Professor fkProfessor) {
		this.fkProfessor = fkProfessor;
	}
	
}
