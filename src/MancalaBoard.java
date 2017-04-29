import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

/**
 * Mancala Board model
 * @author Team Ocean - Shawn Darrel Suarez
 *
 */
public class MancalaBoard
{
	private DrawableStyle ds;
	private int stoneCount;
	private ArrayList<Pit> pits;
	private ArrayList<ChangeListener> clist;
	private boolean isP1;
	
	final static int PIT_COUNT = 14;
	
	/**
	 * Constructs a Mancala Board with the starting stones
	 * @param stones the starting amount of stones in each pit
	 */
	public MancalaBoard(int stones)
	{
		this.stoneCount = stones;
		this.pits = new ArrayList<Pit>(PIT_COUNT);
		for(int i = 0; i < PIT_COUNT ; i++)
		{
			if(i == 6 || i == 13)
				pits.add(new Pit(true));
			else
				pits.add(new Pit(stones));
		}
		this.clist = new ArrayList<ChangeListener>();
		this.isP1 = true; //Starts the game with player 1
	}
	
	/**
	 * Constructs an empty Mancala Board
	 */
	public MancalaBoard()
	{
		this.stoneCount = 0;
		this.pits = new ArrayList<Pit>(PIT_COUNT);
		for(int i = 0; i < PIT_COUNT ; i++)
		{
			if(i == 6 || i == 13)
				pits.add(new Pit(true));
			else
				pits.add(new Pit(stoneCount));
		}
		this.clist = new ArrayList<ChangeListener>();
		this.isP1 = true; //Starts the game with player 1
	}
	
	/**
	 * Constructs a MancalaBoard with starting stones and a DrawableStyle
	 * @param stones The starting amount of stones
	 * @param ds The starting DrawableStyle
	 */
	public MancalaBoard(int stones, DrawableStyle ds)
	{
		this(stones);
		this.ds = ds;
	}
	
	/**
	 * Sets the new starting stone count, effectively restarting the game
	 * @param stones the new starting stone count
	 */
	public void setStoneCount(int stones)
	{
		this.stoneCount = stones;
		for(int i = 0; i < pits.size(); i++)
		{
			if(i == 6 || i == 13)
			{
				pits.get(i).setStones(0);
			}
			else
			{
				pits.get(i).setStones(stones);
			}
		}
	}
	
	/**
	 * Gets the Mancala Board's starting stone count
	 * @return the starting stone count
	 */
	public int getStoneCount()
	{
		return stoneCount;
	}
	
	/**
	 * Gets the name of the style being used
	 * @return the style name
	 */
	public String getStyle()
	{
		return ds.getStyleName();
	}
	
	/**
	 * Sets the board's style
	 * @param ds
	 */
	public void setStyle(DrawableStyle ds)
	{
		this.ds = ds;
	}
	
	/**
	 * Draws the Mancala Board using the current DrawableStyle
	 * @param g2 Graphics2D
	 */
	public void draw(Graphics2D g2)
	{
		ds.draw(g2);
	}
	
	/**
	 * Attaches change listener
	 * @param c
	 */
	public void addChangeListener(ChangeListener c)
	{
		clist.add(c);
	}
	
	/**
	 * Do a player's move starting at a given pit index
	 * @param pitInd The index of the pit that the player has clicked on
	 */
	public void mancalaMove(int pitInd)
	{
		Pit startPit = pits.get(pitInd);
		int startPitScore = startPit.getStones();
		startPit.setEmpty();
		
		for(int i = startPitScore; i >= 0; i--)
		{
			pits.get(++pitInd).addStones(1);
			if(i == 0 && pits.get(pitInd).isEmpty())
			{
				Pit acrossPit = getAcross(pitInd);
				if(isP1)
				{
					pits.get(6).addStones(acrossPit.getStones());
					acrossPit.setEmpty();
				}
				else
				{
					pits.get(13).addStones(acrossPit.getStones());
					acrossPit.setEmpty();
				}
			}
		}
		
		for(ChangeListener c : clist)
		{
			c.stateChanged(new ChangeEvent(this));
		}
	}
	
	/**
	 * Helper method that returns the pit across from the given pit
	 * @param pitInd The index of the given pit
	 * @return The Pit across
	 */
	public Pit getAcross(int pitInd)
	{
		return pits.get(2*6 - pitInd);
	}
}
