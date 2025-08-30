package com.empresa.portfolio.projeto.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.empresa.portfolio.membro.model.entity.Membro;
import com.empresa.portfolio.projeto.model.enums.ClassificacaoRisco;
import com.empresa.portfolio.projeto.model.enums.StatusProjeto;

@Setter
@Getter
@Entity
@Table(name = "projeto")
public class Projeto {
    
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDate dataFimPrevisto;
    private BigDecimal orcamento;

    @Enumerated
    private StatusProjeto status;

    @Enumerated
    private ClassificacaoRisco classificacaoRisco;

    @ManyToMany
    private List<Membro> membros = new ArrayList<>();


}
