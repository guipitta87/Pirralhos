package br.com.pirralhos.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id 
    @Basic(optional = false)
    @Column(name = "idusuario")
    private String idusuario;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Column(name = "trocar_senha")
    private boolean trocarSenha;
    @JoinColumn(name = "fk_perfil", referencedColumnName = "idperfil")
    @ManyToOne(optional = false)
    private Perfil fkPerfil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuario")
    private List<Professor> professorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuario")
    private List<Responsavel> responsavelList;

    public Usuario() {
    }

    public Usuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(String idusuario, String senha) {
        this.idusuario = idusuario;
        this.senha = senha;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getFkPerfil() {
        return fkPerfil;
    }

    public void setFkPerfil(Perfil fkPerfil) {
        this.fkPerfil = fkPerfil;
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

    public boolean getTrocarSenha() {
		return trocarSenha;
	}

	public void setTrocarSenha(boolean trocarSenha) {
		this.trocarSenha = trocarSenha;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
