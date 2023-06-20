package com.lr.projects.tenhochance.utils.Classificador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lr.projects.tenhochance.entity.Candidato;

@Service
public class Ordenador {

    public static List<Candidato> mergeSort(List<Candidato> candidatos) {
        if (candidatos.size() <= 1) {
            return candidatos;
        }

        int meio = candidatos.size() / 2;
        List<Candidato> esquerda = candidatos.subList(0, meio);
        List<Candidato> direita = candidatos.subList(meio, candidatos.size());

        return merge(mergeSort(esquerda), mergeSort(direita));
    }

    public static List<Candidato> merge(List<Candidato> esquerda, List<Candidato> direita) {
        List<Candidato> resultado = new ArrayList<>();

        int i = 0, j = 0;
        while (i < esquerda.size() && j < direita.size()) {
            if (esquerda.get(i).getClassificacaoFinalUniversal() <= direita.get(j).getClassificacaoFinalUniversal()) {
                resultado.add(esquerda.get(i++));
            } else {
                resultado.add(direita.get(j++));
            }
        }

        while (i < esquerda.size()) {
            resultado.add(esquerda.get(i++));
        }

        while (j < direita.size()) {
            resultado.add(direita.get(j++));
        }

        return resultado;
    }

}
