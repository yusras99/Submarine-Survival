import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

// This file will load in buffer images
public class loadImage {
    private BufferedImage image;
    // this will be the path where we load in our images
    // IOException is just there in case you enter a non-valid image
    public BufferedImage getBufferedImage(String path) throws IOException {
        image = ImageIO.read(getClass().getResource(path));
        return image;
    }

}
