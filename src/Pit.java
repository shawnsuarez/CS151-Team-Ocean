/**
 * Model of a Mancala Pit
 * @author Team Ocean - Shawn Darrel Suarez
 *
 */
public class Pit 
{
	private int stones;
	private boolean isEmpty;
	private boolean isPlayerPit;
	
	/**
	 * Constructs a normal Mancala Pit (ie. can be selected by any player)
	 * @param stones The starting amount of stones
	 */
	public Pit(int stones)
	{
		this.stones = stones;
		this.isEmpty = false;
		this.isPlayerPit = false;
	}
	
	/**
	 * Constructs a player Mancala Pit which holds the player's scores
	 * @param stones The starting amount of stones
	 * @param player If this pit is a player pit
	 */
	public Pit(boolean player)
	{
		this.isEmpty = true;
		this.stones = 0;
		this.isPlayerPit = player;
	}
	
	/**
	 * Add stones to the Pit's score
	 * @param addStones The amount of stones to be added
	 */
	public void addStones(int addStones)
	{
		this.stones += addStones;
	}
	
	/**
	 * Sets the amount of stones to a new value
	 * @param stones The new amount of stones
	 */
	public void setStones(int stones)
	{
		this.stones = stones;
	}
	
	/**
	 * Gets the amount of stones
	 * @return The amount of stones
	 */
	public int getStones()
	{
		return this.stones;
	}
	
	/**
	 * Checks if the pit is empty
	 * @return isEmpty
	 */
	public boolean isEmpty()
	{
		return this.isEmpty;
	}
	
	/**
	 * Checks if the pit is a player pit
	 * @return
	 */
	public boolean isPlayerPit()
	{
		return isPlayerPit;
	}
	
	/**
	 * Empties the pit
	 */
	public void setEmpty()
	{
		this.stones = 0;
		this.isEmpty = true;
	}
	
	/**
	 * To string helper
	 */
	public String toString()
	{
		return "PitScore: " + stones;
	}
}
