package com.empresa.portfolio.membro.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Table;
import com.empresa.portfolio.projeto.model.entity.Projeto;
import jakarta.persistence.Transient;


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
    
    @ManyToMany(mappedBy = "membros")
    private List<Projeto> projetos = new ArrayList<>();

    @Transient
    public long getProjetosAtivos() {
        return projetos.stream()
                .filter(p -> p.getStatus() != null &&
                             p.getStatus() != com.empresa.portfolio.projeto.model.enums.StatusProjeto.ENCERRADO &&
                             p.getStatus() != com.empresa.portfolio.projeto.model.enums.StatusProjeto.CANCELADO)
                .count();
    }

    @Transient
    public boolean podeSerAlocado() {
        return "funcionário".equalsIgnoreCase(funcao) && getProjetosAtivos() < 3;
    }

}
