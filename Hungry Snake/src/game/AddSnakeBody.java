package game;

/**
 * AddSnakeBody is a functional interface used for the lambda class in the snake
 * object that changes the size length array of the snake.
 * 
 * @author Quoc Dang
 * @author Anthony Chan
 */
public interface AddSnakeBody {

	/**
	 * Interface method that returns a point array as a return type.
	 * 
	 * @return Point array with a new size.
	 */
	public Point[] addSnakeBody(Point[] array);
}