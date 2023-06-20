package com.lr.projects.tenhochance.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lr.projects.tenhochance.entity.Candidato;
import com.lr.projects.tenhochance.repository.CandidatoRepository;
import com.lr.projects.tenhochance.utils.Classificador.Classificador;
import com.lr.projects.tenhochance.utils.Classificador.Ordenador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@Service
public class CandidatoService {

    @Autowired
    private Classificador classificador;

    @Autowired
    private CandidatoRepository candidatoRepository;

    private void atualizarAprovacaoCandidatos(Page<Candidato> candidatos) {
        this.classificador.atualizarAprovacaoCandidatos(candidatos);
    }

    private Page<Candidato> atualizarAprovacaoCandidatosCotistas(Page<Candidato> candidatos, Pageable pageable) {
        classificador.atualizaAprovacaoCandidatosCotistasPorPagina(candidatos);
        return new PageImpl<>(Ordenador.mergeSort(candidatos.getContent()), pageable,
                candidatos.getTotalElements());
    }

    public Page<Candidato> buscaCandidatosSistemaUniversal(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository.buscaCandidatosSistemaUniversal(cursoId, pageable);
        this.atualizarAprovacaoCandidatos(pageCandidatos);
        return new PageImpl<>(Ordenador.mergeSort(pageCandidatos.getContent()), pageable,
                pageCandidatos.getTotalElements());
    }

    public Page<Candidato> buscaCandidatosSistemaNegros(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository.buscaCandidatosSistemaNegros(cursoId, pageable);
        return this.atualizarAprovacaoCandidatosCotistas(pageCandidatos, pageable);
    }

    public Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRendaPpi(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository
                .buscaCandidatosSistemaEscolaPublicaBaixaRendaPpi(cursoId, pageable);
        return this.atualizarAprovacaoCandidatosCotistas(pageCandidatos, pageable);
    }

    public Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRendaPpiPcd(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository
                .buscaCandidatosSistemaEscolaPublicaBaixaRendaPpiPcd(cursoId, pageable);
        return this.atualizarAprovacaoCandidatosCotistas(pageCandidatos, pageable);
    }

    public Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRenda(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository.buscaCandidatosSistemaEscolaPublicaBaixaRenda(cursoId,
                pageable);
        return this.atualizarAprovacaoCandidatosCotistas(pageCandidatos, pageable);
    }

    public Page<Candidato> buscaCandidatosSistemaEscolaPublicaBaixaRendaPcd(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository
                .buscaCandidatosSistemaEscolaPublicaBaixaRendaPcd(cursoId, pageable);
        return this.atualizarAprovacaoCandidatosCotistas(pageCandidatos, pageable);
    }

    public Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpi(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository
                .buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpi(cursoId, pageable);
        return this.atualizarAprovacaoCandidatosCotistas(pageCandidatos, pageable);
    }

    public Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpiPcd(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository
                .buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPpiPcd(cursoId, pageable);
        return this.atualizarAprovacaoCandidatosCotistas(pageCandidatos, pageable);
    }

    public Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRenda(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository
                .buscaCandidatosSistemaEscolaPublicaNaoBaixaRenda(cursoId, pageable);
        return this.atualizarAprovacaoCandidatosCotistas(pageCandidatos, pageable);
    }

    public Page<Candidato> buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPcd(Pageable pageable, Long cursoId) {
        Page<Candidato> pageCandidatos = this.candidatoRepository
                .buscaCandidatosSistemaEscolaPublicaNaoBaixaRendaPcd(cursoId, pageable);
        return this.atualizarAprovacaoCandidatosCotistas(pageCandidatos, pageable);
    }

}
