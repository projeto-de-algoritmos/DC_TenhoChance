package com.lr.projects.tenhochance.utils.ProcessadorDeArquivos.ProcessadorDePDF;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.lr.projects.tenhochance.utils.ProcessadorDeArquivos.TratadorDeTexto.TratadorDeTexto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProcessadorDePDF {

    private static final Logger logger = LogManager.getLogger(ProcessadorDePDF.class);


    private static String lerPDF(String caminhoPDF, Integer paginaInicial, Integer paginaFinal) {
        try (PDDocument document = PDDocument.load(new File(caminhoPDF))) {
            logger.info("PDF carregado com sucesso.");
            logger.info("Iniciando leitura do PDF.");
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setStartPage(paginaInicial);
            pdfTextStripper.setEndPage(paginaFinal);
            logger.info("Leitura do PDF finalizada.");
            return pdfTextStripper.getText(document);
        } catch (IOException e) {
            logger.error("Erro ao carregar PDF: " + e.getMessage());
            return null;
        }
    }

    public static List<String> processaPDF(String caminhoPDF,Integer paginaInicial,Integer paginaFinal,String sequenciaApagada) {
        logger.info("Iniciando processamento do PDF.");
        String texto = ProcessadorDePDF.lerPDF(caminhoPDF, paginaInicial, paginaFinal);
        List<String> textoTratado = TratadorDeTexto.tratarTexto(texto,sequenciaApagada);
        return textoTratado;
    }

}
