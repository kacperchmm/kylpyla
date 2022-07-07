package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_door_open extends SuperObject{
	
	public OBJ_door_open() {
		collision = false;
		name = "door_open";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door_open.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}