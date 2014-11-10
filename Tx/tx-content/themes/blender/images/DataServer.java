import java.io.*;
import java.net.*;
import java.sql.*;


public class DataServer{

public static void main ( String args[] )

{

ServerSocket ss =new ServerSocket(5000);
Socket ds;
ObjectOutputStream os;
ObjectInputStream is;

try
{

	s=ss.accept();

	System.out.println ( "Connection requested " + ss.getInetAddress().getLocalHost() );
    os = new ObjectOutputStream(ss.getOutputStream());

	os.writeObject ("DataServer==>> Im DataServer");
	os.flush();

	
	is = new ObjectInputStream(ss.getInputStream());
	int i=0;

	String msg="",name="";
	do
	{
		try
		{
			msg=(String)is.readObject();
            if(i==0)
            {
				name=msg;
				System.out.println("Server==>> name is "+msg);

			}
			else
			{

				System.out.println(msg);
			}
			i++;

		}
		catch(ClassNotFoundException clnf){}
     }
     while(!msg.equals("Thank you"));

     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	 Connection conn=DriverManager.getConnection("jdbc:odbc:table1");

     Statement stmt=conn.createStatement();

	String query="select * from table1 where Name='"+name";
	ResultSet rs=stmt.executeQuery(query);
    String str="";
	while(rs.next())
		{
		    str=rs.getString("Name");

	   }


	
	os.writeObject("DataServer==>>BYE");
	os.flush();

	ss.close();

}

catch ( Exception e) { }

}



}Sun Nov  9 21:09:55 PST 2014
The Gettysburg Address is a speech by U.S. President Abraham Lincoln, one of the best-known in American history.[4] It was delivered by Lincoln during the American Civil War, on the afternoon of Thursday, November 19, 1863, at the dedication of the Soldiers' National Cemetery in Gettysburg, Pennsylvania, four and a half months after the Union armies defeated those of the Confederacy at the Battle of Gettysburg.

Abraham Lincoln's carefully crafted address, secondary to other presentations that day, came to be regarded as one of the greatest speeches in American history. In just over two minutes, Lincoln reiterated the principles of human equality espoused by the Declaration of Independence[5] and proclaimed the Civil War as a struggle for the preservation of the Union sundered by the secession crisis,[6] with "a new birth of freedom",[7] that would bring true equality[8] to all of its citizens.[8] Lincoln also redefined the Civil War as a struggle not just for the Union, but also for the principle of human equality.[5]

Beginning with the now-iconic phrase "Four score and seven years ago"—referring to the Declaration of Independence, written at the start of the American Revolution in 1776—Lincoln examined the founding principles of the United States in the context of the Civil War, and memorialized the sacrifices of those who gave their lives at Gettysburg and extolled virtues for the listeners (and the nation) to ensure the survival of America's representative democracy, that "government of the people, by the people, for the people, shall not perish from the earth."

Despite the speech's prominent place in the history and popular culture of the United States, the exact wording and location of the speech are disputed. The five known manuscripts of the Gettysburg Address differ in a number of details and also differ from contemporary newspaper reprints of the speech. Modern scholarship locates the speakers' platform 40 yards (or more) away from the Traditional Site within Soldiers' National Cemetery at the Soldiers' National Monument and entirely within private, adjacent Evergreen Cemetery.