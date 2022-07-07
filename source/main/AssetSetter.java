package main;

import object.OBJ_boots;
import object.OBJ_chest;
import object.OBJ_door;
import object.OBJ_door_open;
import object.OBJ_key;

public class AssetSetter{
	
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_key();
		gp.obj[0].worldX = 12 * gp.tileSize;
		gp.obj[0].worldY = 33 * gp.tileSize;
		
		gp.obj[1] = new OBJ_key();
		gp.obj[1].worldX = 36 * gp.tileSize;
		gp.obj[1].worldY = 8 * gp.tileSize;
		
		gp.obj[2] = new OBJ_chest();
		gp.obj[2].worldX = 10 * gp.tileSize;
		gp.obj[2].worldY = 8 * gp.tileSize;
		
		gp.obj[3] = new OBJ_door();
		gp.obj[3].worldX = 10 * gp.tileSize;
		gp.obj[3].worldY = 11 * gp.tileSize;
		gp.obj[3].collision = true;
		
		//gp.obj[4] = new OBJ_door_open();
	
		//gp.obj[5] = new OBJ_boots();
		//gp.obj[5].worldX = 11 * gp.tileSize;
		//gp.obj[5].worldY = 11 * gp.tileSize;
	}
}