package br.com.caelum.argentum.resources;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Candle {
	
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;

	public Candle(double abertura, double fechamento, double minimo,
			double maximo, double volume, Calendar data) {
		this.abertura = abertura;
		this.fechamento = fechamento;
		if(maximo<minimo){
			throw new IllegalArgumentException("Valor Mínimo maior que o máximo");
		}
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		if(data == null){
			throw new IllegalArgumentException("Data nao pode ser nula");
		}
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}
	
	public boolean isAlta(){
		return this.abertura <= this.fechamento;
	}
	public boolean isBaixa(){
		return this.abertura < this.fechamento;
	}
	Calendar c = Calendar.getInstance();
	Date data1 = c.getTime();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public String toString() {
		
		return      "[Abertura: "+ abertura +
					", Fechamento: "+ fechamento +
					", Mínima: "+ minimo+
					", Máxima: "+maximo +
					", Volume: "+ volume +
					", Data: " + sdf.format(data1)+"]";
	}

}