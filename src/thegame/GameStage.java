package thegame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameStage extends Application {

    GraphicsContext gc ;
    List<GameField> gameObject = new ArrayList<>();


    @Override
    public void start(Stage stage){
        //Tao canvas
        Menu tower = new Menu("simple tower \n 3$");
        Canvas canvas = new Canvas(128*9 , 128*5);
        gc = canvas.getGraphicsContext2D();
        //Tao root container
        Group root= new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(tower);
        //tao scene
        Scene scene = new Scene(root);
        //them scene vao stage
        stage.setTitle("Tower defense 2.0");
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                render();
              update();
            }
        };
        timer.start();
        gameObject.add(createEnemy());
    }
    public Enemy createEnemy(){
        Enemy enemy = new Enemy();
        enemy.x = 64;
        enemy.y = 128*3+64;
        enemy.speed = 10;
        enemy.direction = Direction.RIGHT;
        enemy.img  = new Image("./resourceimg/enemy.png");
        return  enemy;
    }

    public static final Point[] wayPoint = new Point[] {
            new Point(64,128*3+64),
            new Point(128*2+64,128*3+64),
            new Point(128*2+64,128+64),
            new Point(128*6+64,128+64),
            new Point(128*6+64,128*4+80)
    };
    public static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    public static final String[][] Map_sprite = new String[][]{
            {"grass","grass","grass","grass","grass","grass","grass","grass","grass"},
            {"grass","grass","road","road","road","road","road","grass","grass"},
            {"grass","grass","road","grass","grass","grass","road","grass","grass"},
            {"road","road","road","grass","grass","grass","road","grass","grass"},
            {"grass","grass","grass","grass","grass","grass","road","grass","grass"},
    };
    private void drawMap(GraphicsContext gc){
        for (int i = 0; i < Map_sprite.length; i++) {
            for (int j = 0;j<Map_sprite[i].length;j++){
                gc.drawImage(new Image("./resourceimg/"+Map_sprite[i][j]+".png"), j*128 ,i*128);
            }
            
        }

    }
    public void update(){
        gameObject.forEach(GameField::update);
    }

    //In ra man hinh
    public void render(){
        drawMap(gc);
        gameObject.forEach(i ->i.render(gc));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

