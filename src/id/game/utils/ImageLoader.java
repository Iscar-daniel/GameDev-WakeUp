package id.game.utils;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageLoader {
    public static BufferedImage load(String path) {
        try {
            return ImageIO.read(ImageLoader.class
                .getResource("../assets/" + path));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ImageLoader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
