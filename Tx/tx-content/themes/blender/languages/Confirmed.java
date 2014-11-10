import java.awt.*;
import java.awt.event.*;

public class Confirmed extends Frame 
{         
	Confirmed()
    {
		addWindowListener(new W());
	}
	class W extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			setVisible(false);
			//dispose();
			System.exit(0);
			
		}
	}
}

85% of millennials say they prefer urban-style living, and 68 percent of college-educated 25- to 34-year-olds say, first, they look for the place they want to live, then they look for a job.