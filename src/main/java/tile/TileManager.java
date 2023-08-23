package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager { //https://www.youtube.com/watch?v=ugzxCcpoSdE
    GamePanel gp;
    Tile [] tile;
    int mapTileNum[] [];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile [10];
        mapTileNum = new int [gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage(){

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass_4.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));


        } catch(IOException e) {
            e.printStackTrace();
        }


    }
        public void loadMap(){
            try {
                InputStream is = getClass().getResourceAsStream("/maps/map.txt");
                //3. Make sure to save the map file without BOM (Byte order mark). If the file contains BOM,
                // the program fails to read the content correctly. Also, if your OS language is not English, saving it as UTF-8 might be safer.
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                int col = 0;
                int row = 0;

                while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                    String line = br.readLine();

                    while (col < gp.maxScreenCol) {
                        String numbers[] = line.split(" "); //from the line we just read, we get numbers one by one.
                        int num = Integer.parseInt(numbers[col]); // Changing from String to Int
                        mapTileNum[col] [row] = num;
                        col ++;
                    }
                    if(col == gp.maxScreenCol){
                        col = 0;
                        row++;
                    }
                }    br.close();

            } catch(Exception e) {

            }


        }
    public void draw (Graphics2D g2) {
//        g2.drawImage(tile[0].image, 0,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 0,0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 0,0, gp.tileSize, gp.tileSize, null);

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image,x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

    }
}
