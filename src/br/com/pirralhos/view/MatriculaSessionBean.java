package br.com.pirralhos.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import br.com.pirralhos.model.entity.Autorizado;
import br.com.pirralhos.model.entity.Responsavel;

@ManagedBean(name = "matriculaSessionBean")
@SessionScoped
public class MatriculaSessionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Responsavel> listaResponsaveis;
	private List<Autorizado> listaAutorizados;
	private static final String tabResponsaveis="tabResponsaveis";

	private boolean alunoNovo;
	{
		setListaAutorizados(new ArrayList<Autorizado>());
		setListaResponsaveis(new ArrayList<Responsavel>());
	}
	
	public String onFlowProcess(FlowEvent event) {
		if(tabResponsaveis.equals(event.getOldStep()) && listaResponsaveis.size() == 0 && !event.getNewStep().equals("tabFichaDeSaude") )
		{
			FacesMessage msg = new FacesMessage("Necess&aacute;rio Incluir pelo menos um respo&aacute;vel",
					"Inclua um respons&aacute;vel ");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null,msg);
			return event.getOldStep();
		}
		return event.getNewStep();
	}

	public List<Responsavel> getListaResponsaveis() {
		return listaResponsaveis;
	}

	public void setListaResponsaveis(List<Responsavel> listaResponsaveis) {
		this.listaResponsaveis = listaResponsaveis;
	}

	public List<Autorizado> getListaAutorizados() {
		return listaAutorizados;
	}

	public void setListaAutorizados(List<Autorizado> listaAutorizados) {
		this.listaAutorizados = listaAutorizados;
	}

	public boolean isAlunoNovo() {
		return alunoNovo;
	}

	public void setAlunoNovo(boolean alunoNovo) {
		this.alunoNovo = alunoNovo;
	}

}
