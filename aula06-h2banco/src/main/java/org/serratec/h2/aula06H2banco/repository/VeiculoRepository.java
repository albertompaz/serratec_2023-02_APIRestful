package org.serratec.h2.aula06H2banco.repository;

import org.serratec.h2.aula06H2banco.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
