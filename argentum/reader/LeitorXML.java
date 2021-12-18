package br.com.caelum.argentum.reader;

import java.io.Reader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import br.com.caelum.argentum.resources.Negocio;

public class LeitorXML {
	public List<Negocio> carrega(Reader fonte) {
		DomDriver domDriver = new DomDriver();
		XStream stream = new XStream(domDriver);
		stream.addPermission(AnyTypePermission.ANY);
		stream.alias("negocio", Negocio.class);
		try {
			List<Negocio> fromXML = (List<Negocio>) stream.fromXML(fonte);
			return fromXML;
		} catch (NullPointerException e) {
			System.out.println("Arquivo nao compatível");
		}
		return null;

	}
}
