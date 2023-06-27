package com.skillrisers.gaming.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Power extends Player{
	private String playerName;
	public Power(int x,String playerName) {
		this.x=x;
		y=50;
		h=50;
		w=MAX_HEALTH;
		health=MAX_HEALTH;
		this.playerName=playerName;
		
	}
	public void setHealth() {
		this.health= health-(int)(MAX_HEALTH*0.05);
		
	}

	public BufferedImage defaultImage() {
		// TODO Auto-generated method stub
		return null;
	}
	public void printBox(Graphics pen) {
		pen.setColor(Color.RED);
		pen.fillRect(x,y,w,h);
		pen.setColor(Color.GREEN);
		pen.fillRect(x, y, health, h);
		pen.setColor(Color.WHITE);
	
		pen.drawString(playerName,x,y+h+10);
		
		
	}

}
