package com.empresa.portfolio.projeto.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.empresa.portfolio.projeto.model.enums.ClassificacaoRisco;
import com.empresa.portfolio.projeto.model.enums.StatusProjeto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjetoRequest {
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDate dataFimPrevisto;
    private BigDecimal orcamento;
    private StatusProjeto status;
    private ClassificacaoRisco classificacaoRisco;
    private List<Long> membrosIds; 
}
