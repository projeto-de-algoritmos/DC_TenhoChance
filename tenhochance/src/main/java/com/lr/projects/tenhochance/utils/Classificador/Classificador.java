package com.lr.projects.tenhochance.utils.Classificador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lr.projects.tenhochance.entity.Candidato;
import com.lr.projects.tenhochance.repository.CandidatoRepository;
import com.lr.projects.tenhochance.utils.Scrapping.ScrappingCandidato;

@Service
public class Classificador {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private ScrappingCandidato scrappingCandidato;

    @Autowired
    private Buscador buscador;

    @Value("${classificador.recursao.maxima}")
    private Integer maximoRecursoes;

    @Transactional
    public void atualizarAprovacaoCandidatos(Page<Candidato> candidatosPage) {
        List<Candidato> candidatos = candidatosPage.getContent();
        int ultimoAprovadoIndex = buscador.buscaBinariaCandidatoAprovado(candidatos);

        for (int i = 0; i <= ultimoAprovadoIndex; i++) {
            Candidato candidato = candidatos.get(i);
            candidato.setAprovado(true);
            candidatoRepository.save(candidato);
        }

        for (int i = ultimoAprovadoIndex + 1; i < candidatos.size(); i++) {
            Candidato candidato = candidatos.get(i);
            candidato.setAprovado(false);
            candidatoRepository.save(candidato);
        }
    }

    @Transactional
    public void atualizaAprovacaoCandidatosCotistasPorPagina(Page<Candidato> candidatosPage) {
        List<Candidato> candidatos = candidatosPage.getContent();

        candidatos.forEach(candidato -> {
            boolean aprovado = this.scrappingCandidato.consultaCandidato(candidato.getNome(),
                    candidato.getNumeroInscricao().toString());

            if (aprovado) {
                candidato.setAprovado(true);
            } else {
                candidato.setAprovado(false);

            }
            candidatoRepository.save(candidato);
        });

        this.substituirAprovadoComNotaInferior(candidatosPage, candidatosPage.getPageable());

    }

   public Page<Candidato> substituirAprovadoComNotaInferior(Page<Candidato> page, Pageable pageable) {
    return substituirAprovadoComNotaInferior(page, pageable, 0);
}

private Page<Candidato> substituirAprovadoComNotaInferior(Page<Candidato> page, Pageable pageable, int depth) {
    boolean verificacao = verificarAprovadoComNotaInferior(page);

    if (verificacao) {
        return page;
    } else {
        List<Candidato> candidatos = new ArrayList<>(page.getContent());

        for (int i = 0; i < candidatos.size(); i++) {
            Candidato candidato = candidatos.get(i);

            if (candidato.getAprovado()) {
                candidatos.remove(i);

                if (depth < maximoRecursoes) {
                    Page<Candidato> novaPagina = substituirAprovadoComNotaInferior(page, pageable, depth + 1);

                    if (novaPagina != null && novaPagina.hasContent()) {
                        Candidato novoCandidato = novaPagina.getContent().get(0);
                        candidatos.add(i, novoCandidato);
                        return new PageImpl<>(candidatos, pageable, page.getTotalElements());
                    }
                }
            }
        }

        return page;
    }
}


    private boolean verificarAprovadoComNotaInferior(Page<Candidato> page) {
        List<Candidato> candidatos = page.getContent();

        for (Candidato candidatoAprovado : candidatos) {
            if (candidatoAprovado.getAprovado()) {
                for (Candidato candidatoReprovado : candidatos) {
                    if (!candidatoReprovado.getAprovado()
                            && candidatoReprovado.getNotaFinal() > candidatoAprovado.getNotaFinal()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
