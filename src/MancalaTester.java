import java.awt.*;
import javax.swing.*;
import java.util.*;

public class MancalaTester 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		
		frame.setLayout(new BorderLayout());
		
		JLabel label = new JLabel();
		
		MancalaButtons buttons = new MancalaButtons();
		
		frame.add(buttons, BorderLayout.SOUTH);
		frame.add(label, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
