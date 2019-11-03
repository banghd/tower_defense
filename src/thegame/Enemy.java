package thegame;


import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.awt.*;

public class Enemy extends GameField implements GameEntity {
    int wayPointindex =0;
        double speed;
    Direction direction;
    public Point getNextWayPoint(){
        if(wayPointindex==GameStage.wayPoint.length)return null;
        if(wayPointindex<GameStage.wayPoint.length-1){
            return GameStage.wayPoint[++wayPointindex];
        }
        return null;
    }
    @Override
    public void render(GraphicsContext gc){

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);

            ImageView iv = new ImageView(img);
            iv.setRotate(this.direction.getDegree());
            Image base = iv.snapshot(params, null);

            gc.drawImage(base, x, y);
            gc.setFill(Color.RED);
            gc.fillOval(GameStage.wayPoint[wayPointindex].x, GameStage.wayPoint[wayPointindex].y, 10, 10);

            gc.setFill(Color.BLUE);
            gc.fillOval(x, y, 10, 10);

    }
    void calculateDirection(){
        if(wayPointindex>=GameStage.wayPoint.length)return; // di den cuoi duong
        Point currentWP = GameStage.wayPoint[wayPointindex];
        if(GameStage.distance(x,y,currentWP.x,currentWP.y)<=speed){
            x = currentWP.x;
            y = currentWP.y;
            Point nextWayPoint = getNextWayPoint();
            if(nextWayPoint==null)return;
            double deltaX = nextWayPoint.x - x;
            double deltaY = nextWayPoint.y - y;
            if (deltaX > speed) direction = Direction.RIGHT;
            else if (deltaX < -speed) direction = Direction.LEFT;
            else if (deltaY > speed) direction = Direction.DOWN;
            else if (deltaY <= -speed) direction = Direction.UP;
        }
    }
    public void update(){
        calculateDirection();
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
    }
}
