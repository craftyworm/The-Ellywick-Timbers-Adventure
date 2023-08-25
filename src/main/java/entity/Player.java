package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX; //Where we draw player
    public final int screenY;

    public Player(GamePanel gp, KeyHandler KeyH) {
        this.gp = gp;
        keyH = KeyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2); //Returns halfway point of the screen
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23; //Set player starting position
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "up";


    }

    public void getPlayerImage() { //https://www.youtube.com/watch?v=wT9uNGzMEM4&t=12s
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/player/EWT_back_straight.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/EWT_back_right.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/EWT_back_left.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/EWT_straight_down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/EWT_straight_left_up.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/EWT_straight_right_up.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/EWT_left_down.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/EWT_right_down.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/EWT_left_up.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/EWT_right_up.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;

            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white); // The good ol' days with a white square
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize); // made tilesize public in GamePanel

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right;
                    break;

                }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }

    }


