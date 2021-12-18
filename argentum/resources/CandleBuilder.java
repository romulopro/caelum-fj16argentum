package br.com.caelum.argentum.resources;

import java.util.Calendar;

public class CandleBuilder {

	private double abertura;
	private double fechamento;
	private double minimo = Double.MAX_VALUE;
	private double maximo = Double.MIN_VALUE;
	private double volume;
	private boolean metodoComAberturaChamado, metodoComFechamentoChamado, metodoComMinimoChamado, metodoComMaximoChamado,
	metodoComVolumeChamado, metodoComDataChamado;
	private Calendar data;

	public CandleBuilder comAbertura(double abertura) {
		this.abertura = abertura;
		this.metodoComAberturaChamado = true;
		return this;
	}

	public CandleBuilder comFechamento(double fechamento) {
		this.fechamento = fechamento;
		this.metodoComFechamentoChamado = true;
		return this;
	}

	public CandleBuilder comMinimo(double minimo) {
		this.minimo = minimo;
		this.metodoComMinimoChamado = true;
		return this;
	}

	public CandleBuilder comMaximo(double maximo) {
		this.maximo = maximo;
		this.metodoComMaximoChamado =true;
		return this;
	}

	public CandleBuilder comVolume(double volume) {
		this.volume = volume;
		this.metodoComVolumeChamado = true;
		return this;
	}

	public CandleBuilder comData(Calendar data) {
		this.data = data;
		this.metodoComDataChamado = true;
		return this;
	}
	
	public Candle geraCandle(){
		if (metodoComAberturaChamado && metodoComFechamentoChamado && metodoComMinimoChamado && metodoComMaximoChamado &&
		metodoComVolumeChamado && metodoComDataChamado){
		return new Candle(abertura, fechamento, minimo, maximo, volume,
				data);
		}else{
			throw new IllegalStateException();
		}
	}

}
