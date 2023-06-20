package com.lr.projects.tenhochance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lr.projects.tenhochance.entity.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

        @Query("SELECT c " +
                        "FROM Candidato c " +
                        "WHERE c.curso.id = :cursoId " +
                        "AND c.classificacaoFinalUniversal IS NOT NULL " +
                        "AND c.classificacaoFinalNegros IS NULL " +
                        "AND c.classificacaoFinalEscolaPublicaBaixaRendaPpi IS NULL " +
                        "AND c.classificacaoFinalEscolaPublicaBaixaRendaPpiPcd IS NULL " +
                        "AND c.classificacaoFinalEscolaPublicaBaixaRenda IS NULL " +
                        "AND c.classificacaoFinalEscolaPublicaBaixaRendaPcd IS NULL " +
                        "AND c.classificacaoFinalEscolaPublicaNaoBaixaRendaPpi IS NULL " +
                        "AND c.classificacaoFinalEscolaPublicaNaoBaixaRendaPpiPcd IS NULL " +
                        "AND c.classificacaoFinalEscolaPublicaNaoBaixaRenda IS NULL " +
                        "AND c.classificacaoFinalEscolaPublicaNaoBaixaRendaPcd IS NULL " +
                        "ORDER BY c.notaFinal DESC")
        Page<Candidato> buscaCandidatosSistemaUniversal(@Param("cursoId") Long cursoId, Pageable pageable);

        @Query("SELECT c " +
                        "FROM Candidato c " +
                        "WHERE c.curso.id = :cursoId " +
                        "AND c.classificacaoFinalNegros IS NOT NULL " +
                        "ORDER BY c.notaFinal DESC")
        Page<Candidato> buscaCandidatosSistemaNegros(@Param("cursoId") Long cursoId, Pageable pageable);

        @Query("SELECT c " +
                        "FROM Candidato c " +
                        "WHERE c.curso.id = :cursoId " +
                        "AND c.classificacaoFinalEscolaPublicaBaixaRendaPpi IS NOT NULL " +
                        "ORDER BY c.notaFinal DESC")
        Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRendaPpi(@Param("cursoId") Long cursoId,Pageable pageable);

        @Query("SELECT c " +
                        "FROM Candidato c " +
                        "WHERE c.curso.id = :cursoId " +
                        "AND c.classificacaoFinalEscolaPublicaBaixaRendaPpiPcd IS NOT NULL " +
                        "ORDER BY c.notaFinal DESC")
        Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRendaPpiPcd(@Param("cursoId") Long cursoId,Pageable pageable);

        @Query("SELECT c " +
                        "FROM Candidato c " +
                        "WHERE c.curso.id = :cursoId " +
                        "AND c.classificacaoFinalEscolaPublicaBaixaRenda IS NOT NULL " +
                        "ORDER BY c.notaFinal DESC")
        Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRenda(@Param("cursoId") Long cursoId,Pageable pageable);

        @Query("SELECT c " +
                        "FROM Candidato c " +
                        "WHERE c.curso.id = :cursoId " +
                        "AND c.classificacaoFinalEscolaPublicaBaixaRendaPcd IS NOT NULL " +
                        "ORDER BY c.notaFinal DESC")
        Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRendaPcd(@Param("cursoId") Long cursoId,Pageable pageable);

        @Query("SELECT c " +
                        "FROM Candidato c " +
                        "WHERE c.curso.id = :cursoId " +
                        "  AND c.classificacaoFinalEscolaPublicaNaoBaixaRendaPpi IS NOT NULL " +
                        "ORDER BY c.notaFinal DESC")
        Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpi(@Param("cursoId") Long cursoId,Pageable pageable);

        @Query("SELECT c " +
                        "FROM Candidato c " +
                        "WHERE c.curso.id = :cursoId " +
                        "  AND c.classificacaoFinalEscolaPublicaNaoBaixaRendaPpiPcd IS NOT NULL " +
                        "ORDER BY c.notaFinal DESC")
        Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpiPcd(@Param("cursoId") Long cursoId,Pageable pageable);

        @Query("SELECT c " +
                        "FROM Candidato c " +
                        "WHERE c.curso.id = :cursoId " +
                        "  AND c.classificacaoFinalEscolaPublicaNaoBaixaRenda IS NOT NULL " +
                        "ORDER BY c.notaFinal DESC")
        Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRenda(@Param("cursoId") Long cursoId,Pageable pageable);

        @Query("SELECT c " +
                        "FROM Candidato c " +
                        "WHERE c.curso.id = :cursoId " +
                        "  AND c.classificacaoFinalEscolaPublicaNaoBaixaRendaPcd IS NOT NULL " +
                        "ORDER BY c.notaFinal DESC")
        Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPcd(@Param("cursoId") Long cursoId,Pageable pageable);

}
