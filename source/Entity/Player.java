package Entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_door_open;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class Player extends Entity
{
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler k) {
		this.gp = gp;
		this.keyH = k;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(9, 27, 30, 15);		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefualtY = solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}

	public void getPlayerImage()
	{
		try
		{
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));

			right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));

			stand = ImageIO.read(getClass().getResourceAsStream("/player/player_stand.png"));
			standleft = ImageIO.read(getClass().getResourceAsStream("/player/player_stand_left.png"));
			standright = ImageIO.read(getClass().getResourceAsStream("/player/player_stand_right.png"));


		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void counter() {
		
		spriteCounter++;
		
		if(spriteCounter > 10){
			spriteNumber = (spriteNumber + 1) % 2;
			spriteCounter = 0;
			
		}
	}
	
	public boolean setCollision() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		return collisionOn;
	}
	public void update() {
		
		int objIndex = gp.cChecker.checkObject(this, true);
		
		
		if(keyH.upPressed){
			direction = "up";
        }
		
        else if(keyH.downPressed){
            direction = "down";
        }
		
        else if(keyH.leftPressed){
            direction = "left";
        }
		
        else if(keyH.rightPressed){
			direction = "right";
        }
        else if(Objects.equals(direction, "left")||Objects.equals(direction, "standleft")){
        	direction = "standleft";
        }
		
       	else if(Objects.equals(direction, "right")||Objects.equals(direction, "standright")){
        	direction = "standright";
        }
		
       	else {
       		direction = "stand";
       	}
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, true);
		if(collisionOn == false)
		{
			switch(direction)
			{
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
		
		

		
		counter(); //wyrzucilem poza ify, zeby poruszal sie jak zablokuje sie o sciane
		
	}
	
	public void pickUpObject(int i) {
		if(i != 2137) {
			
			String objName = gp.obj[i].name;
			
			switch(objName) {
			case"key":
				hasKey++;
				gp.obj[i] = null;
				System.out.println("Key:" + hasKey);
				break;
			case"door":
				if(hasKey > 0) {
					int x = gp.obj[i].worldX;
					int y = gp.obj[i].worldY;
					gp.obj[i] = new OBJ_door_open(); //gp.obj[i+1];
					gp.obj[i].worldX = x;
					gp.obj[i].worldY = y;
					hasKey--;
					System.out.println("Key:" + hasKey);
				}
			}
		}
	}
	
	
	public void draw(Graphics2D g2)
	{
		//g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = standright;
		switch(direction)
		{
		case "up":
			if(spriteNumber == 1)
				image = up1;
			else
				image = up2;
			break;
			
		case "down":
			if(spriteNumber == 1)
				image = down1;
			else
				image = down2;
			break;
			
		case "left":
			if(spriteNumber == 1)
				image = left1;
			else
				image = left2;
			break;
			
		case "right":
			if(spriteNumber == 1)
				image = right1;
			else
				image = right2;
			break;
			
		case "standleft":
			image = standleft;
			break;
			
		case "standright":
			image = standright;
			break;
			
		case "stand":
			image = stand;
			break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	
	}
}