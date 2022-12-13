package br.edu.uepb.diario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uepb.diario.domain.Aluno;
import br.edu.uepb.diario.repositories.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public List<Aluno> ListarAlunos (){
		return alunoRepository.findAll();
	}

	public Aluno adicionarAluno(Aluno aluno){
		return alunoRepository.save(aluno);
	}

	public Optional<Aluno> buscarAluno(Long alunoID){
		return alunoRepository.findById(alunoID);
	}

	public boolean verificarSeAlunoExiste(Long alunoID){
		return alunoRepository.existsById(alunoID);
	}

	public Aluno atualizarAluno(Aluno alunoRecebido){
		Aluno alunoBD = alunoRepository.findById(alunoRecebido.getId()).get();

		alunoRecebido.setId(alunoBD.getId());
		alunoRecebido.setTurmas(alunoBD.getTurmas());

		return alunoRepository.save(alunoRecebido);
	}

	public void deletarAluno(Long alunoID){
		alunoRepository.deleteById(alunoID);
	}

}
