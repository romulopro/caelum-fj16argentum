package br.com.caelum.argentum.grafico;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.resources.SerieTemporal;

public class GeradorDeGrafico {
	private SerieTemporal serie;
	private int comeco;
	private int fim;
	private DefaultCategoryDataset dados;
	private JFreeChart grafico;

	public GeradorDeGrafico(SerieTemporal serie, int comeco, int fim) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
		this.dados = new DefaultCategoryDataset();
		this.grafico = ChartFactory.createLineChart("Indicadores", "Dias",
				"Valores", dados, PlotOrientation.VERTICAL, true, true, false);

	}

	public void plotaIndicador(Indicador ind) {

		for (int i = comeco; i <=fim; i++) {
			double valor = ind.calcula(i, serie, 2);
			dados.addValue(valor, ind.toString(), Integer.valueOf(i));
		}
	}

	public void salva(OutputStream out) throws IOException {
		ChartUtils.writeChartAsPNG(out, grafico, 500, 350);

	}

	public JPanel getPainel() {
		return new ChartPanel(grafico);
	}
}
