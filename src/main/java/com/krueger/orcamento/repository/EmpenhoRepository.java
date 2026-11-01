package com.krueger.orcamento.repository;

import com.krueger.orcamento.model.Empenho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Acesso a dados dos empenhos.
 * O Spring Data cria a implementacao em tempo de execucao e a gerencia como
 * um bean unico (Singleton) no contexto da aplicacao.
 */
@Repository
public interface EmpenhoRepository extends JpaRepository<Empenho, Long> {
}
