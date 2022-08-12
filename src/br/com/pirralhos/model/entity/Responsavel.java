package br.com.pirralhos.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "responsavel")
public class Responsavel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idresponsavel")
	private Integer idresponsavel;
	@Basic(optional = false)
	@Column(name = "bairro")
	private String bairro;
	@Column(name = "cep")
	private Integer cep;
	@Column(name = "complemento")
	private String complemento;
	@Basic(optional = false)
	@Column(name = "cpf")
	private String cpf;
	@Basic(optional = false)
	@Column(name = "data_nasc")
	@Temporal(TemporalType.DATE)
	private Date dataNasc;
	@Basic(optional = false)
	@Column(name = "email")
	private String email;
	@Basic(optional = false)
	@Column(name = "endereco_comercial")
	private String enderecoComercial;
	@Basic(optional = false)
	@Column(name = "tipo_responsavel")
	private String tipoResponsavel;
	@Basic(optional = false)
	@Column(name = "fk_perfil")
	private int fkPerfil;
	@Basic(optional = false)
	@Column(name = "nome")
	private String nome;
	@Basic(optional = false)
	@Column(name = "nome_empresa")
	private String nomeEmpresa;
	@Basic(optional = false)
	@Column(name = "numero")
	private String numero;
	@Basic(optional = false)
	@Column(name = "telefone_celular")
	private String telefoneCelular;
	@Basic(optional = true)
	@Column(name = "telefone_comercial")
	private String telefoneComercial;
	@Basic(optional = false)
	@Column(name = "telefone_residencial")
	private String telefoneResidencial;
	@ManyToMany(mappedBy = "responsavelList")
	private List<Aluno> alunoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkResponsavel")
	private List<Recado> recadoList;
	@JoinColumn(name = "fk_usuario", referencedColumnName = "idusuario")
	@ManyToOne(optional = true)
	private Usuario fkUsuario;
	@JoinColumn(name = "fk_estado", referencedColumnName = "idestado")
	@ManyToOne(optional = false)
	private Estado fkEstado;
	@JoinColumn(name = "fk_cidade", referencedColumnName = "idcidade")
	@ManyToOne(optional = false)
	private Cidade fkCidade;
	@JoinColumn(name = "fk_sexo", referencedColumnName = "idsexo")
	@ManyToOne(optional = false)
	private Sexo fkSexo;

	public Responsavel() {
	}

	public Responsavel(Integer idresponsavel) {
		this.idresponsavel = idresponsavel;
	}

	public Responsavel(Integer idresponsavel, String bairro, String cpf,
			Date dataNasc, String email, String enderecoComercial,
			int fkPerfil, String nome, String nomeEmpresa, String numero,
			String telefoneCelular, String telefoneResidencial) {
		this.idresponsavel = idresponsavel;
		this.bairro = bairro;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.email = email;
		this.enderecoComercial = enderecoComercial;
		this.fkPerfil = fkPerfil;
		this.nome = nome;
		this.nomeEmpresa = nomeEmpresa;
		this.numero = numero;
		this.telefoneCelular = telefoneCelular;
		this.telefoneResidencial = telefoneResidencial;
	}

	public Integer getIdresponsavel() {
		return idresponsavel;
	}

	public void setIdresponsavel(Integer idresponsavel) {
		this.idresponsavel = idresponsavel;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnderecoComercial() {
		return enderecoComercial;
	}

	public void setEnderecoComercial(String enderecoComercial) {
		this.enderecoComercial = enderecoComercial;
	}

	public int getFkPerfil() {
		return fkPerfil;
	}

	public void setFkPerfil(int fkPerfil) {
		this.fkPerfil = fkPerfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public List<Aluno> getAlunoList() {
		return alunoList;
	}

	public void setAlunoList(List<Aluno> alunoList) {
		this.alunoList = alunoList;
	}

	public List<Recado> getRecadoList() {
		return recadoList;
	}

	public void setRecadoList(List<Recado> recadoList) {
		this.recadoList = recadoList;
	}

	public Usuario getFkUsuario() {
		return fkUsuario;
	}

	public void setFkUsuario(Usuario fkUsuario) {
		this.fkUsuario = fkUsuario;
	}

	public Estado getFkEstado() {
		return fkEstado;
	}

	public void setFkEstado(Estado fkEstado) {
		this.fkEstado = fkEstado;
	}

	public Cidade getFkCidade() {
		return fkCidade;
	}

	public void setFkCidade(Cidade fkCidade) {
		this.fkCidade = fkCidade;
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
		hash += (idresponsavel != null ? idresponsavel.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Responsavel)) {
			return false;
		}
		Responsavel other = (Responsavel) object;
		if ((this.idresponsavel == null && other.idresponsavel != null)
				|| (this.idresponsavel != null && !this.idresponsavel
						.equals(other.idresponsavel))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.pirralhos.model.entity.Responsavel[ idresponsavel="
				+ idresponsavel + " ]";
	}

	public String getTipoResponsavel() {
		return tipoResponsavel;
	}

	public void setTipoResponsavel(String tipoResponsavel) {
		this.tipoResponsavel = tipoResponsavel;
	}

	public String getTelefoneComercial() {
		return telefoneComercial;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

}
