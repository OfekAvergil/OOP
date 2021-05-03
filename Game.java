//316279702

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;

/**
 * @author ofek avergil
 * @version 1.0
 * @since 24/04/2021
 */
public class Game {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int BORDER_SIZE = 5;
    private static final int BALL_RADIUS = 5;


    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private biuoop.Sleeper sleeper;

    /**
     * Constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * adds collidable to the game.
     * @param c - Collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adds Sprite to the game.
     * @param s - Sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle), and add them to the game.
     */
    public void initialize() {
        Random rand = new Random();

        //create the window
        this.gui = new GUI("Random Circles Example", WINDOW_WIDTH, WINDOW_HEIGHT);
        this.sleeper = new biuoop.Sleeper();
        this.createBorders();

        //create the paddle
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle pad = new Paddle(keyboard);
        pad.addToGame(this);

        //create the balls
        createBall(new Velocity(5, 5), new Point(200, 500));
        createBall(new Velocity(5, -5), new Point(200, 500));

        //create the blocks
        this.createBlocks(6, 12);
    }

    /**
     * create the blocks on the screen.
     * @param rowsNum - the number of block rows
     * @param blocksNum - the number of blocks in each row
     */
    private void createBlocks(int rowsNum, int blocksNum) {
        int i, j, row = 20, column = WINDOW_WIDTH - 85;
        int red = 0, g = 0, b = 100;
        for (j = 0; j < rowsNum; j++) {
            for (i = 0; i < blocksNum; i++) {
                Point p1 = new Point(column, row);
                Rectangle r = new Rectangle(p1, 60, 20);
                java.awt.Color color = new Color(red, g, b + j * 20);
                Block block = new Block(r, color);
                block.addToGame(this);
                column -= 60;
            }
            row += 20;
            column = WINDOW_WIDTH - 85;
            blocksNum--;
        }
    }

    /**
     * draws the border.
     */
    private void createBorders() {
        Block rightBlock = new Block(new Point(0, 0), BORDER_SIZE, WINDOW_HEIGHT);
        rightBlock.addToGame(this);
        Block leftBlock = new Block(new Point(WINDOW_WIDTH - BORDER_SIZE, 0), BORDER_SIZE, WINDOW_HEIGHT);
        leftBlock.addToGame(this);
        Block upBlock = new Block(new Point(0, 0), WINDOW_WIDTH, BORDER_SIZE);
        upBlock.addToGame(this);
        Block downBlock = new Block(new Point(0, WINDOW_HEIGHT - BORDER_SIZE),  WINDOW_WIDTH, BORDER_SIZE);
        downBlock.addToGame(this);
    }

    /**
     * initialize ball.
     * @param v - the ball's velocity
     * @param center - the ball's center Point
     */
    private void createBall(Velocity v, Point center) {
        Ball b1 = new Ball(center, BALL_RADIUS, Color.BLACK, this.environment);
        b1.setVelocity(v);
        b1.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }



}
