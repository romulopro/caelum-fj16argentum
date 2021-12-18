package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.resources.SerieTemporal;

public class MediaMovelPonderada implements Indicador {

	private Indicador outroIndicador;

	public double calcula(int posicao, SerieTemporal serie, int diasParaMedia) {
		

		double soma = 0;
		int j = 1;
		int fator = 1;
		for (int i = posicao - diasParaMedia; i <= posicao; i++) {
			soma += outroIndicador.calcula(i, serie, diasParaMedia)*j;
			// soma += serie.getCandle(i).getFechamento() * (j);
			fator *= j;
			j++;

		}
		return soma / fator;

	}
	
	public  MediaMovelPonderada(Indicador outroIndicador){
		this.outroIndicador = outroIndicador;
		
	}

	public String toString() {
		return "Media Móvel Ponderada do " + outroIndicador;
	}

}
