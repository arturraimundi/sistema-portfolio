package com.empresa.portfolio.membro.model.entity;

import jakarta.persistence.Entity;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Table;


@Setter
@Getter
@Entity
@Table(name = "membro")
public class Membro {
    
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String funcao;

}
