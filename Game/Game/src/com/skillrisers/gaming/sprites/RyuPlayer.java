package com.skillrisers.gaming.sprites;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;

import com.skillrisers.gaming.utils.GameConstants;
public class RyuPlayer extends Player  {
	
	//private BufferedImage image;
	private BufferedImage walkImages[]=new BufferedImage[6];
	private BufferedImage standingImages[]=new BufferedImage[6];
	private BufferedImage kickImages[]=new BufferedImage[6];
	private BufferedImage punchImages[]=new BufferedImage[6];
	public RyuPlayer() throws IOException {
		x=200;
		
		y=FLOOR-h;
		speed=0;
		currentMove=STANDING;
		image=ImageIO.read(RyuPlayer.class.getResource(RYU_IMAGE));
		loadWalkImages();
		loadStandingImage();
		loadKickImages();
		loadPunchImages();
		
		}
	public void jump() {
		if(!isJump) {
		force=DEFAULT_FORCE;
		y=y+force;
		isJump=true;
		}
	}
	public void fall() {
		if(y>=FLOOR-h) {
			isJump=false;
			return;
		}
	force=force+GRAVITY;	
	y=y+force;
	}
	private void loadWalkImages() {
		walkImages[0]=image.getSubimage(137,0,48,80);
		walkImages[1]=image.getSubimage(183,0,47,74);
		walkImages[2]=image.getSubimage(228,2,47,71);
		walkImages[3]=image.getSubimage(104,82,44,78);
		walkImages[4]=image.getSubimage(153,82,45,78);
		walkImages[5]=image.getSubimage(248,79,41,71);
	}
public void loadStandingImage() {
	standingImages[0]=image.getSubimage(4,241,52,73);
	standingImages[1]=image.getSubimage(130,238,54,76);
	standingImages[2]=image.getSubimage(205,314,48,70);
	standingImages[3]=image.getSubimage(253,310,43,71);
	standingImages[4]=image.getSubimage(4,952,64,72);
	standingImages[5]=image.getSubimage(285,1290,51,76);
}
private void loadKickImages() {
	kickImages[0]=image.getSubimage(9,619,43,73);
	kickImages[1]=image.getSubimage(57,618,49,68);
	kickImages[2]=image.getSubimage(109,620,83,64);
	kickImages[3]=image.getSubimage(900,318,76,62);
	kickImages[4]=image.getSubimage(980,318,79,62);
	kickImages[5]=image.getSubimage(1054,317,79,65);
}
private void loadPunchImages() {
	punchImages[0]=image.getSubimage(672,307,70,78);
	punchImages[1]=image.getSubimage(62,239,72,75);
	punchImages[2]=image.getSubimage(63,240,71,74);
	punchImages[3]=image.getSubimage(62,239,72,75);
	punchImages[4]=image.getSubimage(63,240,71,74);
	punchImages[5]=image.getSubimage(63,240,71,74);
	
}
private BufferedImage walkImage() {
	if(imageIndex>5) {
		imageIndex=0;
		currentMove=STANDING;
	}
	BufferedImage img=walkImages[imageIndex];
	imageIndex++;
	return img;
		}
private BufferedImage kickImage() {
	if(imageIndex>5) {
		imageIndex=0;
		currentMove=STANDING;
		isAttacking=false;
	}
	BufferedImage img=kickImages[imageIndex];
	imageIndex++;
	return img;
		}
private BufferedImage punchImage() {
	if(imageIndex>5) {
		imageIndex=0;
		currentMove=STANDING;
		isAttacking=false; 
	}
	BufferedImage img=punchImages[imageIndex];
	imageIndex++;
	return img;
		}
private BufferedImage standingImage() {
	if(imageIndex>5) {
		imageIndex=0;
	}
	BufferedImage img=standingImages[imageIndex];
	imageIndex++;
	return img;
}
	

	public BufferedImage defaultImage() {
		 if(currentMove==WALK) {
		return walkImage();
		}
		else if(currentMove==PUNCH) {
			return punchImage();
		}
		else if(currentMove==KICK) {
			return kickImage();
		}
		
			return standingImage();
		

   
}
	}
