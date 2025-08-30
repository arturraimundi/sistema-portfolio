package com.empresa.portfolio.projeto.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.portfolio.projeto.service.ProjetoService;
import java.util.List;
import com.empresa.portfolio.projeto.model.entity.Projeto;

@RestController
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping("/projetos")
    public ResponseEntity <List<Projeto>> listarProjetos() {
        return ResponseEntity.ok(projetoService.listarProjetos());
    }

}
