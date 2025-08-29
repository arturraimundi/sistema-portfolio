package com.empresa.portfolio.membro.service;

import org.springframework.stereotype.Service;
import com.empresa.portfolio.membro.repository.MembroRepository;

@Service
public class MembroService {
    
    private final MembroRepository membroRepository;

    public MembroService(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }
    
}
