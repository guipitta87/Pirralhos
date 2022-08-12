package br.com.pirralhos.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
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
import javax.persistence.Table;
@Entity
@Table(name = "camera")
public class Camera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcamera")
    private Integer idcamera;
    @Basic(optional = false)
    @Column(name = "desc_camera")
    private String descCamera;
    @Basic(optional = false)
    @Column(name = "nome_camera")
    private String nomeCamera;
    
    @JoinTable(name = "camera_perfil", joinColumns = {
        @JoinColumn(name = "idcamera", referencedColumnName = "idcamera")}, inverseJoinColumns = {
        @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")})
    @ManyToMany(fetch=FetchType.EAGER)
    private List<Perfil> perfilList;

    public Camera() {
    }

    public Camera(Integer idcamera) {
        this.idcamera = idcamera;
    }

    public Camera(Integer idcamera, String descCamera, String nomeCamera) {
        this.idcamera = idcamera;
        this.descCamera = descCamera;
        this.nomeCamera = nomeCamera;
    }

    public Integer getIdcamera() {
        return idcamera;
    }

    public void setIdcamera(Integer idcamera) {
        this.idcamera = idcamera;
    }

    public String getDescCamera() {
        return descCamera;
    }

    public void setDescCamera(String descCamera) {
        this.descCamera = descCamera;
    }

    public String getNomeCamera() {
        return nomeCamera;
    }

    public void setNomeCamera(String nomeCamera) {
        this.nomeCamera = nomeCamera;
    }

    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcamera != null ? idcamera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camera)) {
            return false;
        }
        Camera other = (Camera) object;
        if ((this.idcamera == null && other.idcamera != null) || (this.idcamera != null && !this.idcamera.equals(other.idcamera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pirralhos.model.entity.Camera[ idcamera=" + idcamera + " ]";
    }

	public String getCaminhoCamera()
	{
		return  FacesContext.getCurrentInstance().getExternalContext().getRealPath("cameras")+"\\camera" + this.getIdcamera() + ".asx";
	}
}
