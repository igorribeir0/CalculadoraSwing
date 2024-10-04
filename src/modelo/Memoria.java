package modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	private enum TipoComando {
			ZERAR, SINAL, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRGULA;
	};
	
	private final List<MemoriaObserver> observer = new ArrayList<>();
	
	private static Memoria instancia = new Memoria();
	
	private TipoComando ultimaoperacao = null;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textBuffer = "";
	
	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0": textoAtual;
	}

	private Memoria() {
		
	}
	
	public static Memoria getInstancia() {
		return instancia;
	}
	
	public void adicionarObservador(MemoriaObserver o) {
		observer.add(o);
	}
	
	public void processarComando (String texto) {
		
		TipoComando tipocomando = detectarTipoComando(texto);
		  
		if(tipocomando == null) {
			return;
		} else if (tipocomando == TipoComando.ZERAR) {
			textoAtual = "";
			textBuffer = "";
			substituir = false;
			ultimaoperacao = null;
		} else if (tipocomando == TipoComando.SINAL && textoAtual.contains("-")) {
			textoAtual = textoAtual.substring(1);
		} else if (tipocomando == TipoComando.SINAL && !textoAtual.contains("-")) {
			textoAtual = "-" + textoAtual;
		}
		else if(tipocomando == TipoComando.NUMERO || tipocomando == TipoComando.VIRGULA) {
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = obterResultadoOpereacao();
			textBuffer = textoAtual;
			ultimaoperacao = tipocomando;
		}
		
		
		observer.forEach(o -> o.valorAlternado(getTextoAtual()));
	}

	private String obterResultadoOpereacao() {
		if(ultimaoperacao == null || ultimaoperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		
		double numeroBuffer = Double.parseDouble(textBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
		
		double resultado = 0;
		
		if(ultimaoperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		} else if (ultimaoperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		} else if (ultimaoperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		} else if (ultimaoperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
		}
		
		String resulString = Double.toString(resultado).replace(",", ".");
		boolean inteiro = resulString.endsWith(",0");
		return inteiro ? resulString.replace(",0", "") : resulString;
	}

	private TipoComando detectarTipoComando(String texto) {
		if(texto .isEmpty() && texto == "0") {
			return null;
		}
		
		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
			if("AC".equals(texto)) {
				return TipoComando.ZERAR;
			} else if ("/".equals(texto)) {
				return TipoComando.DIV;
			} else if ("*".equals(texto)) {
				return TipoComando.MULT;
			} else if ("+".equals(texto)) {
				return TipoComando.SOMA;
			} else if ("-".equals(texto)) {
				return TipoComando.SUB;
			} else if ("=".equals(texto)) {
				return TipoComando.IGUAL;
			} else if ("±".equals(texto)) {
				return TipoComando.SINAL;
			} else if (",".equals(texto) && ! textoAtual.contains(",")) {
				return TipoComando.VIRGULA;
			}
		}

		return null;
	}
	
	
}
