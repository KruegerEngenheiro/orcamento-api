package com.krueger.orcamento.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.krueger.orcamento.model.Empenho;
import com.krueger.orcamento.model.FonteRecurso;

@Component
public class ReservaRecursoInternacional implements RegraReservaStrategy {

    @Override
    public FonteRecurso fonteAtendida() {
        return FonteRecurso.INTERNACIONAL;
    }

    @Override
    public BigDecimal calcularValorReservado(Empenho empenho) {
        return empenho.getValorSolicitado()
                .multiply(new BigDecimal("1.05"))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
