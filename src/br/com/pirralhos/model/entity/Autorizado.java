package br.com.pirralhos.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "autorizado")
public class Autorizado implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idautorizado")
	private Integer idautorizado;
	@Column(name = "estado_civil")
	private String estadoCivil;
	@Column(name = "telefone_residencial")
	private String telefoneResidencial;
	@Column(name = "telefone_celular")
	private String telefoneCelular;
	@Column(name = "cpf")
	private String cpf;
	@Basic(optional = false)
	@Column(name = "email")
	private String email;
	@Basic(optional = false)
	@Column(name = "nome")
	private String nome;
	@Column(name = "periodo_de")
	private Date periodoDe;
	@Column(name = "periodo_ate")
	private Date periodoAte;
	@JoinTable(name = "aluno_autorizado", joinColumns = { @JoinColumn(name = "idautorizado", referencedColumnName = "idautorizado") }, inverseJoinColumns = { @JoinColumn(name = "idaluno", referencedColumnName = "ra") })
	@ManyToMany
	private List<Aluno> alunoList;
	@JoinColumn(name = "fk_sexo", referencedColumnName = "idsexo")
	@ManyToOne(optional = false)
	private Sexo fkSexo;

	public Autorizado() {
	}

	public Autorizado(Integer idautorizado) {
		this.idautorizado = idautorizado;
	}

	public Integer getIdautorizado() {
		return idautorizado;
	}
	public void setIdautorizado(Integer idautorizado) {
		this.idautorizado = idautorizado;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getAlunoList() {
		return alunoList;
	}

	public void setAlunoList(List<Aluno> alunoList) {
		this.alunoList = alunoList;
	}

	public Sexo getFkSexo() {
		return fkSexo;
	}

	public void setFkSexo(Sexo fkSexo) {
		this.fkSexo = fkSexo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idautorizado != null ? idautorizado.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Autorizado)) {
			return false;
		}
		Autorizado other = (Autorizado) object;
		if ((this.idautorizado == null && other.idautorizado != null)
				|| (this.idautorizado != null && !this.idautorizado
						.equals(other.idautorizado))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.pirralhos.model.entity.Autorizado[ idautorizado="
				+ idautorizado + " ]";
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public Date getPeriodoDe() {
		return periodoDe;
	}

	public void setPeriodoDe(Date periodoDe) {
		this.periodoDe = periodoDe;
	}

	public Date getPeriodoAte() {
		return periodoAte;
	}

	public void setPeriodoAte(Date periodoAte) {
		this.periodoAte = periodoAte;
	}
}
