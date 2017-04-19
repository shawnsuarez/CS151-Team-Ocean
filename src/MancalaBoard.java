import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Mancala Board model
 * @author Team Ocean - Shawn S.
 *
 */
public abstract class MancalaBoard extends JLabel
{
	private int[] score;
	
	/**
	 * Constructs a Mancala Board with the starting stones
	 * @param stones the starting amount of stones in each pit
	 */
	public MancalaBoard(int stones)
	{
		this.score = new int[14];
		for(int i = 0; i < score.length; i++)
		{
			if(i != 6 || i != 13)
			{
				score[i] = stones;
			}
			else
			{
				score[i] = 0;
			}
		}
	}
	//MancalaBoard is the model this will store the score and pit values
}
