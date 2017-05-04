import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;

/**
 * MancalaFrame
 * @author Team Ocean
 *
 */
public class MancalaFrame extends JFrame
{
	private static final int FRAME_WIDTH = 1400;
	private static final int FRAME_HEIGHT = 800;
	
	private boolean isClickable; //Determines if the player's clicks will be registered
	
	/**
	 * Constructs and runs the Mancala Game
	 */
	public MancalaFrame()
	{
		this.setLayout(new BorderLayout());
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		MancalaBoard mb = new MancalaBoard(0);
		MancalaPanel mp = new MancalaPanel(mb);

		//North Text Panel
		JLabel mancalaTextLabel = new JLabel();
		mancalaTextLabel.setText("<html>Player 1 Score: " + mb.getPlayer1Score() + " | " + "Player 2 Score: " + mb.getPlayer2Score()
			+ "<br>Current Player: " + mb.getCurrentPlayer() +  " | " + "Undos Left: "  + mb.getUndoCount() + mb.getStringHasExtraTurn() + "</html>");
		JPanel mancalaTextPanel = new JPanel();
		mancalaTextLabel.setLayout(new BorderLayout());
		mancalaTextPanel.add(mancalaTextLabel, BorderLayout.CENTER);
		
		//South Button Panel
		JPanel mancalaButtons = new JPanel();
		JButton endTurnButton = new JButton("End Turn");
		JButton undoButton = new JButton("Undo");
		JButton settingsButton = new JButton("Settings");
		
		//Adds tool tips for buttons
		endTurnButton.setToolTipText("<html>Ends the current player's turn.</html>");
		undoButton.setToolTipText("<html>Undo current move.</html>");
		settingsButton.setToolTipText("<html>Opens settings menu.</html>");
		
		//Beginning of settings Frame
		JPanel settingPanel = new JPanel();
		
		//Creates Combo Boxes for settings
		String[] styleChoice = { "Simple", "Fancy" };
		JComboBox styles = new JComboBox(styleChoice);
		styles.setToolTipText("<html>Changes the look of the mancala board: <br>-Simple: Simply Square<br> -Fancy: Circles</html>");
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
		settingFrame.setLocationRelativeTo(this);
		settingFrame.setResizable(false);
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
					isClickable = true;
					mancalaTextLabel.setText("<html>Player 1 Score: " + mb.getPlayer1Score() + " | " + "Player 2 Score: " + mb.getPlayer2Score()
						+ "<br>Current Player: " + mb.getCurrentPlayer() +  " | " + "Undos Left: "  + mb.getUndoCount() + mb.getStringHasExtraTurn() + "</html>");
					undoButton.setEnabled(false);
					endTurnButton.setEnabled(false);
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

		//Changes player and updates score
		endTurnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				mb.mancalaEnd();
				mancalaTextLabel.setText("<html>Player 1 Score: " + mb.getPlayer1Score() + " | " + "Player 2 Score: " + mb.getPlayer2Score()
					+ "<br>Current Player: " + mb.getCurrentPlayer() +  " | " + "Undos Left: "  + mb.getUndoCount() + mb.getStringHasExtraTurn() + "</html>");
				isClickable = true;
				undoButton.setEnabled(false);
				endTurnButton.setEnabled(false);
			}
		});
		
		//Adds ChangeListener that re-enables the undoButton after a move is done
		mb.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				if(mb.getUndoCount() > 0)
					undoButton.setEnabled(true);
				endTurnButton.setEnabled(true);
			}
		});
		
		//The main board view listener
		mp.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event)
			{
				Point point = event.getPoint();
				
				if(mp.getStyle() != null)
				{
					ArrayList<? extends StylePit> pitViews = mp.getStyle().getStylePits();
					if(mb.isP1() && isClickable)
					{
						for(int i = 0; i < 6; i++)
						{
							StylePit current = pitViews.get(i);
							if(current.contains(point) && !mb.isPitEmpty(current.getPitIndex()))
							{
								mb.mancalaMove(current.getPitIndex());
								if(mb.getHasExtraTurn())
									isClickable = true;
								else
									isClickable = false;
								repaint();
								break;
							}
						}
					}
					else if(!mb.isP1() && isClickable)
					{
						for(int i = 7; i < 13; i++)
						{
							StylePit current = pitViews.get(i);
							if(current.contains(point) && !mb.isPitEmpty(current.getPitIndex()))
							{
								mb.mancalaMove(current.getPitIndex());
								if(mb.getHasExtraTurn())
									isClickable = true;
								else
									isClickable = false;
								repaint();
								break;
							}
						}
					}
					mancalaTextLabel.setText("<html>Player 1 Score: " + mb.getPlayer1Score() + " | " + "Player 2 Score: " + mb.getPlayer2Score()
						+ "<br>Current Player: " + mb.getCurrentPlayer() +  " | " + "Undos Left: "  + mb.getUndoCount() + mb.getStringHasExtraTurn() + "</html>");
				}
			}
		});

		//Undoes player's move
		undoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				mb.mancalaUndo();
				repaint();
				isClickable = true;
				mancalaTextLabel.setText("<html>Player 1 Score: " + mb.getPlayer1Score() + " | " + "Player 2 Score: " + mb.getPlayer2Score()
					+ "<br>Current Player: " + mb.getCurrentPlayer() +  " | " + "Undos Left: "  + mb.getUndoCount() + "</html>");
				undoButton.setEnabled(false); //Player cannot make multiple undos in a row
				endTurnButton.setEnabled(false); //Player must make a turn
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
		
		
		add(mancalaTextPanel, BorderLayout.NORTH);
		add(mp, BorderLayout.CENTER);
		add(mancalaButtons, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//Initial game startup
		settingFrame.setVisible(true);
		cancelButton.setEnabled(false); 
		undoButton.setEnabled(false);
		endTurnButton.setEnabled(false);
		isClickable = true;
	}
}
