package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Aluno;
import br.com.pirralhos.model.entity.TelefoneCreche;

@ManagedBean(name = "telefoneCrecheBean")
@ViewScoped
public class TelefoneCrecheBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<SelectItem> listaTelefoneView ;

	private TelefoneCreche telefoneCreche ;
	private List<TelefoneCreche> listaTelefoneCreche;
	private GenericDAO<TelefoneCreche,Integer> telefoneCrecheDAO = (GenericDAO<TelefoneCreche,Integer>) BeanFactory.getBean("telefoneCrecheDAO");
	private GenericDAO<Aluno,Integer> alunoDAO = (GenericDAO<Aluno,Integer>) BeanFactory.getBean("alunoDAO");
	public TelefoneCrecheBean() {
		atualizarTela();
		alunoDAO.listAll();
	}
	public void gravar()
	{
		
		boolean isTelefoneDuplicado = getTelefoneCrecheDAO().findBySQL("from TelefoneCreche where numero ='" +telefoneCreche.getNumero() + "'").size() >0 ;
		if(isTelefoneDuplicado)
		{
			FacesMessage message = new FacesMessage(
					"Telefone duplicado");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("numero_telefone", message);
			return;
		}
		getTelefoneCrecheDAO().save(getTelefoneCreche());
		atualizarTela();
	}
	public void limpar()
	{
		setTelefoneCreche(new TelefoneCreche());
	}
	
	public void excluir() {
		getTelefoneCrecheDAO().delete(getTelefoneCreche());
		atualizarTela();
	}
	/**
	 * Limpa os campos input e atualiza a lista de telefones da creche cadastrados
	 */
	private void atualizarTela() {
		limpar();
		setListaTelefoneCreche(getTelefoneCrecheDAO().listAll());
	}
	
	public List<SelectItem> getListaTelefoneView() {
		return listaTelefoneView;
	}

	public void setListaTelefoneView(List<SelectItem> listaTelefoneView) {
		this.listaTelefoneView = listaTelefoneView;
	}

	public TelefoneCreche getTelefoneCreche() {
		return telefoneCreche;
	}
	public void setTelefoneCreche(TelefoneCreche telefoneCreche) {
		this.telefoneCreche = telefoneCreche;
	}
	public List<TelefoneCreche> getListaTelefoneCreche() {
		return listaTelefoneCreche;
	}
	public void setListaTelefoneCreche(List<TelefoneCreche> listaTelefoneCreche) {
		this.listaTelefoneCreche = listaTelefoneCreche;
	}

	public GenericDAO<TelefoneCreche, Integer> getTelefoneCrecheDAO() {
		return telefoneCrecheDAO;
	}

	public void setTelefoneCrecheDAO(
			GenericDAO<TelefoneCreche, Integer> telefoneCrecheDAO) {
		this.telefoneCrecheDAO = telefoneCrecheDAO;
	}
	
}
