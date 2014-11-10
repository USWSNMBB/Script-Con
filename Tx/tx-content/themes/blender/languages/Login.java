import java.awt.*;
import java.awt.event.*;
public class Login extends Frame implements ActionListener
{
	String username = "iamavnish";
	String password = "sachin";
	TextField t1,t2;
	Label l1,l2,l3,l4,l5,l6;
	Button b2,b3,b4;
	GridBagLayout gbl;
	GridBagConstraints gbc; 
	Font f1,f2;
	public Login()
	{
		//setTitle("Login Screen");
		//g = new GridLayout(4,2,0,60);
		//setLayout(g);
		setBackground(Color.cyan);
		f1 = new Font("TimesRoman",Font.BOLD,20);
        f2 = new Font("TimesRoman",Font.BOLD,15);
		gbl=new GridBagLayout();
		gbc=new GridBagConstraints();
		setLayout(gbl);
		l1 = new Label("Username",Label.CENTER);
        l1.setFont(f1);
		l2 = new Label("Password",Label.CENTER);
        l2.setFont(f1);
		l3 = new Label("");
		l4 = new Label("");
        l5 = new Label("");
        l6 = new Label("");
		t1 = new TextField(15);
		t2 = new TextField(15);
		t2.setEchoChar('*');
		//b1 = new Button("Change Login Details");
		b2 = new Button("Reset");
        b2.setFont(f2);
		b3 = new Button("Submit");
        b3.setFont(f2);
		b4 = new Button("Close");
		b4.setFont(f2);
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbl.setConstraints(l1,gbc);
		add(l1);

		gbc.gridx=2;
		gbc.gridy=0;
		gbl.setConstraints(t1,gbc);
		add(t1);
		
		gbc.gridx=0;
		gbc.gridy=2;
		gbl.setConstraints(l2,gbc);
		add(l2);

		gbc.gridx=2;
		gbc.gridy=2;
		gbl.setConstraints(t2,gbc);
		add(t2);
		
		
		gbc.gridx=0;
		gbc.gridy=4;
		gbl.setConstraints(l3,gbc);
		add(l3);

		gbc.gridx=2;
		gbc.gridy=4;
		gbl.setConstraints(l4,gbc);
		add(l4);



		gbc.gridx=0;
		gbc.gridy=6;
		gbl.setConstraints(b2,gbc);
		add(b2);



		gbc.gridx=2;
		gbc.gridy=6;
		gbl.setConstraints(b3,gbc);
		add(b3);

		
		
		gbc.gridx=0;
		gbc.gridy=8;
		gbl.setConstraints(l4,gbc);
		add(l4);

		gbc.gridx=2;
		gbc.gridy=8;
		gbl.setConstraints(l5,gbc);
		add(l5);

		gbc.gridx=0;
		gbc.gridy=10;
		gbl.setConstraints(b4,gbc);
		add(b4);


		//add(l1);
		//add(t1);
		//add(l2);
		//add(t2);
		//add(b1);
		//add(b2);
		//add(b3);
		//add(b4);
		//b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	}

	/*public Insets getInsets()
	{
		return new Insets(40,40,40,40);
	}*/
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b2)
		{
			t1.setText("");
			t2.setText("");
		}
		if(ae.getSource()==b4)
		{
			System.exit(0);
		}
		if(ae.getSource()==b3)
		{
			if((t1.getText().equals(username))&&(t2.getText().equals(password)))
			{
				MainMenu m = new MainMenu();
				setVisible(false);
				m.setSize(400,400);
				m.setVisible(true);
				m.setTitle("Main Menu");
			}
			else
			{
				//Warning w = new Warning();
				//w.setSize(300,200);
				//w.setVisible(true);
				//w.setTitle("Message Box");
				MessageBox mb = new MessageBox(this);
				mb.setLocation(200,200);
				mb.setVisible(true);
            }
		}
		/*if(ae.getSource() == b1)
		{
			Change c = new Change();
			c.setSize(400,400);
			c.setVisible(true);
			c.setTitle("Screen for Changing Login Details");
		}*/
	}
}
What did the Civil War’s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians — including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many – to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers’ deaths in the Civil War – approximately 750,000 – was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. “The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).Sun Nov  9 21:00:27 PST 2014
