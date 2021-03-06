#region XD World Recipe V 2.8
// FileName: default.cs
// Author: Dexter Zafra
// Date Created: 5/19/2008
// Website: www.ex-designz.net
#endregion
using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.HtmlControls;
using GetMainCourseRecipeCategory;
using GetEthnicRecipeCategory;
using XDRecipe.UI;
using BLL;
using Util;

public partial class _default : BasePage
{
    protected void Page_Load(Object sender, EventArgs e)
    {
        //Instantiate Action Stored Procedure object
        Blogic FetchData = new Blogic();

        GetMetaKeywords();

        lbltotalRecipe.Text = "There are " + string.Format("{0:#,###}",
        FetchData.GetHomepageTotalRecipeCount) + " recipes in " + FetchData.GetHomepageTotalCategoryCount + " categories";

        BindList();

        //Display random image
        Myranimage.ImageUrl = Utility.GetRandomImage;

        FetchData = null;
    }

    //Data bind
    private void BindList()
    {
        //Return main course
        MainCourseCategory.DataSource = MainCourseRecipeCategoryProvider.GetMainCourseCategory();
        MainCourseCategory.DataBind();

        //Return Ehtnic regional
        EthnicRegionalCat.DataSource = EthnicRecipeCategoryProvider.GetEthnicCategory();
        EthnicRegionalCat.DataBind();
    }

    //Assign Metatag
    private void GetMetaKeywords()
    {
        //Assign page title and meta keywords
        Page.Header.Title = "XD World Recipe";
        HtmlMeta metaTag = new HtmlMeta();
        metaTag.Name = "Keywords";
        //You can add more keywords if you want.
        metaTag.Content = "chinese recipe, barbeque recipe, seafoods recipes, salad recipe, mexican recipe";
        this.Header.Controls.Add(metaTag);
    }
}
The Gettysburg Address is a speech by U.S. President Abraham Lincoln, one of the best-known in American history.[4] It was delivered by Lincoln during the American Civil War, on the afternoon of Thursday, November 19, 1863, at the dedication of the Soldiers' National Cemetery in Gettysburg, Pennsylvania, four and a half months after the Union armies defeated those of the Confederacy at the Battle of Gettysburg.

Abraham Lincoln's carefully crafted address, secondary to other presentations that day, came to be regarded as one of the greatest speeches in American history. In just over two minutes, Lincoln reiterated the principles of human equality espoused by the Declaration of Independence[5] and proclaimed the Civil War as a struggle for the preservation of the Union sundered by the secession crisis,[6] with "a new birth of freedom",[7] that would bring true equality[8] to all of its citizens.[8] Lincoln also redefined the Civil War as a struggle not just for the Union, but also for the principle of human equality.[5]

Beginning with the now-iconic phrase "Four score and seven years ago"�referring to the Declaration of Independence, written at the start of the American Revolution in 1776�Lincoln examined the founding principles of the United States in the context of the Civil War, and memorialized the sacrifices of those who gave their lives at Gettysburg and extolled virtues for the listeners (and the nation) to ensure the survival of America's representative democracy, that "government of the people, by the people, for the people, shall not perish from the earth."

Despite the speech's prominent place in the history and popular culture of the United States, the exact wording and location of the speech are disputed. The five known manuscripts of the Gettysburg Address differ in a number of details and also differ from contemporary newspaper reprints of the speech. Modern scholarship locates the speakers' platform 40 yards (or more) away from the Traditional Site within Soldiers' National Cemetery at the Soldiers' National Monument and entirely within private, adjacent Evergreen Cemetery.