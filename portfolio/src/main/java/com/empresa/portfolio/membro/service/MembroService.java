package com.empresa.portfolio.membro.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.portfolio.membro.dto.MembroRequest;
import com.empresa.portfolio.membro.dto.MembroResponse;
import com.empresa.portfolio.membro.repository.MembroRepository;
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
    
}
