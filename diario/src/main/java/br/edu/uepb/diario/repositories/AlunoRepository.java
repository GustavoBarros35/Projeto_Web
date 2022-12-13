package br.edu.uepb.diario.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uepb.diario.domain.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}