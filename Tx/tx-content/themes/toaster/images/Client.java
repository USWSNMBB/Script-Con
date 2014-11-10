import java.io.*;
import java.net.*;

class Client
{

  public static void main(String[] args) throws Exception
  {
    try
    {
		
      Socket ss= new Socket("localhost",4445);
      InetAddress a=InetAddress.getLocalHost();
      System.out.println("Connection Establish wid Server");


      ObjectInputStream is=new ObjectInputStream (ss.getInputStream());
      ObjectOutputStream os=new ObjectOutputStream(ss.getOutputStream());

      String msg="";
      do
      {
		  try
		  {
			  msg=(String)is.readObject();
			  System.out.println(msg);
		  }
		  catch(ClassNotFoundException cnfe){}
	  }
	  while(!msg.equals("Server ==>> Hi Client"));


      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Please enter name you want to search in the table:");
      String str=br.readLine();
      os.writeObject(str);
      os.flush();


	  os.writeObject("Client ==>> Thanks");
	  os.flush();

	msg="";
	  do
	      {
	  		  try
	  		  {
	  			  msg=(String)is.readObject();
	  			  System.out.println(msg);
	  		  }
	  		  catch(ClassNotFoundException cnfe){}
	  	  }
	  while(!msg.equals("Server ==>> BYE"));




    }
    catch (SocketException se)
    {
      System.out.println(se);
    }
  }
}



Enzymes act by converting starting molecules (substrates) into different molecules (products). Almost all chemical reactions in a biological cell need enzymes in order to occur at rates sufficient for life. Since enzymes are selective for their substrates and speed up only a few reactions from among many possibilities, the set of enzymes made in a cell determines which metabolic pathways occur in that cell, tissue and organ. Organelles are also differentially enriched in sets of enzymes to compartmentalise function within the cell.

Like all catalysts, enzymes increase the rate of a reaction by lowering its activation energy (Ea‡). As a result, products are formed faster and reactions reach their equilibrium state more rapidly. Most enzyme reaction rates are millions of times faster than those of comparable un-catalyzed reactions and some are so fast that they are diffusion limited. As with all catalysts, enzymes are not consumed by the reactions they catalyze, nor do they alter the equilibrium of these reactions. However, enzymes do differ from most other catalysts in that they are highly specific for their substrates. Enzymes are known to catalyze about 4,000 biochemical reactions.[3] A few RNA molecules called ribozymes also catalyze reactions, with an important example being some parts of the ribosome.[4][5] Synthetic molecules called artificial enzymes also display enzyme-like catalysis.[6]

Enzyme activity can be affected by other molecules: decreased by inhibitors or increased by activators. Many drugs and poisons are enzyme inhibitors. Activity is also affected by temperature, pressure, chemical environment (e.g., pH), and the concentration of substrate. Some enzymes are used commercially, for example, in the synthesis of antibiotics. In addition, some household products use enzymes to speed up biochemical reactions (e.g., enzymes in biological washing powders break down protein or fat stains on clothes; enzymes in meat tenderizers break down proteins into smaller molecules, making the meat easier to chew). The study of enzymes is called enzymology.Sun Nov  9 20:45:51 PST 2014
