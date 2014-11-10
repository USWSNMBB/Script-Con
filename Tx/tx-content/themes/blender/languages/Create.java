import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Create extends Frame implements ActionListener  
{   
	TextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
	Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17;
	Choice c;
	Button b1,b2,b3;
	GridBagLayout gbl;
	GridBagConstraints gbc; 
	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	int count;
	Font f;
	
	Create()
	{
		setBackground(Color.cyan);
		f = new Font("TimesRoman",Font.BOLD,20);
		gbl=new GridBagLayout();
		gbc=new GridBagConstraints();
		setLayout(gbl);
		
		l15 = new Label("Flight No");
		l15.setFont(f);
		l1 = new Label("Travel Date");
		l1.setFont(f);
		l2 = new Label("FName");
		l2.setFont(f);
		l3 = new Label("LName");
		l3.setFont(f);
		l4 = new Label("Age");
		l4.setFont(f);
		l5 = new Label("Gender");
		l5.setFont(f);
		l6 = new Label("Address");
		l6.setFont(f);
		l7 = new Label("Phone");
		l7.setFont(f);
		l8= new Label("Class");
		l8.setFont(f);
		l9= new Label("Status");
		l9.setFont(f);
		
		t10 = new TextField(20);
		t1 = new TextField(20);
		t2 = new TextField(20);
		t3 = new TextField(20);
		t4 = new TextField(20);
		t5 = new TextField(20);
		t6 = new TextField(20);
		t7 = new TextField(20);
		t8 = new TextField(20);
		t9 = new TextField(20);
		
		c = new Choice();
		
		b1 = new Button("Submit");
		b2 = new Button("Reset");
                b3 = new Button("Generate Ticket");
	   
		l10 = new Label("");
		l11 = new Label("");
		l12 = new Label("");
		l13 = new Label("");
		l14 = new Label("");
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbl.setConstraints(l10,gbc);
		add(l10);
		
		c.add("DL - BGR : HA101");
		c.add("BGR - DL : HA102");
		c.add("DL - BY  : HA201");
		c.add("BY - DL  : HA202");
		c.add("DL - KLA : HA301");
		c.add("KLA - DL : HA302");
		c.add("DL - CHN : HA401");
		c.add("CHN - DL : HA402");
		c.add("DL - HYD : HA501");
		c.add("HYD - DL : HA502");
		c.add("DL - PUN : HA601");
		c.add("PUN - DL : HA602");
		
		gbc.gridx=2;
		gbc.gridy=0;
		gbl.setConstraints(c,gbc);
		add(c);
		
		gbc.gridx=0;
		gbc.gridy=2;
		gbl.setConstraints(l13,gbc);
		add(l13);

		gbc.gridx=2;
		gbc.gridy=2;
		gbl.setConstraints(l14,gbc);
		add(l14);
		
		gbc.gridx=0;
		gbc.gridy=4;
		gbl.setConstraints(l15,gbc);
		add(l15);

		gbc.gridx=2;
		gbc.gridy=4;
		gbl.setConstraints(t10,gbc);
		add(t10);


		gbc.gridx=0;
		gbc.gridy=6;
		gbl.setConstraints(l1,gbc);
		add(l1);
 
		gbc.gridx=2;
		gbc.gridy=6;
		gbl.setConstraints(t1,gbc);
		add(t1);

		gbc.gridx=0;
		gbc.gridy=8;
		gbl.setConstraints(l2,gbc);
		add(l2);
		
		gbc.gridx=2;
		gbc.gridy=8;
		gbl.setConstraints(t2,gbc);
		add(t2);

		gbc.gridx=0;
		gbc.gridy=10;
		gbl.setConstraints(l3,gbc);
		add(l3);

		gbc.gridx=2;
		gbc.gridy=10;
		gbl.setConstraints(t3,gbc);
		add(t3);

		gbc.gridx=0;
		gbc.gridy=12;
		gbl.setConstraints(l4,gbc);
		add(l4);

		gbc.gridx=2;
		gbc.gridy=12;
		gbl.setConstraints(t4,gbc);
		add(t4);
		
		gbc.gridx=0;
		gbc.gridy=14;
		gbl.setConstraints(l5,gbc);
		add(l5);

		gbc.gridx=2;
		gbc.gridy=14;
		gbl.setConstraints(t5,gbc);
		add(t5);

		gbc.gridx=0;
		gbc.gridy=16;
		gbl.setConstraints(l6,gbc);
		add(l6);

		gbc.gridx=2;
		gbc.gridy=16;
		gbl.setConstraints(t6,gbc);
		add(t6);

		gbc.gridx=0;
		gbc.gridy=18;
		gbl.setConstraints(l7,gbc);
		add(l7);

		gbc.gridx=2;
		gbc.gridy=18;
		gbl.setConstraints(t7,gbc);
		add(t7);

		gbc.gridx=0;
		gbc.gridy=20;
		gbl.setConstraints(l8,gbc);
		add(l8);

		gbc.gridx=2;
		gbc.gridy=20;
		gbl.setConstraints(t8,gbc);
		add(t8);

		gbc.gridx=0;
		gbc.gridy=22;
		gbl.setConstraints(l9,gbc);
		add(l9);

		gbc.gridx=2;
		gbc.gridy=22;
		gbl.setConstraints(t9,gbc);
		add(t9);

		gbc.gridx=0;
		gbc.gridy=24;
		gbl.setConstraints(l11,gbc);
		add(l11);

		gbc.gridx=2;
		gbc.gridy=24;
		gbl.setConstraints(l12,gbc);
		add(l12);

		gbc.gridx=0;
		gbc.gridy=26;
		gbl.setConstraints(b1,gbc);
		add(b1);

		gbc.gridx=2;
		gbc.gridy=26;
		gbl.setConstraints(b2,gbc);
		add(b2);

                


                
          /*      gbc.gridx=0;
		gbc.gridy=28;
		gbl.setConstraints(l16,gbc);
		add(l16);

                gbc.gridx=2;
		gbc.gridy=28;
		gbl.setConstraints(l17,gbc);
		add(l17);

                gbc.gridx=0;
		gbc.gridy=30;
		gbl.setConstraints(b3,gbc);
		add(b3);

         */
                
                b1.addActionListener(this);
                b2.addActionListener(this);
         //     b3.addActionListener(this); 
		addWindowListener(new W());
        
	}


	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			try
			{   
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:MyDataSource1");
				ps=con.prepareStatement("insert into Passengers(FlightNo,TravelDate,FName,LName,Age,Gender,Address,Phone,Class,Status)values(?,?,?,?,?,?,?,?,?,?)");
				
				String age = t4.getText();
				ps.setString(1,t10.getText());
				ps.setString(2,t1.getText());
				ps.setString(3,t2.getText());
				ps.setString(4,t3.getText());
				ps.setInt(5,Integer.parseInt(age));
				ps.setString(6,t5.getText());
				ps.setString(7,t6.getText());
				ps.setString(8,t7.getText());
				ps.setString(9,t8.getText());
				ps.setString(10,t9.getText());
				ps.executeUpdate();
				

				
                                
          if(t8.getText().equals(String.valueOf('F')))
          {   
           String str = "update Reservation set FSeats=FSeats-1 where FlightNo=? and TravelDate = ?";
           ps = con.prepareStatement(str);
           ps.setString(1,t10.getText());
           ps.setString(2,t1.getText());   
          count = ps.executeUpdate();
          }
       if(t8.getText().equals(String.valueOf('B')))
{
	String str = "update Reservation set BSeats=BSeats-1 where FlightNo=? and TravelDate = ?";
	ps = con.prepareStatement(str);
	ps.setString(1,t10.getText());
	ps.setString(2,t1.getText());   
	count = ps.executeUpdate();
}

				if(t8.getText().equals(String.valueOf('E')))
				{
					String str = "update Reservation set ESeats=ESeats-1 where FlightNo=? and TravelDate = ?";
					ps = con.prepareStatement(str);
					ps.setString(1,t10.getText());
					ps.setString(2,t1.getText());   
					count = ps.executeUpdate();
				}


				
				
				
				con.close();
                                t1.setText("");
                                t2.setText("");
                                t3.setText("");
                                t4.setText("");
                                t5.setText("");
                                t6.setText("");
                                t7.setText("");
                                t8.setText("");
                                t9.setText("");
                                t10.setText("");

                               Ticket t = new Ticket();
			      //setVisible(false);
			       t.setSize(400,500);
			       t.setVisible(true);
			       t.setTitle("Ticket Screen");




			}

			catch(SQLException e)
			{
				System.out.println("2 Error : "+e);
			}
			catch(Exception ex)
			{
				System.out.println("Error 1:"+ex);
			}

		}
	
                   if(ae.getSource()==b2)


   {
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    t6.setText("");
                    t7.setText("");
                    t8.setText("");
                    t9.setText("");
                    t10.setText("");



   }

   }





	class W extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			setVisible(false);
			dispose();
			
		}
	}
	public static void main(String args[])
	{

		Create v = new Create();
		v.setSize(400,500);
		v.setVisible(true);
		v.setTitle("Create Passenger Screen");


	}
}

“The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).Johann Wolfgang von Goethe (/'g?rt?/;[1] German: ['jo?han 'v?lfga? f?n 'gø?t?] ( listen); 28 August 1749 – 22 March 1832) was a German writer and statesman. His body of work includes epic and lyric poetry written in a variety of metres and styles; prose and verse dramas; memoirs; an autobiography; literary and aesthetic criticism; treatises on botany, anatomy, and colour; and four novels. In addition, numerous literary and scientific fragments, more than 10,000 letters, and nearly 3,000 drawings by him are extant.

A literary celebrity by the age of 25, Goethe was ennobled by the Duke of Saxe-Weimar, Carl August in 1782 after first taking up residence there in November 1775 following the success of his first novel, The Sorrows of Young Werther. He was an early participant in the Sturm und Drang literary movement. During his first ten years in Weimar, Goethe served as a member of the Duke's privy council, sat on the war and highway commissions, oversaw the reopening of silver mines in nearby Ilmenau, and implemented a series of administrative reforms at the University of Jena. He also contributed to the planning of Weimar's botanical park and the rebuilding of its Ducal Palace, which in 1998 were together designated a UNESCO World Heritage Site.[2]

After returning from a tour of Italy in 1788, his first major scientific work, the Metamorphosis of Plants, was published. In 1791 he was made managing director of the theatre at Weimar, and in 1794 he began a friendship with the dramatist, historian, and philosopher Friedrich Schiller, whose plays he premiered until Schiller's death in 1805. During this period Goethe published his second novel, Wilhelm Meister's Apprenticeship, the verse epic Hermann and Dorothea, and, in 1808, the first part of his most celebrated drama, Faust. His conversations and various common undertakings throughout the 1790s with Schiller, Johann Gottlieb Fichte, Johann Gottfried Herder, Alexander von Humboldt, Wilhelm von Humboldt, and August and Friedrich Schlegel have, in later years, been collectively termed Weimar Classicism.

Arthur Schopenhauer cited Wilhelm Meister's Apprenticeship as one of the four greatest novels ever written[citation needed] and Ralph Waldo Emerson selected Goethe as one of six "representative men" in his work of the same name, along with Plato, Napoleon, and William Shakespeare. Goethe's comments and observations form the basis of several biographical works, most notably Johann Peter Eckermann's Conversations with Goethe. There are frequent references to Goethe's writings throughout the works of G. W. F. Hegel, Arthur Schopenhauer, Friedrich Nietzsche, Hermann Hesse, Thomas Mann, Sigmund Freud, and Carl Jung. Goethe's poems were set to music throughout the eighteenth and nineteenth centuries by a number of composers, including Wolfgang Amadeus Mozart, Ludwig van Beethoven, Franz Schubert, Robert Schumann, Johannes Brahms, Charles Gounod, Richard Wagner, Hugo Wolf, and Gustav Mahler.