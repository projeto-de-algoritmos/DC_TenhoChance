package com.lr.projects.tenhochance.api.controllers.external.cespe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lr.projects.tenhochance.utils.Scrapping.ScrappingCandidato;


@RestController
@RequestMapping("/integracao/cespe")
public class IntegracaoCespe {

    @Autowired
    private ScrappingCandidato scrappingCandidato;

    @GetMapping("/consultaCandidato")
    public ResponseEntity<Boolean> verificaSeCandidatoFoiAprovado(@RequestParam String nomeCandidato, @RequestParam String numeroInscricao) {
        return ResponseEntity.ok(scrappingCandidato.consultaCandidato(nomeCandidato, numeroInscricao));
    }
    
}   