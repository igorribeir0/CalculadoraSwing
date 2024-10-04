package br.Igorribeiro.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.Memoria;
import modelo.MemoriaObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObserver{
	
	private final JLabel labe = new JLabel(Memoria.getInstancia().getTextoAtual());
	public Display() {
		
	Memoria.getInstancia().adicionarObservador(this);
	setBackground(new Color(46, 49, 50));
	labe.setForeground(Color.white);
	labe.setFont(new Font("courier", Font.PLAIN, 30));
	setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
	
	add(labe);
	}
	
	@Override
	public void valorAlternado(String novovalor) {
		labe.setText(novovalor);
	}
}
