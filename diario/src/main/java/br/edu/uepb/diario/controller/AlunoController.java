package br.edu.uepb.diario.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import br.edu.uepb.diario.domain.Aluno;
import br.edu.uepb.diario.services.AlunoService;

@RestController
@RequestMapping(value= "/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping()
	public ResponseEntity<List<Aluno>> listarAlunos(){
		return ResponseEntity.status(HttpStatus.OK).body(alunoService.ListarAlunos());
	}
	
	@PostMapping
	public ResponseEntity<Object> registrarAluno(@RequestBody Aluno aluno){
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.adicionarAluno(aluno));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarAluno(@PathVariable Long id){
		boolean alunoExiste = alunoService.verificarSeAlunoExiste(id);

		if(!alunoExiste){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(alunoService.buscarAluno(id).get());

	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarAluno(@PathVariable("id") Long id, @RequestBody Aluno aluno){
		boolean alunoExiste = alunoService.verificarSeAlunoExiste(id);
		
		if(!alunoExiste){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.atualizarAluno(aluno));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarAluno(@PathVariable("id") Long id){
		boolean alunoExiste = alunoService.verificarSeAlunoExiste(id);
		
		if(!alunoExiste){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
		}
		alunoService.deletarAluno(id);
		return ResponseEntity.status(HttpStatus.OK).body("Aluno Deletado com sucesso");

	}

}

