package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.*;

public class Game extends Canvas implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	//tamanho da tela
	public static int WIDTH = 480, HEIGHT = 480;
	//player importado
	public Player player;
	public World world;
	
	public Game() {
		//comandos teclado
		this.addKeyListener(this);
		//definir dimensao da janela
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		new Spritesheet();
		player = new Player(32, 32);
		world = new World();
		
	}
	//logica do game
	public void tick() {
		player.tick();
		
	}
	//renderizacao
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		 Graphics g = bs.getDrawGraphics();
		 
		 g.setColor(Color.black);
		 g.fillRect(0, 0, WIDTH, HEIGHT);
		 player.render(g);
		 
		 world.render(g);
		 
		 bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("Mini Zelda");
		
		frame.pack();
		
		frame.setLocationRelativeTo(null); //janela centralizada
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //finalizar processo java
		
		frame.setVisible(true); //visualizar janela
		
		new Thread(game).start();
	}
	@Override
	public void run() {
		
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60); //fps
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
	}
	
	

}
