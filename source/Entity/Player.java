package Entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class Player extends Entity
{
	GamePanel gp;
	KeyHandler keyH;

	public Player(GamePanel gp, KeyHandler k)
	{
		this.gp = gp;
		this.keyH = k;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues()
	{
		x = 100;
		y = 100;
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
	public void counter()
	{
		spriteCounter++;
		if(spriteCounter > 10)
		{
			spriteNumber = (spriteNumber + 1) % 2;
			spriteCounter = 0;
		}
	}
	public void update()
	{
		if(keyH.upPressed){
			direction = "up";
            y -= speed;
            counter();
        }
        else if(keyH.downPressed){
            direction = "down";
			y += speed;
			counter();
        }
        else if(keyH.leftPressed){
            direction = "left";
			x -= speed;
			counter();
        }
        else if(keyH.rightPressed){
			direction = "right";
            x += speed;
            counter();
        }else if(Objects.equals(direction, "left")||Objects.equals(direction, "standleft") )
        {
        	direction = "standleft";
        }
       	else if(Objects.equals(direction, "right")||Objects.equals(direction, "standright"))
        {
        	direction = "standright";
        }
       	else 
       	{
       		direction = "stand";
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
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	
	}
}