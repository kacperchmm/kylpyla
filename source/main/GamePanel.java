package main;

import java.lang.System;
import java.awt.*;
import javax.swing.JPanel;
import Entity.Player;
import tile.TileManager;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable{
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8670548652711159680L;
	final int originalTileSize = 16; // 16x16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768
    public final int screenHeight = tileSize * maxScreenRow; //576

    
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    public CollisionChecker cChecker = new CollisionChecker(this);
    
    int fps = 60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);    
    }
    
    public void setupGame() {
    	
    	aSetter.setObject();
    }
    
    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
     public void run(){

         double drawInterval = 1000000000/fps;
         double nextDrawTime = System.nanoTime() + drawInterval;
        
         while(gameThread != null){

             update();

             repaint();

             try{
                 double remainingTime = nextDrawTime - System.nanoTime();
                 remainingTime = remainingTime / 1000000;
                
                 if(remainingTime < 0){
                     remainingTime = 0;
                 }

                 Thread.sleep((long)remainingTime);

                 nextDrawTime += drawInterval;

             } catch (InterruptedException e){
                 e.printStackTrace();
             }
         }
     }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        
        for(int i = 0; i < obj.length; i++) {
        	if(obj[i] != null) {
        		obj[i].draw(g2, this);
        	}
        }
        
        player.draw(g2);

        g2.dispose();
    }
}