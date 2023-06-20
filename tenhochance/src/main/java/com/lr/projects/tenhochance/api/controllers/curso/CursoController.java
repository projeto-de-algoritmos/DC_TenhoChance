package com.lr.projects.tenhochance.api.controllers.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lr.projects.tenhochance.entity.Curso;
import com.lr.projects.tenhochance.serivce.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> obterCursos() {
        return ResponseEntity.ok(cursoService.buscaCursos());
    }
    
}
