package br.com.caelum.argentum.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.caelum.argentum.grafico.GeradorDeGrafico;
import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.resources.Candle;
import br.com.caelum.argentum.resources.CandlestickFactory;
import br.com.caelum.argentum.resources.Negocio;
import br.com.caelum.argentum.resources.SerieTemporal;
//Classe principal
public class ArgentumUI {
	// private final JTable tabela;
	private JFrame janela;
	private JPanel painelPrincipal;

	private JTable tabela;
	private Container painelBotoes;
	private JTabbedPane abas;
	private JFormattedTextField campoData;
	private MenuIndicadores menu;
	

	public static void main(String[] args) {
		new ArgentumUI().montaTela();

	}

	private void montaTela() {
		preparaJanela();
		preparaMenu();
		preparaPainelPrincipal();
		preparaAbas();
		preparaTitulo();
		preparaTabela();
		preparaPainelBotoes();
		preparaCampoData();
		preparaBotaoCarregar();
		preparaBotaoSair();

		mostraJanela();

	}

	private void preparaMenu() {
		menu = new MenuIndicadores();
		janela.setJMenuBar(menu.getMenuBar());
		
		
	}

	private void preparaCampoData() {
		try {
			JLabel labelData = new JLabel("Apenas a partir de");
			MaskFormatter mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');
			campoData = new JFormattedTextField(mascara);
			painelBotoes.add(labelData);
			painelBotoes.add(campoData);

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private void preparaAbas() {
		abas = new JTabbedPane();
		abas.addTab("Tabela", null);
		abas.addTab("Gráfico", null);
		painelPrincipal.add(abas);

	}

	private void preparaPainelBotoes() {
		
		painelBotoes = new JPanel(new GridLayout());
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	}

	private void preparaTitulo() {
		JLabel titulo = new JLabel("Lista de Negócios", SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		painelPrincipal.add(titulo, BorderLayout.NORTH);

	}

	private void preparaTabela() {
		tabela = new JTable();
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(tabela);
		// painelPrincipal.add(scroll, BorderLayout.CENTER);
		abas.setComponentAt(0, scroll);
	}

	private void preparaJanela() {
		
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void preparaPainelPrincipal() {
		
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
		janela.add(painelPrincipal);

	}

	private void preparaBotaoCarregar() {
		
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.setMnemonic(KeyEvent.VK_C);
		botaoCarregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carregaDados();
				// new EscolhedorDeXML().escolhe();
			}
		});

		painelBotoes.add(botaoCarregar);

	}

	private void preparaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.setMnemonic(KeyEvent.VK_S);
		botaoSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);


			}
		});
		painelBotoes.add(botaoSair); // .setMnemonic(KeyEvent.VK_S)

	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(940, 540);
		janela.setVisible(true);

		JTable table = new JTable();

		table.setBorder(new LineBorder(Color.black));
		table.setGridColor(Color.black);
		table.setShowGrid(true);

		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(table);
		scroll.setSize(450, 450);
	}

	private void carregaDados() {
		List<Negocio> lista = new EscolhedorDeXML().escolhe();
		if(campoData.getValue() != null) {
			try {
			new FiltradorPorData(campoData.getText()).filtra(lista);
			}catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Data inválida");
				campoData = null;
			}
		}
		ArgentumTableModel ntm = new ArgentumTableModel(lista);
		tabela.setModel(ntm);

		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candle> candles = fabrica.constroiCandles(lista);
		SerieTemporal serie = new SerieTemporal(candles);

		GeradorDeGrafico gerador = new GeradorDeGrafico(serie, 2, serie.getTotal() - 1);
		List<Indicador> indicadores = menu.getIndicadoresSelecionados();
		for(Indicador indicador : indicadores) {
			gerador.plotaIndicador(indicador);
		}
		abas.setComponentAt(1, gerador.getPainel());
	}
}
