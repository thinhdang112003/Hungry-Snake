package game;

import java.awt.Color;
import java.awt.Graphics;
import game.Apple.SpecialApple;

/**
* Snake object is a subclass extending from polygon which paints a snake on the game board.
* When this object collides with an apple object, the length of the snake body will increase by one,
* regardless of whether the snake collides with a special apple or not.
* 
* 
* @author Quoc Dang
* @author Anthony Chan
*/
public class Snake extends Polygon {

	static Point[] snakeBody = {new Point(5, 4), new Point(5, 3), new Point(5, 2)};
	static Point initialPoint = new Point(5, 10);
	static double rotation = 90;
	private static int score;
	public int currentScore;

	private static int snakeLength = 3;

	long time = 0;

	public static int GO_UP = 1;
	public static int GO_DOWN = -1;
	public static int GO_LEFT = 2;
	public static int GO_RIGHT = -2;
	public int vector = Snake.GO_DOWN;

	boolean isPlaying = true;
	boolean isGameOver = false;
	boolean updateVector = true;

	/**
	 * Class constructor for the Snake class that specifies the shape, location, and 
	 * rotation for the Snake object. 
	 * This method initializes the body of the snake as well as the length for the snake 
	 * as it updates. 
	 * Initializes the static snakeBody and snakeLength classes with the inShape parameter and its length.
	 * 
	 * @param inShape Shape of the Snake.
	 * @param inPosition Location of the Snake.
	 * @param inRotation Rotation of the Snake.
	 */
	public Snake(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		snakeBody = inShape;
		snakeLength = inShape.length;
	}

	/**
	 * Default class constructor for the snake class that specifies the default
	 * shape, location, and rotation for the snake object.
	 */
	public Snake() {
		super(snakeBody, initialPoint, rotation);
	}

	/**
	 * Sets the beginning default points for the snake and it's segments when the hits game start. 
	 * Called in the Update method when the snake collides with itself. 
	 * Will reset the snake body position and the snake length at the coordinates specified.
	 * 
	 * @see Update()
	 */
	public void resetGame() {
		Snake.snakeBody = new Point[3];
		Snake.snakeBody[0] = new Point(5, 4);
		Snake.snakeBody[1] = new Point(5, 3);
		Snake.snakeBody[2] = new Point(5, 2);

		Snake.initialPoint = new Point(5, 10);
		Snake.rotation = 90;
		Snake.snakeLength = 3;

	}

	/**
	 * This method updates the direction that the Snake is facing with the integer
	 * value given by the SnakeMove inner class.
	 * 
	 * @see KeyPressed() in SnakeMove inner class
	 * @param currentVector Integer received by the snakeMove inner class that uses
	 * keyListener interface.
	 */
	public void setVector(int currentVector) {
		if (vector != -currentVector && updateVector) {
			vector = currentVector;
			updateVector = false;
		}
	}

	/**
	 * This method returns an integer that is related to the player's score every
	 * time the Snake collides with an apple object.
	 * 
	 * @return the current score of the player
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Resets the static game score variable to zero after the game ends which is
	 * when the Snake has collided with itself. 
	 * Called in the Update method when the snake collides with itself.
	 * 
	 * @see Update()
	 */
	public void resetScore() {
		Snake.score = 0;
	}

	/**
	 * This method updates the body of the Snake every time the Snake is in a
	 * collision with an apple object or with the outside of the game board. 
	 * Allows the Snake body to move in conjunction with the rest of its segments and also
	 * checks when the Snake has intersected itself or other objects. 
	 * <p> 
	 * Checks when the Snake head has collided with the border of the screen. 
	 * If collided, the Snake head will loop around to the other side of the screen. 
	 * Checks when the Snake head has collided with itself. 
	 * If collided, the method will call resetGame and resetScore, putting the Snake back to its default length,
	 * position, and player score of zero. 
	 * Checks when the Snake head has collided with a red or pink apple. 
	 * If collided the score of the player will increase by one and three respectively.
	 * 
	 * @see resetGame()
	 * @see resetScore()
	 */
	public void update() {

		if (System.currentTimeMillis() - time > 200) {
			updateVector = true;

			// this loop checks for collision between the snake head and it's segments
			for (int i = 1; i < snakeLength; i++) {
				if ((int) snakeBody[0].getX() == (int) snakeBody[i].getX()
						&& (int) snakeBody[0].getY() == (int) snakeBody[i].getY()) {
					isPlaying = false;
					isGameOver = true;
					resetGame();
					currentScore = score;
					resetScore();
				}
			}

			if (Apple.getBG()[(int) snakeBody[0].getX()][(int) snakeBody[0].getY()] == 2) {
				snakeLength++;
				score += 1;

				// lambda expression
				AddSnakeBody add = (array) -> {
					array = new Point[snakeLength];
					for (int i = 0; i < snakeLength - 1; i++) {
						array[i] = snakeBody[i];
					}

					array[snakeLength - 1] = new Point(snakeBody[snakeLength - 2].getX() - 1,
							snakeBody[snakeLength - 2].getY() - 1);

					return array;
				};
				snakeBody = add.addSnakeBody(snakeBody);
				Apple.getBG()[(int) snakeBody[0].getX()][(int) snakeBody[0].getY()] = 0;
				Point newApplePosition = Apple.getNewApplePosition();
				Apple.getBG()[(int) newApplePosition.x][(int) newApplePosition.y] = 2;
			}

			// if the snake eats the special apple, increase point by 3
			if (SpecialApple.getBG1()[(int) snakeBody[0].getX()][(int) snakeBody[0].getY()] == 2) {
				snakeLength++;
				score += 3;

				// lambda expression
				AddSnakeBody add = (array) -> {
					array = new Point[snakeLength];
					for (int i = 0; i < snakeLength - 1; i++) {
						array[i] = snakeBody[i];
					}

					array[snakeLength - 1] = new Point(snakeBody[snakeLength - 2].getX() - 1,
							snakeBody[snakeLength - 2].getY() - 1);

					return array;
				};
				snakeBody = add.addSnakeBody(snakeBody);
				SpecialApple.getBG1()[(int) snakeBody[0].getX()][(int) snakeBody[0].getY()] = 0;
				Point newApplePosition = SpecialApple.getNewSpecialApplePosition();
				SpecialApple.getBG1()[(int) newApplePosition.x][(int) newApplePosition.y] = 2;
			}

			for (int i = snakeLength - 1; i > 0; i--) {
				if (snakeBody[i] != null) {
					snakeBody[i].setX(snakeBody[i - 1].getX());
					snakeBody[i].setY(snakeBody[i - 1].getY());
				}
			}

			if (vector == Snake.GO_UP) {
				snakeBody[0].setY(snakeBody[0].getY() - 1);
			}
			if (vector == Snake.GO_DOWN) {
				snakeBody[0].setY(snakeBody[0].getY() + 1);
			}
			if (vector == Snake.GO_LEFT) {
				snakeBody[0].setX(snakeBody[0].getX() - 1);
			}
			if (vector == Snake.GO_RIGHT) {
				snakeBody[0].setX(snakeBody[0].getX() + 1);
			}

			// when the snake goes out of bounds, the snake head will loop around to the
			// other side
			if (snakeBody[0].getX() < 0) {
				snakeBody[0].setX(19);
			}
			if (snakeBody[0].getX() > 19) {
				snakeBody[0].setX(0);
			}
			if (snakeBody[0].getY() < 0) {
				snakeBody[0].setY(19);
			}
			if (snakeBody[0].getY() > 19) {
				snakeBody[0].setY(0);
			}
			time = System.currentTimeMillis();
		}
	}

	/**
	 * Paints the Snake head and body shape along with the color using the Graphics object 
	 * in the parameter. 
	 * Called in the paint method in HungrySnake which
	 * draws out the Snake on to the game board.
	 * 
	 * @see paint in HungrySnake
	 * @param brush Object from the graphics class used to paint the object on the
	 * screen.
	 */
	public void paint(Graphics brush) {
		for (int i = 0; i < snakeBody.length; i++) {
			if (i == 0) {
				brush.setColor(Color.green);
				brush.fillOval((int) snakeBody[i].getX() * 20 + 1, (int) snakeBody[i].getY() * 20 + 1, 18, 18);
			} else {
				brush.setColor(Color.GREEN);
				brush.fillRect((int) snakeBody[i].getX() * 20 + 1, (int) snakeBody[i].getY() * 20 + 1, 18, 18);
			}
		}

	}

}