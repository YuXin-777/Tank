import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;
public class ImageTest {
    @Test
    public void testLoadImage(){
        try{
            BufferedImage image = ImageIO.read(new File("/image/asd"));
            assertNotNull(image);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testRotateImage(){
        try{
            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/tankL.jpg"));
            assertNotNull(image);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
