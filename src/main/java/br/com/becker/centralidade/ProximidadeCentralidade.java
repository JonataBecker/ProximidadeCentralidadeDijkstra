package br.com.becker.centralidade;

import br.com.becker.dijkstra.Dijkstra;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por calcula a priximidade da centralidade 
 * 
 * @author Jonata Becker
 */
public class ProximidadeCentralidade {
    
    
    public List<Float> calcula(Mapa mapa) {
        
        List<Float> proximidade = new ArrayList<>();
        
        Dijkstra dijkstra = new Dijkstra();
        List<List<Integer>> mapaMenorCaminho = new ArrayList<>();
        // Percorre nodos
        for (int nodo = 0; nodo < mapa.getQuantidadeNodo(); nodo ++) {
            mapaMenorCaminho.add(dijkstra.calculaMenorCaminho(mapa, nodo));
        }

        System.out.println("Matriz de adjacência");
        for (int nodo = 0; nodo < mapa.getQuantidadeNodo(); nodo ++) {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            for (Integer eml : mapa.getLinha(nodo)) {
                sb.append(eml).append("|");
            }
            System.out.println(sb);
        }
        

        System.out.println();
        System.out.println();
        System.out.println("Matriz menor caminho");
        for (List<Integer> linha : mapaMenorCaminho) {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            for (Integer eml : linha) {
                sb.append(eml).append("|");
            }
            System.out.println(sb);
        }        
        
        
        for (int nodo = 0; nodo < mapa.getQuantidadeNodo(); nodo ++) {
            int distanciaTotal = 0;
            for (List<Integer> linha : mapaMenorCaminho) {
                distanciaTotal += linha.get(nodo);
            }
            float proximidadeCentralidade = (float) (mapa.getQuantidadeNodo() - 1) / distanciaTotal;
            proximidade.add(proximidadeCentralidade);
        }
        
        return proximidade;
    }
    
}
