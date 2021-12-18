package br.com.caelum.argentum.resources;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {
	public Candle constroiCandleParaData(Calendar data, List<Negocio> negocios) {
		double maximo = Double.MIN_VALUE;// negocios.get(0).getPreco();
		double minimo = Double.MAX_VALUE;// negocios.get(0).getPreco();
		double volume = 0;
		if (negocios.isEmpty()) {
			maximo = 0;
			minimo = 0;
		}

		for (Negocio negocio : negocios) {
			volume += negocio.getVolume();
			double preco = negocio.getPreco();
			if (preco > maximo) {
				maximo = preco;
			}
			if (preco < minimo) {
				minimo = preco;
			}

		}
		// if(negocios.isEmpty()) maximo = 0; minimo = 0;

		double abertura = negocios.isEmpty() ? 0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty() ? 0 : negocios.get(
				negocios.size() - 1).getPreco();

		return new Candle(abertura, fechamento, minimo, maximo, volume, data);
	}

	public List<Candle> constroiCandles(List<Negocio> todosNegocios) {

		List<Candle> candles = new ArrayList<Candle>();
		List<Negocio> negociosDoDia = new ArrayList<Negocio>();
		Calendar dataAtual = todosNegocios.get(0).getData();
		for (Negocio negocio : todosNegocios) {
			if (negocio.getData().before(dataAtual)) {
				throw new IllegalStateException("Negocios em ordem erada!!");
			}
		}
		for (Negocio negocio : todosNegocios) {
			if (!negocio.isMesmoDia(dataAtual)) {
				criaEGuardaCandle(candles, negociosDoDia, dataAtual);
				negociosDoDia = new ArrayList<Negocio>();
				dataAtual = negocio.getData();
			}
			if (negocio.getData().before(dataAtual)) {
				throw new IllegalStateException("Negocios em ordem erada!!");
			}
			negociosDoDia.add(negocio);
		}
		criaEGuardaCandle(candles, negociosDoDia, dataAtual);

		return candles;
	}

	private void criaEGuardaCandle(List<Candle> candles,
			List<Negocio> negociosDoDia, Calendar dataAtual) {
		Candle candleDoDia = constroiCandleParaData(dataAtual, negociosDoDia);
		candles.add(candleDoDia);
	}
}
