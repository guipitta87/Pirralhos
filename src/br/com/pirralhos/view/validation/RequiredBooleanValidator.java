package br.com.pirralhos.view.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("br.com.pirralhos.view.validation.RequiredBooleanValidator")
public class RequiredBooleanValidator implements Validator{

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
			throws ValidatorException {
		if (value == null || !Boolean.valueOf(value.toString())) 
		{
			FacesMessage msg = new FacesMessage("Campo " + arg1.getId()
					+ " &eacute; obrigat&oacute;rio!","Campo " + arg1.getId()
					+ " &eacute; obrigat&oacute;rio!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		
	}

}
