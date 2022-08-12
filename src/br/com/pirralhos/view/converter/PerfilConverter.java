package br.com.pirralhos.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.pirralhos.model.entity.Perfil;

@FacesConverter("br.com.pirralhos.view.converter.PerfilConverter")
public class PerfilConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !"".equals(value.toString())) {
			Perfil perfil = new Perfil();
			String values[] = value.split(";");
			perfil.setIdperfil(Integer.parseInt(values[0]));
			perfil.setNome(values[1]);
			return perfil;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Perfil Perfil = (Perfil) value;
		StringBuffer sb = new StringBuffer();
		sb.append(Perfil.getIdperfil());
		sb.append(";");
		sb.append(Perfil.getNome());
		return sb.toString();
	}

}
