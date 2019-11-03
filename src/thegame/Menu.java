package thegame;

import java.awt.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Menu extends Button {
        public Menu(String s){
        this.setText(s);
       this.setPrefWidth(140);
       this.setPrefHeight(90);
        this.setVisible(true);
        this.setGraphic(new ImageView("./resourceimg/simple tower.png"));
        }
}
