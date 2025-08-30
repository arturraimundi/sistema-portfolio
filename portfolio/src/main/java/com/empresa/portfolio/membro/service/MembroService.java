package com.empresa.portfolio.membro.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.portfolio.membro.dto.MembroRequest;
import com.empresa.portfolio.membro.dto.MembroResponse;
import com.empresa.portfolio.membro.repository.MembroRepository;
import com.empresa.portfolio.projeto.dto.ProjetoResponse;
import com.empresa.portfolio.projeto.model.entity.Projeto;
import com.empresa.portfolio.membro.model.entity.Membro;
@Service
public class MembroService {
    
    private final MembroRepository membroRepository;
    private final ModelMapper modelMapper;

    public MembroService(MembroRepository membroRepository, ModelMapper modelMapper) {
        this.membroRepository = membroRepository;
        this.modelMapper = modelMapper;
    }

    public MembroResponse salvarMembro(MembroRequest request) {
        Membro membro = modelMapper.map(request, Membro.class);


        membro = membroRepository.save(membro);
        return modelMapper.map(membro, MembroResponse.class);
    }

    public List<MembroResponse> listarMembros() {
    List<Membro> membros = membroRepository.findAll();
    List<MembroResponse> responses = new ArrayList<>();

    for (Membro membro : membros) {
        MembroResponse response = modelMapper.map(membro, MembroResponse.class);
        responses.add(response);
    }
    return responses;
}

    public Membro getById(Long id) {
        return membroRepository.findById(id).orElse(null);
    }
}
