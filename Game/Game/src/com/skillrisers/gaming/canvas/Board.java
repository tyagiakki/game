package com.skillrisers.gaming.canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.skillrisers.gaming.sprites.Power;
import com.skillrisers.gaming.sprites.RyuPlayer;
import com.skillrisers.gaming.sprites.kenPlayer;
import com.skillrisers.gaming.utils.GameConstants;
import com.skillrisers.gaming.utils.PlayerConstants;

public class Board extends JPanel implements GameConstants,PlayerConstants{
	 BufferedImage imagebg;
  private RyuPlayer ryuplayer;
  private kenPlayer kenPlayer;
  private Timer timer;
  private Power ryupower;
  private Power kenpower;
  private boolean isGameOver;
    private void loadPower() {
    	ryupower=new Power(20,"Ryu".toUpperCase());
    	kenpower=new Power(GWIDTH/2+40,"Ken".toUpperCase());
    }
    private void paintPower(Graphics pen) {
    	ryupower.printBox(pen);
    	kenpower.printBox(pen);
    }
  
public Board() throws IOException {
	loadBackgroundImage();
	ryuplayer=new RyuPlayer();
	kenPlayer=new kenPlayer();
	setFocusable(true);
	bindEvents();
	gameLoop();
	loadPower();
	
}
public void collision() {
	if(isCollide()) {
		if(ryuplayer.isAttacking()) {
			kenPlayer.setCurrentMove(DAMAGE);
			kenpower.setHealth();
		}
		if(kenpower.getHealth()<=0 || ryupower.getHealth()<=0) {
			isGameOver=true;
		}
		ryuplayer.setCollide(true);
		//System.out.println("Collide");
		ryuplayer.setSpeed(0);
	}
	else {
		ryuplayer.setSpeed(SPEED);
	}
}
private void printMessage(Graphics pen) {
	pen.setColor(Color.BLACK);
	pen.setFont(new Font("times",Font.BOLD,50));
	pen.drawString("Game Over", GWIDTH/2, GHEIGHT/2);
}
private boolean isCollide() {
	int xDistance=Math.abs(ryuplayer.getX()-kenPlayer.getX());
	int yDistance=Math.abs(ryuplayer.getY()-kenPlayer.getY());
	int maxW=Math.max(ryuplayer.getW(),kenPlayer.getW());
	int maxH=Math.max(ryuplayer.getH(),kenPlayer.getH());
	return xDistance<=maxW-40 && yDistance<=maxH;
}
private void gameLoop() {
	//thread trigger
	timer=new Timer(150,new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			repaint();
			ryuplayer.fall();
			collision();
		}
	});
	timer.start();
}
private void bindEvents() {
	this.addKeyListener(new KeyAdapter() {
		public void keyReleased(KeyEvent e) {
			ryuplayer.setSpeed(0);	
		}
		
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			ryuplayer.setSpeed(-SPEED);
			ryuplayer.setCurrentMove(WALK);
			ryuplayer.move();
			ryuplayer.setCollide(false);
			//repaint();
		}
			else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				if(ryuplayer.isCollide()) {
					ryuplayer.setSpeed(0);
				}
				else {
					ryuplayer.setCollide(false);
					ryuplayer.setSpeed(SPEED);
				}
				//ryuplayer.setSpeed(SPEED);
				ryuplayer.setCurrentMove(WALK);
				ryuplayer.move();
				//repaint();
			}
			else if(e.getKeyCode()==KeyEvent.VK_K) {
				ryuplayer.setAttacking(true);
				ryuplayer.setCurrentMove(KICK);
			}
			else if(e.getKeyCode()==KeyEvent.VK_P) {
				ryuplayer.setAttacking(true);
				ryuplayer.setCurrentMove(PUNCH);
			}
			else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			 ryuplayer.jump();
			}
			//ken
			else if(e.getKeyCode()==KeyEvent.VK_A) {
				kenPlayer.setSpeed(-SPEED);
				kenPlayer.move();
				repaint();
			}
			else if(e.getKeyCode()==KeyEvent.VK_D) {
				kenPlayer.setSpeed(SPEED);
				kenPlayer.move();
				repaint();
			}
	}});
}
private void loadBackgroundImage() {
	try {
		imagebg=ImageIO.read(Board.class.getResource("dm.jpeg"));
		}
		catch(Exception ex) {
			System.out.println("Image loading failed");
			System.exit(ABORT);
		}
}
public void paintComponent(Graphics pen) {
	super.paintComponent(pen);
	printBackgroundImage(pen);
	ryuplayer.printPlayer(pen);
	kenPlayer.printPlayer(pen);
	paintPower(pen);
	if(isGameOver) {
		printMessage(pen);
		timer.stop();
	}
	
}

private void printBackgroundImage(Graphics pen) {
	pen.drawImage(imagebg,0,0,1400,900,null);
	
}

}
