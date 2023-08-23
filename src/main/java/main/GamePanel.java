package main;
import entity.Player;
import tile.TileManager;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; //16 x 16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile ACTUAL TILE SIZE

    public final int maxScreenCol = 16; // wide
    public final int maxScreenRow = 12; // long

    public final int screenWidth =  tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    int FPS = 60; // Setting the FPS rate
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this,keyH);

    // Set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; // moves 4 pixels


    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double drawInterval = (double) 1000000000 /FPS; // How  many times per second we draw the screen / 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            long currentTime = System.nanoTime();
            timer +=(currentTime - lastTime);
            lastTime = currentTime;
            drawCount++;

            if (timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
          // System.out.println("The game loop is running");
            // 1. Update: Update information such as character positions
            update();
            //2. Draw: Draw the screen with the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }


                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void update() {

         player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); // Parent class means JPanel
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2); //Draw the background before you draw the player
        player.draw(g2);

        g2.dispose();
    }
}
