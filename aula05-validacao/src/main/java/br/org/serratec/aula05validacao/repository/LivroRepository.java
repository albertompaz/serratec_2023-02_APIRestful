package br.org.serratec.aula05validacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.aula05validacao.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
