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

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).