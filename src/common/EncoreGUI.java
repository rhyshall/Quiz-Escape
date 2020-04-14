package common;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import map.MapGUI;
import map.Reload;

public class EncoreGUI extends JFrame implements ActionListener
{
  MapGUI mapGUI;
  String statusText;
	
  public EncoreGUI(MapGUI mapGUI,
		           boolean hasWon)
  {
	super();
	
	this.mapGUI = mapGUI;
	
	if (hasWon == true)
	{
	  statusText = Constants.WIN_TEXT;
	}
	
	else
	{
	  statusText = Constants.LOSE_TEXT;
	}
  }
  
  public void run()
  {
	setProperties();
	setContent();
	
	setVisible(true);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) 
  {
	JButton buttonChose;

	buttonChose = (JButton)e.getSource();
	
	if (buttonChose.getText().equals("Play Again"))
	{
	  Reload.restartMapGUI(mapGUI);
	  
	  dispose();
	}
	
	else
	{
	  mapGUI.dispose();
	  dispose();
	}
  }
  
  private void setProperties()
  {
	//set basic window frame properties
	setSize(Constants.ENCORE_SCREEN_WIDTH,
	    	Constants.ENCORE_SCREEN_HEIGHT);

	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	setLocationRelativeTo(null);
	
	getContentPane().setBackground(Colours.LIGHT_GREY_2);
	getContentPane().setLayout(new BoxLayout(getContentPane(), 
                                             BoxLayout.Y_AXIS));
  }
  
  private void setContent()
  {
	JPanel textPanel;
	JPanel againButtonPanel;	
	JPanel exitButtonPanel;
	JLabel statusLabel;
	int buttonWidth = Constants.ENCORE_BUTTON_WIDTH;
	int buttonHeight = Constants.ENCORE_BUTTON_HEIGHT;
	JButton againButton = new JButton("Play Again");
	JButton exitButton = new JButton("Exit");
	
	statusLabel = new JLabel(statusText);
	statusLabel.setFont(new Font("TimesRoman", 
					             Font.PLAIN, 
					             20));
	
	textPanel = new JPanel();
	textPanel.setBackground(Colours.LIGHT_GREY_2);
	textPanel.add(statusLabel);
	
	textPanel.setBorder(BorderFactory.createEmptyBorder(32, 
											            0, 
											            0, 
											            0));
	
	againButton.setPreferredSize(new Dimension(buttonWidth, 
                                               buttonHeight));
    exitButton.setPreferredSize(new Dimension(buttonWidth, 
                                              buttonHeight));

	againButton.setBackground(Colours.GREEN_1);
	exitButton.setBackground(Colours.GREEN_1);   
	againButton.setForeground(Colours.WHITE_1);
	exitButton.setForeground(Colours.WHITE_1);
	
	againButton.setFont(new Font("Calabri", 
                                 Font.PLAIN, 
                                 17));
	
	exitButton.setFont(new Font("Calabri", 
                                Font.PLAIN, 
                                17));
	
	againButton.addActionListener(this);
	exitButton.addActionListener(this);
	
	againButtonPanel = new JPanel();
	againButtonPanel.setBackground(Colours.LIGHT_GREY_2);
	againButtonPanel.add(againButton);
	
	exitButtonPanel = new JPanel();
	exitButtonPanel.setBackground(Colours.LIGHT_GREY_2);
	exitButtonPanel.add(exitButton);
	
	againButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 
												               0, 
												               0, 
												               0));
														
	getContentPane().add(textPanel);
	setDivider();
	getContentPane().add(againButtonPanel);
	getContentPane().add(exitButtonPanel);
  }
  
  private void setDivider()
  {
	JTextField line;
	JPanel linePanel;
	
	line = new JTextField();
	line.setPreferredSize(new Dimension(Constants.MAP_WIDTH,
			                            1));
	line.setBackground(Colours.BLACK_1);
	
	linePanel = new JPanel();
	linePanel.setPreferredSize(new Dimension(Constants.MAP_WIDTH, 
                                             0));
	linePanel.setBackground(Colours.LIGHT_GREY_2);
	linePanel.add(line);
	getContentPane().add(linePanel);
  }
}
