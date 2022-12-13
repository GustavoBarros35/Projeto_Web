package br.edu.uepb.diario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uepb.diario.domain.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
