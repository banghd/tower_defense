package thegame;

import javafx.scene.canvas.GraphicsContext;

public class simple_tower extends GameField{
    @Override
    void render(GraphicsContext gc) {
        gc.drawImage(img,x,y);
    }

}
