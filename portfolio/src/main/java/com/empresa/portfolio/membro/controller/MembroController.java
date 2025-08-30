package com.empresa.portfolio.membro.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.portfolio.membro.dto.MembroRequest;
import com.empresa.portfolio.membro.dto.MembroResponse;
import com.empresa.portfolio.membro.service.MembroService;

@RestController
@RequestMapping("/membro")
public class MembroController {
    
    @Autowired
    private MembroService membroService;
    
    
    @PostMapping("/salvar")
    public ResponseEntity<MembroResponse> salvarMembro(@RequestBody MembroRequest request) {
        MembroResponse response = membroService.salvarMembro(request);
       
        return ResponseEntity.ok(response);
    }
}
