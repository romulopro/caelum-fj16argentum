package br.com.caelum.argentum.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.IndicadorAbertura;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.IndicadorMáximo;
import br.com.caelum.argentum.indicadores.IndicadorMínimo;
import br.com.caelum.argentum.indicadores.MediaMovelPonderada;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;

public class MenuIndicadores {

	private JMenuBar menuBar;
	private HashMap<JCheckBoxMenuItem, Indicador> indicadoresNoMenu;
	private HashMap<JCheckBoxMenuItem, Indicador> indicadoresNoMenu1;
	private HashMap<JCheckBoxMenuItem, Indicador> indicadoresNoMenu3;
	private HashMap<JCheckBoxMenuItem, Indicador> indicadoresNoMenu2;

	public MenuIndicadores() {
		List<Indicador> indicadoresDeFechamento = new ArrayList<Indicador>();
		List<Indicador> indicadoresDeAbertura = new ArrayList<Indicador>();
		List<Indicador> indicadoresDeMinimo = new ArrayList<Indicador>();
		List<Indicador> indicadoresDeMaximo = new ArrayList<Indicador>();
		indicadoresDeFechamento.add(new IndicadorFechamento());
		indicadoresDeFechamento.add(new MediaMovelSimples(new IndicadorFechamento()));
		indicadoresDeFechamento.add(new MediaMovelPonderada(new IndicadorFechamento()));

		indicadoresDeAbertura.add(new IndicadorAbertura());
		indicadoresDeAbertura.add(new MediaMovelSimples(new IndicadorAbertura()));
		indicadoresDeAbertura.add(new MediaMovelPonderada(new IndicadorAbertura()));

		indicadoresDeMinimo.add(new IndicadorMáximo());
		indicadoresDeMinimo.add(new MediaMovelSimples(new IndicadorMáximo()));
		indicadoresDeMinimo.add(new MediaMovelPonderada(new IndicadorMáximo()));

		indicadoresDeMaximo.add(new IndicadorMínimo());
		indicadoresDeMaximo.add(new MediaMovelSimples(new IndicadorMínimo()));
		indicadoresDeMaximo.add(new MediaMovelPonderada(new IndicadorMínimo()));

		// mais indicadores
		menuBar = new JMenuBar();
		JMenu menuIndicadoresDeFechamento = new JMenu("Indicadores de Fechamento");
		JMenu menuIndicadoresDeAbertura = new JMenu("Indicadores de Abertura");
		JMenu menuIndicadoresdeMinimo = new JMenu("Indicadores de Mínimo");
		JMenu menuIndicadoresDeMaximo = new JMenu("Indicadores de Máximo");

		menuBar.add(menuIndicadoresDeFechamento);
		menuBar.add(menuIndicadoresDeAbertura);
		menuBar.add(menuIndicadoresdeMinimo);
		menuBar.add(menuIndicadoresDeMaximo);
		indicadoresNoMenu = new HashMap<JCheckBoxMenuItem, Indicador>();
		indicadoresNoMenu1 = new HashMap<JCheckBoxMenuItem, Indicador>();
		indicadoresNoMenu2 = new HashMap<JCheckBoxMenuItem, Indicador>();
		indicadoresNoMenu3 = new HashMap<JCheckBoxMenuItem, Indicador>();

		adicionaGraficoseMenus(indicadoresDeFechamento, menuIndicadoresDeFechamento);
		adicionaGraficoseMenus(indicadoresDeAbertura, menuIndicadoresDeAbertura);
		adicionaGraficoseMenus(indicadoresDeMinimo, menuIndicadoresdeMinimo);
		adicionaGraficoseMenus(indicadoresDeMaximo, menuIndicadoresDeMaximo);

	}

	private void adicionaGraficoseMenus(List<Indicador> indicadores, JMenu menuIndicadores) {
		for (Indicador indicador : indicadores) {
			JCheckBoxMenuItem opcaoIndicador = new JCheckBoxMenuItem(indicador.toString(), true);
			menuIndicadores.add(opcaoIndicador);
			indicadoresNoMenu3.put(opcaoIndicador, indicador);

		}
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public List<Indicador> getIndicadoresSelecionados() {
		ArrayList<Indicador> indicadores = new ArrayList<Indicador>();
		for (JCheckBoxMenuItem menuItem : indicadoresNoMenu.keySet()) {
			if (menuItem.isSelected())
				indicadores.add(indicadoresNoMenu.get(menuItem));
		}
		for (JCheckBoxMenuItem menuItem : indicadoresNoMenu1.keySet()) {
			if (menuItem.isSelected())
				indicadores.add(indicadoresNoMenu1.get(menuItem));
		}
		for (JCheckBoxMenuItem menuItem : indicadoresNoMenu2.keySet()) {
			if (menuItem.isSelected())
				indicadores.add(indicadoresNoMenu2.get(menuItem));
		}
		for (JCheckBoxMenuItem menuItem : indicadoresNoMenu3.keySet()) {
			if (menuItem.isSelected())
				indicadores.add(indicadoresNoMenu3.get(menuItem));
		}
		return indicadores;

	}

}
