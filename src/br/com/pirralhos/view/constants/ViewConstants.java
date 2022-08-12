package br.com.pirralhos.view.constants;

import java.util.Arrays;
import java.util.List;

public interface ViewConstants {
	Character SIM = 'S';
	Character NAO = 'N';
	String SENHA_PADRAO="PRR123";
	int PERFIL_ADMINISTRADOR = 1;
	int PERFIL_PROFESSOR = 2;
	int PERFIL_RESPONSAVEL = 3;
	int PERFIL_VISITANTE = 0;
	char RECADO_PROFESSOR='P';
	char RECADO_RESPONSAVEL='R';
	int CURSO_BERCARIO =  1;
	int CURSO_MATERNAL = 2;
	int CURSO_EMEI = 3;
	int PERIODO_INTEGRAL=1;
	int PERIODO_MATUTINO=2;
	int PERIODO_VESPERTINO=3;
	
	
	
	List<String> telasAdministrador = Arrays.asList(new String[] {
			"consulta_matricula.xhtml","cadastro_matricula.xhtml", "cadastro_camera.xhtml",
			"consulta_camera.xhtml", "cadastro_turma.xhtml","cadastro_usuario.xhtml",
			"consulta_turma.xhtml", "cadastro_telefoneCreche.xhtml","cadastro_professor.xhtml","consulta_professor.xhtml","consulta_aluno.xhtml","visualizacao_cameras.xhtml","gravacao_camera.xhtml","timeout.xhtml" });
	List<String> telasProfessor = Arrays.asList(new String[] {"dashboard_professor.xhtml","cadastro_lcto_agenda.xhtml","enviar_recado_professor.xhtml","visualizacao_cameras.xhtml","timeout.xhtml"});
	List<String> telasResponsavel = Arrays.asList(new String[] {"dashboard_responsavel.xhtml","consulta_lcto_agenda.xhtml","enviar_recado_responsavel.xhtml","visualizacao_cameras.xhtml","timeout.xhtml"});

}
