package com.empresa.portfolio.projeto.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    private Date dataInicio;
    private Date dataFim;
    private Date dataFimPrevisto;
    private BigDecimal orcamento;

    private StatusProjeto status;
    private ClassificacaoRisco classificacaoRisco;

    @ManyToMany
    private List<Membro> membros = new ArrayList<>();


}
