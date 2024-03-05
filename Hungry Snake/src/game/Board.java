package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Board object is a subclass extending from Polygon which paints a grey border on the 
 * edges of the screen of the game. 
 * Mostly used for UI purposes in order to make the game better looking and to make the 
 * score more visible to the player.
 * 
 * @author Quoc Dang
 * @author Anthony Chan
 */
public class Board extends Polygon {

	private int[] xCords;
	private int[] yCords;

	/**
	 * Class constructor for the board class that specifies the shape, location, and
	 * rotation of the board object. This method initializes a board object with the
	 * given parameters and the size of the xCord and yCord private fields using
	 * this objects points length.
	 * 
	 * @param inShape Shape of the gray board.
	 * @param inPosition Location of the gray board.
	 * @param inRotation Rotation of the gray board.
	 */
	public Board(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		xCords = new int[this.getPoints().length];
		yCords = new int[this.getPoints().length];
	}

	/**
	 * Once this method is called in HungrySnake's paint method, it will show a
	 * gray border with orange outlines and display it on the game screen by using
	 * the Graphics parameter. Initializes the xCords and yCords private fields with
	 * this points' x and y values.
	 * 
	 * @see paint() in HungrySnake
	 * @param brush Object from the graphics class used to paint the object on the
	 * screen.
	 */
	public void paint(Graphics brush) {
		int i = 0;
		int j = 0;
		for (Point p : this.getPoints()) {
			xCords[i] = (int) p.getX();
			yCords[j] = (int) p.getY();
			i++;
			j++;
		}
		brush.setColor(Color.DARK_GRAY);
		brush.fillPolygon(xCords, yCords, 4);
		brush.setColor(Color.orange);
		brush.drawRect((int) this.position.x - 25, (int) this.position.y - 25, 1000, 1000);
	}

}