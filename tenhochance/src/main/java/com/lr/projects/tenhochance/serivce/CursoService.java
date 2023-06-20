package com.lr.projects.tenhochance.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lr.projects.tenhochance.entity.Curso;
import com.lr.projects.tenhochance.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> buscaCursos() {
        return this.cursoRepository.findAll();
    }
    
}
