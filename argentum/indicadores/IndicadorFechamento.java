package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.resources.SerieTemporal;

public class IndicadorFechamento implements Indicador{

	@Override
	public double calcula(int posicao, SerieTemporal serie, int diasParaMedia){
		return serie.getCandle(posicao).getFechamento();
	}
	@Override
	public String toString(){
		return "Indicador de Fechamento";
	}

}
