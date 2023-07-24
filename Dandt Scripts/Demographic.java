package DandtScripts;
import java.awt.*;

public class Demographic {
    
    private String name;
    private Color color;

    public Demographic(String name, int r, int g, int b){
        this.name = name;
        color = new Color(r,g,b);
    }

    public String getName() {
        return name;
    }
    public Color getColor() {
        return color;
    }
}
