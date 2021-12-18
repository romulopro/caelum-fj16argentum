package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.resources.SerieTemporal;

public interface Indicador {

	public abstract double calcula(int posicao, SerieTemporal serie, int diasParaMedia);

}