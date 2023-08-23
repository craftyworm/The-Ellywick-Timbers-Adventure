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

    public Player(GamePanel gp, KeyHandler KeyH) {
        this.gp = gp;
        keyH = KeyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "up";


    }

    public void getPlayerImage() { //https://www.youtube.com/watch?v=wT9uNGzMEM4&t=12s
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/player/green_up_1_1.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/green_down_1_1.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/green_left_1_2.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/green_right_1_2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/green_left_2_2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/green_right_2_2.png"));
            closed = ImageIO.read(getClass().getResourceAsStream("/player/green_closed_1_1.png"));
            straight = ImageIO.read(getClass().getResourceAsStream("/player/green_straight_1_1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;

            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        }

        public void draw (Graphics2D g2){
//        g2.setColor(Color.white); // The good ol' days with a white square
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize); // made tilesize public in GamePanel

            BufferedImage image = null;

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up;
                    }
                    if (spriteNum == 2) {
                        image = closed;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down;
                    }
                    if (spriteNum == 2) {
                        image = closed;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;

            }
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        }

    }

