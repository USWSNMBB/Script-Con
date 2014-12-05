import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class Cancellation extends Frame implements ActionListener
{
        Label l1,l2,l3;
	TextField t1;
        
	Button b1,b2;
	GridBagLayout gbl;
	GridBagConstraints gbc; 
        Connection con;
        PreparedStatement ps;
        Statement stmt;
	ResultSet rs;
	int count;
	Font f;
	




	Cancellation()
 {
         setBackground(Color.cyan);
         f = new Font("TimesRoman",Font.BOLD,20);
         gbl=new GridBagLayout();
	 gbc=new GridBagConstraints();
	 setLayout(gbl);
	 
         l1 = new Label("PNR No");
	 l1.setFont(f);
         
         t1 = new TextField(20);
         
         l2 = new Label("");
	 l3 = new Label("");
          
         b1 = new Button("Submit");
	 b2 = new Button("Reset");

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
	 gbl.setConstraints(l3,gbc);
	 add(l3);

         gbc.gridx=0;
	 gbc.gridy=4;
	 gbl.setConstraints(b1,gbc);
	 add(b1);

         gbc.gridx=2;
	 gbc.gridy=4;
	 gbl.setConstraints(b2,gbc);
	 add(b2);

         b1.addActionListener(this);
	 b2.addActionListener(this);
         addWindowListener(new TU());
 }
        public void actionPerformed(ActionEvent ae)
	{
        
        if(ae.getSource()==b2)
	{
         t1.setText("");
        }
         
       
        if(ae.getSource()==b1)
	{
	try
	{   
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con=DriverManager.getConnection("jdbc:odbc:MyDataSource1");
        
        ps=con.prepareStatement("select FlightNo,TravelDate,Class from Passengers where PNRNo=?");
        String pnrno = t1.getText();
        ps.setInt(1,Integer.parseInt(pnrno));
        rs=ps.executeQuery();
        rs.next();
        
        System.out.println(rs.getString(1)+""+rs.getString(2)+" "+rs.getString(3));          
        
        

        
        

       
  /*      if(rs.getString(3).equals(String.valueOf('F')))
        {
       
        
        ps=con.prepareStatement("update Reservation set FSeats=FSeats+1 where FlightNo=? and TravelDate=?"); 
        ps.setString(1,rs.getString(1));
        ps.setString(2,rs.getString(2));
        count=ps.executeUpdate();
        
        }  

      if(rs.getString(3).equals(String.valueOf('B')))
        {
        
        
        ps=con.prepareStatement("update Reservation set BSeats=BSeats+1 where FlightNo= ? and TravelDate= ? ");
        ps.setString(1,rs.getString(1));
        ps.setString(2,rs.getString(2));
        count=ps.executeUpdate();
       
        }


       if(rs.getString(3).equals(String.valueOf('E')))
        {
        
       
        ps=con.prepareStatement("update Reservation set ESeats=ESeats+1 where FlightNo=? and TravelDate=?"); 
        ps.setString(1,rs.getString(1));
        ps.setString(2,rs.getString(2));
        count=ps.executeUpdate();
        
        }
        
*/
     
        ps=con.prepareStatement("delete from Passengers where PNRNo=?");
        ps.setInt(1,Integer.parseInt(pnrno));
        count = ps.executeUpdate();  
        con.close();
        t1.setText("");


        }
        
        catch(Exception e)
        {
        System.out.println("Error : "+e);
        }
        
        }

        }
	
        class TU extends WindowAdapter
	{
	public void windowClosing(WindowEvent e)
	{
	setVisible(false);
	dispose();
			
	}
	}

}

Modern Furniture designed for Modern Living.
Welcome To Viesso, we’re really glad you found us! We believe modern furniture isn’t just about the modern style. It’s also about products that appeal to and enhance our modern lives. Smart design, custom options, Green furniture materials, innovative functions. It all adds up to giving you exciting solutions for your space. We've scoured the marketplace to bring you home furnishings that meet these modern ideals.

Our exclusive Viesso line, which is American built, offers numerous options throughout the shopping process so that you can create custom furniture that is completely unique. It’s contemporary furniture only sold here. Designed by us. Customized by you. Choose size, finish, filling, and more!

All of our modern furniture products can be priced and purchased either in our Los Angeles showroom or online, where we encourage you to request material samples and ask us for advice in choosing the perfect pieces for your space. We hope you enjoy exploring our website, and please don’t hesitate to contact us with any questions along the way.About the Project

This project is an online interactive featuring the Eagle lunar landing. The presentation includes original Apollo 11 spaceflight video footage, communication audio, mission control room conversations, text transcripts, and telemetry data, all synchronized into an integrated audio-visual experience.

Until today, it has been impossible to comprehensively experience mankind's shining exploratory accomplishment in a singular experience. We have compiled hours of content available from public domain sources and various NASA websites. Thamtech staff and volunteers generously devoted their time to transcribe hours of speech to text. By using simultaneous space and land based audio and video, transcripts, images, spacecraft telemetry, and biomedical data—this synchronized presentation reveals the Moon Shot as experienced by the astronauts and flight controllers.

Our goal is to capture a moment in history so that generations may now relive the events with this interactive educational resource. The world remembers the moon landing as a major historical event but often fails to recognize the scale of the mission. This interactive resource aims to educate visitors while engaging them with the excitement of manned-spaceflight to build a passion for scientific exploration.

Visitors begin the experience by hearing the words of Buzz Aldrin while simultaneously viewing the moon through the lunar module window. Moments later, the audience hears capsule communicator Charlie Duke inform flight director Gene Kranz that the astronauts are on schedule to start the descent engine. Throughout the presentation, visitors are able to customize their experience by jumping to key moments in the timeline. The timeline guides visitors to the crucial moments in the mission, including: program alarms (computer alerts), famous Go No-Go polls in the control room, low level fuel milestones, and landing.

"The Eagle has Landed." Neil Armstrong's words signal a technical milestone and successful execution of John F. Kennedy's vision to land a man on the moon safely. Prior to these famous words, visitors see the synchronized audio communications, transcripts, video of the lunar module's casting a shadow on the lunar surface, and biomedical telemetry of Armstrong's heart rate surpassing 150 beats per minute!

The footprints from Buzz Aldrin and Neil Armstrong on July 20, 1969 paved the way for five additional successful trips to the lunar surface over the following years. Thamtech takes pride in providing visitors with a glimpse into this and mankind's enduring spirit for exploration.
- See more at: http://www.firstmenonthemoon.com/about.html#sthash.iXDZXDDA.dpuf(GOSS NET 1) Tape 1/3 Page 3

00 00 08 22 CDR
AGS just got the mixture ratio shift.

00 00 08 24 CC
Roger. We got PU shift down here, too.

00 00 08 34 CDR
Well, it looks like a nice day for it. These thunderstorms down range is about all.

GRAND BAHAMA ISLANDS (REV 1)

00 00 08 52 CC
11, this is Houston. You are GO for staging. Over.

00 00 08 56 CDR
Understand, GO for staging. And - -

00 00 08 57 CC
Stand by for mode IV capability.

00 00 08 59 CDR
Okay. Mode IV.

00 00 09 00 CC
MARK.

00 00 09 01 CC
Mode IV capability.

00 00 09 15 CDR
Staging -

00 00 09 16 CDR
- And ignition.

00 00 09 19 CC
Ignition confirmed; thrust is GO, 11.

00 00 10 01 CC
Apollo 11, this is Houston. At 10 minutes, you are GO.

00 00 10 06 CDR
Roger. 11's GO.


Although it increases the risk of poor outcomes and raises the costs of care, cognitive impairment in hospitalized older adults is often neither accurately identified nor well managed. In conducting a two-phase, comparative-effectiveness clinical trial of the effects of three nursing interventions—augmented standard care, resource nurse care, and the transitional care model—on hospitalized older adults with cognitive deficits, a team of researchers encountered several challenges. For example, in assessing potential subjects for the study, they found that nearly half of those assessed had cognitive impairment, yet many family caregivers could not be identified or had no interest in participating in the study. One lesson the researchers learned was that research involving cognitively impaired older adults must actively engage clinicians, patients, and family caregivers, as well as address the complex process of managing postdischarge care.



I am not reciting these facts for the purpose of recrimination. That I judge to be utterly futile and even harmful. We cannot afford it. I recite them in order to explain why it was we did not have, as we could have had, between twelve and fourteen British divisions fighting in the line in this great battle instead of only three. Now I put all this aside. I put it on the shelf, from which the historians, when they have time, will select their documents to tell their stories. We have to think of the future and not of the past. This also applies in a small way to our own affairs at home. There are many who would hold an inquest in the House of Commons on the conduct of the Governments--and of Parliaments, for they are in it, too--during the years which led up to this catastrophe. They seek to indict those who were responsible for the guidance of our affairs. This also would be a foolish and pernicious process. There are too many in it. Let each man search his conscience and search his speeches. I frequently search mine. 