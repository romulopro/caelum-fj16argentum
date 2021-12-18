package br.com.caelum.argentum.indicadores;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.caelum.argentum.resources.SerieTemporal;

public class MediaMovelSimples implements Indicador {
	private Indicador outroIndicador;
	private static final Logger logger = LogManager.getLogger(MediaMovelSimples.class);
	public double calcula(int posicao, SerieTemporal serie, int diasParaMedia) {
		//logger.info("Calculando média móvel simples para posição " + posicao);
		double soma = 0;
		for (int i = posicao -diasParaMedia ;i<= posicao; i++){
			soma += outroIndicador.calcula(i, serie, diasParaMedia);
			logger.info("Calculando média móvel simples para posição " + posicao +", com soma "+ soma);
			//soma+= serie.getCandle(i).getFechamento();
			
			
		}
		
		return soma /(diasParaMedia+1);
	}
	public MediaMovelSimples(Indicador outroIndicador){
		this.outroIndicador = outroIndicador;
		
	}
	public String toString(){
		return "Media Móvel Simples do " + outroIndicador;
	}
}
