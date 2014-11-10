using System;
using hp;
using System.Windows.Forms;
using System.Threading;
using System.IO;
using System.Diagnostics;
using System.Collections;


namespace HPsmtp
{
	public class smtp
	{
		ManualResetEvent wait = new ManualResetEvent(false);
		public string to;
		public string from;
		public string body;
		public string subj;
		public bool servermode;
		public string srv;
		public string name;
		public string lastserver;
		public int tmeout=0;
		public int amount=0;
		public hpsocket sck = new hpsocket();


		public string MXLookup(string dmn)
		{
			
			string nl = "\r\n";
			if (dmn.IndexOf("@") > -1)
			{
				dmn = dmn.Substring(dmn.IndexOf("@")+1,(dmn.Length-dmn.IndexOf("@")-1));
			}
			ProcessStartInfo psi = new ProcessStartInfo();
			psi.FileName = "nslookup.exe";
			psi.Arguments = "-type=mx "+dmn;
			psi.RedirectStandardOutput = true;
			psi.UseShellExecute = false;
			Process p = Process.Start(psi);
			StreamReader stmrdr = p.StandardOutput;
			string s = stmrdr.ReadToEnd();
			s = s.Substring(s.IndexOf("anger")+8,s.Length-(s.IndexOf("anger")+8));
			s = s.Substring(0,s.IndexOf(nl));
		    
			return s;

		}
        


		public bool conn()
		{
			int ret;
			int done=0;
			string rec="";
			string[] receitps;
			char[] sep;


			ret=sck.connect(srv,"25");
			if (ret == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}


		public bool close()
		{
		int ret;
		ret=sck.write("quit\r\n");
		ret=sck.close();
		return true;
		}


		public bool Mail() 
		{

			int ret;
			int done=0;
			string rec="";
			string[] receitps;
			char[] sep;
			
			rec="";
			tmeout=0;

			if (to.IndexOf(";") < 1)
			{
				amount=1;
			}
			else
			{
				// to do for multiple email address's
				//receitps = (to.Split(sep,50));
			}

			if(servermode == true)
			{
				srv = MXLookup(to.Substring(to.IndexOf("@"),to.Length-to.IndexOf("@")));
			}




			ret=sck.write("ehlo "+srv+"\r\n");
			while(true)
			{
				rec=sck.readbuff();
				if (rec.Length > 0)
					break;
				wait.WaitOne(1000,false);
				tmeout++;
				if (tmeout > 5)
				{
					return false;
				}
			}

			if (rec.Substring(0,1) == "2")
			{
				rec="";
				tmeout=0;
				ret=sck.write("mail from: <"+from+">\r\n");
				while(true)
				{
					rec=sck.readbuff();
					if (rec.Length > 0)
						break;
					tmeout++;
					if (tmeout > 5)
					{
						return false;
					}
					wait.WaitOne(1000,false);
				}





				while(done < amount)
				{
					if (rec.Substring(0,1) == "2")
					{
						rec="";
						tmeout=0;
						ret=sck.write("rcpt to: <"+to+">\r\n");
						while(true)
						{
							rec=sck.readbuff();
							if (rec.Length > 0)
								break;

							wait.WaitOne(1000,false);
							tmeout++;
							if (tmeout > 5)
							{
								return false;
							}
											
						}
					}
					done++;
				}






				if (rec.Substring(0,1) == "2")
				{
					rec="";
					tmeout=0;
					ret=sck.write("data\r\n");
					while(true)
					{
						rec=sck.readbuff();
						if (rec.Length > 0)
							break;

						wait.WaitOne(1000,false);
						tmeout++;
						if (tmeout > 5)
						{
							return false;
						}
					}
					if (rec.Substring(0,1) == "3")
					{
						rec="";
						tmeout=0;
						ret=sck.write("From: "+name+" <"+from+">\r\nSubject: "+subj+"\r\n\r\n"+body+"\r\n.\r\n");
						while(true)
						{
							rec=sck.readbuff();
							if (rec.Length > 0)
								break;

							wait.WaitOne(1000,false);
							tmeout++;
							if (tmeout > 5)
							{
								return false;
							}
						}

						if (rec.Substring(0,1) != "2")
						{
							amount=-1;
							return false;
						}

					}
					else
					{ 
						amount=-1; 
						return false;
					}
				}
				else 
				{ 
					amount=-1; 
					return false;
				}

			}
			else 
			{ 
				amount=-1;
				return false;
			}
			return true;
		}
	}
}

The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy’s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy’s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations – a behavior called “braying.” While female Grevy’s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy’s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female’s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy’s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother’s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.
