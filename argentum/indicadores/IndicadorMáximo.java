package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.resources.SerieTemporal;

public class IndicadorMáximo implements Indicador{

	@Override
	public double calcula(int posicao, SerieTemporal serie, int diasParaMedia){
		return serie.getCandle(posicao).getMaximo();
	}
	@Override
	public String toString(){
		return "Indicador de Máximo";
	}

}
