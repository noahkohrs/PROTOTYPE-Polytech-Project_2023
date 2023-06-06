package info3.level.editor;

import java.io.IOException;

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
}
