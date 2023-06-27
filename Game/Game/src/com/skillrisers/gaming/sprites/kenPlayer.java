package com.skillrisers.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class kenPlayer extends Player{
	BufferedImage[] damageImages=new BufferedImage[4];
	BufferedImage[] standingImages=new BufferedImage[4];
	public kenPlayer() throws IOException {
		x=GWIDTH-400;
         int h=220;	
		y=FLOOR-h;
		speed=0;
		image=ImageIO.read(kenPlayer.class.getResource(KEN_IMAGE));
		loadDamageImage();
		loadstandingImage();
	}
	private void loadstandingImage() {
		standingImages[0]=image.getSubimage(8,13,77,110);
		standingImages[1]=image.getSubimage(88,10,68,110);
		standingImages[2]=image.getSubimage(156,15,70,108);
		standingImages[3]=image.getSubimage(229,16,71,107);
	}
	private void loadDamageImage() {
		
		damageImages[0]=image.getSubimage(99,882,92,113);
		damageImages[1]=image.getSubimage(200,879,68,117);
		damageImages[2]=image.getSubimage(269,909,70,84);
		damageImages[3]=image.getSubimage(343,921,90,76);
		//damageImages[4]=image.getSubimage(528,922,104,73);
	}
	public BufferedImage damageImage() {
		if(imageIndex>=4) {
			imageIndex=0;
			currentMove=STANDING;
		}
		BufferedImage img=damageImages[imageIndex];
		imageIndex++;
		return img;
	}
	public BufferedImage standingImage() {
		if(imageIndex>=4) {
			imageIndex=0;
		}
		BufferedImage img=standingImages[imageIndex];
		imageIndex++;
		return img;
	}
	public BufferedImage defaultImage() {
		if(currentMove==DAMAGE) {
			return damageImage();
		}
//		BufferedImage subimage=image.getSubimage(85,10,72,110);
//		return subimage;
		return standingImage() ;
	}
}
