package jogoDaCobrinha;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class PainelDoJogo  extends JPanel implements ActionListener{

	static final int LARGURA_TELA = 600;
	static final int ALTURA_TELA = 600;
	static final int TAMANHO_PIXEL = 25;
	static final int QNTD_PIXELS_TELA = ((LARGURA_TELA*ALTURA_TELA)/TAMANHO_PIXEL);                       
	static final int DELAY = 75;
	
	final int x[] = new int [QNTD_PIXELS_TELA];
	final int y[] = new int [QNTD_PIXELS_TELA];
	
	int partesDoCorpo = 6;
	int macasComidas;
	int macaX;
	int macaY;
	char direcao = 'D';
	boolean jogando = false;
	
	Timer timer;
	Random random;
	
	
	PainelDoJogo(){
		random = new Random();
		this.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		comecarJogo();
	}
	
	public void comecarJogo() {
		novaMaca();
		jogando = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Desenho(g);
	}
	
	public void Desenho(Graphics g) {
		for(int i = 0; i < ALTURA_TELA/TAMANHO_PIXEL; i++) {
			g.drawLine(i*TAMANHO_PIXEL, 0, i*TAMANHO_PIXEL, ALTURA_TELA);
			g.drawLine(0, i*TAMANHO_PIXEL, LARGURA_TELA, i*TAMANHO_PIXEL);
		}
		
		g.setColor(Color.red);
		g.fillOval(macaX, macaY, TAMANHO_PIXEL, TAMANHO_PIXEL);
		
		
		for(int i = 0; i < partesDoCorpo; i++) {
			if (i == 0) {
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], TAMANHO_PIXEL, TAMANHO_PIXEL);
			}
			else {
				g.setColor(new Color(45,180,0));
				g.fillRect(x[i], y[i], TAMANHO_PIXEL, TAMANHO_PIXEL);
			}
		}
	}
	
	public void novaMaca() {
		macaX = random.nextInt((int)(LARGURA_TELA/TAMANHO_PIXEL))*TAMANHO_PIXEL;
		macaY = random.nextInt((int)(ALTURA_TELA/TAMANHO_PIXEL))*TAMANHO_PIXEL;
	}
	
	public void movimento() {
		
		for(int i = partesDoCorpo; i > 0; i--) {
			x[i] = x [i-1];
			y[i] = y [i-1];
		}
		
		switch(direcao){
		case 'C':
			y[0] = y[0] - TAMANHO_PIXEL;
		break;
		
		case 'B':
			y[0] = y[0] + TAMANHO_PIXEL;
		break;
		
		case 'E':
			x[0] = x[0] - TAMANHO_PIXEL;
		break;
		
		case 'D':
			x[0] = x[0] + TAMANHO_PIXEL;
		break;
		
		}
	}
	
	public void checarPonto() {
		
	}
	
	public void checarColisoes() {
		
	}
	
	public void fimDeJogo(ActionEvent g) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(jogando) {
			movimento();
			checarPonto();
			checarColisoes();
		}
		repaint();
		
	}    
	
	public class MyKeyAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}

}
