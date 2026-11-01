package com.krueger.orcamento.model;

/**
 * Origem do recurso que banca a despesa.
 * NACIONAL: recurso do orcamento da Uniao.
 * INTERNACIONAL: recurso de organismo internacional (ex.: agencias da ONU).
 * Cada fonte segue uma regra diferente de reserva (ver os Strategy).
 */
public enum FonteRecurso {
    NACIONAL,
    INTERNACIONAL
}
