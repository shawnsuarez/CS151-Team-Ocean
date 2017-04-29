import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Test
{
	public static void main(String[] args)
	{
		ArrayList<Pit> pits = new ArrayList<Pit>(14);
		for(int i = 0; i < 14 ; i++)
		{
			if(i == 6 || i == 13)
				pits.add(new Pit(true));
			else
				pits.add(new Pit(i));
		}
		
		for(Pit p: pits)
		{
			System.out.println(p);
		}
		
		System.out.println(pits.size());
		
		MancalaBoard mb = new MancalaBoard();
		
		ArrayList<Pit> mbpits = mb.getPits();
		
		for(int i = 0; i < mbpits.size(); i++)
		{
			if(i < 6)
				mbpits.get(i).setStones(3);
		}
		
		mb.mancalaMove(5);
//		for(int i = 0; i < pits.size() - 1; i++)
//		{
//			System.out.println(pits.get(i) + " is across: " + mb.getAcross(i) );
//		}
	
		for(Pit p : mbpits)
		{
			System.out.println(p);
		}
	}
}
