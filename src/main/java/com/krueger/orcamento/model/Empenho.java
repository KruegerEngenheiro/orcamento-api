package com.krueger.orcamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Um empenho e a reserva de recurso para uma despesa futura.
 * valorSolicitado: quanto a area pediu.
 * valorReservado: quanto o sistema efetivamente reserva (calculado por um Strategy, conforme a fonte).
 */
@Entity
public class Empenho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private BigDecimal valorSolicitado;

    @Enumerated(EnumType.STRING)
    private FonteRecurso fonteRecurso;

    private String naturezaDespesa;

    private String unidade;

    private BigDecimal valorReservado;

    private LocalDateTime criadoEm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(BigDecimal valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public FonteRecurso getFonteRecurso() {
        return fonteRecurso;
    }

    public void setFonteRecurso(FonteRecurso fonteRecurso) {
        this.fonteRecurso = fonteRecurso;
    }

    public String getNaturezaDespesa() {
        return naturezaDespesa;
    }

    public void setNaturezaDespesa(String naturezaDespesa) {
        this.naturezaDespesa = naturezaDespesa;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getValorReservado() {
        return valorReservado;
    }

    public void setValorReservado(BigDecimal valorReservado) {
        this.valorReservado = valorReservado;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
