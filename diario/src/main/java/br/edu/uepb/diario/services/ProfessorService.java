package br.edu.uepb.diario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uepb.diario.domain.Professor;
import br.edu.uepb.diario.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	public List<Professor> ListarProfessor (){
		return professorRepository.findAll();
	}

	public Professor adicionarProfessor(Professor professor){
		return professorRepository.save(professor);
	}

	public Optional<Professor> buscarProfessor(Long professorID){
		return professorRepository.findById(professorID);
	}

	public boolean verificarSeProfessorExiste(Long professorID){
		return professorRepository.existsById(professorID);
	}

	public Professor atualizarProfessor(Professor professorRecebido){
		Professor professorBD = professorRepository.findById(professorRecebido.getId()).get();

		professorRecebido.setId(professorBD.getId());
		professorRecebido.setTurmas(professorBD.getTurmas());

		return professorRepository.save(professorRecebido);
	}
	
	public void deletarProfessor(Long professorID){
		professorRepository.deleteById(professorID);
	}

}
