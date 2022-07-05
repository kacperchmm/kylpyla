package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap();
	}
	public void getTileImage(){
		
		try{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/stone.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/road.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void loadMap(){
		
		try{
			InputStream is = getClass().getResourceAsStream("/maps/world01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			for(int i = 0; i < gp.maxWorldRow; i++){
				
					String line = br.readLine();		
					//System.out.println(line);	
					//System.out.println(numbers[0]);
					String numbers[] = line.split(" ");
					
					for(int j = 0; j < gp.maxWorldCol; j++){
						
						
						mapTileNum[j][i] = Integer.parseInt(numbers[j]);
						//System.out.print(mapTileNum[i][j] + " ");
						//System.out.print(mapTileNum[i][j]);
						
					}
					
					//System.out.println();
							
					
			}
			
			br.close();
			
			
		}catch(Exception e){
			
		}
	}
	
	
	public void draw(Graphics2D g2){
		
		for(int i = 0; i < gp.maxWorldRow; i++){
		
			int worldY = i * gp.tileSize;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			for(int j = 0; j < gp.maxWorldCol; j++) {				
				//mapTileNum[i][j]
				int worldX = j * gp.tileSize;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				
				if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
					
					g2.drawImage(tile[mapTileNum[j][i]].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				}
			}
		}
	}
}
