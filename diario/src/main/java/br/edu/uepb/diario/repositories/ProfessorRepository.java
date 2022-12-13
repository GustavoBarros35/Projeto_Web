package br.edu.uepb.diario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uepb.diario.domain.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}