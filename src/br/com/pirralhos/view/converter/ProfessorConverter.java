package br.com.pirralhos.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.pirralhos.model.entity.Professor;

@FacesConverter("br.com.pirralhos.view.converter.ProfessorConverter")
public class ProfessorConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(value != null && !"".equals(value.toString()))
		{
			Professor professor = new Professor();
			String values [] = value.split(";");
			professor.setIdprofessor(Integer.parseInt(values[0]));
			professor.setNome(values[1]);
			return professor; 
		}
		else
		{
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Professor professor = (Professor) value;
		StringBuffer sb = new StringBuffer();
		sb.append(professor.getIdprofessor());
		sb.append(";");
		sb.append(professor.getNome());
		return sb.toString();
	}

}

