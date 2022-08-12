package br.com.pirralhos.view.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("br.com.pirralhos.view.validation.ComboValidator")
public class ComboValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
			throws ValidatorException {
		if (value == null || value.toString().equals("0")) {
			FacesMessage msg = new FacesMessage("Campo " + arg1.getId()
					+ " � obrigat�rio!", "Selecione um valor para o campo "
					+ arg1.getId());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	}

}
