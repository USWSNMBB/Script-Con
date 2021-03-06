using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using System.ServiceProcess;
using System.IO;
using HPsmtp;


namespace backupbatch
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class Form1 : System.Windows.Forms.Form
	{
		string destPath = "";
		string filePath = "";
		ServiceController controller1 = new ServiceController();
		ServiceController controller2 = new ServiceController();
		ServiceController controller3 = new ServiceController();
		ServiceController controller4 = new ServiceController();
		ServiceController controller5 = new ServiceController();
		ServiceController controller6 = new ServiceController();
		smtp email2 = new smtp();
		bool waserror = false;
		private System.Windows.Forms.Button button1;
		private bool ret;

		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
  
		public Form1()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			// 
			// Form1
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(292, 266);
			this.Name = "Form1";
			this.Text = "Form1";
			this.Load += new System.EventHandler(this.Form1_Load);

		}
		#endregion

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.Run(new Form1());
		}

		private void Form1_Load(object sender, System.EventArgs e)
		{
			
			//email settings that are not going to change
			email2.srv = "localhost";
			email2.from = "server@server.com";
			email2.name = "Server";
			email2.to = "Mike@MichaelEvanchik.com";
			email2.subj = "Backup.Net ERROR";

			//connect to the email server and make sure we have a connection before we mess with critical services and can report errors
			try
			{
				ret=email2.conn();
				if(ret == false)
				{
					email2.close();
				    waserror = true;
				}
			}
			catch(Exception ex)
			{
				email2.close();
				waserror = true;
			}




			if (waserror == false)
			{
				//start stopping services.  the code could be smarter here if services didnt stop correctly
				try
				{
					controller1.ServiceName = "MSExchangeIS";
					controller1.Stop();
					controller1.WaitForStatus(ServiceControllerStatus.Running);
				}
				catch(Exception ex)
				{
					waserror = true;
					email2.body = "Microsoft Exchange Information Store";
					email2.Mail();
				}


				try
				{
					controller2.ServiceName = "MSExchangeMGMT";
					controller2.Stop();
					controller2.WaitForStatus(ServiceControllerStatus.Running);
				}
				catch(Exception ex)
				{
					waserror = true;
					email2.body = "Microsoft Exchange Management";
					email2.Mail();
				}
  

				try
				{
					controller3.ServiceName = "MSExchangeMTA";
					controller3.Stop();
					controller3.WaitForStatus(ServiceControllerStatus.Running);
				}
				catch(Exception ex)
				{
					waserror = true;
					email2.body = "Microsoft Exchange MTA Stacks";
					email2.Mail();
				}


				try
				{
					controller4.ServiceName = "POP3Svc";
					controller4.Stop();
					controller4.WaitForStatus(ServiceControllerStatus.Running);
				}
				catch(Exception ex)
				{
					waserror = true;
					email2.body = "Microsoft Exchange POP3";
					email2.Mail();
				}


				try
				{
					controller5.ServiceName = "RESvc";
					controller5.Stop();
					controller5.WaitForStatus(ServiceControllerStatus.Running);}
				catch(Exception ex)
				{
					waserror = true;
					email2.body = "Microsoft Exchange Routing Engine";
					email2.Mail();
				}


				try
				{
					controller6.ServiceName = "MSExchangeSA";
					controller6.Stop();
					controller6.WaitForStatus(ServiceControllerStatus.Running);
				}


				catch(Exception ex)
				{
					waserror = true;
					email2.body = "Microsoft Exchange System Attendant";
					email2.Mail();
				}







                //ok now its safe to copy the files
				destPath = "D:\\fileshare\\asp\\priv1.edb";
				try
				{
					filePath = "D:\\Program Files\\Exchsrvr\\MDBDATA\\priv1.edb";
					File.Copy(filePath,destPath,true);
				}
				catch(Exception ex)
				{
					waserror = true;
					email2.body = "error coping priv1.edb";
					email2.Mail();
				}


				destPath = "D:\\fileshare\\asp\\priv1.stm";
				try
				{
					filePath = "D:\\Program Files\\Exchsrvr\\MDBDATA\\priv1.stm";
					File.Copy(filePath,destPath,true);
				}
				catch(Exception ex)
				{
					waserror = true;
					email2.body = "error coping priv1.stm";
					email2.Mail();
				}


				destPath = "D:\\fileshare\\asp\\pub1.edb";
				try
				{
					filePath = "D:\\Program Files\\Exchsrvr\\MDBDATA\\pub1.edb";
					File.Copy(filePath,destPath,true);
				}
				catch(Exception ex)
				{
					waserror = true;
					email2.body = "error coping pub1.edb";
					email2.Mail();
				}


				destPath = "D:\\fileshare\\asp\\pub1.stm";
				try
				{
					filePath = "D:\\Program Files\\Exchsrvr\\MDBDATA\\pub1.stm";
					File.Copy(filePath,destPath,true);
				}
				catch(Exception ex)
				{
					waserror = true;
					email2.body = "error coping pub1.stm";
					email2.Mail();
				}
            


                 //ok services can be started up again
				try
				{
					controller1.Start();
					controller2.Start();
					controller3.Start();
					controller4.Start();
					controller5.Start();
					controller6.Start();
				}
				catch
				{
					waserror = true;
					email2.body = "error starting services backup";
					email2.Mail();
				}


				if (waserror == false)
				{
					email2.subj = "Backup.Net SUCCESS";
					email2.body = "backup successful";
					email2.Mail();
				}


				email2.close();
				Application.Exit();
			}
		Application.Exit();
        }

	}
}
Shoe making is the process of making Foot Wear. Originally, shoes were made one at a time by hand. Traditional handicraft shoemaking has now been largely superseded in volume of shoes produced by industrial mass production of footwear, but not necessarily in quality, attention to detail, or craftsmanship.

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
M: Yes, they are.  Go look in the mirror and see for yourselves.

The human characteristics of curiosity, wonder, and ingenuity are as old as mankind. People around the world have been harnessing their curiosity into inquiry and the process of scientific methodology
Recent years have witnessed an unprecedented growth in research in the area of nanoscience. There is increasing optimism that nanotechnology applied to medicine and dentistry will bring significant advances in the diagnosis, treatment, and prevention of disease. Growing interest in the future medical applications of nanotechnology is leading to the emergence of a new field called nanomedicine. Nanomedicine needs to overcome the challenges for its application, to improve the understanding of pathophysiologic basis of disease, bring more sophisticated diagnostic opportunities, and yield more effective therapies and preventive properties. When doctors gain access to medical robots, they will be able to quickly cure most known diseases that hobble and kill people today, to rapidly repair most physical injuries our bodies can suffer, and to vastly extend the human health span. Molecular technology is destined to become the core technology underlying all of 21st century medicine and dentistry. In this article, we have made an attempt to have an early glimpse on future impact of nanotechnology in medicine and dentistry.Sun Dec  7 16:59:42 PST 2014
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
M: Yes, they are.  Go look in the mirror and see for yourselves.

The three then travel to Rebma, a reflection of Amber underwater, and there they meet their sister Llewella and Moire, the queen of Rebma. Moire originally believes that the three of them came to Rebma to seek support to defeat Eric but Deirdre explains their true intentions. Because Rebma is a reflection of Amber, there is also a reflection of the Pattern in Rebma, which Corwin is to walk to restore his memory. Although Random is impounded for past crimes in Rebma and sentenced to wed a blind girl named Vialle, Corwin convinces the queen Moire to allow him to walk the Pattern. After receiving advice from Random and Deirdre, he walks the Pattern, reliving all of his past life, which stretches back to his time in Amber and when Eric deposited him in Elizabethan England on our Earth. He remembers the powers which his heritage and the Pattern grant him - the power to walk through shadow, and to pronounce a powerful curse before dying. After he completes the Pattern he uses its power to project himself into the Castle of Amber, from which he finds a safe spot and rests.Wed Dec 10 10:13:15 PST 2014


The human characteristics of curiosity, wonder, and ingenuity are as old as mankind. People around the world have been harnessing their curiosity into inquiry and the process of scientific methodology
Recent years have witnessed an unprecedented growth in research in the area of nanoscience. There is increasing optimism that nanotechnology applied to medicine and dentistry will bring significant advances in the diagnosis, treatment, and prevention of disease. Growing interest in the future medical applications of nanotechnology is leading to the emergence of a new field called nanomedicine. Nanomedicine needs to overcome the challenges for its application, to improve the understanding of pathophysiologic basis of disease, bring more sophisticated diagnostic opportunities, and yield more effective therapies and preventive properties. When doctors gain access to medical robots, they will be able to quickly cure most known diseases that hobble and kill people today, to rapidly repair most physical injuries our bodies can suffer, and to vastly extend the human health span. Molecular technology is destined to become the core technology underlying all of 21st century medicine and dentistry. In this article, we have made an attempt to have an early glimpse on future impact of nanotechnology in medicine and dentistry.

The one absolutely unselfish friend that man can have in this selfish world, the one that never deserts him, the one that never proves ungrateful or treacherous is his dog. A man's dog stands by him in prosperity and in poverty, in health and in sickness. He will sleep on the cold ground, where the wintry winds blow and the snow drives fiercely, if only he may be near his master's side. He will kiss the hand that has no food to offer. He will lick the wounds and sores that come in encounters with the roughness of the world. He guards the sleep of his pauper master as if he were a prince. When all other friends desert, he remains. When riches take wings, and reputation falls to pieces, he is as constant in his love as the sun in its journey through the heavens. Modern Furniture designed for Modern Living.
Welcome To Viesso, we�re really glad you found us! We believe modern furniture isn�t just about the modern style. It�s also about products that appeal to and enhance our modern lives. Smart design, custom options, Green furniture materials, innovative functions. It all adds up to giving you exciting solutions for your space. We've scoured the marketplace to bring you home furnishings that meet these modern ideals.

Our exclusive Viesso line, which is American built, offers numerous options throughout the shopping process so that you can create custom furniture that is completely unique. It�s contemporary furniture only sold here. Designed by us. Customized by you. Choose size, finish, filling, and more!

All of our modern furniture products can be priced and purchased either in our Los Angeles showroom or online, where we encourage you to request material samples and ask us for advice in choosing the perfect pieces for your space. We hope you enjoy exploring our website, and please don�t hesitate to contact us with any questions along the way.