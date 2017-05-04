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
	private int stoneCount;
	private int undoCount;
	private boolean isP1;
	private boolean hasExtraTurn;

	private ArrayList<Pit> pits;
	private int[] oldMoveScore;
	private ArrayList<ChangeListener> clist;
	
	private static final int MAX_UNDO = 3;
	private static final int PIT_COUNT = 14;
	
	/**
	 * Constructs a MancalaBoard with the starting stones
	 * @param stones The starting amount of stones in each pit
	 */
	public MancalaBoard(int stones)
	{
		this.stoneCount = stones;
		this.pits = new ArrayList<Pit>(PIT_COUNT);
		this.oldMoveScore = new int[PIT_COUNT];
		for(int i = 0; i < PIT_COUNT ; i++)
		{
			if(i == 6 || i == 13)
				pits.add(new Pit(true));
			else
				pits.add(new Pit(stones));
		}
		this.clist = new ArrayList<ChangeListener>();
		this.isP1 = true; //Starts the game with player 1
		this.hasExtraTurn = false;
		this.undoCount = MAX_UNDO;
		saveToOldPits(); //Initial copy of pits for undo function
	}
	
	/**
	 * Constructs an empty MancalaBoard
	 */
	public MancalaBoard()
	{
		this.stoneCount = 0;
		this.pits = new ArrayList<Pit>(PIT_COUNT);
		this.oldMoveScore = new int[PIT_COUNT];
		for(int i = 0; i < PIT_COUNT ; i++)
		{
			if(i == 6 || i == 13)
				pits.add(new Pit(true));
			else
				pits.add(new Pit(stoneCount));
		}
		this.clist = new ArrayList<ChangeListener>();
		this.isP1 = true; //Starts the game with player 1
		this.hasExtraTurn = false;
		this.undoCount = MAX_UNDO;
		saveToOldPits(); //Initial copy of pits for undo function
	}
	
	/**
	 * Sets the new starting stone count, effectively restarting the game
	 * @param stones The new starting stone count
	 */
	public void setStoneCount(int stones)
	{
		this.stoneCount = stones;
		for(int i = 0; i < pits.size(); i++)
		{
			if(i == 6 || i == 13)
			{
				pits.get(i).setEmpty();;
			}
			else
			{
				pits.get(i).setStones(stones);
			}
		}
		this.isP1 = true; //Starts the game with player 1
		this.hasExtraTurn = false;
		this.undoCount = MAX_UNDO;
		saveToOldPits();
	}
	
	/**
	 * Gets the MancalaBoard's starting stone count
	 * @return The starting stone count
	 */
	public int getStoneCount()
	{
		return stoneCount;
	}
	
	/**
	 * Attaches change listener
	 * @param c The change listener to be added
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
		if(pits.get(pitInd).isEmpty() || pits.get(pitInd).isPlayerPit())
			return;
		
		saveToOldPits(); //Saves copy of pits
		Pit startPit = pits.get(pitInd);
		int startPitScore = startPit.getStones();
		startPit.setEmpty();
		
		int currentInd = pitInd+1; //The index after the starting pit.
		for(int i = startPitScore; i > 0; i--)
		{
			if(currentInd > 13)
			{
				currentInd = 0;
			}
			//If the last pit is empty, take the stones from the pit across it.
			if(i == 1 && pits.get(currentInd).isEmpty() && currentInd != 6 && currentInd != 13)
			{
				Pit acrossPit = getAcross(currentInd);
				if(isP1) //Adds across score to P1's pit
				{
					pits.get(6).addStones(acrossPit.getStones());
					acrossPit.setEmpty();
				}
				else //Adds across score to P2's pit
				{
					pits.get(13).addStones(acrossPit.getStones());
					acrossPit.setEmpty();
				}
			}
			else if(i == 1 && (currentInd == 6 || currentInd == 13))
			{
				this.hasExtraTurn = true;
			}
			else
			{
				this.hasExtraTurn = false;
			}
			pits.get(currentInd).addStones(1);
			currentInd++;
		}
		
		for(ChangeListener c : clist)
		{
			c.stateChanged(new ChangeEvent(this));
		}
	}
	
	/**
	 * Undo a move by replacing the current pits with the old version
	 */
	public void mancalaUndo()
	{
		for(int i = 0; i < PIT_COUNT; i++)
		{
			pits.get(i).setStones(oldMoveScore[i]);
		}
		undoCount--;
	}
	
	/**
	 * Gets the amount of undos left
	 * @return The undo count
	 */
	public int getUndoCount()
	{
		return this.undoCount;
	}
	
	/**
	 * Checks if the player has an extra turn
	 * @return True/ False
	 */
	public boolean getHasExtraTurn()
	{
		return this.hasExtraTurn;
	}
	
	/**
	 * Resets undoCount to max and changes player
	 */
	public void mancalaEnd()
	{
		this.undoCount = MAX_UNDO;
		this.hasExtraTurn = false;
		this.isP1 = !isP1;
	}
	
	/**
	 * Saves the current pits as previous moves.
	 */
	public void saveToOldPits()
	{
		for(int i = 0; i < PIT_COUNT; i++)
		{
			oldMoveScore[i] = pits.get(i).getStones();
		}
	}
	
	/**
	 * Checks if the current player is Player 1
	 * @return True/ False
	 */
	public boolean isP1()
	{
		return isP1;
	}
	
	/**
	 * Gets the current player
	 * @return The current player
	 */
	public String getCurrentPlayer()
	{
		if(isP1)
			return "Player 1";
		return "Player 2";
	}
	
	/**
	 * Gets Player 1's score
	 * @return Player 1's score
	 */
	public int getPlayer1Score()
	{
		return pits.get(6).getStones();
	}
	
	/**
	 * Gets Player 2's score
	 * @return Player 2's score
	 */
	public int getPlayer2Score()
	{
		return pits.get(13).getStones();
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
	
	/**
	 * Gets the pits
	 * @return The pits
	 */
	public ArrayList<Pit> getPits()
	{
		return pits;
	}
	
	/**
	 * Checks if the pit at the given index is empty
	 * @param pitInd The index of the pit to be checked
	 * @return True/False isEmpty
	 */
	public boolean isPitEmpty(int pitInd)
	{
		return pits.get(pitInd).isEmpty();
	}
	
	/**
	 * Returns the string of hasExtraTurn
	 * @return The sting of hasExtraTurn
	 */
	public String getStringHasExtraTurn()
	{
		if(hasExtraTurn)
			return " EXTRA TURN!!!";
		return "";
	}
	
	/**
	 * Helper method that prints the current score
	 */
	public void printScore()
	{
		String score = "";
		for(Pit p : pits)
		{
			score += p.getStones() + " | ";
		}
		System.out.println(score);
	}
}
