package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.resources.SerieTemporal;

public class IndicadorAbertura implements Indicador{

	@Override
	public double calcula(int posicao, SerieTemporal serie, int diasParaMedia){
		return serie.getCandle(posicao).getAbertura();
	}
	@Override
	public String toString(){
		return "Indicador de Abertura";
	}

}
