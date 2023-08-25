package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage closed, left, left2, right, right2, up, up2, up3,down, down2, down3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
