package com.lr.projects.tenhochance.utils.Classificador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lr.projects.tenhochance.entity.Candidato;
import com.lr.projects.tenhochance.utils.Scrapping.ScrappingCandidato;

@Service
public class Buscador {

    @Autowired
    private ScrappingCandidato scrappingCandidato;

    public int buscaBinariaCandidatoAprovado(List<Candidato> candidatos) {
        
        int esquerda = 0;
        int direita = candidatos.size() - 1;
        int meio;

        while (esquerda <= direita) {
            meio = esquerda + (direita - esquerda) / 2;

            Candidato candidatoMeio = candidatos.get(meio);
            boolean aprovado = scrappingCandidato.consultaCandidato(candidatoMeio.getNome(),
                    candidatoMeio.getNumeroInscricao().toString());

            if (aprovado) {

                if (meio == direita || !scrappingCandidato.consultaCandidato(candidatos.get(meio + 1).getNome(),
                        candidatos.get(meio + 1).getNumeroInscricao().toString())) {
                    return meio;
                }
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return -1;
    }

}
