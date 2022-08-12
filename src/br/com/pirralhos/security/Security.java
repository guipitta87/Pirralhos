package br.com.pirralhos.security;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.pirralhos.view.constants.ViewConstants;

public class Security implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();
		String viewId = fc.getViewRoot().getViewId().replace("/", "");

		if (!viewId.equals("home.xhtml") && !hasPermission(viewId)) {
			// fc.getExternalContext().redirect("semPermissao.xhtml");
			fc.getViewRoot().setViewId("/semPermissao.xhtml");
		}

	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	public boolean hasPermission(String viewId) {

		String perfil = String.valueOf(FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("PERFIL"));
		if (perfil != null && !"null".equals(perfil)) {
			switch (Integer.parseInt(perfil)) {
			case ViewConstants.PERFIL_ADMINISTRADOR:
				return ViewConstants.telasAdministrador.contains(viewId);
			case ViewConstants.PERFIL_PROFESSOR:
				return ViewConstants.telasProfessor.contains(viewId);

			case ViewConstants.PERFIL_RESPONSAVEL:
				return ViewConstants.telasResponsavel.contains(viewId);
			}
		}
		return false;
	}

}