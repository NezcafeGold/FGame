package ru.java2e;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Road extends JPanel implements ActionListener, Runnable {
	
	Timer mainTimer = new Timer(20, this); //каждые 20 секунд будет срабатывать actionPerformed

	Image img = new ImageIcon("Res/nebo.jpg").getImage();

	player p = new player ();
	
	Thread enemiesFactory = new Thread(this);
	Thread audio = new Thread(new Audio());
	
	List <Enemy> enemies = new ArrayList<Enemy>();
	
	public Road() {
		mainTimer.start();
		enemiesFactory.start();
		audio.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true); // ??фокусе?
	}
	
	private class MyKeyAdapter extends KeyAdapter {
		public void keyPressed (KeyEvent e) {
			p.keyPressed(e);
			
		}
		public void keyReleased(KeyEvent e){
			p.keyReleased(e);
			
		}
	}
	
	public void paint(Graphics g) {
		g = (Graphics2D) g;
		g.drawImage(img, p.layer1, 0, null);
		g.drawImage(img, p.layer2, 0, null);
		g.drawImage(p.img, p.x, p.y, null);
		
		double v = (player.MAX_V) * p.v;
		
		
		
		g.setColor(Color.GREEN);
		Font font = new Font("Arial", Font.ITALIC, 25);
		g.setFont(font);
		g.drawString("—корость: " + v + " км/ч", 100, 550);
		g.drawString("—чет: " + p.s , 100, 520);
		
		Iterator<Enemy> i = enemies.iterator(); //??
		while (i.hasNext()){
			Enemy e = i.next();
			if (e.x >=2400 || e.x <= -2400)
			{
				i.remove();
			} else{
			e.move();
			g.drawImage(e.img, e.x, e.y, null);
			
		}
		}
	}
	
	public void actionPerformed (ActionEvent e) { //MainTimer работает с этим
		p.move();
		repaint();
		testCollisionWithEnemies();
		testWin();
	}

	private void testWin() {
	if (p.s > 40000) 
		{JOptionPane.showMessageDialog(null, "јй малаца!");
			System.exit(0);}
			
	}

	private void testCollisionWithEnemies() {

		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy e = i.next();
			if (p.getRect().intersects(e.getRect())){
				JOptionPane.showMessageDialog(null, "лох - " + p.s + " очков");
				System.exit(1);
			}
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(2000));
				enemies.add( new Enemy(1300, rand.nextInt(600), rand.nextInt(60), this));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}