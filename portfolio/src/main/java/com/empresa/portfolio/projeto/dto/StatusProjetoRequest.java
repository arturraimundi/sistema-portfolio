package com.empresa.portfolio.projeto.dto;

import com.empresa.portfolio.projeto.model.enums.StatusProjeto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusProjetoRequest {
    private StatusProjeto novoStatus;
}
