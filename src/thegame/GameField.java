package thegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameField {
    int x;
    int y;
    Image img;
    void update(){};
    void render(GraphicsContext gc){};
}
