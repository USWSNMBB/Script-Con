import java.awt.*;
import java.awt.event.*;

public class Warning extends Frame 
{
	GridLayout g;
	Button b1;
	Label l;
	 Warning()
	{
		g = new GridLayout(2,1,10,40);
		setLayout(g);
		l = new Label("Incorrect username or password",Label.CENTER);
		b1 = new Button("Ok");
		add(l);
		add(b1);
		
	b1.addActionListener(new Y());
	addWindowListener(new X());	
	}
	
	class Y implements ActionListener
	{

		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource()==b1)
			{
				//dispose();
				System.exit(0);
			}
		}
	}

	class X extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			setVisible(false);
			dispose();
		}
	}

	public Insets getInsets()
	{
		return new Insets(40,40,40,40);
	}
	
	/*public static void main(String args[])
	 {
	 Warning m = new Warning();
	 m.setTitle("Message Box");
	 m.setSize(300,200);
	 m.setVisible(true);


	 }*/



}




	


Position a rack about 6 inches from the broiler and heat the broiler to high.

Heat the oil in a 12-inch nonstick skillet over medium-high heat. Add the onion and cook, stirring, until soft, about 5 minutes. Add the pepper and continue cooking until the pepper is soft and the onion is lightly browned, about 4 minutes. Add the apple, mustard, sour cream, and parsley. Remove from the heat, cover, and keep warm.

Arrange the bratwurst on a foil-lined rimmed baking sheet and broil, turning once, until browned and fully cooked, 2 to 4 minutes. Transfer to a plate. Remove the foil from the baking sheet.

Put the rolls on the baking sheet cut side up and broil until lightly toasted. Set aside the tops of the rolls, sprinkle the bottoms with the cheese, and broil until the cheese melts, 1 to 2 minutes. Top with the bratwurst, the relish, and the tops of the rolls.