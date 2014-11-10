import java.util.*;
import javax.swing.*;

class age_calculator {

Boolean reply = true;

	public void display()
	{

    	GregorianCalendar now = new GregorianCalendar();

	while(reply) {

		int now_year=0,birth_year=0,years_old=0;
		String output;

		output = "In what year you are born?";

try
        {
		birth_year = Integer.parseInt
		    (JOptionPane.showInputDialog(null,output));
	 }

catch (final NumberFormatException e)
	         {

	            output = "Please type in a numerical value." + "\n"
	                  + "Try Again ";

	 		 	 JOptionPane.showMessageDialog(null,output,
	 		 	  "Error Report",JOptionPane.INFORMATION_MESSAGE);
	 		      System.exit(0);
   	      }

		now_year = now.get(GregorianCalendar.YEAR);
		years_old = now_year - birth_year;

		output = "This is the year you become "
		         + years_old + " years old.";
		JOptionPane.showMessageDialog(null,output);

       output = "Do you want to continue";
       int n = JOptionPane.showConfirmDialog(null,output,
               "Yes/No", JOptionPane.YES_NO_OPTION);
	             if (n == JOptionPane.NO_OPTION)
	             {
	          reply = false;
              System.out.print("\t Thank you for using this Program");
              System.out.print("\n\n");
              System.exit(0);
                }
        }
     }
}

	class age {

		public static void main(String[] args)
		{

        age_calculator person = new age_calculator();

        person.display();
   }
}
“The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).