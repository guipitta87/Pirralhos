/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pirralhos.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import java.awt.image.BufferedImage;

import br.com.pirralhos.config.BeanFactory;
import br.com.pirralhos.model.dao.base.GenericDAO;
import br.com.pirralhos.model.entity.Aluno;

/**
 * 
 * @author HOUSE
 */
@ManagedBean
@SessionScoped
public class UploadBean implements Serializable {

	private StreamedContent imgContent;
	private GenericDAO<Aluno, Integer> alunoDAO = (GenericDAO<Aluno, Integer>) BeanFactory
			.getBean("alunoDAO");

	public StreamedContent getImgContent() throws IOException {

		final String nome = "C:\\Desenvolvimento\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\pirralhos_web\\fotos8f24a01a.jpg";
		FileInputStream fis = new FileInputStream(nome);
		return this.imgContent = new DefaultStreamedContent(fis, "img/jpeg");
	}

	public void setImgContent(StreamedContent imgContent) {
		this.imgContent = imgContent;
	}

	public void handleFileUpload(FileUploadEvent event)
			throws FileNotFoundException, IOException {

		FacesMessage msg = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage("teste", msg);

		byte[] conteudo = event.getFile().getContents();
		String caminho = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/fotos/")
				+ event.getFile().getFileName();
		System.out.println("TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" + caminho);
		FileOutputStream fos = new FileOutputStream(caminho);
		fos.write(conteudo);
		fos.close();

	}

}
