package com.krueger.orcamento.strategy;

import com.krueger.orcamento.model.Empenho;
import com.krueger.orcamento.model.FonteRecurso;

import java.math.BigDecimal;

/**
 * Padrao Strategy.
 * Um mesmo "o que fazer" (calcular quanto reservar de um empenho) com varios
 * "como fazer", um por fonte de recurso. Cada implementacao e uma regra diferente,
 * e quem chama nao precisa saber qual esta sendo usada.
 */
public interface RegraReservaStrategy {

    /** Qual fonte de recurso esta regra atende. Serve para o selector achar a regra certa. */
    FonteRecurso fonteAtendida();

    /** Calcula quanto o sistema deve efetivamente reservar para este empenho. */
    BigDecimal calcularValorReservado(Empenho empenho);
}
