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
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[32][32];
		getTileImage();
		loadMap();
	}
	public void getTileImage()
	{
		try
		{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/stone.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
			


		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void loadMap()
	{
		try
		{
			InputStream is = getClass().getResourceAsStream("/maps/mapa.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			for(int i = 0; i < 32; i++)
			{
					String line = br.readLine();
					//System.out.println(line);
					String numbers[] = line.split(" ");
					//System.out.println(numbers[0]);
					
					for(int j = 0; j < 32; j++)
					{
						mapTileNum[i][j] = Integer.parseInt(numbers[j]);
						//System.out.print(mapTileNum[i][j] + " ");
						//System.out.println(mapTileNum[i][j]);
					}
					System.out.println();
							
					
			}					
			
			
		}catch(Exception e)
		{
			
		}
	}
	public void draw(Graphics2D g2)
	{
		for(int i = 0; i < 32; i++)
		{
			for(int j = 0; j < 32; j++)
				//mapTileNum[i][j]
				g2.drawImage(tile[mapTileNum[i][j]].image, j*32, i*32, gp.tileSize, gp.tileSize, null);
		}
	}
}
