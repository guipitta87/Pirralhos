package br.com.pirralhos.model.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ra")
	private Integer ra;
	@Basic(optional = false)
	@Column(name = "data_nasc")
	@Temporal(TemporalType.DATE)
	private Date dataNasc;
	@Column(name = "dcc_catapora")
	private Boolean dccCatapora;
	@Column(name = "dcc_caxumba")
	private Boolean dccCaxumba;
	@Column(name = "dcc_coqueluxe")
	private Boolean dccCoqueluxe;
	@Column(name = "dcc_escarlatina")
	private Boolean dccEscarlatina;
	@Column(name = "dcc_outras")
	private String dccOutras;
	@Column(name = "dcc_rubeola")
	private Boolean dccRubeola;
	@Column(name = "dcr_flg_eplepsia")
	private Boolean dcrFlgEplepsia;
	@Column(name = "dcc_sarampo")
	private Boolean dccSarampo;
	@Column(name = "dcr_flg_asma")
	private Boolean dcrFlgAsma;
	@Column(name = "dcr_flg_bronquite")
	private Boolean dcrFlgBronquite;
	@Column(name = "dcr_flg_diabete")
	private Boolean dcrFlgDiabete;
	@Column(name = "dcr_flg_hipertensao")
	private Boolean dcrFlgHipertensao;
	@Column(name = "dcr_flg_reumatismo")
	private Boolean dcrFlgReumatismo;
	@Column(name = "dcr_outras")
	private String dcrOutras;
	@Column(name = "def_auditiva")
	private Boolean defAuditiva;
	@Column(name = "def_fala")
	private Boolean defFala;
	@Column(name = "def_fisica")
	private Boolean defFisica;
	@Column(name = "def_outras")
	private String defOutras;
	@Column(name = "def_visual")
	private Boolean defVisual;
	@Column(name = "foto")
	private String foto;
	@Column(name = "fs_alergias")
	private String fsAlergias;
	@Column(name = "fs_febre")
	private String fsFebre;
	@Column(name = "fs_tratamento_medico")
	private String fsTratamentoMedico;
	@Column(name = "hosp_emergencia")
	private String hospEmergencia;
	@Basic(optional = false)
	@Column(name = "nome")
	private String nome;
	@Column(name = "nome_emergencia")
	private String nomeEmergencia;
	@Column(name = "planod_saude")
	private String planodSaude;
	@Column(name = "tel_emergencia")
	private String telEmergencia;
	@Column(name = "tel_hosp")
	private String telHosp;
	@JoinTable(name = "aluno_responsavel", joinColumns = { @JoinColumn(name = "idaluno", referencedColumnName = "ra") }, inverseJoinColumns = { @JoinColumn(name = "idresponsavel", referencedColumnName = "idresponsavel") })
	@ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
	private List<Responsavel> responsavelList;
	@ManyToMany(mappedBy = "alunoList")
	private List<Autorizado> autorizadoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAluno")
	private List<Recado> recadoList;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "fkRa")
	private List<Matricula> matriculaList;
	@JoinColumn(name = "fk_sexo", referencedColumnName = "idsexo")
	@ManyToOne(optional = false)
	private Sexo fkSexo;

	public Aluno() {
	}

	public Aluno(Integer ra) {
		this.ra = ra;
	}

	public Aluno(Integer ra, Date dataNasc, String nome) {
		this.ra = ra;
		this.dataNasc = dataNasc;
		this.nome = nome;
	}

	public Integer getRa() {
		return ra;
	}

	public void setRa(Integer ra) {
		this.ra = ra;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Boolean getDccCatapora() {
		return dccCatapora;
	}

	public void setDccCatapora(Boolean dccCatapora) {
		this.dccCatapora = dccCatapora;
	}

	public Boolean getDccCaxumba() {
		return dccCaxumba;
	}

	public void setDccCaxumba(Boolean dccCaxumba) {
		this.dccCaxumba = dccCaxumba;
	}

	public Boolean getDccCoqueluxe() {
		return dccCoqueluxe;
	}

	public void setDccCoqueluxe(Boolean dccCoqueluxe) {
		this.dccCoqueluxe = dccCoqueluxe;
	}

	public Boolean getDccEscarlatina() {
		return dccEscarlatina;
	}

	public void setDccEscarlatina(Boolean dccEscarlatina) {
		this.dccEscarlatina = dccEscarlatina;
	}

	public String getDccOutras() {
		return dccOutras;
	}

	public void setDccOutras(String dccOutras) {
		this.dccOutras = dccOutras;
	}

	public Boolean getDccRubeola() {
		return dccRubeola;
	}

	public void setDccRubeola(Boolean dccRubeola) {
		this.dccRubeola = dccRubeola;
	}

	public Boolean getDccSarampo() {
		return dccSarampo;
	}

	public void setDccSarampo(Boolean dccSarampo) {
		this.dccSarampo = dccSarampo;
	}

	public Boolean getDcrFlgAsma() {
		return dcrFlgAsma;
	}

	public void setDcrFlgAsma(Boolean dcrFlgAsma) {
		this.dcrFlgAsma = dcrFlgAsma;
	}

	public Boolean getDcrFlgBronquite() {
		return dcrFlgBronquite;
	}

	public void setDcrFlgBronquite(Boolean dcrFlgBronquite) {
		this.dcrFlgBronquite = dcrFlgBronquite;
	}

	public Boolean getDcrFlgDiabete() {
		return dcrFlgDiabete;
	}

	public void setDcrFlgDiabete(Boolean dcrFlgDiabete) {
		this.dcrFlgDiabete = dcrFlgDiabete;
	}

	public Boolean getDcrFlgHipertensao() {
		return dcrFlgHipertensao;
	}

	public void setDcrFlgHipertensao(Boolean dcrFlgHipertensao) {
		this.dcrFlgHipertensao = dcrFlgHipertensao;
	}

	public Boolean getDcrFlgReumatismo() {
		return dcrFlgReumatismo;
	}

	public void setDcrFlgReumatismo(Boolean dcrFlgReumatismo) {
		this.dcrFlgReumatismo = dcrFlgReumatismo;
	}

	public String getDcrOutras() {
		return dcrOutras;
	}

	public void setDcrOutras(String dcrOutras) {
		this.dcrOutras = dcrOutras;
	}

	public Boolean getDefAuditiva() {
		return defAuditiva;
	}

	public void setDefAuditiva(Boolean defAuditiva) {
		this.defAuditiva = defAuditiva;
	}

	public Boolean getDefFala() {
		return defFala;
	}

	public void setDefFala(Boolean defFala) {
		this.defFala = defFala;
	}

	public Boolean getDefFisica() {
		return defFisica;
	}

	public void setDefFisica(Boolean defFisica) {
		this.defFisica = defFisica;
	}

	public String getDefOutras() {
		return defOutras;
	}

	public void setDefOutras(String defOutras) {
		this.defOutras = defOutras;
	}

	public Boolean getDefVisual() {
		return defVisual;
	}

	public void setDefVisual(Boolean defVisual) {
		this.defVisual = defVisual;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFsAlergias() {
		return fsAlergias;
	}

	public void setFsAlergias(String fsAlergias) {
		this.fsAlergias = fsAlergias;
	}

	public String getFsFebre() {
		return fsFebre;
	}

	public void setFsFebre(String fsFebre) {
		this.fsFebre = fsFebre;
	}

	public String getFsTratamentoMedico() {
		return fsTratamentoMedico;
	}

	public void setFsTratamentoMedico(String fsTratamentoMedico) {
		this.fsTratamentoMedico = fsTratamentoMedico;
	}

	public String getHospEmergencia() {
		return hospEmergencia;
	}

	public void setHospEmergencia(String hospEmergencia) {
		this.hospEmergencia = hospEmergencia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeEmergencia() {
		return nomeEmergencia;
	}

	public void setNomeEmergencia(String nomeEmergencia) {
		this.nomeEmergencia = nomeEmergencia;
	}

	public String getPlanodSaude() {
		return planodSaude;
	}

	public void setPlanodSaude(String planodSaude) {
		this.planodSaude = planodSaude;
	}

	public String getTelEmergencia() {
		return telEmergencia;
	}

	public void setTelEmergencia(String telEmergencia) {
		this.telEmergencia = telEmergencia;
	}

	public String getTelHosp() {
		return telHosp;
	}

	public void setTelHosp(String telHosp) {
		this.telHosp = telHosp;
	}

	public List<Responsavel> getResponsavelList() {
		return responsavelList;
	}

	public void setResponsavelList(List<Responsavel> responsavelList) {
		this.responsavelList = responsavelList;
	}

	public List<Autorizado> getAutorizadoList() {
		return autorizadoList;
	}

	public void setAutorizadoList(List<Autorizado> autorizadoList) {
		this.autorizadoList = autorizadoList;
	}

	public List<Recado> getRecadoList() {
		return recadoList;
	}

	public void setRecadoList(List<Recado> recadoList) {
		this.recadoList = recadoList;
	}

	public List<Matricula> getMatriculaList() {
		return matriculaList;
	}

	public void setMatriculaList(List<Matricula> matriculaList) {
		this.matriculaList = matriculaList;
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
		hash += (ra != null ? ra.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Aluno)) {
			return false;
		}
		Aluno other = (Aluno) object;
		if ((this.ra == null && other.ra != null)
				|| (this.ra != null && !this.ra.equals(other.ra))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.pirralhos.model.entity.Aluno[ ra=" + ra + " ]";
	}

	public Boolean getDcrFlgEplepsia() {
		return dcrFlgEplepsia;
	}

	public void setDcrFlgEplepsia(Boolean dcrFlgEplepsia) {
		this.dcrFlgEplepsia = dcrFlgEplepsia;
	}
	public Matricula getMatriculaAtual()
	{
		return this.matriculaList !=null && this.matriculaList.size() >0? this.matriculaList.get(this.matriculaList.size()-1):null; 
	}
}
