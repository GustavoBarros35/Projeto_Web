package br.edu.uepb.diario.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uepb.diario.domain.Turma;
import br.edu.uepb.diario.services.AlunoService;
import br.edu.uepb.diario.services.ProfessorService;
import br.edu.uepb.diario.services.TurmaService;

@RestController
@RequestMapping(value = "/turmas")
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;

    @Autowired
	private ProfessorService professorService;

	@Autowired
	private AlunoService alunoService;
	
	
	@GetMapping()
	public ResponseEntity<List<Turma>> listarTurmas(){
		return ResponseEntity.status(HttpStatus.OK).body(turmaService.ListarTurmas());
	}
	
	@PostMapping
	public ResponseEntity<Object> registrarTurma(@RequestBody Turma turma){
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.adicionarTurma(turma));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarTurma(@PathVariable Long id){
		boolean turmaExiste = turmaService.verificarSeTurmaExiste(id);

		if(!turmaExiste){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(turmaService.buscarTurma(id).get());

	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarTurma(@PathVariable("id") Long id, @RequestBody Turma turma){
		boolean turmaExiste = turmaService.verificarSeTurmaExiste(id);
		
		if(!turmaExiste){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.atualizarTurma( turma));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarTurma(@PathVariable("id") Long id){
		boolean turmaExiste = turmaService.verificarSeTurmaExiste(id);
		
		if(!turmaExiste){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
		}
		turmaService.deletarTurma(id);
		return ResponseEntity.status(HttpStatus.OK).body("Turma Deletada com sucesso");

	}

    //PATCH
	
	@PatchMapping("/{idTurma}/matricularAluno/{idAluno}")
    public ResponseEntity<Object> matricularAluno(@PathVariable("idTurma") Long idTurma,@PathVariable("idAluno") Long idAluno, @RequestBody Turma turma){
        boolean turmaExiste = turmaService.verificarSeTurmaExiste(idTurma);
        boolean alunoExiste = alunoService.verificarSeAlunoExiste(idAluno);

		if(!turmaExiste || !alunoExiste){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma ou Aluno não encontrado");
		}

        turmaService.matricularAluno(idTurma, idAluno);

        return ResponseEntity.status(HttpStatus.OK).body("Aluno Matriculado com sucesso");
    }

	@PatchMapping("/{idTurma}/vincularProfessor/{idProf}")
    public ResponseEntity<Object> vincularProfessor(@PathVariable("idTurma") Long idTurma,@PathVariable("idProf") Long idProf, @RequestBody Turma turma){
        boolean turmaExiste = turmaService.verificarSeTurmaExiste(idTurma);
        boolean profExiste = professorService.verificarSeProfessorExiste(idProf);

		if(!turmaExiste || !profExiste){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma ou Professor não encontrado");
		}

        turmaService.vincularProfessor(idTurma, idProf);

        return ResponseEntity.status(HttpStatus.OK).body("Professor Vinculado com sucesso");
    }

	

}

