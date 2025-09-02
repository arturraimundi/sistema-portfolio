package com.empresa.portfolio.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.portfolio.projeto.service.ProjetoService;
import java.util.List;

import com.empresa.portfolio.projeto.dto.ProjetoRequest;
import com.empresa.portfolio.projeto.dto.ProjetoResponse;
import com.empresa.portfolio.projeto.dto.StatusProjetoRequest;
@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping("/projetos")
    public ResponseEntity <List<ProjetoResponse>> listarProjetos() {
        return ResponseEntity.ok(projetoService.listarProjetos());
    }

    @PostMapping("/salvar")
    public ResponseEntity<ProjetoResponse> salvarProjeto(@RequestBody ProjetoRequest request) {
        ProjetoResponse response = projetoService.salvarProjeto(request);
       
        return ResponseEntity.ok(response);
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProjetoResponse> atualizarProjeto(
            @PathVariable Long id,
            @RequestBody ProjetoRequest request) {
        ProjetoResponse response = projetoService.atualizarProjeto(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/status/{id}")
    public ResponseEntity<ProjetoResponse> atualizarStatusProjeto(
    @PathVariable Long id,
    @RequestBody StatusProjetoRequest request) {
    ProjetoResponse response = projetoService.atualizarStatus(id, request.getNovoStatus());
    return ResponseEntity.ok(response);
}

}
