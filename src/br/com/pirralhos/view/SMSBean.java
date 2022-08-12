package br.com.pirralhos.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import br.com.pirralhos.view.constants.SMSConstants;
@ManagedBean(name="smsBean")
@SessionScoped
public class SMSBean implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Object[] enviarSMS(String numero, String mensagem) {
		Object [] response = null;	
		try {
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost();

				post.setURI(new URI(SMSConstants.SITE_SERVICO_SMS));
				List<NameValuePair> formData = new ArrayList<NameValuePair>();
				formData.add(new BasicNameValuePair("usuario",
						SMSConstants.USUARIO));
				formData.add(new BasicNameValuePair("senha", SMSConstants.SENHA));
				formData.add(new BasicNameValuePair("para", numero));
				formData.add(new BasicNameValuePair("texto", mensagem));
				post.setEntity(new UrlEncodedFormEntity(formData));
				String retorno = getMessage(client.execute(post).getEntity());
				response= tratarRetorno(retorno);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return response;
	}

	public Object[] tratarRetorno(String retorno) {
		String msg = "";
		boolean status = false;
		boolean isInt = true;
		int code = 0;
		Object[] o = new Object[2];
		try {
			code = Integer.parseInt(retorno);
		} catch (NumberFormatException e) {
			isInt = false;
		}
		if (isInt) {
			// TRATANDO O RETORNO DO ENVIO DE SMS
			switch (code) {
			case -2:
				msg = "ERRO: -2 (Formato de número móvel de destinatário inválido)";
				break;

			case -3:
				msg = "ERRO: -3 (Indica que o texto da mensagem está vazio)";
				break;

			case -4:
				msg = "ERRO: -4 (Número móvel inválido ou não suportado pelo sistema.)";
				break;

			case -5:
				msg = "ERRO: -5 (Sistema indisponível tente novamente mais tarde.)";
				break;

			case -7:
				msg = "ERRO: -7 (Crédito insuficiente para realizar o envio.)";
				break;

			case -8:
				msg = "ERRO: -8 (Usuário ou senha inválidos)";
				break;

			case -9:
				msg = "ERRO: -9 Data ou hora de agendamento inválidos.Os parÃ¢metros data e hora (se informados), devem possuir valores válidos.";
				break;
			}
		}
		else
		{
			status = true;
			msg = retorno;
		}
		o[0] = status;
		o[1] = msg;
		return o;

	}

	public String getMessage(HttpEntity entity) throws IOException {
		InputStreamReader isr = new InputStreamReader(entity.getContent());
		BufferedReader in = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
}
