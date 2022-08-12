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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idcidade")
	private Integer idcidade;
	@Column(name = "desc_cidade")
	private String descCidade;
	@JoinColumn(name = "idestado", referencedColumnName = "idestado")
	@ManyToOne(optional = false)
	private Estado idestado;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCidade")
	private List<Professor> professorList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkCidade")
	private List<Responsavel> responsavelList;

	public Cidade() {
	}

	public Cidade(Integer idcidade) {
		this.idcidade = idcidade;
	}

	public Integer getIdcidade() {
		return idcidade;
	}

	public void setIdcidade(Integer idcidade) {
		this.idcidade = idcidade;
	}

	public String getDescCidade() {
		return descCidade;
	}

	public void setDescCidade(String descCidade) {
		this.descCidade = descCidade;
	}

	public Estado getIdestado() {
		return idestado;
	}

	public void setIdestado(Estado idestado) {
		this.idestado = idestado;
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
		hash += (idcidade != null ? idcidade.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Cidade)) {
			return false;
		}
		Cidade other = (Cidade) object;
		if ((this.idcidade == null && other.idcidade != null)
				|| (this.idcidade != null && !this.idcidade
						.equals(other.idcidade))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.pirralhos.model.entity.Cidade[ idcidade=" + idcidade
				+ " ]";
	}

}
