/*************************************************************
 * tcpClient.java
 *
 *  *       Version 1.1
 *
 * Set up a Server that will receive
 *    - a connection from a client
 *      - requests for services
 *        - Add
 *        - Delete
 *        - Find
 *        - Update
 *        - List
 *    - respond to requests by sending a string(s) back to the client
 *    - and close the connection when the Client is finished.
 *
 *
 * Copyright (c) 2002-2006 Advanced Applications Total Applications Works.
 * (AATAW)  All Rights Reserved.
 *
 * AATAW grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to AATAW.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. AATAW AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL AATAW OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes or for commercial purposes.
 *
 * Changelog:
 * default port is 5050
 *************************************************************/

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;

/** ***************************************************************
  * The tcpClient class creates an object for the Client frame.
  * Set up a client that will receive
  *    - a connection from a server
  *      - sends requests for services to the Server
  *        - Send
  *
  *    - process server responses to string(s) sent back to the client
  *    - and close the connection when the Client is finished.
  *
 ******************************************************************/
public class tcpClient extends JFrame implements  ActionListener {
   private int port = 5050;
   private String server = "localhost";
   private Socket socket = null;
   private BufferedReader input;
   private PrintWriter output;
   private int ERROR = 1;
   private Container c ;
   private JTextArea display ;
   private JButton clear , find, list, add, update, delete, exit;
   private JPanel buttonPanel, textPanel ;
   private JTextField enterBox;
   private JLabel enterLabel;
   private StringTokenizer tokens ;
   private String messageTokens[]  = new String[ 16 ] ;


   /** ***************************************************************
    *  The tcpClient constructor initializes the tcpClient object.
    ******************************************************************/
   public tcpClient() {
      super( "Client" ) ;

      setUp() ;

      connect() ;

      RunClient() ;

      closeConnection() ;
   }

   /** ***************************************************************
    * The setUp() method does the intialization for the application.
    * The setUp() method
    * 1- Creates JButtons
    * 2- Creates JPanels
    * 3- Creates JLabels
    * 4- Adds Action Listeners to the JButtons
    * 5- Sets the size for the JFrame
    * 6- Sets the location for the JFrame
    * 7- Makes the JFrame visible
    ******************************************************************/
   public  void setUp() {

      c = getContentPane();

      /** Create JButtons */
      find   = new JButton( "Find" );
      list   = new JButton( "List" );
      add    = new JButton( "Add" );
      update = new JButton( "Update" );
      delete = new JButton( "Delete" );
      clear  = new JButton( "Clear Message" );
      exit   = new JButton( "Exit" );

      /** ******************************************************
       * Set up the Background and Foreground Colors
       *  for JButtons
       ******************************************************* */
      list.setBackground( Color.blue ) ;
      list.setForeground( Color.white ) ;
      find.setBackground( Color.blue ) ;
      find.setForeground( Color.white ) ;
      add.setBackground( Color.blue ) ;
      add.setForeground( Color.white ) ;
      update.setBackground( Color.blue ) ;
      update.setForeground( Color.white ) ;
      delete.setBackground( Color.blue ) ;
      delete.setForeground( Color.white ) ;
      exit.setBackground( Color.red ) ;
      exit.setForeground( Color.white ) ;
      clear.setBackground( Color.white ) ;
      clear.setForeground( Color.blue ) ;
      buttonPanel = new JPanel() ;

      /** Add the JButtons to the buttonPanel */
      buttonPanel.add( list ) ;
      buttonPanel.add( find ) ;
      //buttonPanel.add( add ) ;
      //buttonPanel.add( update ) ;
      buttonPanel.add( delete ) ;
      buttonPanel.add( clear ) ;
      buttonPanel.add( exit ) ;
      c.add( buttonPanel , BorderLayout.SOUTH) ;

      /** Create JLabels */
      enterLabel = new JLabel("Enter a last name below and then press a button." ) ;
      enterLabel.setFont(new Font( "Serif", Font.BOLD, 14) );
      enterLabel.setForeground( Color.black );
      enterBox = new JTextField( 100 );
      enterBox.setEditable( true );

      /** Create JPanel */
      textPanel = new JPanel() ;
      textPanel.setLayout( new GridLayout( 2, 1 ) );
      textPanel.add( enterLabel ) ;
      textPanel.add( enterBox ) ;
      c.add( textPanel , BorderLayout.NORTH) ;


      /** *************************************************************
       * Add an Action Listener for the send, exit,
       *   and clear JButtons
       ************************************************************** */
      find.addActionListener( this );
      list.addActionListener( this );
      add.addActionListener( this );
      update.addActionListener( this );
      delete.addActionListener( this );
      exit.addActionListener( this );
      clear.addActionListener( this );

      /** Create JTextArea */
      display = new JTextArea();
      display.setEditable( false );

      /** Create JScrollPane for the main area of the JFrame  */
      c.add( new JScrollPane( display ),
             BorderLayout.CENTER );

      addWindowListener( new WindowHandler( this ) );
      setSize( 500, 400 );
      setLocation( 450, 20 ) ;
      show();

   }

   /** *****************************************************************
    *  The connect() method does the intialization of the client socket
    *  on localhost and port 5050
    ***************************************************************** */
   public void connect() {
      // connect to server
      try {
         socket = new Socket(server, port);
         display.setText("Connected with server " +
              socket.getInetAddress() +
              ": Port " + socket.getPort());
      }
      catch (UnknownHostException e) {
         display.setText("" + e);
         System.exit(ERROR);
      }
      catch (IOException e) {
         display.setText("\n" + e);
         System.exit(ERROR);
      }
   }

   /** *****************************************************************
    * The sendData() method in the client sends data to the server
    ***************************************************************** */
   public void sendData(String str) {
      output.println( str );
   }


   /** *****************************************************************
    * The RunClient() method in the client reads and writes data to the server.
    ***************************************************************** */
   public void RunClient() {
      try {
         input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         output = new PrintWriter(socket.getOutputStream(),true);
      enterBox.requestFocus();

         while(true) {

            display.append( "\nThe names in the addresss book are: ") ;
            sendData( "LISTALL NAMES"  );

            String message = input.readLine();
            // stop if input line equals "QUIT"
            while ( !message.toUpperCase().equals( "FROM SERVER==> QUIT" ) ) {

               tokens = new StringTokenizer( message, ";" ) ;
               if ( tokens.countTokens() >= 1 )   {
                  int ii = 0 ;
                  while( tokens.hasMoreTokens() )  {
                     messageTokens[ ii ] = tokens.nextToken().toString() ;

                     ii++ ;
                  }

                  if ( messageTokens[ 0 ].toUpperCase().equals( "RECORDFOUND" ) ) {
                     showName() ;
                  }
                  else if ( messageTokens[ 0 ].toUpperCase().equals( "LISTRECORD" ) ) {
                     listNames() ;
                  }
                  else if ( messageTokens[ 0 ].toUpperCase().equals( "RECORDDELETED" ) ) {
                     display.append( "\n" +  message ) ;
                  }
                  else if ( messageTokens[ 0 ].toUpperCase().equals( "NOTFOUND" ) ) {
                     display.append( "\n" +  message ) ;
                  }
               }
               message = input.readLine();
            }
         }
      }
      catch (IOException e) {
         display.append("\n" + e);
      }

   }

   /** *****************************************************************
    * This method responds to the find, delete, clear or exit button
    *  being pressed on the tcpClient frame.
    ***************************************************************** */
   public void actionPerformed( ActionEvent e )    {

      if ( e.getSource() == exit ) {
            closeConnection() ;
      }
      // list all names in the array
      else if ( e.getSource() == list ) {
            sendData( "LISTALL NAMES"  );
            display.append( "\n LISTALL"  ) ;
      }
      else  if ( !enterBox.getText().equals( "" ) ) {
         if ( e.getSource() == clear ) {
            enterBox.setText( "" );
         }
         // get user input and send it to server
         else if ( e.getSource() == find ) {
            sendData( "FIND " + enterBox.getText() );
            display.append( "\n" +  enterBox.getText() ) ;
         }
         else if ( e.getSource() == add ) {
            sendData( "ADD " + enterBox.getText() );
            display.append( "\n" +  enterBox.getText() ) ;
         }
         else if ( e.getSource() == update ) {
            sendData( "UPDATE " + enterBox.getText() );
            display.append( "\n" +  enterBox.getText() ) ;
         }
         else if ( e.getSource() == delete ) {
            sendData( "DELETE " + enterBox.getText() );
            display.append( "\n" +  enterBox.getText() ) ;
         }
      }
      else {
          JOptionPane.showMessageDialog(null,
                 "Please enter a last name" + "\n",
                 "No last name",
                 JOptionPane.INFORMATION_MESSAGE ) ;
      }
   }

   /*******************************************************************
    * This method shows the name found by the server.
    ***************************************************************** */
   public  void showName() {
      display.append( "\n Name:         " + messageTokens[ 1 ] + " " + messageTokens[ 2 ] ) ;
      display.append( "\n Address:     " + messageTokens[ 3 ]  ) ;
      display.append( "\n Telephone: " + messageTokens[ 4 ]  ) ;
   }

   /** *****************************************************************
    * This method lists all of the name found by the server.
    ***************************************************************** */
   public  void listNames() {
      if ( !messageTokens[ 1 ].equals( "" ) && !messageTokens[ 1 ].equals( " " )) {
         display.append( "\n Last Name: " + messageTokens[ 1 ]  ) ;
      }
   }

   /** *****************************************************************
    * This method closes the socket connect to the server.
    ***************************************************************** */
   public  void closeConnection() {
      sendData( "QUIT" ) ;
      try {
         socket.close();
         input.close();
         output.close();
      }
      catch (IOException e) {
         display.append("\n" + e);
      }

      setVisible( false );
      System.exit( 0 );
   }

   /** *****************************************************************
    * This method is the main entry point called by the JVM.
    ****************************************************************** */
   public static void main(String[] args) {
      final tcpClient client = new tcpClient() ;

      client.addWindowListener(
         new WindowAdapter() {
            public void windowClosing( WindowEvent e )
            {
               client.closeConnection() ;
            }
         }
      );
   }

   /** *****************************************************************
    * This method closes the socket connect to the server when the
    * application window is closed.
    ***************************************************************** */
   public class WindowHandler extends WindowAdapter {
      tcpClient tcpC;

      public WindowHandler( tcpClient t ) { tcpC = t; }

      public void windowClosing( WindowEvent e ) { tcpC.closeConnection(); }
   }

}The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy’s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy’s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations – a behavior called “braying.” While female Grevy’s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy’s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female’s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy’s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother’s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.
(GOSS NET 1) Tape 1/3 Page 3

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
The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy’s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy’s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations – a behavior called “braying.” While female Grevy’s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy’s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female’s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy’s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother’s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.
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

Yet, there is no consensus regarding the impact of the tobacco industry on deforestation on Malawi. Some assert that tobacco production in Malawi has a direct negative impact on the forest resources in this African country (Madeley,1993). In contrast, there are those who argue that there is no basis for such claims. It is worth noting that those who fall into this later category are the ones who stand to gain the most from continued tobacco production in Malawi. In fact, the Tobacco Association of Malawi states not only that tobacco production poses no threat to forest resources, but they further state that their reforestation programs are actually resulting in a net increase on forest resources. Modern Furniture designed for Modern Living.
Welcome To Viesso, we’re really glad you found us! We believe modern furniture isn’t just about the modern style. It’s also about products that appeal to and enhance our modern lives. Smart design, custom options, Green furniture materials, innovative functions. It all adds up to giving you exciting solutions for your space. We've scoured the marketplace to bring you home furnishings that meet these modern ideals.

Our exclusive Viesso line, which is American built, offers numerous options throughout the shopping process so that you can create custom furniture that is completely unique. It’s contemporary furniture only sold here. Designed by us. Customized by you. Choose size, finish, filling, and more!

All of our modern furniture products can be priced and purchased either in our Los Angeles showroom or online, where we encourage you to request material samples and ask us for advice in choosing the perfect pieces for your space. We hope you enjoy exploring our website, and please don’t hesitate to contact us with any questions along the way.About the Project

This project is an online interactive featuring the Eagle lunar landing. The presentation includes original Apollo 11 spaceflight video footage, communication audio, mission control room conversations, text transcripts, and telemetry data, all synchronized into an integrated audio-visual experience.

Until today, it has been impossible to comprehensively experience mankind's shining exploratory accomplishment in a singular experience. We have compiled hours of content available from public domain sources and various NASA websites. Thamtech staff and volunteers generously devoted their time to transcribe hours of speech to text. By using simultaneous space and land based audio and video, transcripts, images, spacecraft telemetry, and biomedical data—this synchronized presentation reveals the Moon Shot as experienced by the astronauts and flight controllers.

Our goal is to capture a moment in history so that generations may now relive the events with this interactive educational resource. The world remembers the moon landing as a major historical event but often fails to recognize the scale of the mission. This interactive resource aims to educate visitors while engaging them with the excitement of manned-spaceflight to build a passion for scientific exploration.

Visitors begin the experience by hearing the words of Buzz Aldrin while simultaneously viewing the moon through the lunar module window. Moments later, the audience hears capsule communicator Charlie Duke inform flight director Gene Kranz that the astronauts are on schedule to start the descent engine. Throughout the presentation, visitors are able to customize their experience by jumping to key moments in the timeline. The timeline guides visitors to the crucial moments in the mission, including: program alarms (computer alerts), famous Go No-Go polls in the control room, low level fuel milestones, and landing.

"The Eagle has Landed." Neil Armstrong's words signal a technical milestone and successful execution of John F. Kennedy's vision to land a man on the moon safely. Prior to these famous words, visitors see the synchronized audio communications, transcripts, video of the lunar module's casting a shadow on the lunar surface, and biomedical telemetry of Armstrong's heart rate surpassing 150 beats per minute!

The footprints from Buzz Aldrin and Neil Armstrong on July 20, 1969 paved the way for five additional successful trips to the lunar surface over the following years. Thamtech takes pride in providing visitors with a glimpse into this and mankind's enduring spirit for exploration.
- See more at: http://www.firstmenonthemoon.com/about.html#sthash.iXDZXDDA.dpuf

Everyone should begin collecting a private library in youth; the instinct of private property, which is fundamental in human beings, can here be cultivated with every advantage and no evils. One should have one's own bookshelves, which should not have doors, glass windows, or keys; they should be free and accessible to the hand as well as to the eye. The best of mural decorations is books; they are more varied in color and appearance than any wallpaper, they are more attractive in design, and they have the prime advantage of being separate personalities, so that if you sit alone in the room in the firelight, you are surrounded with intimate friends. The knowledge that they are there in plain view is both stimulating and refreshing. You do not have to read them all. Most of my indoor life is spent in a room containing six thousand books; and I have a stock answer to the invariable question that comes from strangers. "Have you read all of these books?"
"Some of them twice." This reply is both true and unexpected.

Although it increases the risk of poor outcomes and raises the costs of care, cognitive impairment in hospitalized older adults is often neither accurately identified nor well managed. In conducting a two-phase, comparative-effectiveness clinical trial of the effects of three nursing interventions—augmented standard care, resource nurse care, and the transitional care model—on hospitalized older adults with cognitive deficits, a team of researchers encountered several challenges. For example, in assessing potential subjects for the study, they found that nearly half of those assessed had cognitive impairment, yet many family caregivers could not be identified or had no interest in participating in the study. One lesson the researchers learned was that research involving cognitively impaired older adults must actively engage clinicians, patients, and family caregivers, as well as address the complex process of managing postdischarge care.

The Gettysburg Address is a speech by U.S. President Abraham Lincoln, one of the best-known in American history.[4] It was delivered by Lincoln during the American Civil War, on the afternoon of Thursday, November 19, 1863, at the dedication of the Soldiers' National Cemetery in Gettysburg, Pennsylvania, four and a half months after the Union armies defeated those of the Confederacy at the Battle of Gettysburg.

Abraham Lincoln's carefully crafted address, secondary to other presentations that day, came to be regarded as one of the greatest speeches in American history. In just over two minutes, Lincoln reiterated the principles of human equality espoused by the Declaration of Independence[5] and proclaimed the Civil War as a struggle for the preservation of the Union sundered by the secession crisis,[6] with "a new birth of freedom",[7] that would bring true equality[8] to all of its citizens.[8] Lincoln also redefined the Civil War as a struggle not just for the Union, but also for the principle of human equality.[5]

Beginning with the now-iconic phrase "Four score and seven years ago"—referring to the Declaration of Independence, written at the start of the American Revolution in 1776—Lincoln examined the founding principles of the United States in the context of the Civil War, and memorialized the sacrifices of those who gave their lives at Gettysburg and extolled virtues for the listeners (and the nation) to ensure the survival of America's representative democracy, that "government of the people, by the people, for the people, shall not perish from the earth."

Despite the speech's prominent place in the history and popular culture of the United States, the exact wording and location of the speech are disputed. The five known manuscripts of the Gettysburg Address differ in a number of details and also differ from contemporary newspaper reprints of the speech. Modern scholarship locates the speakers' platform 40 yards (or more) away from the Traditional Site within Soldiers' National Cemetery at the Soldiers' National Monument and entirely within private, adjacent Evergreen Cemetery.Shoe making is the process of making Foot Wear. Originally, shoes were made one at a time by hand. Traditional handicraft shoemaking has now been largely superseded in volume of shoes produced by industrial mass production of footwear, but not necessarily in quality, attention to detail, or craftsmanship.

Shoemakers or cordwainers (cobblers being those who repair shoes) may produce a range of footwear items, including shoes, boots, sandals, clogs and moccasins. Such items are generally made of leather, wood, rubber, plastic, jute or other plant material, and often consist of multiple parts for better durability of the sole, stitched to a leather upper.About the Project

This project is an online interactive featuring the Eagle lunar landing. The presentation includes original Apollo 11 spaceflight video footage, communication audio, mission control room conversations, text transcripts, and telemetry data, all synchronized into an integrated audio-visual experience.

Until today, it has been impossible to comprehensively experience mankind's shining exploratory accomplishment in a singular experience. We have compiled hours of content available from public domain sources and various NASA websites. Thamtech staff and volunteers generously devoted their time to transcribe hours of speech to text. By using simultaneous space and land based audio and video, transcripts, images, spacecraft telemetry, and biomedical data—this synchronized presentation reveals the Moon Shot as experienced by the astronauts and flight controllers.

Our goal is to capture a moment in history so that generations may now relive the events with this interactive educational resource. The world remembers the moon landing as a major historical event but often fails to recognize the scale of the mission. This interactive resource aims to educate visitors while engaging them with the excitement of manned-spaceflight to build a passion for scientific exploration.

Visitors begin the experience by hearing the words of Buzz Aldrin while simultaneously viewing the moon through the lunar module window. Moments later, the audience hears capsule communicator Charlie Duke inform flight director Gene Kranz that the astronauts are on schedule to start the descent engine. Throughout the presentation, visitors are able to customize their experience by jumping to key moments in the timeline. The timeline guides visitors to the crucial moments in the mission, including: program alarms (computer alerts), famous Go No-Go polls in the control room, low level fuel milestones, and landing.

"The Eagle has Landed." Neil Armstrong's words signal a technical milestone and successful execution of John F. Kennedy's vision to land a man on the moon safely. Prior to these famous words, visitors see the synchronized audio communications, transcripts, video of the lunar module's casting a shadow on the lunar surface, and biomedical telemetry of Armstrong's heart rate surpassing 150 beats per minute!

The footprints from Buzz Aldrin and Neil Armstrong on July 20, 1969 paved the way for five additional successful trips to the lunar surface over the following years. Thamtech takes pride in providing visitors with a glimpse into this and mankind's enduring spirit for exploration.
- See more at: http://www.firstmenonthemoon.com/about.html#sthash.iXDZXDDA.dpuf% Marge steps in.

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
M: Yes, they are.  Go look in the mirror and see for yourselves.Barley contains starch and it is this starch which needs to be converted into soluble sugars to make alcohol. For this to occur, the barley must undergo germination and this first part of the prodess is called 'malting'. Each distiller has their own preference about the type of barley they buy, but they need a type that produce high yields of soluble sugar. The barley is soaked for 2-3 days in warm water and then traditionally spread on the floor of a building called a malting house. It is turned regularly to maintain a constant temperature. This is also carried out on a commercial scale in large drums which rotate.

The malting floor at Springbank

When the barley has started to shoot, the germination has to be stopped by drying it in a kiln. Traditionally peat is used to power the kiln and it is at this point where the type of peat used and length of drying in the peat smoke can influence the flavour of the final spirit. The barley is now called 'malt' and this is ground down in a mill, with any husks and other debris being removed. 