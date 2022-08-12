package br.com.pirralhos.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Camera;
import br.com.pirralhos.model.entity.Perfil;
import br.com.pirralhos.view.constants.CameraConstants;
import br.com.pirralhos.view.utils.SelectItemUtils;

@ManagedBean(name = "cameraBean")
@SessionScoped
public class CameraBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Camera camera;
	private List<Camera> listaCamera;
	private List<Perfil> listaPerfil;
	private GenericDAO<Camera, Integer> cameraDAO = (GenericDAO<Camera, Integer>) BeanFactory
			.getBean("cameraDAO");
	private GenericDAO<Perfil, Integer> perfilDAO = (GenericDAO<Perfil, Integer>) BeanFactory
			.getBean("perfilDAO");
	private List<SelectItem> selectItensPerfil;
	private List<Integer> perfisSelecionados;
	private boolean alterou;
	private DualListModel<Perfil> perfis;
	@ManagedProperty(value="#{usuarioBean}")
	private UsuarioBean usuarioBean;

	
	public CameraBean() {
		atualizarTela();
		escreverArquivos();
		
	}

	public void selecionarPerfisCamera() {
		perfisSelecionados = new ArrayList<Integer>();
		for (Perfil p : getCamera().getPerfilList()) {
			perfisSelecionados.add(p.getIdperfil());
		}

	}

	public String gravar() {
		this.camera.setPerfilList(this.perfis.getTarget());
		getCameraDAO().save(getCamera());
		atualizarTela();
		escreverArquivos();
		return "consulta_camera";
	}

	public void limpar() {
		setCamera(new Camera());
		setPerfisSelecionados(new ArrayList<Integer>());
	}

	public DualListModel<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(DualListModel<Perfil> perfis) {
		this.perfis = perfis;
	}

	public void excluir() {
		getCameraDAO().delete(this.camera);
		atualizarTela();
		this.listaCamera = null;
	}

	/**
	 * Limpa os campos input e atualiza a lista de cameras cadastradas
	 */
	private void atualizarTela() {
		limpar();
		setListaCamera(getCameraDAO().listAll());
		List<Perfil> _perfis =getPerfilDAO().listAll();
		_perfis.remove(1);
		setListaPerfil(_perfis);
		setSelectItensPerfil(SelectItemUtils.getSelectItems("idperfil", "nome",
				getListaPerfil()));
		perfis = new DualListModel<Perfil>(getListaPerfil(),
				new ArrayList<Perfil>());
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public boolean isAlterou() {
		return alterou;
	}

	public void setAlterou(boolean alterou) {
		this.alterou = alterou;
	}

	public List<Camera> getListaCamera() {
		return listaCamera = this.cameraDAO.listAll();
	}

	public void setListaCamera(List<Camera> listaCamera) {
		this.listaCamera = listaCamera;
	}

	public GenericDAO<Camera, Integer> getCameraDAO() {
		return cameraDAO;
	}

	public void setCameraDAO(GenericDAO<Camera, Integer> cameraDAO) {
		this.cameraDAO = cameraDAO;
	}

	public List<Perfil> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<Perfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public GenericDAO<Perfil, Integer> getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(GenericDAO<Perfil, Integer> perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	public List<SelectItem> getSelectItensPerfil() {
		return selectItensPerfil;
	}

	public void setSelectItensPerfil(List<SelectItem> selectItensPerfil) {
		this.selectItensPerfil = selectItensPerfil;
	}

	public List<Integer> getPerfisSelecionados() {
		return perfisSelecionados;
	}

	public void setPerfisSelecionados(List<Integer> perfisSelecionados) {
		this.perfisSelecionados = perfisSelecionados;
	}

	public void atualizar() {
		this.getCamera().setPerfilList(this.getPerfis().getTarget());
		getCameraDAO().save(getCamera());
		atualizarTela();
		this.setAlterou(true);
		this.alterou = true;
	}

	public void atualizarDadosCamera() {
		perfis.setTarget(new ArrayList<Perfil>());
		perfis.getTarget().addAll(camera.getPerfilList());
		for (int i = 0; i < perfis.getTarget().size(); i++) {
			perfis.getSource().remove(this.perfis.getTarget().get(i));
		}
		this.setAlterou(false);
	}
	
	public List <Camera> getCamerasPerfil()
	{
		List<Camera> cameras = new ArrayList<Camera>();
		List<Camera> camerasAll =  getListaCamera();
		if(camerasAll !=null)
		for(Camera c : camerasAll)
		{
			for(Perfil perfil : c.getPerfilList())
			{
				if(perfil.getIdperfil().equals(usuarioBean.getPerfilUsuario()))
				{
					cameras.add(c);
				}
				
			}
		}
		return cameras;
	}
	private void escreverArquivos()
	{
		for( Camera camera : getListaCamera() )
		{
			File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("cameras/")+ "\\" +camera.getNomeCamera() + ".asx");
		
			if(!file.exists())
			{
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try
			{
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				String hostname = InetAddress.getLocalHost().getHostName();
				writer.write(String.format(CameraConstants.ASX_FILE,camera.getNomeCamera(),hostname,camera.getNomeCamera()));
				writer.flush();
			}
			catch (IOException e) {
					e.printStackTrace();
			}
		}
		
	}

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}
	public List<String> getListaArquivos()
	{
		return Arrays.asList(new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("gravacoes/")).list());
	}
	public List<String> getListaCameras()
	{
		List<String> listaArquivos = new ArrayList<String>();
		for(Camera c : getCamerasPerfil())
		{
			listaArquivos.add(c.getNomeCamera() +".asx");
		}
		return listaArquivos;
	}

}



