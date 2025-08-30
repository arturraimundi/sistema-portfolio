package com.empresa.portfolio.projeto.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.portfolio.projeto.service.ProjetoService;
import java.util.List;

import com.empresa.portfolio.projeto.dto.ProjetoRequest;
import com.empresa.portfolio.projeto.dto.ProjetoResponse;
import com.empresa.portfolio.projeto.model.entity.Projeto;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping("/projetos")
    public ResponseEntity <List<Projeto>> listarProjetos() {
        return ResponseEntity.ok(projetoService.listarProjetos());//ajustar
    }

    @PostMapping("/salvar")
    public ResponseEntity<ProjetoResponse> salvarProjeto(@RequestBody ProjetoRequest request) {
        ProjetoResponse response = projetoService.salvarProjeto(request);
       
        return ResponseEntity.ok(response);
    }


}
