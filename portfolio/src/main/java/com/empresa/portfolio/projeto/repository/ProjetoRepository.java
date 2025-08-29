package com.empresa.portfolio.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.empresa.portfolio.projeto.model.entity.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    
}
