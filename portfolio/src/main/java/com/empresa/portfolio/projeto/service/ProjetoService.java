package com.empresa.portfolio.projeto.service;

import org.springframework.stereotype.Service;
import com.empresa.portfolio.projeto.repository.ProjetoRepository;

@Service
public class ProjetoService {
    
    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }
    
}
