package br.com.becker.janelas;

import br.com.becker.centralidade.Mapa;
import br.com.becker.centralidade.ProximidadeCentralidade;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe responsável pelo controle da janela principal
 *
 * @author Jonata Becker
 */
public class Pricipal extends JFrame {

    private final JPanel panelGrafico;
    private final JPanel panelGrafo;

    public Pricipal() {
        super("Proximidade centralidade");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Arquivo");
        menu.add(new AbstractAction("Abrir") {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                calcula(fileChooser.getSelectedFile());
            }
        });
        menuBar.add(menu);
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
        setVisible(true);
        panelGrafico = new JPanel();
        add(panelGrafico, BorderLayout.WEST);
        panelGrafo = new JPanel();
        add(panelGrafo, BorderLayout.CENTER);
        openInit();
    }

    /**
     * Abre arquivo inicial casos exista
     */
    private void openInit() {
        File file = new File("c:\\tmp\\csv.csv");
        if (file.exists()) {
            calcula(file);
        }
    }
    
    /**
     * Executa calculo da proximidade da centralidade
     *
     * @param file Arquivo
     */
    private void calcula(File file) {
        try {
            Mapa mapa = new Mapa();
            mapa.loadCsv(file);
            ProximidadeCentralidade proximidadeCentralidade = new ProximidadeCentralidade();
            List<Float> valorProximidadeCentralidade = proximidadeCentralidade.calcula(mapa);
            panelGrafico.add(new Grafico(valorProximidadeCentralidade));
            panelGrafo.add(new Grafo(mapa));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Não foi possivel carregar o arquivo!");
        }
    }
}
