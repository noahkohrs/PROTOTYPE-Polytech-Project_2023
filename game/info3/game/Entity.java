package info3.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

abstract public class Entity {
  BufferedImage[] m_images;
  int m_imageIndex;
  long m_imageElapsed;
  long m_moveElapsed;
  int m_x = 10, m_y = 10;
  int m_width;
  int m_height;

  public Entity(int x, int y, int width, int height) {
    m_x = x;
    m_y = y;
    m_width = width;
    m_height = height;
  }

  public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
    File imageFile = new File(filename);
    if (imageFile.exists()) {
      BufferedImage image = ImageIO.read(imageFile);
      int width = image.getWidth(null) / ncols;
      int height = image.getHeight(null) / nrows;

      BufferedImage[] images = new BufferedImage[nrows * ncols];
      for (int i = 0; i < nrows; i++) {
        for (int j = 0; j < ncols; j++) {
          int x = j * width;
          int y = i * height;
          images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
        }
      }
      return images;
    }
    return null;
  }

  abstract public void paint(Graphics g);

  abstract public void viewportView(Graphics g, double scale);

}
