package info3.level.editor;

import java.io.IOException;

import org.json.JSONObject;

public class Block1 extends Element {
    
    public Block1() throws IOException {
        super("resources/blocks/1.png");
    }
    
    public Element copy() throws IOException {
        return new Block1();
    }
    
    public String toString() {
        return "Block1";
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("fixed", true);
        return obj;
    }
}
