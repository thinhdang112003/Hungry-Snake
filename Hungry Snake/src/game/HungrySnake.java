package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

/**
 * Control a snake that continuously moves and eats apples! Try to eat as many as you can before you hit
 * yourself and be wary.. for every seventh apple, a magic one appears! 
 * This class controls a snake object and spawns in apple objects for the snake to eat. 
 * This class also dictates the laws of the snake game and the background as well. 
 * Responsible for drawing out the game that is called by the game class every second.
 * 
 * @author Quoc Dang
 * @author Anthony Chan
 */
public class HungrySnake extends Game implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int gameWidth = 500;
	private static final int gameHeight = 500;

	static int counter = 0;
	Board board;
	Board board2;

	Snake snake;
	Apple apple;
	Apple.SpecialApple specialApple;

	/**
	 * Class constructor for the HungrySnake class that specifies the name, width,
	 * and height for the game board. 
	 * This method also initializes all the objects on the game board but does not paint them out yet. 
	 * This constructor only initializes their values and sets them to their respective positions.
	 */
	public HungrySnake() {
		super("Hungry Snake", gameWidth, gameHeight);
		this.setFocusable(true);
		this.requestFocus();

		snake = new Snake();

		apple = new Apple();

		specialApple = apple.new SpecialApple();

		addKeyListener(new SnakeMove());

		Point[] boardVertical = { new Point(0, 0), new Point(1000, 0), new Point(1000, 100), new Point(0, 100) };
		Point[] boardHorizontal = { new Point(0, 0), new Point(0, 1000), new Point(100, 1000), new Point(100, 0) };

		board = new Board(boardHorizontal, new Point(420, 0), 0);
		board2 = new Board(boardVertical, new Point(0, 420), 0);
		this.setVisible(true);
	}

	/**
	 * Keeps the Snake object moving while the game has not finished. 
	 * Calls the snakes Update method continuously while the snake is still running or if the
	 * game has not stopped/paused.
	 * 
	 * @see Update() in Snake class
	 */
	public void run() {
		while (true) {
			if (snake.isPlaying == true) {
				snake.update();
				repaint();
			}
		}
	}

	/**
	 * Paints the Background of the game with the black color using the Graphics object in the parameter. 
	 * Called in the paint method, drawing out the black background and filling the borders.
	 * 
	 * @see paint()
	 * @param brush Object from the graphics class used to paint the object on the
	 * screen.
	 */
	public void paintBackGround(Graphics brush) {

		brush.setColor(Color.BLACK);
		brush.fillRect(0, 0, 400, 400);
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				brush.fillRect(i * 20 + 1, j * 20 + 1, 18, 18);
			}
		}
	}

	/**
	 * Paints the UI of the game using the colors from the Graphics object in the parameter. 
	 * Responsible for making the start screen, pause screen, and the game over screen for HungrySnake. 
	 * This method also calls upon Apple's paint, Snake's paint, Board's paint, and SpecialApple's paint 
	 * method to create and show the object on the board by using the Graphics object in the parameter.
	 * <p> 
	 * Displays the text on the side of the screen by using the Graphics object
	 * and also uses anonymous classes to concatenate the player's score and the
	 * score label to make the score of the player more noticeable.
	 * <p>
	 * Makes sure that if the snake eats a red apple instead of a pink one while the player
	 * has a score divisible by seven, the pink apple will be removed since the score no longer
	 * fits the criteria.
	 * 
	 * 
	 * @see paint() in Apple
	 * @see paint() in Snake
	 * @see paint() in Board
	 * @see paint() in SpecialApple
	 * @param brush Object from the graphics class used to paint the object on the
	 * screen.
	 */
	public void paint(Graphics brush) {

		paintBackGround(brush);
		drawScreenLine(brush);
		apple.paint(brush);

		if (snake.getScore() % 7 == 0 && snake.getScore() >= 7) {
			specialApple.paint(brush);
		}

		snake.paint(brush);

		board.paint(brush);
		board2.paint(brush);
		brush.setColor(Color.white);
		brush.drawString("Instructions: ", 400, 20);
		brush.drawString("Welcome to", 400, 35);
		brush.drawString("HungrySnake!", 400, 50);
		brush.drawString("Your objective ", 400, 65);
		brush.drawString("is to eat as", 400, 80);
		brush.drawString("many apples", 400, 95);
		brush.drawString("as you can!", 400, 110);
		brush.drawString("Stay alive for", 400, 125);
		brush.drawString("every seven", 400, 140);
		brush.drawString("apples you eat", 400, 155);
		brush.drawString("and a special", 400, 170);
		brush.drawString("one appears!", 400, 185);

		// ANONYMOUS CLASS
		AddMessage score = new AddMessage() {
			String score = "Score: ";

			public String Concatenate() {
				return score + snake.getScore();
			}
		};
		AddMessage highScore = new AddMessage() {
			String score = "High Score: ";

			public String Concatenate() {
				return score + snake.currentScore;
			}
		};
		brush.setColor(Color.black);
		brush.setFont(brush.getFont().deriveFont(28.0f));
		brush.drawString(score.Concatenate(), 40, 430);
		brush.drawString(highScore.Concatenate(), 250, 430);

		if (!snake.isPlaying) {
			brush.setColor(Color.white);
			brush.setFont(brush.getFont().deriveFont(18.0f));
			brush.drawString("PRESS TO PLAY GAME", 60, 200);
		}
		if (snake.isGameOver) {
			brush.setColor(Color.white);
			brush.setFont(brush.getFont().deriveFont(28.0f));
			brush.drawString("GAME OVER!", 60, 150);
		}

	}

	/**
	 * Draws the outlines for the screen bordering the black board of the snake by
	 * using the Graphics object in the parameter. 
	 * Called in paint method to show the lines
	 * 
	 * @see paint()
	 */
	public void drawScreenLine(Graphics brush) {
		brush.setColor(Color.ORANGE);
		brush.drawRect(1, 1, 400 - 3, 400 - 3);
	}

	public static void main(String[] args) {
		HungrySnake a = new HungrySnake();
		a.run();
	}

	/**
	 * Replaces the high score instance field when the player's score is higher from
	 * their previous highest. 
	 * If Snake has ate an apple more times than it did on the player's previous run, 
	 * the high score instance field will be updated.
	 * 
	 * @return high score of the player through all runs of the game
	 */
	public int highScore() {
		int highScore = snake.currentScore;
		if (snake.getScore() > highScore) {
			highScore = snake.getScore();
		}
		return highScore;
	}

	/**
	 * SnakeMove object is a class that implements the KeyListener interface that
	 * control's the snakes movement.
	 * 
	 * @author Quoc Dang
	 * @author Anthony Chan
	 */
	public class SnakeMove implements KeyListener {

		/**
		 * This method uses KeyPressed to move the snake in the direction that the user
		 * presses. If the user presses up, the snake will move up etc... This method
		 * uses the KeyListener interface to control the snake accordingly by passing
		 * integers into the Snake's setVector method. 
		 * <p> 
		 * If space is clicked, the game will change the boolean for Snake's isPlaying boolean value. 
		 * This will stop the game and pause it for the player. 
		 * To unpause, the player simply presses space again and the boolean value changes again. 
		 * This will allow the game to continue where the player left off.
		 * 
		 * @see setVector in Snake
		 */
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				snake.isPlaying = !snake.isPlaying;
				if (snake.isGameOver) {
					snake.isGameOver = !snake.isGameOver;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				snake.setVector(Snake.GO_UP);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				snake.setVector(Snake.GO_DOWN);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				snake.setVector(Snake.GO_RIGHT);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				snake.setVector(Snake.GO_LEFT);
			}
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}
}