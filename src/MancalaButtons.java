import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

/**
 * The settings panel
 * @author Team Ocean - Shawn S.
 *
 */
public class MancalaButtons extends JPanel
{
	/**
	 * Creates the settings panel with handles the end turn, undo turn, and settings
	 */
	public MancalaButtons()
	{
		JButton endTurnButton = new JButton("End Turn");
		JButton undoButton = new JButton("Undo");
		JButton settingsButton = new JButton("Settings");

		endTurnButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("End Turn");
			}
		});

		undoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Undo");
			}
		});

		settingsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String[] styleChoice = { "Default", "Simple", "Fancy" };
				JComboBox styles = new JComboBox(styleChoice);

				String[] stoneChoice = { "# of Stones", "3", "4" };
				JComboBox stones = new JComboBox(stoneChoice);

				JLabel styleOptions = new JLabel("Style Option: ");
				JLabel stoneOptions = new JLabel("Stone Options: ");

				JButton closeButton = new JButton("OK");

				JPanel settingPanel = new JPanel();

				settingPanel.setName("Settings");
				settingPanel.setLayout(new GridLayout(6, 3));
				settingPanel.add(styleOptions);
				settingPanel.add(styles);
				settingPanel.add(stoneOptions);
				settingPanel.add(stones);
				settingPanel.add(closeButton);

				styles.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						System.out.println(styles.getSelectedItem());
					}
				});

				stones.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						System.out.println(stones.getSelectedItem());
					}
				});

				JFrame settingFrame = new JFrame();

				settingFrame.add(settingPanel);

				settingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				settingFrame.pack();
				settingFrame.setVisible(true);

				closeButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						settingFrame.dispose();
					}
				});
			}
		});
		this.add(endTurnButton);
		this.add(undoButton);
		this.add(settingsButton);
	}
}
