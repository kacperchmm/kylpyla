package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_key;

public class UI {
	GamePanel gp;
	Font Comic_Sans_MS_42;
	Font Comic_Sans_MS_84;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int counter = 0;
	public boolean gameFinished;
	public UI(GamePanel gp)
	{
		this.gp = gp;
		Comic_Sans_MS_42 = new Font("Comic Sans MS", Font.BOLD, 42);
		Comic_Sans_MS_84 = new Font("Comic Sans MS", Font.BOLD, 84);
		OBJ_key key = new OBJ_key();
		keyImage = key.image;
	}
	
	public void showMessage(String text)
	{
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2)
	{
		if(gameFinished == false)
		{
			g2.setFont(Comic_Sans_MS_42);
			g2.setColor(Color.yellow);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString(":" + gp.player.hasKey, 80, 70);
			
			if(messageOn == true)
			{
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.maxScreenCol*16, gp.maxScreenRow*36);
				counter++;
				if(counter > 180)
				{
					counter = 0;
					messageOn = false;
				}
			}
		}else
		{
			g2.setFont(Comic_Sans_MS_42);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			
			text = "Znalazłeś skrzynię, koniec gry!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			int x = gp.screenWidth/2 - textLength/2;
			int y = gp.screenWidth/2 - gp.tileSize;
			g2.drawString(text, x, y);
			
			g2.setFont(Comic_Sans_MS_84);
			g2.setColor(Color.black);
			
			text = "Gratulacje!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenWidth/2 - gp.tileSize*6;
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
		}
	}
}
