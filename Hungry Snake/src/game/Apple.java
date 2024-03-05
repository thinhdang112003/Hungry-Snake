package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Apple object is a subclass extending from polygon which paints a red apple on the 
 * board for the snake to eat. 
 * When this object collides with the snake class' head, it will be removed from the screen and spawn 
 * somewhere else at a random coordinate location.
 * 
 * @author Quoc Dang
 * @author Anthony Chan
 */
public class Apple extends Polygon {

	static Random r;
	static int x;
	static int y;
	static int[][] bg = new int[20][20];
	public static Point[] apple = { new Point(0, 0), new Point(0, 10), new Point(10, 10), new Point(10, 0) };

	/**
	 * Class constructor for the Apple class that specifies the shape, location, and rotation for the
	 * Apple object. 
	 * This method initializes the position of the Apple as well as the size.
	 * 
	 * @param inShape Shape of the Apple.
	 * @param inPosition Location of the Apple.
	 * @param inRotation Rotation of the Apple.
	 */
	public Apple(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}

	/**
	 * This method will return a random point on the board when the Snake head
	 * collides with an apple object. 
	 * The point will be assigned to a new Apple object.
	 * 
	 * @return new point that is randomized within the borders of the game board.
	 */
	public static Point getNewApplePosition() {
		Random r = new Random();
		Apple.x = r.nextInt(19);
		Apple.y = r.nextInt(19);
		Apple.bg[x][y] = 2;
		return new Point(x, y);
	}

	/**
	 * Returns the coordinate of the Apple.
	 * 
	 * @return 2d integer array that corresponds with Apple location.
	 */
	public static int[][] getBG() {
		return bg;
	}

	/**
	 * Default class constructor for the Apple class that specifies the default
	 * shape, location, and rotation for the Apple object.
	 */
	public Apple() {
		super(apple, getNewApplePosition(), 90);
	}

	/**
	 * Paints the Apple shape and color with the Graphics object in the parameter.
	 * Called in the paint method in HungrySnake which draws out the Apple on to the
	 * game board
	 * 
	 * @see paint() in HungrySnake
	 * @param brush Object from the graphics class used to paint the object on the
	 * screen.
	 */
	public void paint(Graphics brush) {
		brush.setColor(Color.RED);
		brush.fillOval(x * 20 + 1, y * 20 + 1, 18, 18);
	}

	/**
	 * SpecialApple object is a subclass extending from polygon which paints a pink
	 * apple on the board for the snake to eat. 
	 * When this object collides with the snake class' head, it will be removed 
	 * from the screen and spawn somewhere else at a random coordinate location.
	 * 
	 * @author Quoc Dang
	 * @author Anthony Chan
	 */
	public class SpecialApple extends Polygon {

		Random r;
		public static int x;
		public static int y;
		public static int[][] specialApple = new int[20][20];

		public static Point[] specialApple1 = { new Point(0, 0), new Point(0, 10), new Point(10, 10),
				new Point(10, 0) };

		/**
		 * Class constructor for the SpecialApple class that specifies the shape, location, and 
		 * rotation for the SpecialApple object. 
		 * This method initializes the position of the SpecialApple as well as the size.
		 * 
		 * @param inShape Shape of the SpecialApple.
		 * @param inPosition Location of the SpecialApple.
		 * @param inRotation Rotation of the SpecialApple.
		 */
		public SpecialApple(Point[] inShape, Point inPosition, double inRotation) {
			super(inShape, inPosition, inRotation);
		}

		/**
		 * This method will return a random point on the board when the Snake head
		 * collides with an apple object. 
		 * The point will be assigned to a new SpecialApple object.
		 * 
		 * @return new point that is randomized within the borders of the game board.
		 */
		public static Point getNewSpecialApplePosition() {
			Random r = new Random();
			SpecialApple.x = r.nextInt(19);
			SpecialApple.y = r.nextInt(19);
			SpecialApple.specialApple[x][y] = 2;
			return new Point(x, y);
		}

		/**
		 * Returns the coordinate of the SpecialApple.
		 * 
		 * @return 2d integer array that corresponds with SpecialApple location.
		 */
		public static int[][] getBG1() {
			return specialApple;
		}

		/**
		 * Default class constructor for the SpecialApple class that specifies the
		 * default shape, location, and rotation for the SpecialApple object.
		 */
		public SpecialApple() {
			super(specialApple1, getNewSpecialApplePosition(), 90);
		}

		/**
		 * Paints the SpecialApple shape and color with the Graphics object in the parameter. 
		 * Called in the paint method in HungrySnake which draws out the SpecialApple on 
		 * to the game board.
		 * 
		 * @see paint() in HungrySnake
		 * @param brush Object from the graphics class used to paint the object on the
		 * screen.
		 */
		public void paint(Graphics brush) {
			brush.setColor(Color.PINK);
			brush.fillOval(x * 20 + 1, y * 20 + 1, 18, 18);
		}
	}

}