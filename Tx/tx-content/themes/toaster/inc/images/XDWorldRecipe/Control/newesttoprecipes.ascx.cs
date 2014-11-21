#region XD World Recipe V 2.8
// FileName: newesttoprecipes.ascx.cs
// Author: Dexter Zafra
// Date Created: 5/19/2008
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
using System.Data.SqlClient;
using XDRecipe.Model.RandomRecipe;
using GetNewestRecipeSideMenu;
using GetPopularRecipeSideMenu;
using XDRecipe.Model;
using BLL;
using Util;
using CacheData;

public partial class newesttoprecipes : System.Web.UI.UserControl
{
    protected void Page_Load(object sender, EventArgs e)
    {
        //Instantiate object
        Blogic myBL = new Blogic();

        //Instantiate object
        Utility Util = new Utility();

        int CatId = (int)Util.Val(Request.QueryString["catid"]);

        //Display random recipe
        GetRandomrecipe(CatId);

        //Get the newest 10 recipes. Change the value to 5 if you want to display only 5 newest recipes.
        int TopNewest = 10;

        //Get the 15 popular recipes. Change the value to 5 or 10 if you want to display only 5/10 most popular recipes.
        int TopPopular = 15;

        NewestRecipeSideMenuProvider GetNewestRecipe = new NewestRecipeSideMenuProvider(CatId, TopNewest);
        PopularRecipeSideMenuProvider GetPopularRecipe = new PopularRecipeSideMenuProvider(CatId, TopPopular);

        string CategoryName = GetNewestRecipe.Category;

        if (!string.IsNullOrEmpty(this.Request.QueryString["catid"]))
        {
            lblcatname.Text = CategoryName.ToString() + "&nbsp;";
            lblcatnamepop.Text = CategoryName.ToString() + "&nbsp;";
            lblrancatname.Text = CategoryName.ToString() + "&nbsp;";
            lbTopCnt.Text = TopNewest.ToString();
        }
        else
        {
            lblcatname.Text = "";
            lbTopCnt.Text = TopNewest.ToString();
        }

        RecipeNew.DataSource = GetNewestRecipe.GetNewestRecipe();
        RecipeNew.DataBind();

        TopRecipe.DataSource = GetPopularRecipe.GetPopularRecipe();
        TopRecipe.DataBind();

        if (!string.IsNullOrEmpty(this.Request.QueryString["catid"]))
        {
            lblpopcounter.Text = "Top Recipes";
        }
        else
        {
            lblpopcounter.Text = "Top 15 Recipes";
        }

        //Release allocated memory
        GetNewestRecipe = null;
        GetPopularRecipe = null;
        Util = null;
    }

    //Handles random recipe
    private void GetRandomrecipe(int CatId)
    {
        //Instantiate random recipe database field
        RandomRecipe Recipe = new RandomRecipe();

        Recipe.CatID = CatId;
        Recipe.fillup();

        LinkRanName.NavigateUrl = "~/recipedetail.aspx?id=" + Recipe.ID;
        LinkRanName.Text = Recipe.RecipeName;
        LinkRanName.ToolTip = "View " + Recipe.RecipeName + " recipe";

        LinkRanCat.NavigateUrl = "~/category.aspx?catid=" + Recipe.CatID;
        LinkRanCat.Text = Recipe.Category;
        LinkRanCat.ToolTip = "Browse " + Recipe.Category + " category";

        lblranhits.Text = Recipe.Hits.ToString();

        ranrateimage.ImageUrl = Utility.GetStarImage(Recipe.Rating);
        lblvotes.Text = Recipe.NoRates;

        //Release allocated memory
        Recipe = null;
    }

    public void TopRecipe_ItemDataBound(Object s, RepeaterItemEventArgs e)
    {
        //Get sequential number counter
        Utility.GetSeqCounter(TopRecipe, e);       
    }

    public void RecipeNew_ItemDataBound(Object s, RepeaterItemEventArgs e)
    {
        //Get the number of days
        //parameter 1 = we are dealing with the newest recipe.
        Utility.GetCounterDay(Convert.ToDateTime(DataBinder.Eval(e.Item.DataItem, "Date")), e, 1);
    }
}
85% of millennials say they prefer urban-style living, and 68 percent of college-educated 25- to 34-year-olds say, first, they look for the place they want to live, then they look for a job.Replicating nanorobots

MNT nanofacturing is popularly linked with the idea of swarms of coordinated nanoscale robots working together, a popularization of an early proposal by K. Eric Drexler in his 1986 discussions of MNT, but superseded in 1992. In this early proposal, sufficiently capable nanorobots would construct more nanorobots in an artificial environment containing special molecular building blocks.

Critics have doubted both the feasibility of self-replicating nanorobots and the feasibility of control if self-replicating nanorobots could be achieved: they cite the possibility of mutations removing any control and favoring reproduction of mutant pathogenic variations. Advocates address the first doubt by pointing out that the first macroscale autonomous machine replicator, made of Lego blocks, was built and operated experimentally in 2002.[8] While there are sensory advantages present at the macroscale compared to the limited sensorium available at the nanoscale, proposals for positionally controlled nanoscale mechanosynthetic fabrication systems employ dead reckoning of tooltips combined with reliable reaction sequence design to ensure reliable results, hence a limited sensorium is no handicap; similar considerations apply to the positional assembly of small nanoparts. Advocates address the second doubt by arguing that bacteria are (of necessity) evolved to evolve, while nanorobot mutation could be actively prevented by common error-correcting techniques. Similar ideas are advocated in the Foresight Guidelines on Molecular Nanotechnology,[9] and a map of the 137-dimensional replicator design space[10] recently published by Freitas and Merkle provides numerous proposed methods by which replicators could, in principle, be safely controlled by good design.

However, the concept of suppressing mutation raises the question: How can design evolution occur at the nanoscale without a process of random mutation and deterministic selection? Critics argue that MNT advocates have not provided a substitute for such a process of evolution in this nanoscale arena where conventional sensory-based selection processes are lacking. The limits of the sensorium available at the nanoscale could make it difficult or impossible to winnow successes from failures. Advocates argue that design evolution should occur deterministically and strictly under human control, using the conventional engineering paradigm of modeling, design, prototyping, testing, analysis, and redesign.