package com.empresa.portfolio.projeto.service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import com.empresa.portfolio.projeto.model.enums.ClassificacaoRisco;
import com.empresa.portfolio.projeto.model.enums.StatusProjeto;
import com.empresa.portfolio.projeto.repository.ProjetoRepository;
import com.empresa.portfolio.projeto.model.entity.Projeto;
@Service
public class ProjetoService {
    
    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }


        private static final List<StatusProjeto> ORDEM_STATUS = List.of(
        StatusProjeto.ANALISE,
        StatusProjeto.ANALISE_REALIZADA,
        StatusProjeto.ANALISE_APROVADA,
        StatusProjeto.INICIADO,
        StatusProjeto.PLANEJADO,
        StatusProjeto.EM_ANDAMENTO,
        StatusProjeto.ENCERRADO
    );

    public Projeto mudarStatus(Long id, StatusProjeto novoStatus) {
        Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        validarTransicao(projeto, novoStatus);

        projeto.setStatus(novoStatus);
        return projetoRepository.save(projeto);
    }

    private void validarTransicao(Projeto projeto, StatusProjeto novoStatus) {
        StatusProjeto atual = projeto.getStatus();

        if (novoStatus == StatusProjeto.CANCELADO) return;

        int idxAtual = ORDEM_STATUS.indexOf(atual);
        int idxNovo = ORDEM_STATUS.indexOf(novoStatus);

        if (idxNovo != idxAtual + 1) {
            throw new IllegalArgumentException("Transição inválida: " + atual + " → " + novoStatus);
        }
    }
    public ClassificacaoRisco classificacaoRisco (Projeto projeto){
        long meses = ChronoUnit.MONTHS.between(projeto.getDataInicio(), projeto.getDataFimPrevisto());
        BigDecimal orcamento = projeto.getOrcamento();

        if(orcamento.compareTo(new BigDecimal(100000))<= 0 && meses >= 3){
            return ClassificacaoRisco.BAIXO;
        } else if (
            (orcamento.compareTo(new BigDecimal("100001")) >= 0 && orcamento.compareTo(new BigDecimal("500000")) <= 0)
            || (meses > 3 && meses <= 6)
        ) {
            return ClassificacaoRisco.MEDIO;
        }
        return ClassificacaoRisco.ALTO;
    }
    
}
