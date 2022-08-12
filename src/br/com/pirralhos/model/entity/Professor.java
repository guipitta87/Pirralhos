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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "professor")
public class Professor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprofessor")
    private Integer idprofessor;
    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @Column(name = "cep")
    private int cep;
    @Basic(optional = false)
    @Column(name = "complemento")
    private String complemento;
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "data_admissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAdmissao;
    @Basic(optional = false)
    @Column(name = "data_nasc")
    @Temporal(TemporalType.DATE)
    private Date dataNasc;
    @Column(name = "data_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaida;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "foto")
    private String foto;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "rg")
    private String rg;
    @Column(name = "telefone_residencial")
    private String telefoneResidencial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkProfessor")
	private List<Recado> recadoList;
    @ManyToMany(mappedBy = "professorList")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Turma> turmaList;
    @JoinColumn(name = "fk_telefone_creche", referencedColumnName = "idtelefone_creche")
    @ManyToOne(optional = false)
    private TelefoneCreche fkTelefoneCreche;
    @JoinColumn(name = "fk_usuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario fkUsuario;
    @JoinColumn(name = "fk_sexo", referencedColumnName = "idsexo")
    @ManyToOne(optional = false)
    private Sexo fkSexo;
    @JoinColumn(name = "fk_estado_civl", referencedColumnName = "idestado_civil")
    @ManyToOne(optional = false)
    private EstadoCivil fkEstadoCivl;
    @JoinColumn(name = "fk_estado", referencedColumnName = "idestado")
    @ManyToOne(optional = false)
    private Estado fkEstado;
    @JoinColumn(name = "fk_cidade", referencedColumnName = "idcidade")
    @ManyToOne(optional = false)
    private Cidade fkCidade;

    public Professor() {
    }

    public Professor(Integer idprofessor) {
        this.idprofessor = idprofessor;
    }

    public Professor(Integer idprofessor, String bairro, int cep, String complemento, String cpf, Date dataAdmissao, Date dataNasc, String endereco, String nome, String numero, String rg) {
        this.idprofessor = idprofessor;
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.cpf = cpf;
        this.dataAdmissao = dataAdmissao;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.nome = nome;
        this.numero = numero;
        this.rg = rg;
    }

    public Integer getIdprofessor() {
        return idprofessor;
    }

    public void setIdprofessor(Integer idprofessor) {
        this.idprofessor = idprofessor;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
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

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        this.turmaList = turmaList;
    }

    public TelefoneCreche getFkTelefoneCreche() {
        return fkTelefoneCreche;
    }

    public void setFkTelefoneCreche(TelefoneCreche fkTelefoneCreche) {
        this.fkTelefoneCreche = fkTelefoneCreche;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Sexo getFkSexo() {
        return fkSexo;
    }

    public void setFkSexo(Sexo fkSexo) {
        this.fkSexo = fkSexo;
    }

    public EstadoCivil getFkEstadoCivl() {
        return fkEstadoCivl;
    }

    public void setFkEstadoCivl(EstadoCivil fkEstadoCivl) {
        this.fkEstadoCivl = fkEstadoCivl;
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
    
    public List<Recado> getRecadoList() {
		return recadoList;
	}

	public void setRecadoList(List<Recado> recadoList) {
		this.recadoList = recadoList;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idprofessor != null ? idprofessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.idprofessor == null && other.idprofessor != null) || (this.idprofessor != null && !this.idprofessor.equals(other.idprofessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.Professor[ idprofessor=" + idprofessor + " ]";
    }
    
    
}
