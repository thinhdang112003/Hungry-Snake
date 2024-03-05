package game;

/**
 * AddMessage interface is a functional interface used for the anonymous classes in 
 * GameBackground that concatenates a score label and the players score or high score
 * 
 * @author Quoc Dang
 * @author Anthony Chan
 */
public interface AddMessage {

	/**
	 * Interface method that has a string as a return type.
	 * 
	 * @return String with concatenated numbers and letters.
	 */
	public String Concatenate();
}