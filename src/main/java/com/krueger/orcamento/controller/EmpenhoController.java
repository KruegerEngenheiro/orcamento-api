package com.krueger.orcamento.controller;

import com.krueger.orcamento.facade.OrcamentoFacade;
import com.krueger.orcamento.model.Empenho;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empenhos")
public class EmpenhoController {

    private final OrcamentoFacade facade;

    public EmpenhoController(OrcamentoFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public Empenho registrar(@RequestBody Empenho empenho) {
        return facade.registrarEmpenho(empenho);
    }

    @GetMapping
    public List<Empenho> listar() {
        return facade.listar();
    }
}
