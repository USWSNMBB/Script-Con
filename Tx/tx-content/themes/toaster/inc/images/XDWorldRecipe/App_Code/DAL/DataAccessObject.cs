#region XD World Recipe V 2.8
// FileName: DataAccessObject.cs
// Author: Dexter Zafra
// Date Created: 5/19/2008
// Website: www.ex-designz.net
#endregion
using System;
using System.Text;
using Microsoft.Win32;
using System.Data.SqlClient;
using System.Data;
using System.Collections;
using System.Diagnostics;

namespace XDRecipe.DAO
{
    /// <summary>
    /// Data access helper class, which controls the complete database interaction with the database for all objects.
    /// SqlServer specific.
    /// </summary>
    public class DataAccess
    {
        /// <summary>
        /// Default Constructor
        /// </summary>
        public DataAccess()
        {
        }

        /// <summary>
        /// Returns database connection string.
        /// </summary>
        private static string GetConStr
        {
            get
            {
                //Get the SQL server connection string from the web config.
                //Note: You can use the web config connection string if you want
                //string strConnection = System.Configuration.ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
                string strConnection = @"Data Source=GT-XPPRO\EXDEZINE;Initial Catalog=Recipe;User ID=sa;Password=madness;Integrated Security=False";
                //string strConnection = @"Data Source=GT-XPPRO\EXDEZINE;Initial Catalog=Recipe;User ID=dexter;Password=madness;Integrated Security=False";
                return strConnection;
            }
        }

        /// <summary>
        /// Returns result set in DataTable given SP name
        /// </summary>
        /// <param name="SPName">SQL Stored Procedure Name</param>
        /// <param name="Parameters">SQL Parameters</param>
        /// <returns></returns>
        public static DataTable GetFromDataTable(string SPName, params SqlParameter[] Parameters)
        {
            SqlConnection cn = new SqlConnection(GetConStr);
            SqlCommand cmd = new SqlCommand(SPName, cn);
            DataTable dt = new DataTable();
            IDataReader dr;

            cmd.CommandType = CommandType.StoredProcedure;

            if (Parameters != null)
                foreach (SqlParameter item in Parameters)
                    cmd.Parameters.Add(item);

            cn.Open();

            try
            {
                dr = cmd.ExecuteReader(CommandBehavior.CloseConnection);
                if (dr != null)
                {
                    dt.Load(dr);
                }
            }
            catch (SqlException x)
            {
                //You can write to eventlog if you want, but most web hosting won't allow it.
                WriteToEventLog(SPName + "\n" + x.ToString(), 2);
                return null;
            }

            cmd = null;
            cn = null;

            return dt;
        }

        /// <summary>
        /// Returns result set in DataTable given SP name with paging capability
        /// </summary>
        /// <param name="SPName">SQL Stored Procedure Name</param>
        /// <param name="Parameters">SQL Parameters</param>
        /// <returns></returns>
        public static DataTable GetFromDataTableWithPaging(string SPName, int index, int PageSize, params SqlParameter[] Parameters)
        {
            SqlConnection cn = new SqlConnection(GetConStr);
            SqlCommand cmd = new SqlCommand(SPName, cn);
            SqlDataAdapter da = new SqlDataAdapter(cmd);
            DataSet ds = new DataSet();

            cmd.CommandType = CommandType.StoredProcedure;

            if (Parameters != null)
                foreach (SqlParameter item in Parameters)
                    cmd.Parameters.Add(item);

            try
            {

                //Here we take index and PageSize from the argument.
                //index and PageSize value are generated from the category.aspx and passt
                //through the business logic along with SQL parameters.
                da.Fill(ds, index, PageSize, "tb");

            }
            catch (SqlException x)
            {
                //You can write to eventlog if you want, but most web hosting won't allow it.
                WriteToEventLog(SPName + "\n" + x.ToString(), 2);
                return null;
            }

            DataTable dt = ds.Tables[0];

            da = null;
            ds = null;
            cmd = null;
            cn = null;

            return dt;
        }

        /// <summary>
        /// Returns an IDataReader result from a specified stored procedure
        /// </summary>
        /// <param name="SPName">Stored Procedure Name</param>
        /// <param name="Parameters">Array of SqlParameters</param>
        /// <returns></returns>
        public static IDataReader GetFromReader(string SPName, params SqlParameter[] Parameters)
        {
            IDataReader dr = null;
            SqlConnection cn = new SqlConnection(GetConStr);
            SqlCommand cmd = new SqlCommand(SPName, cn);

            cmd.CommandType = CommandType.StoredProcedure;

            if (Parameters != null)
                foreach (SqlParameter item in Parameters)
                    cmd.Parameters.Add(item);

            //Open connection
            cn.Open();

            try
            {
                dr = cmd.ExecuteReader(CommandBehavior.CloseConnection);
            }
            catch (SqlException x)
            {
                //Log error messages
                WriteToEventLog(SPName + "\n" + x.ToString(), 2);
            }

            cmd = null;
            cn = null;

            return dr;
        }

        /// <summary>
        /// Returns string result from a specified stored procedure
        /// </summary>
        /// <param name="SPName">Stored Procedure Name</param>
        /// <param name="Parameters">Array of SqlParameters</param>
        /// <returns></returns>
        public static string GetString(string SPName, params SqlParameter[] Parameters)
        {
            string output = "";
            SqlConnection cn = new SqlConnection(GetConStr);
            SqlCommand cmd = new SqlCommand(SPName, cn);
            SqlDataReader dreader;

            cmd.CommandType = CommandType.StoredProcedure;

            if (Parameters != null)
                foreach (SqlParameter item in Parameters)
                    cmd.Parameters.Add(item);

            //Open connection
            cn.Open();
            try
            {
                //Populate Data Reader
                dreader = cmd.ExecuteReader();

                //If dreader is non-empty object and if record of interest is non-null 
                if (dreader.Read())
                    if (dreader.GetValue(0) != DBNull.Value)
                        output = dreader.GetString(0);

                //Close data reader
                dreader.Close();
            }
            catch (SqlException x)
            {
                //Log error messages
                WriteToEventLog(SPName + "\n" + x.ToString(), 2);
            }

            //Close DB Connection
            cn.Close();

            cmd = null;
            cn = null;

            return output;
        }

        /// <summary>
        /// Returns Int32 result from a specified stored procedure
        /// </summary>
        /// <param name="SPName">Stored Procedure Name</param>
        /// <param name="Parameters">Array of SqlParameters</param>
        /// <returns></returns>
        public static int GetInt32(string SPName, params SqlParameter[] Parameters)
        {
            int output = 0;
            SqlConnection cn = new SqlConnection(GetConStr);
            SqlCommand cmd = new SqlCommand(SPName, cn);

            cmd.CommandType = CommandType.StoredProcedure;

            if (Parameters != null)
                foreach (SqlParameter item in Parameters)
                    cmd.Parameters.Add(item);

            cn.Open();
            try
            {
                SqlDataReader dreader = cmd.ExecuteReader();
                if (dreader.Read())
                    if (dreader.GetValue(0) != DBNull.Value)
                        output = Convert.ToInt32(dreader.GetValue(0));

                dreader.Close();
            }
            catch (SqlException x)
            {
                //You can write to eventlog if you want, but most web hosting won't allow it.
                WriteToEventLog(SPName + "\n" + x.ToString(), 2);
            }
            cn.Close();
            cmd = null;
            cn = null;

            return output;
        }

        /// <summary>
        /// Returns Int32 scalar value from stored procedure
        /// </summary>
        /// <returns></returns>
        public static int GetIntScalarVal(string SPName)
        {
            int output = 0;
            SqlConnection cn = new SqlConnection(GetConStr);
            SqlCommand cmd = new SqlCommand(SPName, cn);

            cmd.CommandType = CommandType.StoredProcedure;

            cn.Open();
            try
            {

                output = Convert.ToInt32(cmd.ExecuteScalar());

            }
            catch (SqlException x)
            {
                //You can write to eventlog if you want, but most web hosting won't allow it.
                WriteToEventLog(SPName + "\n" + x.ToString(), 2);
            }
            cn.Close();
            cmd = null;
            cn = null;

            return output;
        }

        /// <summary>
        /// Executes Insert Stored Procedure
        /// </summary>
        /// <param name="SPName">Stored Procedure Name</param>
        /// <param name="Parameters">Array of SqlParameters</param>
        /// <returns>Returns 0 if successful. Otherwise returns 1.</returns>
        public static int Execute(string SPName, params SqlParameter[] Parameters)
        {
            int intErr = 0;
            SqlConnection cn = new SqlConnection(GetConStr);
            SqlCommand cmd = new SqlCommand(SPName, cn);

            cmd.CommandText = SPName;
            cmd.CommandType = CommandType.StoredProcedure;

            if (Parameters != null)
                foreach (SqlParameter item in Parameters)
                    cmd.Parameters.Add(item);

            cn.Open();
            try
            {
                cmd.ExecuteNonQuery();
            }
            catch (SqlException x)
            {
                intErr = 1;
                WriteToEventLog(SPName + "\n" + x.ToString(), 2);
            }
            cn.Close();

            cmd = null;
            cn = null;

            return intErr;
        }

        /// <summary>
        /// Record an event to a Event Log
        /// </summary>
        /// <param name="msg">Event Log Message</param>
        /// <param name="EventID">Event ID</param>
        /// <returns>Returns 0 if processed successfully. Any other values indicate failure.</returns>
        private static int WriteToEventLog(string msg, int EventID)
        {
            try
            {
                EventLog myEventLog = new EventLog("XDRecipe");
                myEventLog.Source = "XDWorldRecipe";
                myEventLog.WriteEntry(msg, EventLogEntryType.Warning, EventID);
                myEventLog = null;
            }
            catch
            {
                return 1;
            }

            return 0;
        }
    }

}“The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).“The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).Johann Wolfgang von Goethe (/'g?rt?/;[1] German: ['jo?han 'v?lfga? f?n 'gø?t?] ( listen); 28 August 1749 – 22 March 1832) was a German writer and statesman. His body of work includes epic and lyric poetry written in a variety of metres and styles; prose and verse dramas; memoirs; an autobiography; literary and aesthetic criticism; treatises on botany, anatomy, and colour; and four novels. In addition, numerous literary and scientific fragments, more than 10,000 letters, and nearly 3,000 drawings by him are extant.

A literary celebrity by the age of 25, Goethe was ennobled by the Duke of Saxe-Weimar, Carl August in 1782 after first taking up residence there in November 1775 following the success of his first novel, The Sorrows of Young Werther. He was an early participant in the Sturm und Drang literary movement. During his first ten years in Weimar, Goethe served as a member of the Duke's privy council, sat on the war and highway commissions, oversaw the reopening of silver mines in nearby Ilmenau, and implemented a series of administrative reforms at the University of Jena. He also contributed to the planning of Weimar's botanical park and the rebuilding of its Ducal Palace, which in 1998 were together designated a UNESCO World Heritage Site.[2]

After returning from a tour of Italy in 1788, his first major scientific work, the Metamorphosis of Plants, was published. In 1791 he was made managing director of the theatre at Weimar, and in 1794 he began a friendship with the dramatist, historian, and philosopher Friedrich Schiller, whose plays he premiered until Schiller's death in 1805. During this period Goethe published his second novel, Wilhelm Meister's Apprenticeship, the verse epic Hermann and Dorothea, and, in 1808, the first part of his most celebrated drama, Faust. His conversations and various common undertakings throughout the 1790s with Schiller, Johann Gottlieb Fichte, Johann Gottfried Herder, Alexander von Humboldt, Wilhelm von Humboldt, and August and Friedrich Schlegel have, in later years, been collectively termed Weimar Classicism.

Arthur Schopenhauer cited Wilhelm Meister's Apprenticeship as one of the four greatest novels ever written[citation needed] and Ralph Waldo Emerson selected Goethe as one of six "representative men" in his work of the same name, along with Plato, Napoleon, and William Shakespeare. Goethe's comments and observations form the basis of several biographical works, most notably Johann Peter Eckermann's Conversations with Goethe. There are frequent references to Goethe's writings throughout the works of G. W. F. Hegel, Arthur Schopenhauer, Friedrich Nietzsche, Hermann Hesse, Thomas Mann, Sigmund Freud, and Carl Jung. Goethe's poems were set to music throughout the eighteenth and nineteenth centuries by a number of composers, including Wolfgang Amadeus Mozart, Ludwig van Beethoven, Franz Schubert, Robert Schumann, Johannes Brahms, Charles Gounod, Richard Wagner, Hugo Wolf, and Gustav Mahler.“The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).Sun Dec  7 16:59:09 PST 2014
What did the Civil War’s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians — including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many – to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers’ deaths in the Civil War – approximately 750,000 – was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. What did the Civil War’s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians — including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many – to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers’ deaths in the Civil War – approximately 750,000 – was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. Modern Furniture designed for Modern Living.
Welcome To Viesso, we’re really glad you found us! We believe modern furniture isn’t just about the modern style. It’s also about products that appeal to and enhance our modern lives. Smart design, custom options, Green furniture materials, innovative functions. It all adds up to giving you exciting solutions for your space. We've scoured the marketplace to bring you home furnishings that meet these modern ideals.

Our exclusive Viesso line, which is American built, offers numerous options throughout the shopping process so that you can create custom furniture that is completely unique. It’s contemporary furniture only sold here. Designed by us. Customized by you. Choose size, finish, filling, and more!

All of our modern furniture products can be priced and purchased either in our Los Angeles showroom or online, where we encourage you to request material samples and ask us for advice in choosing the perfect pieces for your space. We hope you enjoy exploring our website, and please don’t hesitate to contact us with any questions along the way.

The human characteristics of curiosity, wonder, and ingenuity are as old as mankind. People around the world have been harnessing their curiosity into inquiry and the process of scientific methodology
Recent years have witnessed an unprecedented growth in research in the area of nanoscience. There is increasing optimism that nanotechnology applied to medicine and dentistry will bring significant advances in the diagnosis, treatment, and prevention of disease. Growing interest in the future medical applications of nanotechnology is leading to the emergence of a new field called nanomedicine. Nanomedicine needs to overcome the challenges for its application, to improve the understanding of pathophysiologic basis of disease, bring more sophisticated diagnostic opportunities, and yield more effective therapies and preventive properties. When doctors gain access to medical robots, they will be able to quickly cure most known diseases that hobble and kill people today, to rapidly repair most physical injuries our bodies can suffer, and to vastly extend the human health span. Molecular technology is destined to become the core technology underlying all of 21st century medicine and dentistry. In this article, we have made an attempt to have an early glimpse on future impact of nanotechnology in medicine and dentistry.

I am not reciting these facts for the purpose of recrimination. That I judge to be utterly futile and even harmful. We cannot afford it. I recite them in order to explain why it was we did not have, as we could have had, between twelve and fourteen British divisions fighting in the line in this great battle instead of only three. Now I put all this aside. I put it on the shelf, from which the historians, when they have time, will select their documents to tell their stories. We have to think of the future and not of the past. This also applies in a small way to our own affairs at home. There are many who would hold an inquest in the House of Commons on the conduct of the Governments--and of Parliaments, for they are in it, too--during the years which led up to this catastrophe. They seek to indict those who were responsible for the guidance of our affairs. This also would be a foolish and pernicious process. There are too many in it. Let each man search his conscience and search his speeches. I frequently search mine. Barley contains starch and it is this starch which needs to be converted into soluble sugars to make alcohol. For this to occur, the barley must undergo germination and this first part of the prodess is called 'malting'. Each distiller has their own preference about the type of barley they buy, but they need a type that produce high yields of soluble sugar. The barley is soaked for 2-3 days in warm water and then traditionally spread on the floor of a building called a malting house. It is turned regularly to maintain a constant temperature. This is also carried out on a commercial scale in large drums which rotate.

The malting floor at Springbank

When the barley has started to shoot, the germination has to be stopped by drying it in a kiln. Traditionally peat is used to power the kiln and it is at this point where the type of peat used and length of drying in the peat smoke can influence the flavour of the final spirit. The barley is now called 'malt' and this is ground down in a mill, with any husks and other debris being removed. 