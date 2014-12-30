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



85% of millennials say they prefer urban-style living, and 68 percent of college-educated 25- to 34-year-olds say, first, they look for the place they want to live, then they look for a job.Enzymes act by converting starting molecules (substrates) into different molecules (products). Almost all chemical reactions in a biological cell need enzymes in order to occur at rates sufficient for life. Since enzymes are selective for their substrates and speed up only a few reactions from among many possibilities, the set of enzymes made in a cell determines which metabolic pathways occur in that cell, tissue and organ. Organelles are also differentially enriched in sets of enzymes to compartmentalise function within the cell.

Like all catalysts, enzymes increase the rate of a reaction by lowering its activation energy (Ea‡). As a result, products are formed faster and reactions reach their equilibrium state more rapidly. Most enzyme reaction rates are millions of times faster than those of comparable un-catalyzed reactions and some are so fast that they are diffusion limited. As with all catalysts, enzymes are not consumed by the reactions they catalyze, nor do they alter the equilibrium of these reactions. However, enzymes do differ from most other catalysts in that they are highly specific for their substrates. Enzymes are known to catalyze about 4,000 biochemical reactions.[3] A few RNA molecules called ribozymes also catalyze reactions, with an important example being some parts of the ribosome.[4][5] Synthetic molecules called artificial enzymes also display enzyme-like catalysis.[6]

Enzyme activity can be affected by other molecules: decreased by inhibitors or increased by activators. Many drugs and poisons are enzyme inhibitors. Activity is also affected by temperature, pressure, chemical environment (e.g., pH), and the concentration of substrate. Some enzymes are used commercially, for example, in the synthesis of antibiotics. In addition, some household products use enzymes to speed up biochemical reactions (e.g., enzymes in biological washing powders break down protein or fat stains on clothes; enzymes in meat tenderizers break down proteins into smaller molecules, making the meat easier to chew). The study of enzymes is called enzymology.Sun Nov  9 20:52:45 PST 2014
Replicating nanorobots

MNT nanofacturing is popularly linked with the idea of swarms of coordinated nanoscale robots working together, a popularization of an early proposal by K. Eric Drexler in his 1986 discussions of MNT, but superseded in 1992. In this early proposal, sufficiently capable nanorobots would construct more nanorobots in an artificial environment containing special molecular building blocks.

Critics have doubted both the feasibility of self-replicating nanorobots and the feasibility of control if self-replicating nanorobots could be achieved: they cite the possibility of mutations removing any control and favoring reproduction of mutant pathogenic variations. Advocates address the first doubt by pointing out that the first macroscale autonomous machine replicator, made of Lego blocks, was built and operated experimentally in 2002.[8] While there are sensory advantages present at the macroscale compared to the limited sensorium available at the nanoscale, proposals for positionally controlled nanoscale mechanosynthetic fabrication systems employ dead reckoning of tooltips combined with reliable reaction sequence design to ensure reliable results, hence a limited sensorium is no handicap; similar considerations apply to the positional assembly of small nanoparts. Advocates address the second doubt by arguing that bacteria are (of necessity) evolved to evolve, while nanorobot mutation could be actively prevented by common error-correcting techniques. Similar ideas are advocated in the Foresight Guidelines on Molecular Nanotechnology,[9] and a map of the 137-dimensional replicator design space[10] recently published by Freitas and Merkle provides numerous proposed methods by which replicators could, in principle, be safely controlled by good design.

However, the concept of suppressing mutation raises the question: How can design evolution occur at the nanoscale without a process of random mutation and deterministic selection? Critics argue that MNT advocates have not provided a substitute for such a process of evolution in this nanoscale arena where conventional sensory-based selection processes are lacking. The limits of the sensorium available at the nanoscale could make it difficult or impossible to winnow successes from failures. Advocates argue that design evolution should occur deterministically and strictly under human control, using the conventional engineering paradigm of modeling, design, prototyping, testing, analysis, and redesign.

For my part, whatever anguish of spirit it may cost, I am willing to know the whole truth -- to know the worst and to provide for it. I have but one lamp by which my feet are guided; and that is the lamp of experience. I know of no way of judging of the future but by the past. And judging by the past, I wish to know what there has been in the conduct of the British ministry for the last ten years, to justify those hopes with which gentlemen have been pleased to solace themselves and the House? Sat Dec  6 17:18:51 PST 2014
 I. Identification

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

Yet, there is no consensus regarding the impact of the tobacco industry on deforestation on Malawi. Some assert that tobacco production in Malawi has a direct negative impact on the forest resources in this African country (Madeley,1993). In contrast, there are those who argue that there is no basis for such claims. It is worth noting that those who fall into this later category are the ones who stand to gain the most from continued tobacco production in Malawi. In fact, the Tobacco Association of Malawi states not only that tobacco production poses no threat to forest resources, but they further state that their reforestation programs are actually resulting in a net increase on forest resources. What did the Civil War’s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians — including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many – to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers’ deaths in the Civil War – approximately 750,000 – was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. Replicating nanorobots

MNT nanofacturing is popularly linked with the idea of swarms of coordinated nanoscale robots working together, a popularization of an early proposal by K. Eric Drexler in his 1986 discussions of MNT, but superseded in 1992. In this early proposal, sufficiently capable nanorobots would construct more nanorobots in an artificial environment containing special molecular building blocks.

Critics have doubted both the feasibility of self-replicating nanorobots and the feasibility of control if self-replicating nanorobots could be achieved: they cite the possibility of mutations removing any control and favoring reproduction of mutant pathogenic variations. Advocates address the first doubt by pointing out that the first macroscale autonomous machine replicator, made of Lego blocks, was built and operated experimentally in 2002.[8] While there are sensory advantages present at the macroscale compared to the limited sensorium available at the nanoscale, proposals for positionally controlled nanoscale mechanosynthetic fabrication systems employ dead reckoning of tooltips combined with reliable reaction sequence design to ensure reliable results, hence a limited sensorium is no handicap; similar considerations apply to the positional assembly of small nanoparts. Advocates address the second doubt by arguing that bacteria are (of necessity) evolved to evolve, while nanorobot mutation could be actively prevented by common error-correcting techniques. Similar ideas are advocated in the Foresight Guidelines on Molecular Nanotechnology,[9] and a map of the 137-dimensional replicator design space[10] recently published by Freitas and Merkle provides numerous proposed methods by which replicators could, in principle, be safely controlled by good design.

However, the concept of suppressing mutation raises the question: How can design evolution occur at the nanoscale without a process of random mutation and deterministic selection? Critics argue that MNT advocates have not provided a substitute for such a process of evolution in this nanoscale arena where conventional sensory-based selection processes are lacking. The limits of the sensorium available at the nanoscale could make it difficult or impossible to winnow successes from failures. Advocates argue that design evolution should occur deterministically and strictly under human control, using the conventional engineering paradigm of modeling, design, prototyping, testing, analysis, and redesign.