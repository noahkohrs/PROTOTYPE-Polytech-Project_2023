package info3.level.editor;

import java.io.IOException;

public class VoidBlock extends Element {

    public VoidBlock() throws IOException {
        super("");
        System.out.println("Loading image " + spritePath);
    }

    public Element copy() throws IOException {
        return new VoidBlock();
    }

    public String toString() {
        return "VoidBlock";
    }
    
}
