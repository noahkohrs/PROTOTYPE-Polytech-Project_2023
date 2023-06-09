package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Map {
    int width;
    int height;
    List<Entity> entities ;
    //Tab of blocks
    Block fixedMap[][] ;

    public Map(String filename) throws IOException {
        loadMap(filename);
    }
    void loadMap(String filename) throws IOException {
        //Load the map from the file level.json
        //Convert a file to a string
        String content = readFile(filename);
        JSONObject json = new JSONObject(content);

        this.width = json.getInt("width");
        this.height = json.getInt("height");
        fixedMap = new Block[width][height];
        entities = new ArrayList<Entity>();
        
        JSONArray jsonBlocks = json.getJSONArray("elements");
        for (int i = 0; i < jsonBlocks.length(); i++) {
            JSONObject jsonBlock = jsonBlocks.getJSONObject(i);
            String id = jsonBlock.getString("id");
            int x = jsonBlock.getInt("x");
            int y = jsonBlock.getInt("y");
            JSONObject tags = jsonBlock.getJSONObject("tags");
            if (tags.has("fixed")) {
                fixedMap[x][y] = IdToBlock(id, x, y);
            } else {
                entities.add(IdToBlock(id, x, y));
            }
            
        }
    }

    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");
    
        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
    
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
    Block IdToBlock(String id, int x, int y) {
        return new Block(x, y);
    }

    public int realWidth() {
        return width*32 ;
    }
    public int realHeight() {
        return height*32 ;
    }

    void paint(Graphics g) {
        for (Entity entity : entities) {
            entity.paint(g);
        }
        for (int i = 0 ; i < width ; i++)
            for (int j = 0 ; j < height ; j++)
                if (fixedMap[i][j] != null)
                    fixedMap[i][j].paint(g);
        g.setColor(Color.yellow);
        g.drawRect(0, 0, realWidth(), realHeight());
    }

void viewportView(Graphics g, double scale) {
        g.setColor(Color.yellow);
        for (Entity entity : entities) {
            entity.viewportView(g, scale);
        }
        for (int i = 0 ; i < width ; i++)
            for (int j = 0 ; j < height ; j++)
                if (fixedMap[i][j] != null)
                    fixedMap[i][j].viewportView(g, scale);
        g.drawRect(Viewport.OnCamViewX(0, scale), Viewport.OnCamViewY(0,scale), Viewport.realSize(realWidth(), scale), Viewport.realSize(realHeight(), scale));
}
    
}
