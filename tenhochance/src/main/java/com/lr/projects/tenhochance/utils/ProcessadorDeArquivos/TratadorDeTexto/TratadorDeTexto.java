package com.lr.projects.tenhochance.utils.ProcessadorDeArquivos.TratadorDeTexto;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TratadorDeTexto {

    private static final Logger logger = LogManager.getLogger(TratadorDeTexto.class);

    private String adicionarQuebraDeLinhaSeMultiplosPipes(String texto) {
        String[] linhas = texto.split("\\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (String linha : linhas) {
            long contador = linha.chars().filter(ch -> ch == '|').count();
            if (contador > 1) {
                linha = linha.replace("|", "|\n");
            }
            stringBuilder.append(linha).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    private String adicionarQuebraDeLinhaAposBarraInvertida(String texto) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] linhas = texto.split("\\n");
        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i].trim();
            if (linha.endsWith("\\")) {
                stringBuilder.append(linha.substring(0, linha.length() - 1)).append("\n");
            } else {
                stringBuilder.append(linha).append("\n");
            }
        }

        return stringBuilder.toString().trim();
    }

    private String adicionarQuebraDeLinhaAposInterrogacao(String texto) {
        return texto.replace("?", "\n");
    }

    private String corrigeLinhasComMaisDeUmRegistroEbarra(String texto) {
        return texto.replace("-/", "-|\n");
    }

    private String removeNumeroDePaginas(String texto) {
        String[] linhas = texto.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (String linha : linhas) {
            String linhaAparada = linha.trim();
            boolean contemNumeroPagina = false;

            if (!linhaAparada.isEmpty()) {
                String[] palavras = linhaAparada.split("\\s+");
                if (palavras.length > 0) {
                    String primeiraPalavra = palavras[0];
                    if (primeiraPalavra.matches("\\d+")) {
                        contemNumeroPagina = true;
                    }
                }
            }

            if (!contemNumeroPagina) {
                stringBuilder.append(linha);
            }
        }

        return stringBuilder.toString().trim();
    }

    private String adicionarQuebraDeLinhaAposBarra(String texto) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] registros = texto.split("\\/\\s*");
        for (String registro : registros) {
            stringBuilder.append(registro.trim()).append("/\n");
        }

        return stringBuilder.toString().trim();
    }

    private String pularLinhaAposParenteses(String texto) {
        String[] linhas = texto.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (String linha : linhas) {
            linha = linha.replace(")", ")\n");
            stringBuilder.append(linha).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    private String adicionarQuebrasDeLinhaEntreCursos(String texto) {
        String[] linhas = texto.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i];
            if (linha.contains("-. ")) {
                if (i > 0 && !linhas[i - 1].endsWith("-")) {
                    stringBuilder.append("\n");
                }

                int primeiroIndicePonto = linha.indexOf(".");
                int segundoIndicePonto = linha.indexOf(".", primeiroIndicePonto + 1);
                if (segundoIndicePonto != -1) {
                    stringBuilder.append(linha.substring(0, segundoIndicePonto + 1)).append("\n");

                    String parteRestante = linha.substring(segundoIndicePonto + 1);
                    int indiceCaracterNumerico = encontrarIndiceCaracterNumerico(parteRestante);
                    if (indiceCaracterNumerico != -1) {
                        stringBuilder.append(parteRestante.substring(0, indiceCaracterNumerico)).append("\n");
                        stringBuilder.append(parteRestante.substring(indiceCaracterNumerico)).append("\n");
                    } else {
                        stringBuilder.append(parteRestante).append("\n");
                    }
                } else {
                    stringBuilder.append(linha).append("\n");
                }
            } else {
                stringBuilder.append(linha).append("\n");
            }
        }

        return stringBuilder.toString().trim();
    }

    private int encontrarIndiceCaracterNumerico(String linha) {
        for (int i = 0; i < linha.length(); i++) {
            char caractere = linha.charAt(i);
            if (Character.isDigit(caractere)) {
                return i;
            }
        }
        return -1;
    }

    private String removerLinhasEmBranco(String texto) {
        String[] linhas = texto.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                stringBuilder.append(linha).append("\n");
            }
        }

        return stringBuilder.toString().trim();
    }

    private String removerEspacosInicioLinhas(String texto) {
        String[] linhas = texto.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (String linha : linhas) {
            String linhaSemEspacosInicio = linha.trim();

            if (!linhaSemEspacosInicio.isEmpty()) {
                stringBuilder.append(linhaSemEspacosInicio).append("\n");
            }
        }

        return stringBuilder.toString().trim();
    }

    private String removerTextoAntes(String texto, String sequencia) {
        int indice = texto.indexOf(sequencia);
        if (indice != -1) {
            return texto.substring(indice + sequencia.length());
        } else {
            return texto;
        }
    }
    
    private String substituirBarraPorPipe(String texto) {
        String[] linhas = texto.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i];
            if (contarVirgulas(linha) == 12) {
                linha = linha.replaceAll("/", "|");
            }
            stringBuilder.append(linha).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    private int contarVirgulas(String linha) {
        int contador = 0;
        for (char caractere : linha.toCharArray()) {
            if (caractere == ',') {
                contador++;
            }
        }
        return contador;
    }

    private String substituirPontoFinalPorInterrogacao(String texto) {
        String[] linhas = texto.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i];
            if (linha.endsWith(".")) {
                linha = linha.substring(0, linha.length() - 1) + "?";
            }
            stringBuilder.append(linha).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    private String concatenarLinhasComBarra(String texto) {
        String[] linhas = texto.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();

        String linhaAnterior = "";
        for (String linha : linhas) {
            if (linha.contains("/")) {
                linhaAnterior = linhaAnterior.trim() + linha.trim();
            } else {
                stringBuilder.append(linhaAnterior).append("\n");
                linhaAnterior = linha;
            }
        }

        stringBuilder.append(linhaAnterior); // Adiciona a última linha
        return stringBuilder.toString().trim();
    }

    private String concatenarLinhasComNomesDeCursoCompostoSemBarra(String texto) {
        String[] linhas = texto.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();

        boolean concatenar = false;
        for (int i = 0; i < linhas.length; i++) {
            String linha = linhas[i];
            if (linha.contains("/")) {
                concatenar = true;
                linha = linha.replace("/", "");
            } else if (concatenar) {
                linha = linhas[i - 1] + linha;
                concatenar = false;
            }
            stringBuilder.append(linha).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    private String removerLinhasRepetidas(String texto) {
        String[] linhas = texto.split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < linhas.length; i++) {
            String linhaAtual = linhas[i];
            if (i < linhas.length - 1) {
                String linhaProxima = linhas[i + 1];
                if (possuiMesmoIdentificador(linhaAtual, linhaProxima)) {
                    continue;
                }
            }
            stringBuilder.append(linhaAtual).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    public String adicionarPontoEVirgulaNoFinalSeNecessario(String texto) {
        String[] linhas = texto.split("\\n");
        StringBuilder stringBuilder = new StringBuilder();
        for(String linha : linhas) {
            if (linha.matches("^\\d{8}.*") && !linha.endsWith(";")) {
                stringBuilder.append(linha).append(";\n");
            }
            else {
                stringBuilder.append(linha).append("\n");
            }
        }

       
        return stringBuilder.toString().trim();
    }


    private boolean possuiMesmoIdentificador(String linha1, String linha2) {
        String identificador1 = extrairIdentificador(linha1);
        String identificador2 = extrairIdentificador(linha2);
        return identificador1.equals(identificador2);
    }

    private String extrairIdentificador(String linha) {
        String[] partes = linha.split(",");
        if (partes.length > 0) {
            return partes[0].trim();
        }
        return "";
    }

    private String deletarUltimaLinha(String texto) {
        String[] linhas = texto.split("\\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < linhas.length - 1; i++) {
            stringBuilder.append(linhas[i]).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    private static String geraStringcursos(String texto) {
        String[] linhas = texto.split("\\n");
        int contador = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String linha : linhas) {
            if (!linha.matches("^\\d{8}.*")) {
                contador++;
                stringBuilder.append(contador).append(",").append(linha).append(";").append("\n");

            }
        }

        return stringBuilder.toString().trim();
    }

    public String removerLinhasSemIdentificador(String texto) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] linhas = texto.split("\\n");

        for (String linha : linhas) {
            if (linha.matches("^\\d{8}.*")) {
                stringBuilder.append(linha).append("\n");
            }
        }

        return stringBuilder.toString().trim();
    }

    private static String adicionarContadorAosRegistros(String texto) {
        String[] linhas = texto.split("\\n");
        int contador = 0;
        StringBuilder stringBuilder = new StringBuilder();
        
        for (String linha : linhas) {
            if (!linha.matches("^\\d{8}.*")) {
                contador++;
            } else {
                linha = linha.replaceAll(";$", "," + contador + ";");
            }
            stringBuilder.append(linha).append("\n");
        }

        return stringBuilder.toString().trim();
    }

    private String substituirNumerosComPontosPorStringVazia(String texto) {
        return texto.replaceAll("\\b\\d+\\.\\d+\\.\\d+\\b", "");
    }

    private String substiuirPipePorPontoEvirgula(String texto) {
        return texto.replaceAll("\\|", ";");
    }

    public String removerVirgulaEntreMaiusculas(String texto) {
        return texto.replaceAll("(?<=[A-Z]), (?=[A-Z])", " ");
    }

    public String removerEspacoAposVirgula(String texto) {
        return texto.replace(", ", ",");
    }

  
    public static List<String> tratarTexto(String texto,String sequencia) {

        logger.info("Iniciando tratamento do texto.");

        List<String> result = new ArrayList<>();
        TratadorDeTexto tratadorDeTexto = new TratadorDeTexto();
        String candidatos = tratadorDeTexto.removerTextoAntes(texto, sequencia);

        candidatos = tratadorDeTexto.removeNumeroDePaginas(candidatos);
        candidatos = tratadorDeTexto.adicionarQuebraDeLinhaAposBarra(candidatos);
        candidatos = tratadorDeTexto.adicionarQuebrasDeLinhaEntreCursos(candidatos);
        candidatos = tratadorDeTexto.removerLinhasEmBranco(candidatos);
        candidatos = tratadorDeTexto.removerEspacosInicioLinhas(candidatos);
        candidatos = tratadorDeTexto.pularLinhaAposParenteses(candidatos);
        candidatos = tratadorDeTexto.removerLinhasEmBranco(candidatos);
        candidatos = tratadorDeTexto.removerEspacosInicioLinhas(candidatos);
        candidatos = tratadorDeTexto.substituirBarraPorPipe(candidatos);
        candidatos = tratadorDeTexto.substituirPontoFinalPorInterrogacao(candidatos);
        candidatos = tratadorDeTexto.concatenarLinhasComBarra(candidatos);
        candidatos = tratadorDeTexto.concatenarLinhasComNomesDeCursoCompostoSemBarra(candidatos);
        candidatos = tratadorDeTexto.removerLinhasRepetidas(candidatos);
        candidatos = tratadorDeTexto.adicionarQuebraDeLinhaAposBarraInvertida(candidatos);
        candidatos = tratadorDeTexto.adicionarQuebraDeLinhaAposInterrogacao(candidatos);
        candidatos = tratadorDeTexto.removerLinhasEmBranco(candidatos);
        candidatos = tratadorDeTexto.deletarUltimaLinha(candidatos);
        candidatos = tratadorDeTexto.adicionarQuebraDeLinhaSeMultiplosPipes(candidatos);
        candidatos = tratadorDeTexto.corrigeLinhasComMaisDeUmRegistroEbarra(candidatos);
        candidatos = tratadorDeTexto.removerLinhasEmBranco(candidatos);
        candidatos = tratadorDeTexto.substituirNumerosComPontosPorStringVazia(candidatos);
        candidatos = tratadorDeTexto.removerEspacosInicioLinhas(candidatos);
        candidatos = tratadorDeTexto.substiuirPipePorPontoEvirgula(candidatos);
        candidatos = tratadorDeTexto.adicionarPontoEVirgulaNoFinalSeNecessario(candidatos);
        candidatos = adicionarContadorAosRegistros(candidatos);

        String cursos = geraStringcursos(candidatos);

        cursos = tratadorDeTexto.removerVirgulaEntreMaiusculas(cursos);
        cursos = tratadorDeTexto.removerEspacoAposVirgula(cursos);

        candidatos = tratadorDeTexto.removerLinhasSemIdentificador(candidatos);
        candidatos = tratadorDeTexto.removerEspacoAposVirgula(candidatos);
        result.add(0, cursos);
        result.add(1, candidatos);

        logger.info("Finalizando tratamento do texto.");

        return result;
    }

}
