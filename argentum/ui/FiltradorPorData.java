package br.com.caelum.argentum.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.caelum.argentum.resources.Negocio;

public class FiltradorPorData {
	private Calendar dataInicial;

	public FiltradorPorData(String dataDigitada) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			dataInicial = Calendar.getInstance();
			dataInicial.setTime(sdf.parse(dataDigitada));

		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data inválida");
			//throw new RuntimeException(e);
		}
	}
	
	public void filtra(List<Negocio> lista) {
		if(dataInicial == null) 
			return;
		Iterator<Negocio> it = lista.iterator();
		while(it.hasNext()) {
			if(it.next().getData().before(dataInicial))
				it.remove();
			
		}
	}

}
