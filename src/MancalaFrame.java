import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;

public class MancalaFrame extends JFrame
{
	private static final int FRAME_WIDTH = 1600;
	private static final int FRAME_HEIGHT = 900;
	
	public MancalaFrame()
	{
		setLayout(new BorderLayout());
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		MancalaBoard mb = new MancalaBoard(0);
		MancalaPanel mp = new MancalaPanel(mb);

		JPanel mancalaButtons = new JPanel();
		
		JButton endTurnButton = new JButton("End Turn");
		JButton undoButton = new JButton("Undo");
		JButton settingsButton = new JButton("Settings");
		
		endTurnButton.setToolTipText("<html>Ends the current player's turn.</html>");
		undoButton.setToolTipText("<html>Undo current move.</html>");
		settingsButton.setToolTipText("<html>Opens settings menu.</html>");
		
		//Beginning of settings Frame
		JPanel settingPanel = new JPanel();
		
		String[] styleChoice = { "Simple", "Fancy" };
		JComboBox styles = new JComboBox(styleChoice);
		styles.setToolTipText("<html>Changes the look of the mancala board</html>");
		if(mp.getStyle() != null)
			styles.setSelectedItem(mp.getStyle());

		Integer[] stoneChoice = { 3, 4 };
		JComboBox stones = new JComboBox(stoneChoice);
		if(mb.getStoneCount() != 0)
			stones.setSelectedItem(mb.getStoneCount());
		stones.setToolTipText("<html>Changes the starting amount of stones per pit<br>"
				+ "WARNING: Changing this setting restarts the game.</html>");

		JLabel styleOptions = new JLabel("Style Option: ");
		JLabel stoneOptions = new JLabel("Stone Options: ");

		JButton closeButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		
		settingPanel.setName("Settings");
		settingPanel.setLayout(new GridLayout(3, 3));
		settingPanel.add(styleOptions);
		settingPanel.add(styles);
		settingPanel.add(stoneOptions);
		settingPanel.add(stones);
		settingPanel.add(closeButton);
		settingPanel.add(cancelButton);

		//Setting Frame
		JFrame settingFrame = new JFrame();
		settingFrame.setSize(200, 150);
		settingFrame.add(settingPanel);

		settingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Settings Close Button - applies any changed settings
		closeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int stone_count = (int)stones.getSelectedItem();
				String style_select = (String)styles.getSelectedItem();
				
				if(style_select.equals("Simple"))
				{
					mp.setStyle(new SimpleStyle());
				}
				else if(style_select.equals("Fancy"))
				{
					mp.setStyle(new FancyStyle());
				}
				
				if(stone_count != mb.getStoneCount())
				{
					mb.setStoneCount(stone_count);
				}
				
				repaint();
				settingFrame.dispose();
			}
		});
		
		//Settings Cancel Button
		cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				settingFrame.dispose();
			}
		});

		//Changes player
		endTurnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("End Turn");
			}
		});
		
		//Adds ChangeListener that re-enables the undoButton after a move is done
		mb.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				undoButton.setEnabled(true);
			}
		});
		
		//
		mp.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event)
			{
				System.out.println(event.getX() + " | " + event.getY());
			}
		});

		//Undoes player's move
		undoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Undo");
				mb.mancalaUndo();
				mp.repaint();
				undoButton.setEnabled(false); //Player cannot make multiple undos in a row
			}
		});

		//Opens settings
		settingsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				settingFrame.setVisible(true);
				cancelButton.setEnabled(true); //Re-enable cancel button after initial startup
			}
		});
		
		mancalaButtons.add(endTurnButton);
		mancalaButtons.add(undoButton);
		mancalaButtons.add(settingsButton);
		
		//JLabel mancalaLabel = new MancalaLabel(mb);
		
		add(mp, BorderLayout.CENTER);
		add(mancalaButtons, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
		//Initial game startup
		settingFrame.setVisible(true);
		cancelButton.setEnabled(false); 
		undoButton.setEnabled(false);
	}
}
