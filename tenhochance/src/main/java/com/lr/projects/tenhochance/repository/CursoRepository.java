package com.lr.projects.tenhochance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lr.projects.tenhochance.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    
}
