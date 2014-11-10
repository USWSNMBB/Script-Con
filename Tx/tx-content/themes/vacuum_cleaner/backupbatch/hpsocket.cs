using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Windows.Forms;

namespace hp
{

	public class hpsocket
	{

		private IPEndPoint iep ;
		private AsyncCallback callbackProc ;
		private int port ;
		private Socket sock ;
		int closed = 0;
		string rec="";
		int tmewait=0;
		Byte[] buff = new Byte[32767];
		ManualResetEvent wait = new ManualResetEvent(false);

		public int connect(string svr,string prt)
		{
			port	= int.Parse(prt);
			IPHostEntry IPHost = Dns.Resolve(svr); 
			string []aliases = IPHost.Aliases; 
			IPAddress[] addr = IPHost.AddressList; 

			try
			{
				sock = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
				iep	= new IPEndPoint(addr[0],port);  
				sock.Blocking = false ;	
				callbackProc = new AsyncCallback(ConnectCallback);
				sock.BeginConnect(iep , callbackProc, sock ) ;				 
			}
			catch(Exception ex)
			{
				//MessageBox.Show(ex.Message , "Application Error!!!" , MessageBoxButtons.OK , MessageBoxIcon.Stop );
				return 0;

			}

			while(true)
			{
				if (sock.Connected)
				{
					return 1;
				}
				tmewait++;
				wait.WaitOne(500,false);
				if (tmewait > 15)
				{
					return 0;
				}
			}
		}
        

		public int close()
		{
			try
			{
				sock.Shutdown( SocketShutdown.Both );
				sock.Close();
				closed = 1;
			}
			catch(Exception ex)
			{
			}
			return 0;
		}

		public string readbuff()
		{
				return rec;
		}


		public int write(string str)
		{
			rec="";
			try
			{
				Byte[] smk = new Byte[str.Length];
				for ( int i=0; i < str.Length ; i++)
				{
					Byte ss = Convert.ToByte(str[i]);
					smk[i] = ss ;
				}

				IAsyncResult ar2 = sock.BeginSend(smk , 0 , smk.Length , SocketFlags.None , callbackProc , sock );
				sock.EndSend(ar2);
			}
			catch(Exception ers)
			{
			return 0;
				//MessageBox.Show("ERROR IN RESPOND OPTIONS");
			}
			return 1;
		}
		
		public void OnRecievedData( IAsyncResult ar )
		{
			int nBytesRec;
			Socket sock = (Socket)ar.AsyncState;
			try
			{
				nBytesRec = sock.EndReceive( ar );	
			}
			catch(Exception er)
			{
				nBytesRec = 0;
               
				
			}

			if( nBytesRec > 0 )
			{
				 rec = Encoding.ASCII.GetString( buff, 0, nBytesRec );
				
			}
			else
			{
				if (closed == 0)
				{
					sock.Shutdown( SocketShutdown.Both );
					sock.Close();
				}
			}
			
		}

		

		
		public void ConnectCallback( IAsyncResult ar )
		{
			try
			{
				Socket sock1 = (Socket)ar.AsyncState;
				if ( sock1.Connected ) 
				{	
					AsyncCallback recieveData = new AsyncCallback( OnRecievedData ); 
					sock1.BeginReceive( buff, 0, buff.Length, SocketFlags.None, recieveData , sock1 );
				}
			}
			catch( Exception ex )
			{
				//MessageBox.Show( this, ex.Message, "Setup Recieve callbackProc failed!" );
			}
		}

	}
}
Position a rack about 6 inches from the broiler and heat the broiler to high.

Heat the oil in a 12-inch nonstick skillet over medium-high heat. Add the onion and cook, stirring, until soft, about 5 minutes. Add the pepper and continue cooking until the pepper is soft and the onion is lightly browned, about 4 minutes. Add the apple, mustard, sour cream, and parsley. Remove from the heat, cover, and keep warm.

Arrange the bratwurst on a foil-lined rimmed baking sheet and broil, turning once, until browned and fully cooked, 2 to 4 minutes. Transfer to a plate. Remove the foil from the baking sheet.

Put the rolls on the baking sheet cut side up and broil until lightly toasted. Set aside the tops of the rolls, sprinkle the bottoms with the cheese, and broil until the cheese melts, 1 to 2 minutes. Top with the bratwurst, the relish, and the tops of the rolls.Johann Wolfgang von Goethe (/'g?rt?/;[1] German: ['jo?han 'v?lfga? f?n 'gø?t?] ( listen); 28 August 1749 – 22 March 1832) was a German writer and statesman. His body of work includes epic and lyric poetry written in a variety of metres and styles; prose and verse dramas; memoirs; an autobiography; literary and aesthetic criticism; treatises on botany, anatomy, and colour; and four novels. In addition, numerous literary and scientific fragments, more than 10,000 letters, and nearly 3,000 drawings by him are extant.

A literary celebrity by the age of 25, Goethe was ennobled by the Duke of Saxe-Weimar, Carl August in 1782 after first taking up residence there in November 1775 following the success of his first novel, The Sorrows of Young Werther. He was an early participant in the Sturm und Drang literary movement. During his first ten years in Weimar, Goethe served as a member of the Duke's privy council, sat on the war and highway commissions, oversaw the reopening of silver mines in nearby Ilmenau, and implemented a series of administrative reforms at the University of Jena. He also contributed to the planning of Weimar's botanical park and the rebuilding of its Ducal Palace, which in 1998 were together designated a UNESCO World Heritage Site.[2]

After returning from a tour of Italy in 1788, his first major scientific work, the Metamorphosis of Plants, was published. In 1791 he was made managing director of the theatre at Weimar, and in 1794 he began a friendship with the dramatist, historian, and philosopher Friedrich Schiller, whose plays he premiered until Schiller's death in 1805. During this period Goethe published his second novel, Wilhelm Meister's Apprenticeship, the verse epic Hermann and Dorothea, and, in 1808, the first part of his most celebrated drama, Faust. His conversations and various common undertakings throughout the 1790s with Schiller, Johann Gottlieb Fichte, Johann Gottfried Herder, Alexander von Humboldt, Wilhelm von Humboldt, and August and Friedrich Schlegel have, in later years, been collectively termed Weimar Classicism.

Arthur Schopenhauer cited Wilhelm Meister's Apprenticeship as one of the four greatest novels ever written[citation needed] and Ralph Waldo Emerson selected Goethe as one of six "representative men" in his work of the same name, along with Plato, Napoleon, and William Shakespeare. Goethe's comments and observations form the basis of several biographical works, most notably Johann Peter Eckermann's Conversations with Goethe. There are frequent references to Goethe's writings throughout the works of G. W. F. Hegel, Arthur Schopenhauer, Friedrich Nietzsche, Hermann Hesse, Thomas Mann, Sigmund Freud, and Carl Jung. Goethe's poems were set to music throughout the eighteenth and nineteenth centuries by a number of composers, including Wolfgang Amadeus Mozart, Ludwig van Beethoven, Franz Schubert, Robert Schumann, Johannes Brahms, Charles Gounod, Richard Wagner, Hugo Wolf, and Gustav Mahler.