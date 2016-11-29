package _08final.mvc.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

    //Instance Variables
    private String path;
    private int frameWidth;
    private int frameHeight;
    private BufferedImage sheet = null;
    private BufferedImage[] frameImages;

    //Constructors
    public SpriteSheet(String aPath, int width, int height) {

        path = aPath;
        frameWidth = width;
        frameHeight = height;

        try {
            sheet = ImageIO.read(new File(path));
            frameImages = getAllSprites();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getSprite(int frame) {
        return frameImages[frame];
    }

    //Methods
    public int getHeight() {
        return frameHeight;
    }

    public int getWidth() {
        return frameWidth;
    }

    public int getColumnCount() {
        return sheet.getWidth() / getWidth();
    }

    public int getRowCount() {
        return sheet.getHeight() / getHeight();
    }

    public int getFrameCount() {
        int cols = getColumnCount();
        int rows = getRowCount();
        return cols * rows;
    }

    private BufferedImage getSprite(int x, int y, int h, int w) {
        BufferedImage sprite = sheet.getSubimage(x, y, h, w);
        return sprite;
    }

    public BufferedImage[] getAllSprites() {
        int frameCount =  getFrameCount();
        BufferedImage[] sprites = new BufferedImage[frameCount];
        int index = 0;
        for (int row = 0; row < getRowCount(); row++) {
            for (int col = 0; col < getColumnCount(); col++) {
                int x = col * getWidth();
                int y = row * getHeight();
                BufferedImage currentSprite = getSprite(x, y, getWidth(), getHeight());
                sprites[index] = currentSprite;
                index++;
            }
        }
        return sprites;
    }

}

