package com.krueger.orcamento.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.krueger.orcamento.model.Empenho;
import com.krueger.orcamento.model.FonteRecurso;

@Component
public class ReservaRecursoNacional implements RegraReservaStrategy {

    @Override
    public FonteRecurso fonteAtendida() {
        return FonteRecurso.NACIONAL;
    }

    @Override
    public BigDecimal calcularValorReservado(Empenho empenho) {
        return empenho.getValorSolicitado();
    }
}
