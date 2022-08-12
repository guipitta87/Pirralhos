package br.com.pirralhos.view.validation;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("br.com.pirralhos.view.validation.AfterDateValidator")
public class AfterDateValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value)
			throws ValidatorException {
		if (value == null || ((Date) value).after(new Date())) {
			FacesMessage msg = new FacesMessage("O campo " + arg1.getId()
					+ " deve ser inferior a  data atual.", "O campo "
					+ arg1.getId() + " deve ser inferior a  data atual!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}
	}
}
