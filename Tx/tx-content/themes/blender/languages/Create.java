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

�The poem,� said Henri Meschonnic, �is the place where one forgets, which is another way of remembering�; so recurrence in COTP: the engram, �memories� of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but� COTP teaches; as we encounter it (a declaration about time, in time) later, too: �memory and the present are not in opposition� (43) so I�m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or �When the anarchic excess has already been anticipated, what next?� (9)�is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: �Also you have aspired to a sincerity of skepticism�; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first �conditional� problem�but what I like is how LR isn�t ever mired in the problem of what to say or how to proceed� the only crisis�again, I think COTP teaches this�is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again� Later we get: �the form never extinguishes its own irony� (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: �an unknowing expands within your pronoun but it feels convivial� (12); I like this party being thrown for two pronouns that can�t really meet and therefore that�s their intimate rendezvous�like LR is saying (to herself, to a future I, to an us) just deal� and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: �you carried the great discovery of poetry as freedom, not form� (75).

-Or instead try this these love is �your muscles made into extremely fine and silky tools� or love is �what light did, how the trees freed it� or love is �an inversion in perception� or love is �the smell of rain, bread and exhaust mixed with tiredness.�

-�And if you yourself are incompatible with your view of the world?� (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR�s kinda sorta How to Write).Johann Wolfgang von Goethe (/'g?rt?/;[1] German: ['jo?han 'v?lfga? f?n 'g�?t?] ( listen); 28 August 1749 � 22 March 1832) was a German writer and statesman. His body of work includes epic and lyric poetry written in a variety of metres and styles; prose and verse dramas; memoirs; an autobiography; literary and aesthetic criticism; treatises on botany, anatomy, and colour; and four novels. In addition, numerous literary and scientific fragments, more than 10,000 letters, and nearly 3,000 drawings by him are extant.

A literary celebrity by the age of 25, Goethe was ennobled by the Duke of Saxe-Weimar, Carl August in 1782 after first taking up residence there in November 1775 following the success of his first novel, The Sorrows of Young Werther. He was an early participant in the Sturm und Drang literary movement. During his first ten years in Weimar, Goethe served as a member of the Duke's privy council, sat on the war and highway commissions, oversaw the reopening of silver mines in nearby Ilmenau, and implemented a series of administrative reforms at the University of Jena. He also contributed to the planning of Weimar's botanical park and the rebuilding of its Ducal Palace, which in 1998 were together designated a UNESCO World Heritage Site.[2]

After returning from a tour of Italy in 1788, his first major scientific work, the Metamorphosis of Plants, was published. In 1791 he was made managing director of the theatre at Weimar, and in 1794 he began a friendship with the dramatist, historian, and philosopher Friedrich Schiller, whose plays he premiered until Schiller's death in 1805. During this period Goethe published his second novel, Wilhelm Meister's Apprenticeship, the verse epic Hermann and Dorothea, and, in 1808, the first part of his most celebrated drama, Faust. His conversations and various common undertakings throughout the 1790s with Schiller, Johann Gottlieb Fichte, Johann Gottfried Herder, Alexander von Humboldt, Wilhelm von Humboldt, and August and Friedrich Schlegel have, in later years, been collectively termed Weimar Classicism.

Arthur Schopenhauer cited Wilhelm Meister's Apprenticeship as one of the four greatest novels ever written[citation needed] and Ralph Waldo Emerson selected Goethe as one of six "representative men" in his work of the same name, along with Plato, Napoleon, and William Shakespeare. Goethe's comments and observations form the basis of several biographical works, most notably Johann Peter Eckermann's Conversations with Goethe. There are frequent references to Goethe's writings throughout the works of G. W. F. Hegel, Arthur Schopenhauer, Friedrich Nietzsche, Hermann Hesse, Thomas Mann, Sigmund Freud, and Carl Jung. Goethe's poems were set to music throughout the eighteenth and nineteenth centuries by a number of composers, including Wolfgang Amadeus Mozart, Ludwig van Beethoven, Franz Schubert, Robert Schumann, Johannes Brahms, Charles Gounod, Richard Wagner, Hugo Wolf, and Gustav Mahler. I. Identification

1. The Issue
Tobacco is well known for its negative effects on health. Yet, the environmental impact of tobacco production is not always recognized. There appears to be a direct link between tobacco production and deforestation. For countries that devote much of their land to the production of tobacco, this could pose a problem. Malawi is one such example. Tobacco is Malawi's largest industry and currently accounts for nearly 80% of the nation's export earnings. Strong government support for the tobacco industry, including subsidies and tax breaks, has led to tobacco's domination of Malawi's export market. The tobacco industry was intended to increase economic growth and promote development in Malawi. Unfortunately, things have not gone accordingly to plan. The price of tobacco on international markets continues to fall, with no sign of abating. Malawi remains economically stunted, the average live expectancy is gradually declining, and malnutrition, especially in children, continues to be a serious problem. Further, the environmental resources of this nation are at risk. Deforestation, in particular, is a major problem in Malawi. The threat of deforestation, some argue (Tobin, 1998), can be largely attributed to the tobacco industry. Malawi is now at a pivotal point where it must reevaluate its economic strategy and make strides to protect its environment. Otherwise, Malawi will most likely continue in its downward spiral.

2. Description

Figure 1. Map of Malawi

The future of Malawi's forests are in jeopardy. The diminishing forests in this small African nation will not be able to withstand current pressure from the tobacco industry and population growth for much longer. If forest loss continues, there will be many negative environmental and social costs. The productive capacity of the land, in addition to the well being of the people, depends on the protection of Malawi's forests. It is ironic that tobacco, a crop that was once seen as Malawi's key for development, may in fact lead this African nation to its demise.

This case study will look at many of the economic and political conditions which have lead this nation to its dependence on tobacco. It will further explore some of the economic, environmental and development implications tobacco production has had on Malawi. Malawi's vulnerability as a tobacco exporter, both environmentally and economically, will also be discussed. And of course, the link between tobacco production and deforestation will be examined in this case study.

Malawi, often referred to as 'The Warm Heart of Africa,' lies in the Eastern portion of Central Africa along the Great Rift Valley. Unfortunately, this nation, home to nearly 10 million people, has failed to secure its position on the path toward development and faces a very uncertain future. Malawi is confronted with many problems. It is considered one of the world's poorest nations, its economy remains weak, its external debt is 2.3 billion, AIDS is ravaging the population, and the average life expectancy is a mere 36.6 years. As if this were not enough, Malawi's environment, the backbone of the economy and the social structure, is also in danger. Deforestation is considered the biggest environmental problem facing Malawi (CIA web page). One of the causes of deforestation in Malawi is the production of tobacco leafs for export.

Figure 2. View from Zomba Plateau, Malawi

Malawi's economy is heavily entrenched in the agriculture sector - especially tobacco. Tobacco alone accounts for nearly 80% of Malawi's export earnings (Hobbs, 1998). Other agriculture products, namely tea and sugar, constitute nearly 20% of the nation's exports. This issue of exports is critical to a country in need of foreign currency to pay its external debt. Malawi's international debt was was $2.27 billion in 1997 and is expected to rise to $2.42 by the end of 1999 (Corssboars Monitor, 1997).

Forty-five percent of the country's GDP comes from agriculture. This figure would be considerably higher if it included the extensive subsistence agricultural production in Malawi. Roughly 90 percent of the population lives in rural areas, most of whom are involved in subsistence agriculture. The tobacco industry dominates the agricultural sector and is the nation's second largest employer after the government. Tea and sugar are also important crops for Malawi.

Tobacco and Deforestation

Figure 3. Tobacco bales

The correlation between tobacco and deforestation has received little attention to date. This is largely because few countries produce enough tobacco for the environmental ramifications to pose a significant threat. For all countries which export tobacco - except for two - one of which is Malawi (the other is Zimbabwe), export earnings for tobacco constitute less than 2.2% of their foreign exchange earnings. As noted earlier, tobacco constitutes nearly 80% of Malawi's export earnings. While some countries may be able to ignore the potential threat of the tobacco industry on deforestation, Malawi can not. Over 4% of Malawi's land is dedicated to producing tobacco, a much larger percentage than other tobacco producing nations (Panos, 1999). For Malawi, a small nation with limited natural resources, the forests represent one of the few assets the nation has.

The primary way in which tobacco contributes to deforestation is through the curing of the tobacco leaf, a process that requires the burning of fuel wood. It is difficult to assess exactly how much wood is need for curing tobacco, but some argue that it takes as much as three hectares of trees to cure one hectare of tobacco in some countries (Madeley, 1993). In Malawi, it has been estimated that tobacco growers account for as much as 25% of household wood consumption (Tobin, 1998). By the early 1990s, the Malawi government acknowledged that it had one of the highest rates of deforestation in the world (Tobin, 1998). However, the government has not identified tobacco as a primary cause of deforestation. Tobacco production can also aggravate deforestation through the need for wood to construct curing huts, structures that need to be replaced every one to two years. And of course the need to clear land for cultivation also contributes to forest degradation.

Yet, there is no consensus regarding the impact of the tobacco industry on deforestation on Malawi. Some assert that tobacco production in Malawi has a direct negative impact on the forest resources in this African country (Madeley,1993). In contrast, there are those who argue that there is no basis for such claims. It is worth noting that those who fall into this later category are the ones who stand to gain the most from continued tobacco production in Malawi. In fact, the Tobacco Association of Malawi states not only that tobacco production poses no threat to forest resources, but they further state that their reforestation programs are actually resulting in a net increase on forest resources.  I. Identification

1. The Issue
Tobacco is well known for its negative effects on health. Yet, the environmental impact of tobacco production is not always recognized. There appears to be a direct link between tobacco production and deforestation. For countries that devote much of their land to the production of tobacco, this could pose a problem. Malawi is one such example. Tobacco is Malawi's largest industry and currently accounts for nearly 80% of the nation's export earnings. Strong government support for the tobacco industry, including subsidies and tax breaks, has led to tobacco's domination of Malawi's export market. The tobacco industry was intended to increase economic growth and promote development in Malawi. Unfortunately, things have not gone accordingly to plan. The price of tobacco on international markets continues to fall, with no sign of abating. Malawi remains economically stunted, the average live expectancy is gradually declining, and malnutrition, especially in children, continues to be a serious problem. Further, the environmental resources of this nation are at risk. Deforestation, in particular, is a major problem in Malawi. The threat of deforestation, some argue (Tobin, 1998), can be largely attributed to the tobacco industry. Malawi is now at a pivotal point where it must reevaluate its economic strategy and make strides to protect its environment. Otherwise, Malawi will most likely continue in its downward spiral.

2. Description

Figure 1. Map of Malawi

The future of Malawi's forests are in jeopardy. The diminishing forests in this small African nation will not be able to withstand current pressure from the tobacco industry and population growth for much longer. If forest loss continues, there will be many negative environmental and social costs. The productive capacity of the land, in addition to the well being of the people, depends on the protection of Malawi's forests. It is ironic that tobacco, a crop that was once seen as Malawi's key for development, may in fact lead this African nation to its demise.

This case study will look at many of the economic and political conditions which have lead this nation to its dependence on tobacco. It will further explore some of the economic, environmental and development implications tobacco production has had on Malawi. Malawi's vulnerability as a tobacco exporter, both environmentally and economically, will also be discussed. And of course, the link between tobacco production and deforestation will be examined in this case study.

Malawi, often referred to as 'The Warm Heart of Africa,' lies in the Eastern portion of Central Africa along the Great Rift Valley. Unfortunately, this nation, home to nearly 10 million people, has failed to secure its position on the path toward development and faces a very uncertain future. Malawi is confronted with many problems. It is considered one of the world's poorest nations, its economy remains weak, its external debt is 2.3 billion, AIDS is ravaging the population, and the average life expectancy is a mere 36.6 years. As if this were not enough, Malawi's environment, the backbone of the economy and the social structure, is also in danger. Deforestation is considered the biggest environmental problem facing Malawi (CIA web page). One of the causes of deforestation in Malawi is the production of tobacco leafs for export.

Figure 2. View from Zomba Plateau, Malawi

Malawi's economy is heavily entrenched in the agriculture sector - especially tobacco. Tobacco alone accounts for nearly 80% of Malawi's export earnings (Hobbs, 1998). Other agriculture products, namely tea and sugar, constitute nearly 20% of the nation's exports. This issue of exports is critical to a country in need of foreign currency to pay its external debt. Malawi's international debt was was $2.27 billion in 1997 and is expected to rise to $2.42 by the end of 1999 (Corssboars Monitor, 1997).

Forty-five percent of the country's GDP comes from agriculture. This figure would be considerably higher if it included the extensive subsistence agricultural production in Malawi. Roughly 90 percent of the population lives in rural areas, most of whom are involved in subsistence agriculture. The tobacco industry dominates the agricultural sector and is the nation's second largest employer after the government. Tea and sugar are also important crops for Malawi.

Tobacco and Deforestation

Figure 3. Tobacco bales

The correlation between tobacco and deforestation has received little attention to date. This is largely because few countries produce enough tobacco for the environmental ramifications to pose a significant threat. For all countries which export tobacco - except for two - one of which is Malawi (the other is Zimbabwe), export earnings for tobacco constitute less than 2.2% of their foreign exchange earnings. As noted earlier, tobacco constitutes nearly 80% of Malawi's export earnings. While some countries may be able to ignore the potential threat of the tobacco industry on deforestation, Malawi can not. Over 4% of Malawi's land is dedicated to producing tobacco, a much larger percentage than other tobacco producing nations (Panos, 1999). For Malawi, a small nation with limited natural resources, the forests represent one of the few assets the nation has.

The primary way in which tobacco contributes to deforestation is through the curing of the tobacco leaf, a process that requires the burning of fuel wood. It is difficult to assess exactly how much wood is need for curing tobacco, but some argue that it takes as much as three hectares of trees to cure one hectare of tobacco in some countries (Madeley, 1993). In Malawi, it has been estimated that tobacco growers account for as much as 25% of household wood consumption (Tobin, 1998). By the early 1990s, the Malawi government acknowledged that it had one of the highest rates of deforestation in the world (Tobin, 1998). However, the government has not identified tobacco as a primary cause of deforestation. Tobacco production can also aggravate deforestation through the need for wood to construct curing huts, structures that need to be replaced every one to two years. And of course the need to clear land for cultivation also contributes to forest degradation.

Yet, there is no consensus regarding the impact of the tobacco industry on deforestation on Malawi. Some assert that tobacco production in Malawi has a direct negative impact on the forest resources in this African country (Madeley,1993). In contrast, there are those who argue that there is no basis for such claims. It is worth noting that those who fall into this later category are the ones who stand to gain the most from continued tobacco production in Malawi. In fact, the Tobacco Association of Malawi states not only that tobacco production poses no threat to forest resources, but they further state that their reforestation programs are actually resulting in a net increase on forest resources. What did the Civil War�s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians � including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many � to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers� deaths in the Civil War � approximately 750,000 � was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. �The poem,� said Henri Meschonnic, �is the place where one forgets, which is another way of remembering�; so recurrence in COTP: the engram, �memories� of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but� COTP teaches; as we encounter it (a declaration about time, in time) later, too: �memory and the present are not in opposition� (43) so I�m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or �When the anarchic excess has already been anticipated, what next?� (9)�is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: �Also you have aspired to a sincerity of skepticism�; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first �conditional� problem�but what I like is how LR isn�t ever mired in the problem of what to say or how to proceed� the only crisis�again, I think COTP teaches this�is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again� Later we get: �the form never extinguishes its own irony� (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: �an unknowing expands within your pronoun but it feels convivial� (12); I like this party being thrown for two pronouns that can�t really meet and therefore that�s their intimate rendezvous�like LR is saying (to herself, to a future I, to an us) just deal� and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: �you carried the great discovery of poetry as freedom, not form� (75).

-Or instead try this these love is �your muscles made into extremely fine and silky tools� or love is �what light did, how the trees freed it� or love is �an inversion in perception� or love is �the smell of rain, bread and exhaust mixed with tiredness.�

-�And if you yourself are incompatible with your view of the world?� (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR�s kinda sorta How to Write).Replicating nanorobots

MNT nanofacturing is popularly linked with the idea of swarms of coordinated nanoscale robots working together, a popularization of an early proposal by K. Eric Drexler in his 1986 discussions of MNT, but superseded in 1992. In this early proposal, sufficiently capable nanorobots would construct more nanorobots in an artificial environment containing special molecular building blocks.

Critics have doubted both the feasibility of self-replicating nanorobots and the feasibility of control if self-replicating nanorobots could be achieved: they cite the possibility of mutations removing any control and favoring reproduction of mutant pathogenic variations. Advocates address the first doubt by pointing out that the first macroscale autonomous machine replicator, made of Lego blocks, was built and operated experimentally in 2002.[8] While there are sensory advantages present at the macroscale compared to the limited sensorium available at the nanoscale, proposals for positionally controlled nanoscale mechanosynthetic fabrication systems employ dead reckoning of tooltips combined with reliable reaction sequence design to ensure reliable results, hence a limited sensorium is no handicap; similar considerations apply to the positional assembly of small nanoparts. Advocates address the second doubt by arguing that bacteria are (of necessity) evolved to evolve, while nanorobot mutation could be actively prevented by common error-correcting techniques. Similar ideas are advocated in the Foresight Guidelines on Molecular Nanotechnology,[9] and a map of the 137-dimensional replicator design space[10] recently published by Freitas and Merkle provides numerous proposed methods by which replicators could, in principle, be safely controlled by good design.

However, the concept of suppressing mutation raises the question: How can design evolution occur at the nanoscale without a process of random mutation and deterministic selection? Critics argue that MNT advocates have not provided a substitute for such a process of evolution in this nanoscale arena where conventional sensory-based selection processes are lacking. The limits of the sensorium available at the nanoscale could make it difficult or impossible to winnow successes from failures. Advocates argue that design evolution should occur deterministically and strictly under human control, using the conventional engineering paradigm of modeling, design, prototyping, testing, analysis, and redesign.MASK BOY: Well, that�s a start.

CHER: Well, what about your news?

MASK BOY: Oh, it�s no big deal. I met a girl. We�re going out.

CHER: Come on. Tell us.

MASK BOY: Well, her name is Diana �.

CHER: Yeah?

MASK BOY: And she�s beautiful. She�s got long blond hair. (A friend whistles.) She rides horses and she�s beautiful and she�s smart and she loves me.

CHER: What�s not to love, baby?

SCENE 14 (OLD MAN enters his kitchen. Sitting at the table, he looks through a photo album filled with pictures of himself and his son, the Great Mutato as a child.. POLLIDORI enters, angry.)

POLLIDORI: Tell me it isn�t true. You didn�t. You wouldn�t. Why?

OLD MAN: Because I can.

(POLLIDORI attacks OLD MAN)

(Grunting as we see the shadows of the two men struggling, POLLIDORI strangling the OLD MAN.)

(Commercial.)

SCENE 15 (J.J.�S diner. MULDER enters. Everyone is hostile. MULDER defensive and over it. Exact opposite of earlier scene. LARGE MAN sticks his leg out as if to trip MULDER.)

LARGE MAN: (sarcastically) Excuse me.

MULDER: (also sarcastic) Not a problem.

(Another hostile diner flips a spoonful of grits? at MULDER which lands on the back of his neck. MULDER reacts with disgust. He sits down and wipes the offending matter away. In the kitchen, J.J. spits loudly onto a plate of barely cooked eggs and hands it to the WAITRESS. The WAITRESS puts it down in front of MULDER.)

MULDER: (looking at disgusting eggs) What�s this?

WAITRESS: (saccharine) Compliments of J.J. Coffee?

MULDER: (looking away) Sure.

(WAITRESS spills hot coffee in MULDER�S lap. He jumps up, brushing off his crotch.)

MULDER: Whoa! That�s not a place you want to burn a guy.

(WAITRESS flounces away. MULDER looks as newspaper headline : "FBI Agents Say Monster A �Hoax�.")

(Commotion outside. Lots of people running past the diner. MULDER follows them to where there is a large gathering outside the post office.)

POSTAL MAN: You want to see your monster? Fri Nov 21 18:29:56 PST 2014
The Gettysburg Address is a speech by U.S. President Abraham Lincoln, one of the best-known in American history.[4] It was delivered by Lincoln during the American Civil War, on the afternoon of Thursday, November 19, 1863, at the dedication of the Soldiers' National Cemetery in Gettysburg, Pennsylvania, four and a half months after the Union armies defeated those of the Confederacy at the Battle of Gettysburg.

Abraham Lincoln's carefully crafted address, secondary to other presentations that day, came to be regarded as one of the greatest speeches in American history. In just over two minutes, Lincoln reiterated the principles of human equality espoused by the Declaration of Independence[5] and proclaimed the Civil War as a struggle for the preservation of the Union sundered by the secession crisis,[6] with "a new birth of freedom",[7] that would bring true equality[8] to all of its citizens.[8] Lincoln also redefined the Civil War as a struggle not just for the Union, but also for the principle of human equality.[5]

Beginning with the now-iconic phrase "Four score and seven years ago"�referring to the Declaration of Independence, written at the start of the American Revolution in 1776�Lincoln examined the founding principles of the United States in the context of the Civil War, and memorialized the sacrifices of those who gave their lives at Gettysburg and extolled virtues for the listeners (and the nation) to ensure the survival of America's representative democracy, that "government of the people, by the people, for the people, shall not perish from the earth."

Despite the speech's prominent place in the history and popular culture of the United States, the exact wording and location of the speech are disputed. The five known manuscripts of the Gettysburg Address differ in a number of details and also differ from contemporary newspaper reprints of the speech. Modern scholarship locates the speakers' platform 40 yards (or more) away from the Traditional Site within Soldiers' National Cemetery at the Soldiers' National Monument and entirely within private, adjacent Evergreen Cemetery.

Everyone should begin collecting a private library in youth; the instinct of private property, which is fundamental in human beings, can here be cultivated with every advantage and no evils. One should have one's own bookshelves, which should not have doors, glass windows, or keys; they should be free and accessible to the hand as well as to the eye. The best of mural decorations is books; they are more varied in color and appearance than any wallpaper, they are more attractive in design, and they have the prime advantage of being separate personalities, so that if you sit alone in the room in the firelight, you are surrounded with intimate friends. The knowledge that they are there in plain view is both stimulating and refreshing. You do not have to read them all. Most of my indoor life is spent in a room containing six thousand books; and I have a stock answer to the invariable question that comes from strangers. "Have you read all of these books?"
"Some of them twice." This reply is both true and unexpected.

The three then travel to Rebma, a reflection of Amber underwater, and there they meet their sister Llewella and Moire, the queen of Rebma. Moire originally believes that the three of them came to Rebma to seek support to defeat Eric but Deirdre explains their true intentions. Because Rebma is a reflection of Amber, there is also a reflection of the Pattern in Rebma, which Corwin is to walk to restore his memory. Although Random is impounded for past crimes in Rebma and sentenced to wed a blind girl named Vialle, Corwin convinces the queen Moire to allow him to walk the Pattern. After receiving advice from Random and Deirdre, he walks the Pattern, reliving all of his past life, which stretches back to his time in Amber and when Eric deposited him in Elizabethan England on our Earth. He remembers the powers which his heritage and the Pattern grant him - the power to walk through shadow, and to pronounce a powerful curse before dying. After he completes the Pattern he uses its power to project himself into the Castle of Amber, from which he finds a safe spot and rests.You do somethin' to me,
Somethin' that simply mystifies me,
Tell me, why should it be?
You have the power to hypnotize me...

Let me live 'neath your spell,
Do do that 'voodoo' that you do so well!
For you do somethin' to me,
That nobody else could do!

You... do... somethin' to me,
Somethin' that simply mystifies me,

Tell... me... why should it be?
You have the power to hypnotize me...

Let me live 'neath your spell,
Do do that 'voodoo' that you do so well!
For you do somethin' to me,
That nobody else could do!

That nobody else could do...
% Marge steps in.

M: Are you kids making ugly faces?
B: Maybe.
M: Well, you know.  If you keep making those faces, you'll freeze that way.
   You'll be stuck with a horrible face forever.

% Marge leaves.

L: Do you think Mom was telling the truth?
B: Mom wouldn't lie.

% The kids consider this for a few seconds.

B+L+A: All right!

% [End of Act One.  Time: 0:30]
%
% [Act Two cut in syndication.  They continue making faces, and Marge
% returns to tell them to knock it off.  When she leaves, Bart and Lisa
% try to imitate her scowl; their attempts pale next to Maggie's
% recreation (``Look at this!'').]
%
% Same as before.

B: Watch this scary face.

% Bart makes a scary face.

L: Oh yeah?  That's not scary.

% Lisa makes a scary face.

B: Oh yeah?  That's not scary.

% Bart makes another scary face.

L: Oh yeah?  That's not...

% The door creaks open.  It's Marge, with a very scary angry face.

B+L: Waaauugh!  Scary!!

% [End of Act Three.  Time: 0:46]

L: I call this face, ``The Howler Monkey''.

% Lisa does a pretty good semblance.

B: I call this face, ``Dad''.

% Bart does a very good job.
%
% Marge bursts in.

M: All right.  I told you, if you kept making those horrible faces,
   they'd freeze that way.  And now they have.
L: They have!?
B: Our faces are frozen?
M: Yes, they are.  Go look in the mirror and see for yourselves.% Marge steps in.

M: Are you kids making ugly faces?
B: Maybe.
M: Well, you know.  If you keep making those faces, you'll freeze that way.
   You'll be stuck with a horrible face forever.

% Marge leaves.

L: Do you think Mom was telling the truth?
B: Mom wouldn't lie.

% The kids consider this for a few seconds.

B+L+A: All right!

% [End of Act One.  Time: 0:30]
%
% [Act Two cut in syndication.  They continue making faces, and Marge
% returns to tell them to knock it off.  When she leaves, Bart and Lisa
% try to imitate her scowl; their attempts pale next to Maggie's
% recreation (``Look at this!'').]
%
% Same as before.

B: Watch this scary face.

% Bart makes a scary face.

L: Oh yeah?  That's not scary.

% Lisa makes a scary face.

B: Oh yeah?  That's not scary.

% Bart makes another scary face.

L: Oh yeah?  That's not...

% The door creaks open.  It's Marge, with a very scary angry face.

B+L: Waaauugh!  Scary!!

% [End of Act Three.  Time: 0:46]

L: I call this face, ``The Howler Monkey''.

% Lisa does a pretty good semblance.

B: I call this face, ``Dad''.

% Bart does a very good job.
%
% Marge bursts in.

M: All right.  I told you, if you kept making those horrible faces,
   they'd freeze that way.  And now they have.
L: They have!?
B: Our faces are frozen?
M: Yes, they are.  Go look in the mirror and see for yourselves.You do somethin' to me,
Somethin' that simply mystifies me,
Tell me, why should it be?
You have the power to hypnotize me...

Let me live 'neath your spell,
Do do that 'voodoo' that you do so well!
For you do somethin' to me,
That nobody else could do!

You... do... somethin' to me,
Somethin' that simply mystifies me,

Tell... me... why should it be?
You have the power to hypnotize me...

Let me live 'neath your spell,
Do do that 'voodoo' that you do so well!
For you do somethin' to me,
That nobody else could do!

That nobody else could do...
Johann Wolfgang von Goethe (/'g?rt?/;[1] German: ['jo?han 'v?lfga? f?n 'g�?t?] ( listen); 28 August 1749 � 22 March 1832) was a German writer and statesman. His body of work includes epic and lyric poetry written in a variety of metres and styles; prose and verse dramas; memoirs; an autobiography; literary and aesthetic criticism; treatises on botany, anatomy, and colour; and four novels. In addition, numerous literary and scientific fragments, more than 10,000 letters, and nearly 3,000 drawings by him are extant.

A literary celebrity by the age of 25, Goethe was ennobled by the Duke of Saxe-Weimar, Carl August in 1782 after first taking up residence there in November 1775 following the success of his first novel, The Sorrows of Young Werther. He was an early participant in the Sturm und Drang literary movement. During his first ten years in Weimar, Goethe served as a member of the Duke's privy council, sat on the war and highway commissions, oversaw the reopening of silver mines in nearby Ilmenau, and implemented a series of administrative reforms at the University of Jena. He also contributed to the planning of Weimar's botanical park and the rebuilding of its Ducal Palace, which in 1998 were together designated a UNESCO World Heritage Site.[2]

After returning from a tour of Italy in 1788, his first major scientific work, the Metamorphosis of Plants, was published. In 1791 he was made managing director of the theatre at Weimar, and in 1794 he began a friendship with the dramatist, historian, and philosopher Friedrich Schiller, whose plays he premiered until Schiller's death in 1805. During this period Goethe published his second novel, Wilhelm Meister's Apprenticeship, the verse epic Hermann and Dorothea, and, in 1808, the first part of his most celebrated drama, Faust. His conversations and various common undertakings throughout the 1790s with Schiller, Johann Gottlieb Fichte, Johann Gottfried Herder, Alexander von Humboldt, Wilhelm von Humboldt, and August and Friedrich Schlegel have, in later years, been collectively termed Weimar Classicism.

Arthur Schopenhauer cited Wilhelm Meister's Apprenticeship as one of the four greatest novels ever written[citation needed] and Ralph Waldo Emerson selected Goethe as one of six "representative men" in his work of the same name, along with Plato, Napoleon, and William Shakespeare. Goethe's comments and observations form the basis of several biographical works, most notably Johann Peter Eckermann's Conversations with Goethe. There are frequent references to Goethe's writings throughout the works of G. W. F. Hegel, Arthur Schopenhauer, Friedrich Nietzsche, Hermann Hesse, Thomas Mann, Sigmund Freud, and Carl Jung. Goethe's poems were set to music throughout the eighteenth and nineteenth centuries by a number of composers, including Wolfgang Amadeus Mozart, Ludwig van Beethoven, Franz Schubert, Robert Schumann, Johannes Brahms, Charles Gounod, Richard Wagner, Hugo Wolf, and Gustav Mahler.