package br.com.becker.janelas;

import br.com.becker.centralidade.Mapa;
import br.com.becker.centralidade.ProximidadeCentralidade;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
        setVisible(true);
        calcula();
    }

    private void calcula() {
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
