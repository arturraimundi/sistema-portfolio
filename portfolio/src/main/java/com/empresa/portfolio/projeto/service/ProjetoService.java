package com.empresa.portfolio.projeto.service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.portfolio.projeto.model.enums.ClassificacaoRisco;
import com.empresa.portfolio.projeto.model.enums.StatusProjeto;
import com.empresa.portfolio.projeto.repository.ProjetoRepository;
import com.empresa.portfolio.projeto.dto.ProjetoRequest;
import com.empresa.portfolio.projeto.dto.ProjetoResponse;
import com.empresa.portfolio.projeto.model.entity.Projeto;
import com.empresa.portfolio.membro.dto.MembroRequest;
import com.empresa.portfolio.membro.model.entity.Membro;
import com.empresa.portfolio.membro.service.MembroService;
@Service
public class ProjetoService {
    
    @Autowired
    private final ProjetoRepository projetoRepository;
    
    @Autowired
    private final MembroService membroService;

    @Autowired
    private ModelMapper modelMapper;

    public ProjetoService(ProjetoRepository projetoRepository, MembroService membroService, ModelMapper modelMapper) {
        this.projetoRepository = projetoRepository;
        this.membroService = membroService;
        this.modelMapper = modelMapper;
    }


    public ProjetoResponse salvarProjeto(ProjetoRequest request) {
        Projeto projeto = modelMapper.map(request, Projeto.class);
        int totalMembros = projeto.getMembros().size();
        
        projeto.getMembros().clear();

        for(Long membroId : request.getMembrosIds()){
           Membro membroExistente = membroService.getById(membroId);
           if(membroExistente == null){
            throw new IllegalArgumentException("Membro com ID " + membroId + " não existe.");
           }
           projeto.getMembros().add(membroExistente);   
            
        }
        if(totalMembros < 1 || totalMembros > 10){
            throw new IllegalArgumentException("O número de membros do projeto deve estar entre 1 e 10.");
        }
        projeto.setClassificacaoRisco(classificacaoRisco(projeto));

        projeto = projetoRepository.save(projeto);
        return modelMapper.map(projeto, ProjetoResponse.class);
    }

    public List<ProjetoResponse> listarProjetos() {
    List<Projeto> projetos = projetoRepository.findAll();
    List<ProjetoResponse> responses = new ArrayList<>();

    for (Projeto projeto : projetos) {
        ProjetoResponse response = modelMapper.map(projeto, ProjetoResponse.class);

        List<String> nomesMembros = projeto.getMembros()
                                           .stream()
                                           .map(m -> m.getNome())
                                           .toList();

        response.setMembros(nomesMembros);
        responses.add(response);
    }

    return responses;
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

  public ProjetoResponse atualizarProjeto(Long id, ProjetoRequest request) {
    Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Projeto com id " + id + " não encontrado"));

    if (request.getNome() != null) projeto.setNome(request.getNome());
    if (request.getDescricao() != null) projeto.setDescricao(request.getDescricao());
    if (request.getDataInicio() != null) projeto.setDataInicio(request.getDataInicio());
    if (request.getDataFim() != null) projeto.setDataFim(request.getDataFim());
    if (request.getDataFimPrevisto() != null) projeto.setDataFimPrevisto(request.getDataFimPrevisto());
    if (request.getOrcamento() != null) projeto.setOrcamento(request.getOrcamento());
    if (request.getStatus() != null) projeto.setStatus(request.getStatus());

    if (request.getMembrosIds() != null && !request.getMembrosIds().isEmpty()) {
        projeto.getMembros().clear();
        for(Long membroId : request.getMembrosIds()){
            Membro membroExistente = membroService.getById(membroId);
            if(membroExistente == null){
                throw new IllegalArgumentException("Membro com ID " + membroId + " não existe.");
            }
            projeto.getMembros().add(membroExistente);
        }
    }

    if (projeto.getDataInicio() != null && projeto.getDataFimPrevisto() != null && projeto.getOrcamento() != null) {
        projeto.setClassificacaoRisco(classificacaoRisco(projeto));
    }

    Projeto atualizado = projetoRepository.save(projeto);

    ProjetoResponse response = modelMapper.map(atualizado, ProjetoResponse.class);
    response.setMembros(
        atualizado.getMembros()
                  .stream()
                  .map(Membro::getNome)
                  .toList()
    );
    return response;
}
public void deletarProjeto(Long id) {
    Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Projeto com id " + id + " não encontrado"));

    projetoRepository.delete(projeto);
}

    
}
