package br.com.becker.centralidade;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Classe responsável pelo mapa de atores
 *
 * @author Jonata Becker
 */
public class Mapa {

    /** Matriz de adjacência */
    private final List<List<Integer>> mapa;

    public Mapa() {
        mapa = new ArrayList<>();
    }

    /**
     * Executa carregamento da matriz de adjacência por um arquivo CSV
     *
     * @param file Arquivo CSV
     * @throws IOException Problema no momento da leitura do arquivo CSV
     */
    public void loadCsv(File file) throws IOException {
        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            List<Integer> linha = new ArrayList<>();
            Iterator<String> linhaCsv = record.iterator();
            while (linhaCsv.hasNext()) {
                try {
                    linha.add(Integer.parseInt(linhaCsv.next()));
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Não foi possivel converter arquivo!", e);
                }
            }
            mapa.add(linha);
        }
    }

    /**
     * Retorna lista de nodos vizinhos de um determinado nodo
     * 
     * @param nodo Nodo
     * @return {@code List<Integer>}
     */
    public List<Integer> getVizinhos(int nodo) {
        List<Integer> list = new ArrayList<>();
        int indice = 0;
        for (Integer nodoMapa : mapa.get(nodo)) {
            if (nodoMapa > 0) {
                list.add(indice);
            }
            indice++;
        }
        return list;
    }
    
    /**
     * Retorna a quantidade de nodos
     * 
     * @return int
     */
    public int getQuantidadeNodo() {
        return mapa.size();
    }

    /**
     * Retorna linha da matriz
     * 
     * @param nodo Nodo
     * @return {@code List<Integer>}
     */
    public List<Integer> getLinha(int nodo) {
        return mapa.get(nodo);
    }
    
}
