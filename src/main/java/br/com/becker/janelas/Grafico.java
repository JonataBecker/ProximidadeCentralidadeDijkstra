package br.com.becker.janelas;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
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
 * Classe responsável pela exibição do gráfio
 *
 * @author Jonata Becker
 */
public class Grafico extends JPanel {

    private final List<Float> valorProximidadeCentralidade;

    public Grafico(List<Float> valorProximidadeCentralidade) {
        this.valorProximidadeCentralidade = valorProximidadeCentralidade;
        addGrafico();
    }

    private void addGrafico() {
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
