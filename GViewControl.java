/**
 * $Id: GViewControl.java,v 1.3 2013/04/24 00:07:22 lxl3375 Exp $
 * $Log: GViewControl.java,v $
 * Revision 1.3  2013/04/24 00:07:22  lxl3375
 * 3rd revision of lab6
 *
 * Revision 1.2  2013/04/23 02:27:28  lxl3375
 * 2nd Revision of Lab6
 *
 * Revision 1.1  2013/04/21 22:29:22  lxl3375
 * First revision of lab6
 *
 */

import java.util.ArrayList;
import java.util.Observable;
import java.awt.*;
import java.util.Observer;
import javax.swing.*;
import java.awt.event.*;

/**
 * Class definition for the graphical view and controller.
 * 
 * @author Lai-Chung Lau, lxl3375@g.rit.edu
 *
 */
public class GViewControl extends JFrame implements Observer
{
	private ConcentrationModel gameModel;
	private ArrayList<Color> colors = new ArrayList<Color>();
	private ArrayList<CardButton> cardButtons;
	private JLabel lblmove;
	
	/**
	 * Construct a GViewControl object.
	 * @param model, The model for the view and controller.
	 */
	public GViewControl(ConcentrationModel model)
	{
		//initialize the states
		gameModel = model;
		colors = setColors(colors);
		cardButtons = new ArrayList<CardButton>();
		
		//panel contains labels
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel moveLabel = new JLabel("Moves: 0 Select the first card.");
		lblmove = moveLabel;
		p1.add(moveLabel);
		
		//panel contains card buttons
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(gameModel.BOARD_SIZE,gameModel.BOARD_SIZE,2,2));
		ButtonListener buttonListener = new ButtonListener();
		for (int i = 0; i < gameModel.NUM_CARDS; i++)
		{
			CardButton button = new CardButton(i);
			button.addActionListener(buttonListener);
			//initailize all the buttons
			button.setText("");
			button.setBackground(Color.white);
			cardButtons.add(button);
			p2.add(button);
		}
		
		//Panel contains buttons
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton btnReset = new JButton("Reset");
		// Register anonymous listeners
		btnReset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				gameModel.reset();
				lblmove.setText("Moves: 0 Select the first card.");
			}
		});
		JButton btnCheat = new JButton("Cheat");
		// Register anonymous listeners
		btnCheat.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//create new button list so that update() won't affect the display of the cheat window
				ArrayList<CardButton> cheatButtons = new ArrayList<CardButton>();
				ArrayList<CardFace> cheatLst = gameModel.cheat();
				for (int i = 0; i < cheatLst.size(); i++)
				{
					CardFace face = cheatLst.get(i);
					CardButton button = new CardButton(i);
					button.setText("" + face.getNumber());
					button.setBackground(colors.get(face.getNumber()));
					cheatButtons.add(button);
				}
				
				CheatFrame cheatFrame = new CheatFrame(cheatButtons, gameModel.BOARD_SIZE);
			}
			
		});
		JButton btnUndo = new JButton("Undo");
		// Register anonymous listeners
		btnUndo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				gameModel.undo();
			}
		});
		p3.add(btnReset);
		p3.add(btnCheat);
		p3.add(btnUndo);
		
		setTitle("Concentration Game");
		setSize(400,400);
		Container container = getContentPane();
		container.add(p1,"North");
		container.add(p2,"Center");
		container.add(p3,"South");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * set the color, so that different number of the button can represent different colors
	 * @param colorLst, an empty ArrayList<Color>
	 * @return an ArrayList contain different colors
	 */
	private ArrayList<Color> setColors(ArrayList<Color> colorLst)
	{
		colorLst.add(Color.gray);
		colorLst.add(Color.blue);
		colorLst.add(Color.green);
		colorLst.add(Color.cyan);
		colorLst.add(Color.red);
		colorLst.add(Color.magenta);
		colorLst.add(Color.yellow);
		colorLst.add(Color.lightGray);
		
		return colorLst;
	}
	
	/**
	 * Update the window when the model indicates an update is required
	 * Update changes the color and string content of a CardButton based on the CardFaces in the model,
	 * and it changes the text in the label based on the model state.
	 * @param t, An Observable -- not used
	 * @param o, An Object -- not used
	 */
	public void update(Observable t, Object o) 
	{
		ArrayList<CardFace> faceLst = gameModel.getCards();
		for (int i = 0; i < cardButtons.size(); i++)
		{
			CardButton button = cardButtons.get(i);
			CardFace face = faceLst.get(i);
			if (face.isFaceUp())
			{
				button.setText("" + face.getNumber());
				button.setBackground(colors.get(face.getNumber()));
			}
			else //if card faces down
			{
				button.setText("");
				button.setBackground(Color.white);
			}
		}
		
		//display status
		String s = "Moves: " + gameModel.getMoveCount();
		int n = gameModel.howManyCardsUp();
		switch(n)
		{
			case 0: s = s + " Select the first card."; break;
			case 1: s = s + " Select the second card."; break;
			case 2: s = s + " No Match: Undo or select a card."; break;
		}
		lblmove.setText(s);
	}
	
	// ButtonListener is an inner class
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) 
		{
			//get source button
			CardButton button = (CardButton) event.getSource();
			gameModel.selectCard(button.getPos());
		}
		
	}

	
    /**
     * The main method used to play a game.
     *
     * @param args Command line arguments -- unused
     */
	public static void main(String args[])
	{
		ConcentrationModel myModel = new ConcentrationModel();
		GViewControl game = new GViewControl(myModel);
		
		myModel.addObserver(game);
	}
}
