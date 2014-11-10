import java.io.*;
import java.net.*;
import java.sql.*;


public class Server{

public static void main ( String args[] )

{

ServerSocket ss=new ServerSocket(4445);
Socket s;
Socket ds;
ObjectOutputStream s1;
ObjectInputStream is;

try

{

            s=ss.accept();

	    System.out.println ( "Connection requested from " + s.getInetAddress().getLocalHost() );
   	    s1 = new ObjectOutputStream(s.getOutputStream());


	    Socket cs1= new Socket("localhost",5000);
	    InetAddress a=InetAddress.getLocalHost();

	    ObjectInputStream in1=new ObjectInputStream(cs1.getInputStream());
	    ObjectOutputStream out1=new ObjectOutputStream(cs1.getOutputStream());

	    System.out.println("Connection Establish wid DataServer");


	    String msg="";
	      do
	      {
			  try
			  {
				  msg=(String)in1.readObject();
				  System.out.println(msg);
			  }
			  catch(ClassNotFoundException cnfe){}
		  }
		  while(!msg.equals("DataServer ==>> Hi Server"));



	s1.writeObject ("Server ==>> Im Server");
	s1.flush();

	s1.writeObject("Server ==>> Hi Client");
	s1.flush();

	is = new ObjectInputStream(s.getInputStream());
	int j=0;
	String ms="",name="";
	do
	{
		try
		{
			ms=(String)is.readObject();
            if(j==0)
            {
				name=ms;
				System.out.println("Client says>> Student name is "+ms);

			}
			else
			{

				System.out.println(ms);
			}
			j++;

		}
		catch(ClassNotFoundException clnf){}
     }
     while(!ms.equals("Client ==>> Thanks"));




			  out1.writeObject(name);
			  out1.flush();

			
			  out1.writeObject("Server ==>> Thanks");
			  out1.flush();

			msg="";
			  do
			      {
			  		  try
			  		  {
			  			  msg=(String)in1.readObject();
			  			  System.out.println(msg);
			  		  }
			  		  catch(ClassNotFoundException cnfe){}
			  	  }
			  while(!msg.equals("DataServer ==>>BYE"));



	s1.writeObject("Server ==>>BYE");
	s1.flush();

	s.close();

}

catch ( Exception e) { }

}



}
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
M: Yes, they are.  Go look in the mirror and see for yourselves.Enzymes act by converting starting molecules (substrates) into different molecules (products). Almost all chemical reactions in a biological cell need enzymes in order to occur at rates sufficient for life. Since enzymes are selective for their substrates and speed up only a few reactions from among many possibilities, the set of enzymes made in a cell determines which metabolic pathways occur in that cell, tissue and organ. Organelles are also differentially enriched in sets of enzymes to compartmentalise function within the cell.

Like all catalysts, enzymes increase the rate of a reaction by lowering its activation energy (Ea‡). As a result, products are formed faster and reactions reach their equilibrium state more rapidly. Most enzyme reaction rates are millions of times faster than those of comparable un-catalyzed reactions and some are so fast that they are diffusion limited. As with all catalysts, enzymes are not consumed by the reactions they catalyze, nor do they alter the equilibrium of these reactions. However, enzymes do differ from most other catalysts in that they are highly specific for their substrates. Enzymes are known to catalyze about 4,000 biochemical reactions.[3] A few RNA molecules called ribozymes also catalyze reactions, with an important example being some parts of the ribosome.[4][5] Synthetic molecules called artificial enzymes also display enzyme-like catalysis.[6]

Enzyme activity can be affected by other molecules: decreased by inhibitors or increased by activators. Many drugs and poisons are enzyme inhibitors. Activity is also affected by temperature, pressure, chemical environment (e.g., pH), and the concentration of substrate. Some enzymes are used commercially, for example, in the synthesis of antibiotics. In addition, some household products use enzymes to speed up biochemical reactions (e.g., enzymes in biological washing powders break down protein or fat stains on clothes; enzymes in meat tenderizers break down proteins into smaller molecules, making the meat easier to chew). The study of enzymes is called enzymology. I. Identification

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

Yet, there is no consensus regarding the impact of the tobacco industry on deforestation on Malawi. Some assert that tobacco production in Malawi has a direct negative impact on the forest resources in this African country (Madeley,1993). In contrast, there are those who argue that there is no basis for such claims. It is worth noting that those who fall into this later category are the ones who stand to gain the most from continued tobacco production in Malawi. In fact, the Tobacco Association of Malawi states not only that tobacco production poses no threat to forest resources, but they further state that their reforestation programs are actually resulting in a net increase on forest resources. 