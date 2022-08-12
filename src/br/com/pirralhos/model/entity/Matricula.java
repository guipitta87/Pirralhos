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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "matricula")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idmatricula")
	private Integer idmatricula;
	@Basic(optional = false)
	@Column(name = "ano_letivo")
	private int anoLetivo;
	@Basic(optional = false)
	@Column(name = "atest_medico")
	private Boolean atestMedico;
	@Basic(optional = false)
	@Column(name = "copia_cert_nasc")
	private Boolean copiaCertNasc;
	@Basic(optional = false)
	@Column(name = "data_matricula")
	@Temporal(TemporalType.DATE)
	private Date dataMatricula;
	@Basic(optional = false)
	@Column(name = "foto_3x4")
	private Boolean foto3x4;
	@Basic(optional = false)
	@Column(name = "caminho_atest_med")
	private String caminhoAtestMed;
	@JoinColumn(name = "fk_ra", referencedColumnName = "ra")
	@ManyToOne(optional = false)
	private Aluno fkRa;
	@JoinColumn(name = "fk_turma", referencedColumnName = "idturma")
	@ManyToOne(optional = false)
	private Turma fkTurma;

	public Matricula() {
	}

	public Matricula(Integer idmatricula) {
		this.idmatricula = idmatricula;
	}

	public Matricula(Integer idmatricula, int anoLetivo, Boolean atestMedico,
			Boolean copiaCertNasc, Date dataMatricula, Boolean foto3x4) {
		this.idmatricula = idmatricula;
		this.anoLetivo = anoLetivo;
		this.atestMedico = atestMedico;
		this.copiaCertNasc = copiaCertNasc;
		this.dataMatricula = dataMatricula;
		this.foto3x4 = foto3x4;
	}

	public Integer getIdmatricula() {
		return idmatricula;
	}

	public void setIdmatricula(Integer idmatricula) {
		this.idmatricula = idmatricula;
	}

	public int getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(int anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public Boolean getAtestMedico() {
		return atestMedico;
	}

	public void setAtestMedico(Boolean atestMedico) {
		this.atestMedico = atestMedico;
	}

	public Boolean getCopiaCertNasc() {
		return copiaCertNasc;
	}

	public void setCopiaCertNasc(Boolean copiaCertNasc) {
		this.copiaCertNasc = copiaCertNasc;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Boolean getFoto3x4() {
		return foto3x4;
	}

	public void setFoto3x4(Boolean foto3x4) {
		this.foto3x4 = foto3x4;
	}

	public Aluno getFkRa() {
		return fkRa;
	}

	public void setFkRa(Aluno fkRa) {
		this.fkRa = fkRa;
	}

	public Turma getFkTurma() {
		return fkTurma;
	}

	public void setFkTurma(Turma fkTurma) {
		this.fkTurma = fkTurma;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idmatricula != null ? idmatricula.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Matricula)) {
			return false;
		}
		Matricula other = (Matricula) object;
		if ((this.idmatricula == null && other.idmatricula != null)
				|| (this.idmatricula != null && !this.idmatricula
						.equals(other.idmatricula))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.pirralhos.model.entity.Matricula[ idmatricula="
				+ idmatricula + " ]";
	}

	public String getCaminhoAtestMed() {
		return caminhoAtestMed;
	}

	public void setCaminhoAtestMed(String caminhoAtestMed) {
		this.caminhoAtestMed = caminhoAtestMed;
	}

	
}
