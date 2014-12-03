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
Enzymes act by converting starting molecules (substrates) into different molecules (products). Almost all chemical reactions in a biological cell need enzymes in order to occur at rates sufficient for life. Since enzymes are selective for their substrates and speed up only a few reactions from among many possibilities, the set of enzymes made in a cell determines which metabolic pathways occur in that cell, tissue and organ. Organelles are also differentially enriched in sets of enzymes to compartmentalise function within the cell.

Like all catalysts, enzymes increase the rate of a reaction by lowering its activation energy (Ea‡). As a result, products are formed faster and reactions reach their equilibrium state more rapidly. Most enzyme reaction rates are millions of times faster than those of comparable un-catalyzed reactions and some are so fast that they are diffusion limited. As with all catalysts, enzymes are not consumed by the reactions they catalyze, nor do they alter the equilibrium of these reactions. However, enzymes do differ from most other catalysts in that they are highly specific for their substrates. Enzymes are known to catalyze about 4,000 biochemical reactions.[3] A few RNA molecules called ribozymes also catalyze reactions, with an important example being some parts of the ribosome.[4][5] Synthetic molecules called artificial enzymes also display enzyme-like catalysis.[6]

Enzyme activity can be affected by other molecules: decreased by inhibitors or increased by activators. Many drugs and poisons are enzyme inhibitors. Activity is also affected by temperature, pressure, chemical environment (e.g., pH), and the concentration of substrate. Some enzymes are used commercially, for example, in the synthesis of antibiotics. In addition, some household products use enzymes to speed up biochemical reactions (e.g., enzymes in biological washing powders break down protein or fat stains on clothes; enzymes in meat tenderizers break down proteins into smaller molecules, making the meat easier to chew). The study of enzymes is called enzymology.Enzymes act by converting starting molecules (substrates) into different molecules (products). Almost all chemical reactions in a biological cell need enzymes in order to occur at rates sufficient for life. Since enzymes are selective for their substrates and speed up only a few reactions from among many possibilities, the set of enzymes made in a cell determines which metabolic pathways occur in that cell, tissue and organ. Organelles are also differentially enriched in sets of enzymes to compartmentalise function within the cell.

Like all catalysts, enzymes increase the rate of a reaction by lowering its activation energy (Ea‡). As a result, products are formed faster and reactions reach their equilibrium state more rapidly. Most enzyme reaction rates are millions of times faster than those of comparable un-catalyzed reactions and some are so fast that they are diffusion limited. As with all catalysts, enzymes are not consumed by the reactions they catalyze, nor do they alter the equilibrium of these reactions. However, enzymes do differ from most other catalysts in that they are highly specific for their substrates. Enzymes are known to catalyze about 4,000 biochemical reactions.[3] A few RNA molecules called ribozymes also catalyze reactions, with an important example being some parts of the ribosome.[4][5] Synthetic molecules called artificial enzymes also display enzyme-like catalysis.[6]

Enzyme activity can be affected by other molecules: decreased by inhibitors or increased by activators. Many drugs and poisons are enzyme inhibitors. Activity is also affected by temperature, pressure, chemical environment (e.g., pH), and the concentration of substrate. Some enzymes are used commercially, for example, in the synthesis of antibiotics. In addition, some household products use enzymes to speed up biochemical reactions (e.g., enzymes in biological washing powders break down protein or fat stains on clothes; enzymes in meat tenderizers break down proteins into smaller molecules, making the meat easier to chew). The study of enzymes is called enzymology.Shoe making is the process of making Foot Wear. Originally, shoes were made one at a time by hand. Traditional handicraft shoemaking has now been largely superseded in volume of shoes produced by industrial mass production of footwear, but not necessarily in quality, attention to detail, or craftsmanship.

Shoemakers or cordwainers (cobblers being those who repair shoes) may produce a range of footwear items, including shoes, boots, sandals, clogs and moccasins. Such items are generally made of leather, wood, rubber, plastic, jute or other plant material, and often consist of multiple parts for better durability of the sole, stitched to a leather upper.% Marge steps in.

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
M: Yes, they are.  Go look in the mirror and see for yourselves.The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy’s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy’s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations – a behavior called “braying.” While female Grevy’s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy’s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female’s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy’s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother’s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.
