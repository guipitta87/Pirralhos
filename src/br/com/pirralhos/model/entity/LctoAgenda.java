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
@Table(name = "lcto_agenda")
public class LctoAgenda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlctoagenda")
    private Integer idlctoagenda;
    @Basic(optional = false)
    @Column(name = "desc_lancamento")
    private String descLancamento;
    @Basic(optional = false)
    @Column(name = "data_lancamento")
    private Date dataLancamento;
    @JoinColumn(name = "fk_turma", referencedColumnName = "idturma")
    @ManyToOne(optional = false)
    private Turma fkTurma;

    public LctoAgenda() {
    }

    public LctoAgenda(Integer idlctoagenda) {
        this.idlctoagenda = idlctoagenda;
    }

    public LctoAgenda(Integer idlctoagenda, String descLancamento) {
        this.idlctoagenda = idlctoagenda;
        this.descLancamento = descLancamento;
    }

    public Integer getIdlctoagenda() {
        return idlctoagenda;
    }

    public void setIdlctoagenda(Integer idlctoagenda) {
        this.idlctoagenda = idlctoagenda;
    }

    public String getDescLancamento() {
        return descLancamento;
    }

    public void setDescLancamento(String descLancamento) {
        this.descLancamento = descLancamento;
    }

    public Turma getFkTurma() {
        return fkTurma;
    }

    public void setFkTurma(Turma fkTurma) {
        this.fkTurma = fkTurma;
    }
    
    
    public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idlctoagenda != null ? idlctoagenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LctoAgenda)) {
            return false;
        }
        LctoAgenda other = (LctoAgenda) object;
        if ((this.idlctoagenda == null && other.idlctoagenda != null) || (this.idlctoagenda != null && !this.idlctoagenda.equals(other.idlctoagenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.LctoAgenda[ idlctoagenda=" + idlctoagenda + " ]";
    }
    
}
