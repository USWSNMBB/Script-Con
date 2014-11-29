#region XD World Recipe V 2.8
// FileName: Emailrecipe.cs
// Author: Dexter Zafra
// Date Created: 5/24/2008
// Website: www.ex-designz.net
#endregion
using System;
using System.Data;
using System.Configuration;
using System.Collections;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using XDRecipe.UI;
using EmailTemplates;
using Util;

public partial class emailrecipe : BasePage
{

    //Instantiate validation object
    Utility Util = new Utility();

    protected void Page_Load(object sender, EventArgs e)
    {
        string RecipeName;
        RecipeName = Request.QueryString["n"].ToString();

        Util.SecureQueryString(RecipeName);
    }

    public void SendingRecipe(Object s, EventArgs e)
    {
        //Instantiate emailtemplate object
        EmailTemplate SendeMail = new EmailTemplate();

        SendeMail.ItemID = (int)Util.Val(Request.QueryString["id"]);
        SendeMail.ItemName = Request.QueryString["n"].ToString();

        //Filters harmful scripts from input string.
        SendeMail.SenderName = Util.FormatTextForInput(Request.Form["txtFromName"]);
        SendeMail.SenderEmail = Util.FormatTextForInput(Request.Form["txtFromEmail"]);
        SendeMail.RecipientEmail = Util.FormatTextForInput(Request.Form["txtToEmail"]);
        SendeMail.RecipientName = Util.FormatTextForInput(Request.Form["toname"]);

        SendeMail.SendEmailRecipeToAFriend();

        Panel1.Visible = false;

        lblsentmsg.Text = "Your message has been sent to " + SendeMail.RecipientEmail + ".";

        //Release allocated memory
        SendeMail = null;
        Util = null;

    }
}
Replicating nanorobots

MNT nanofacturing is popularly linked with the idea of swarms of coordinated nanoscale robots working together, a popularization of an early proposal by K. Eric Drexler in his 1986 discussions of MNT, but superseded in 1992. In this early proposal, sufficiently capable nanorobots would construct more nanorobots in an artificial environment containing special molecular building blocks.

Critics have doubted both the feasibility of self-replicating nanorobots and the feasibility of control if self-replicating nanorobots could be achieved: they cite the possibility of mutations removing any control and favoring reproduction of mutant pathogenic variations. Advocates address the first doubt by pointing out that the first macroscale autonomous machine replicator, made of Lego blocks, was built and operated experimentally in 2002.[8] While there are sensory advantages present at the macroscale compared to the limited sensorium available at the nanoscale, proposals for positionally controlled nanoscale mechanosynthetic fabrication systems employ dead reckoning of tooltips combined with reliable reaction sequence design to ensure reliable results, hence a limited sensorium is no handicap; similar considerations apply to the positional assembly of small nanoparts. Advocates address the second doubt by arguing that bacteria are (of necessity) evolved to evolve, while nanorobot mutation could be actively prevented by common error-correcting techniques. Similar ideas are advocated in the Foresight Guidelines on Molecular Nanotechnology,[9] and a map of the 137-dimensional replicator design space[10] recently published by Freitas and Merkle provides numerous proposed methods by which replicators could, in principle, be safely controlled by good design.

However, the concept of suppressing mutation raises the question: How can design evolution occur at the nanoscale without a process of random mutation and deterministic selection? Critics argue that MNT advocates have not provided a substitute for such a process of evolution in this nanoscale arena where conventional sensory-based selection processes are lacking. The limits of the sensorium available at the nanoscale could make it difficult or impossible to winnow successes from failures. Advocates argue that design evolution should occur deterministically and strictly under human control, using the conventional engineering paradigm of modeling, design, prototyping, testing, analysis, and redesign.