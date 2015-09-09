package br.com.becker.janelas;

import br.com.becker.centralidade.Mapa;
import br.com.becker.centralidade.ProximidadeCentralidade;
import java.awt.Font;
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
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Classe responsável pelo controle da janela principal
 *
 * @author Jonata Becker
 */
public class Pricipal extends JFrame {

    public Pricipal() {
        super("Proximidade centralidade");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
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
    }

    /**
     * Executa calculo da proximidade da centralidade
     * 
     * @param file Arquivo
     */
    private void calcula(File file) {
        try {
            Mapa mapa = new Mapa();
            mapa.loadCsv(new File("c:\\Tmp\\csv.csv"));
            ProximidadeCentralidade proximidadeCentralidade = new ProximidadeCentralidade();
            List<Float> valorProximidadeCentralidade = proximidadeCentralidade.calcula(mapa);
            addGrafico(valorProximidadeCentralidade);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Não foi possivel carregar o arquivo!");
        }
    }

    /**
     * Adiciona gráfico na tela
     * 
     * @param valorProximidadeCentralidade 
     */
    private void addGrafico(List<Float> valorProximidadeCentralidade) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int nodo = 0;
        for (Float valor : valorProximidadeCentralidade) {
            System.out.println(valor);
            dataset.setValue("Nodo " + nodo, valor);
            nodo++;
        }
        JFreeChart chart = ChartFactory.createPieChart(
                "Proximidade da centralidade",
                dataset,
                false,
                false,
                false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data");
        PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0} = {1}");
        plot.setLabelGenerator(generator);
        final JPanel panel = new ChartPanel(chart);
        add(panel);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                panel.revalidate();
            }
        });
    }

}
