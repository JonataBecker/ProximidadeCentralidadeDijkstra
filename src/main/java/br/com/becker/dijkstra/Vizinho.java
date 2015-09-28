package br.com.becker.dijkstra;

/**
 * Classe responsável por informações do vizinho
 *
 * @author Jonata Becker
 */
public class Vizinho {

    /** Vizinho */
    private final int vizinho;
    /** Peso */
    private final int peso;

    public Vizinho(int vizinho, int peso) {
        this.vizinho = vizinho;
        this.peso = peso;
    }

    /**
     * Retorna o vizinho
     *
     * @return int
     */
    public int getVizinho() {
        return vizinho;
    }
    
    /**
     * Retorna o peso
     *
     * @return int
     */
    public int getPeso() {
        return peso;
    }

}
