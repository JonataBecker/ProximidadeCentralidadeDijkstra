package br.com.becker.janelas;

import br.com.becker.centralidade.Mapa;
import br.com.becker.dijkstra.Vizinho;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Classe responsável pela montagem de grafos
 *
 * @author Jonata Becker
 */
public class Grafo extends JPanel {
    
    /** Tamanho do grafo */
    private static final int TAM_GRAFO = 50;
    /** Centro do grafo */
    private static final int CENTER_GRAFO = TAM_GRAFO / 2;
    /** Informações do mapa */
    private final Mapa mapa;
    
    public Grafo(Mapa mapa) {
        this.mapa = mapa;
        setPreferredSize(new Dimension(500, 500));
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                revalidate();
            }
        });
    }

    /**
     * Rotina responsável pelo desenho da janela
     * 
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gd2 = (Graphics2D) g;
        List<Point> list = new ArrayList<>();
        double slice = 2 * Math.PI / mapa.getQuantidadeNodo();
        for (int i = 0; i < mapa.getQuantidadeNodo(); i++) {
            double angle = slice * i;
            int newX = (int) (200 + 120 * Math.cos(angle));
            int newY = (int) (200 + 120 * Math.sin(angle));
            list.add(new Point(newX, newY));
        }
        // Desenha ligações
        for (int i = 0; i < mapa.getQuantidadeNodo(); i++) {
            for (Vizinho vizinho : mapa.getVizinhos(i)) {
                Point origem = list.get(i);
                Point destio = list.get(vizinho.getVizinho());
                gd2.drawLine(origem.x + CENTER_GRAFO, origem.y + CENTER_GRAFO,
                        destio.x + CENTER_GRAFO, destio.y + CENTER_GRAFO);
            }
        }
        int graf = 0;
        // Desenha pontos
        for (Point point : list) {
            gd2.setColor(Color.WHITE);
            gd2.fillOval(point.x, point.y, TAM_GRAFO, TAM_GRAFO);
            gd2.setColor(Color.BLACK);
            gd2.drawOval(point.x, point.y, TAM_GRAFO, TAM_GRAFO);
            char[] grafArray = String.valueOf(graf).toCharArray();
            gd2.drawChars(grafArray, 0, grafArray.length,
                    point.x + CENTER_GRAFO - 3, point.y + CENTER_GRAFO + 3);
            graf++;
        }
    }

}
