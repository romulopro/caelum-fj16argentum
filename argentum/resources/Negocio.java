package br.com.caelum.argentum.resources;

import java.util.Calendar;

import br.com.caelum.argentum.ui.Coluna;

public final class Negocio implements Comparable<Negocio> {
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	//private long dataEmMilisegundos;

	public Negocio(double preco, int quantidade, Calendar data) {
		if (data == null) {
			throw new IllegalArgumentException("Data nao pode ser nula");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
		//this.dataEmMilisegundos = this.data.getTimeInMillis();
	}

	@Coluna(nome = "Preco", posicao = 0, formato="R$ %,#.2f")
	public double getPreco() {
		return preco;
	}
	@Coluna(nome = "Quantidade" ,posicao = 1)
	public int getQuantidade() {
		return quantidade;
	}
	@Coluna(nome = "Data", posicao = 3, formato="%1$td/%1$tm/%1$tY")
	public Calendar getData() {
		return (Calendar) this.data.clone();
	}
	@Coluna(nome = "Volume", posicao = 2, formato="R$ %,#.2f")
	public double getVolume() {
		return preco * quantidade;
	}

	public boolean isMesmoDia(Calendar outraData) {

		return data.get(Calendar.DATE) == outraData.get(Calendar.DATE)
				&& data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH)
				&& data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR);
	}

	@Override
	public int compareTo(Negocio t) {
		return data.compareTo(t.data);
	}
}
