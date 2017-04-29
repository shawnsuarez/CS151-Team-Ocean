import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;

public class MancalaFrame extends JFrame
{
	public MancalaFrame()
	{
		setLayout(new BorderLayout());
		
		MancalaBoard mb = new MancalaBoard(4, new FancyStyle());

		JPanel mancalaButtons = new JPanel();
		
		JButton endTurnButton = new JButton("End Turn");
		JButton undoButton = new JButton("Undo");
		JButton settingsButton = new JButton("Settings");
		
		//Beginning of settings Frame
		JPanel settingPanel = new JPanel();
		
		String[] styleChoice = { "Simple", "Fancy" };
		JComboBox styles = new JComboBox(styleChoice);
		styles.setToolTipText("<html>Changes the look of the mancala board</html>");
		styles.setSelectedItem(mb.getStyle());

		Integer[] stoneChoice = { 3, 4 };
		JComboBox stones = new JComboBox(stoneChoice);
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
		
		//Style TEST
		styles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(styles.getSelectedItem());
			}
		});

		//Stone TEST
		stones.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(stones.getSelectedItem());
			}
		});

		//Setting Frame
		JFrame settingFrame = new JFrame();
		settingFrame.setSize(200, 150);
		settingFrame.add(settingPanel);

		settingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settingFrame.setVisible(true);

		//Settings Close Button - applies any changed settings
		closeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int stone_count = (int)stones.getSelectedItem();
				String style_select = (String)styles.getSelectedItem();
				
				System.out.println(style_select);
				
				if(style_select.equals("Simple"))
				{
					mb.setStyle(new SimpleStyle());
				}
				else if(style_select.equals("Fancy"));
				{
					mb.setStyle(new FancyStyle());
				}
				
				if(stone_count != mb.getStoneCount())
				{
					mb.setStoneCount(stone_count);
				}
				
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

		//Undoes player's move
		undoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Undo");
				undoButton.setEnabled(false);
			}
		});

		//Opens settings
		settingsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				settingFrame.setVisible(true);
			}
		});
		
		mancalaButtons.add(endTurnButton);
		mancalaButtons.add(undoButton);
		mancalaButtons.add(settingsButton);
		
		add(mancalaButtons, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
