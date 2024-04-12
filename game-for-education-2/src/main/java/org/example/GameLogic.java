package org.example;

import org.example.logic.*;

import java.awt.*;
import java.util.ArrayList;

public class GameLogic {
    private Ball ball;
    private ArrayList<Enemy> enemies;
    private ArrayList<Wall> walls;

    public GameLogic() {
        this.ball = null;
        this.enemies = new ArrayList<>();
        this.walls = new ArrayList<>();
    }

    public void initialize() {

        ball = new Ball(20, 20, "bomb_green.jpg");

        Enemy enemy1 = new Enemy(350,350, "bomb.jpg",100);
        Enemy enemy2 = new Enemy(150,250, "bomb.jpg",100);
        enemies.add(enemy1);
        enemies.add(enemy2);

        Wall wall1 = new Wall(250, 30, 250, 130, Color.BLACK);
        Wall wall2 = new Wall(100, 50, 150, 50, Color.BLACK);
        walls.add(wall1);
        walls.add(wall2);
    }

    public void update() {
        //ball.move(2, Direction.RIGHT);
        for (Enemy enemy: enemies) {
            int differenceX = Math.abs(ball.getCoord().x - enemy.getCoord().x);
            int differenceY = Math.abs(ball.getCoord().y - enemy.getCoord().y);

            if (differenceX > differenceY) {
                // Direction LEFT, RIGHT
                if (ball.getCoord().x - enemy.getCoord().x > 0) {
                    // Direction RIGHT
                    enemy.move(5, Direction.RIGHT);
                } else {
                    // Direction LEFT
                    enemy.move(5, Direction.LEFT);
                }
            } else {
                // Direction UP, DOWN
                if (ball.getCoord().y - enemy.getCoord().y > 0) {
                    // Direction DOWN
                    enemy.move(5, Direction.DOWN);
                } else {
                    // Direction UP
                    enemy.move(5, Direction.UP);
                }
            }
        }
        for (Wall wall: walls) {
            if (ball.isCollided(wall.getRectangle())){
                System.out.println("kolize se zdi, konec hry");
                System.exit(0);
            }
        }
        for(Enemy enemy: enemies){
            if(ball.isCollided(enemy.getRectangle())){
                System.out.println("kolize s nepritelem, konec hry");
                System.exit(0);
            }
        }
    }


    public ArrayList<Enemy> getEnemy() {
        return enemies;
    }

    public Ball getBall() {
        return ball;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
}
