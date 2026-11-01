package com.krueger.orcamento.facade;

import com.krueger.orcamento.model.Empenho;
import com.krueger.orcamento.repository.EmpenhoRepository;
import com.krueger.orcamento.strategy.RegraReservaStrategy;
import com.krueger.orcamento.strategy.SeletorRegraReserva;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrcamentoFacade {

    private final SeletorRegraReserva seletor;
    private final EmpenhoRepository repositorio;

    public OrcamentoFacade(
            SeletorRegraReserva seletor,
            EmpenhoRepository repositorio) {
        this.seletor = seletor;
        this.repositorio = repositorio;
    }

    public Empenho registrarEmpenho(Empenho empenho) {
        RegraReservaStrategy regra =
                seletor.selecionar(empenho.getFonteRecurso());

        empenho.setValorReservado(
                regra.calcularValorReservado(empenho));

        empenho.setCriadoEm(LocalDateTime.now());

        return repositorio.save(empenho);
    }

    public List<Empenho> listar() {
        return repositorio.findAll();
    }
}
