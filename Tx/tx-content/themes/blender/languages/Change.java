import java.awt.*;
import java.awt.event.*;

public class Change extends Frame 
{
	Label l1,l2;
	TextField t1,t2;
	GridLayout g;
	Button b1,b2;
	Change()
	{
	g = new GridLayout(3,2,20,40);
	setLayout(g);
    l1 = new Label("Username",Label.CENTER);
	l2 = new Label("Password",Label.CENTER);
	t1 = new TextField(20);
	t2 = new TextField(20);
    t2.setEchoChar('*'); 
	b1 = new Button("Submit");
	b2 = new Button("Close");
	b2.addActionListener(new W());
	addWindowListener(new L());
	add(l1);
	add(t1);
	add(l2);
	add(t2);
	add(b1);
	add(b2);
	
    }
	class W implements ActionListener
	{

		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource()==b2)
			{
				dispose();
				
			}
			
		}
	}
	class L extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			setVisible(false);
			//dispose();
			System.exit(0);
			
		}
	}


	public Insets getInsets()
	{
		return new Insets(40,40,40,40);
	}
	public static void main(String args[])
	 {
	 Change m = new Change();
	 m.setTitle("Change Login Details");
	 m.setSize(400,300);
	 m.setVisible(true);
     }
     }


“The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).