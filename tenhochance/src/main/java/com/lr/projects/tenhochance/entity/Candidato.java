package com.lr.projects.tenhochance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "candidato")
public class Candidato {
    
    @Id
    @Column(name = "numero_inscricao", nullable = false, unique = true)
    private Long numeroInscricao;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "nota_final", nullable = false)
    private Double notaFinal;

    @JsonIgnore
    @Column(name = "classificacao_final_universal")
    private Integer classificacaoFinalUniversal;

    @JsonIgnore
    @Column(name = "classificacao_final_negros")
    private Integer classificacaoFinalNegros;

    @JsonIgnore
    @Column(name = "classificacao_final_escola_publica_baixa_renda_ppi")
    private Integer classificacaoFinalEscolaPublicaBaixaRendaPpi;

    @JsonIgnore
    @Column(name = "classificacao_final_escola_publica_baixa_renda_ppi_pcd")
    private Integer classificacaoFinalEscolaPublicaBaixaRendaPpiPcd;

    @JsonIgnore
    @Column(name = "classificacao_final_escola_publica_baixa_renda")
    private Integer classificacaoFinalEscolaPublicaBaixaRenda;

    @JsonIgnore
    @Column(name = "classificacao_final_escola_publica_baixa_renda_pcd")
    private Integer classificacaoFinalEscolaPublicaBaixaRendaPcd;

    @JsonIgnore
    @Column(name = "classificacao_final_escola_publica_nao_baixa_renda_ppi")
    private Integer classificacaoFinalEscolaPublicaNaoBaixaRendaPpi;

    @JsonIgnore
    @Column(name = "classificacao_final_escola_publica_nao_baixa_renda_ppi_pcd")
    private Integer classificacaoFinalEscolaPublicaNaoBaixaRendaPpiPcd;

    @JsonIgnore
    @Column(name = "classificacao_final_escola_publica_nao_baixa_renda")
    private Integer classificacaoFinalEscolaPublicaNaoBaixaRenda;

    @JsonIgnore
    @Column(name = "classificacao_final_escolas_publica_nao_baixa_renda_pcd")
    private Integer classificacaoFinalEscolaPublicaNaoBaixaRendaPcd;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
    
    @Column(name = "aprovado")
    private Boolean aprovado;

}
