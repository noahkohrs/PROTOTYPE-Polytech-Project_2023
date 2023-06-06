package info3.level.editor;

import java.awt.Graphics;

public class ElementContainer {
    Element m_element ;
    int m_x ;
    int m_y ;

    public ElementContainer(Element elem, int x, int y) {
        m_element = elem ;
        m_x = x ;
        m_y = y ;
    }

    void changeElement(Element elem) {
        m_element = elem ;
    }

    public void paint(Graphics g, int i, int j, float scale) {
        m_element.paint(g, i, j, scale);
        if (LevelEditor.levelEditor.selected == this)
            g.setColor(java.awt.Color.blue);
        else 
            g.setColor(java.awt.Color.black);
        int imgSize = Element.imageRealSize(scale);
        g.drawRect(imgSize*i, imgSize*j, imgSize-1, imgSize-1);
    }

    @Override
    public String toString() {
        return m_element.toString();
    }
}
