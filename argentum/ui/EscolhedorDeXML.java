package br.com.caelum.argentum.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.caelum.argentum.reader.LeitorXML;
import br.com.caelum.argentum.resources.Negocio;

public class 
EscolhedorDeXML {
	public List<Negocio> escolhe() {
		try {
			JFileChooser chooser = new JFileChooser("/media/d/NovoVolume/workspace/fj16-argentum");
			chooser.setFileFilter(new FileNameExtensionFilter("Apenas XML",
					"xml"));
			int retorno = chooser.showOpenDialog(null);
			if (retorno == JFileChooser.APPROVE_OPTION) {
				FileReader reader = new FileReader(chooser.getSelectedFile());
				return new LeitorXML().carrega(reader);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
