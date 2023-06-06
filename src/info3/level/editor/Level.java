package info3.level.editor;

import java.awt.*;
import java.io.IOException;
import java.io.FileWriter;

public class Level {

    int x = 20 ;
    int y = 20 ;
    private ElementContainer m_elements[][];
    int width;
    int height;
    private float scale = 1.5f; 
    float scaleChange = 0 ; // 0 = no change, 1 = increase, -1 = decrease 



    public Level() throws IOException {
        this(10, 10);
    }

    public Level(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        m_elements = new ElementContainer[width][height];
        for (int i = 0; i < m_elements.length; i++) {
            for (int j = 0; j < m_elements[i].length; j++) {
                m_elements[i][j] = new ElementContainer(new VoidBlock(), i, j);
            }
        }
    }


    public void paint(Graphics g) {
        Graphics g2 = g.create(x, y, width*Element.imageRealSize(scale)+1, height*Element.imageRealSize(scale)+1);
        for (int i = 0; i < m_elements.length; i++) {
            for (int j = 0; j < m_elements[i].length; j++) {
                m_elements[i][j].paint(g2, i, j, scale);
            }
        }
    }

    private int scaleUpdate = 0;
    public void tick(long elapsed) {
        if (scaleChange != 0) {
            scaleUpdate += elapsed;
            if (scaleUpdate > 100) {
                scaleUpdate = 0;
                scale *= scaleChange;
                if (scale < 0.2f) {
                    scale = 0.2f;
                }
            }
        }
    }

    public int getRealWidth() {
        return width*Element.imageRealSize(scale);
    }

    public int getRealHeight() {
        return height*Element.imageRealSize(scale);
    }

    public ElementContainer select(int x, int y) {
        return m_elements[x/Element.imageRealSize(scale)][y/Element.imageRealSize(scale)];
    }

    public void changeElement(int x, int y) {
        m_elements[x/Element.imageRealSize(scale)][y/Element.imageRealSize(scale)].changeElement(LevelEditor.levelEditor.selected.m_element);
    }



    // public void exportJson(String filename) {
    //     Gson gson = new Gson();
    //     String json=gson.toJson(m_elements);

    //     try(FileWriter file = new FileWriter(filename)) {
    //         file.write(json);
    //         file.flush();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}

