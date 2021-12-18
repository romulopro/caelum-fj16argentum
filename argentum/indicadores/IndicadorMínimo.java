package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.resources.SerieTemporal;

public class IndicadorMínimo implements Indicador{

	@Override
	public double calcula(int posicao, SerieTemporal serie, int diasParaMedia){
		return serie.getCandle(posicao).getMinimo();
	}
	@Override
	public String toString(){
		return "Indicador de Mínimo";
	}

}
