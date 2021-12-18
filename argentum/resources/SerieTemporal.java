package br.com.caelum.argentum.resources;

import java.util.List;

public class SerieTemporal {
	private final List<Candle> candles;

	public SerieTemporal(List<Candle> candles) {
		this.candles = candles;
		if (candles == null) {
			throw new NullPointerException("Candle Vazio");
		}
	}

	public Candle getCandle(int i) {
		if(this.candles.size() < i){
			throw new IndexOutOfBoundsException("Posicao nao válida");
		}else{
			return this.candles.get(i);	
		}
		
	}

	public int getTotal() {
		return this.candles.size();
	}

}
