package info3.game;

import java.awt.Color;
import java.awt.Graphics;

public class Block extends Entity {
    public Block(int x, int y) {
        super(x*32, y*32, 32, 32);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(m_x, m_y, m_width, m_height);
    }

    public void viewportView(Graphics g, double scale) {
        g.setColor(Color.BLUE);
        g.fillRect(Viewport.OnCamViewX(m_x, scale), Viewport.OnCamViewY(m_y, scale), Viewport.realSize(m_width, scale), Viewport.realSize(m_height, scale));
    }
    
}
