package com.krueger.orcamento.strategy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.krueger.orcamento.model.FonteRecurso;

@Component
public class SeletorRegraReserva {

    private final List<RegraReservaStrategy> regras;

    public SeletorRegraReserva(List<RegraReservaStrategy> regras) {
        this.regras = regras;
    }

    public RegraReservaStrategy selecionar(FonteRecurso fonte) {
        for (RegraReservaStrategy regra : regras) {
            if (regra.fonteAtendida() == fonte) {
                return regra;
            }
        }

        throw new IllegalArgumentException(
                "Sem regra para a fonte: " + fonte
        );
    }
}
