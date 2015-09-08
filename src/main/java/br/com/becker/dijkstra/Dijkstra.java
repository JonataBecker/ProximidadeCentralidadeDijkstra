package br.com.becker.dijkstra;

import br.com.becker.centralidade.Mapa;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar o calculo do menor caminho
 *
 * @author Jonata Becker
 */
public class Dijkstra {

    /** Pesso padrão para processamento */
    private static final short PESO = 1;

    /**
     * Executa calculo do menor caminho
     *
     * @param mapa Mapa
     * @param origem Origem
     * @return {@code List<Integer>}
     */
    public List<Integer> calculaMenorCaminho(Mapa mapa, int origem) {
        List<Integer> prioridade = montaListaPrioridade(mapa);
        List<Integer> distancia = montaListaDistancia(mapa);
        distancia.set(origem, 0);
        int w;
        while (true) {
            // Se não possuir mais prioridades
            if (prioridade.isEmpty()) {
                break;
            }
            w = getVerticeMenorDistancia(prioridade, distancia);
            // Remove vertice com menor distância
            prioridade.remove((Integer) w);
            // Percorre vizinhos
            for (Integer vizinho : mapa.getVizinhos(w)) {
                int distanciaW = distancia.get(w) + PESO;
                // Se distância for maior que distância processada
                if (distancia.get(vizinho) > distanciaW) {
                    distancia.set(vizinho, distanciaW);
                }
            }
        }
        return distancia;
    }

    /**
     * Retorna lista inicial de distâncias
     *
     * @param mapa Mapa
     * @return {@code List<Integer>}
     */
    private List<Integer> montaListaDistancia(Mapa mapa) {
        List<Integer> distancia = new ArrayList<>();
        for (int i = 0; i < mapa.getQuantidadeNodo(); i++) {
            distancia.add(Integer.MAX_VALUE);
        }
        return distancia;
    }

    /**
     * Retorna lista de prioridades
     *
     * @param mapa Mapa
     * @return {@code List<Integer>}
     */
    private List<Integer> montaListaPrioridade(Mapa mapa) {
        List<Integer> prioridade = new ArrayList<>();
        for (int i = 0; i < mapa.getQuantidadeNodo(); i++) {
            prioridade.add(i);
        }
        return prioridade;
    }

    /**
     * Retorn o vertice de menor distância existênte na lista de prioridades
     *
     * @param prioridade Lista de prioridades
     * @param distancia Lista de distâncias
     * @return Integer
     */
    private Integer getVerticeMenorDistancia(List<Integer> prioridade, List<Integer> distancia) {
        int menor = Integer.MAX_VALUE;
        for (Integer p : prioridade) {
            if (distancia.get(p) < menor) {
                menor = p;
            }
        }
        return menor;
    }

}
