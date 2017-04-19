
public class Pit 
{
	private int stones;
	private boolean isEmpty;
	private boolean isPlayerPit;
	private String playerName;
	
	public Pit(int stones)
	{
		this.stones = stones;
		this.isEmpty = false;
		this.isPlayerPit = false;
	}
	
	public Pit(String name, boolean player)
	{
		this.isEmpty = true;
		this.playerName = name;
		this.isPlayerPit = player;
	}
	
	public void add(int addStones)
	{
		this.stones += addStones;
	}
	
	public int getStones()
	{
		return this.stones;
	}
	
	public boolean isEmpty()
	{
		return this.isEmpty;
	}
	
	public void setEmpty(boolean b)
	{
		this.isEmpty = b;
	}
}
