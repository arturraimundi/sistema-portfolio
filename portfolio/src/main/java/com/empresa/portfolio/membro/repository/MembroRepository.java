package com.empresa.portfolio.membro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.empresa.portfolio.membro.model.entity.Membro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long>{   
}
