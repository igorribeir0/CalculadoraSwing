package br.Igorribeiro.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton{
	 public Botao(String text, Color cor) {
		setText(text);
		setOpaque(true);
		setBackground(cor);
		setFont(new Font("courier", Font.PLAIN, 30));
		setForeground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
