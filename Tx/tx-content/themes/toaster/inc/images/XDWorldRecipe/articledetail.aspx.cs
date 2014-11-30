#region XD World Recipe V 2.8
// FileName: articledetail.cs
// Author: Dexter Zafra
// Date Created: 5/28/2008
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
using XDRecipe.Model.ArticleDetail;
using RatingCookie;
using BookmarkURL;
using Util;
using ConstantVar;

public partial class articledetail : BasePage
{
    //Instantiate database field
    ArticleDetail Article = new ArticleDetail();

    public int ArtCatId;
    public string strCatName;
    public string strArtTitle;
    public string strBookmarkURL;
    public int ArticleSection;

    protected void Page_Load(object sender, EventArgs e)
    {
        //Instantiate utility/common object
        Utility Util = new Utility();

        Article.WhatPageID = constant.intArticleDetails;
        Article.ID = (int)Util.Val(Request.QueryString["aid"]);

        //Fill up article database fields
        Article.fillup();

        lblwordcount.Text = Utility.WordCount(Article.Content).ToString();

        lbtitle.Text = Article.Title;
        lbcontent.Text = Article.Content;
        lbhits.Text = string.Format("{0:#,###}", Article.Hits);
        lbauthor.Text = Article.Author;
        lblrating.Text = Article.Rating;
        lblvotescount.Text = Article.NoRates;
        starimage.ImageUrl = Utility.GetStarImage(Article.Rating);
        lbldate.Text = string.Format("{0:MMMM dd, yyyy}", Article.Date);

        //Save to Favorite/Bookmark URL.
        strBookmarkURL = Bookmark.URL;

        strArtTitle = Article.Title;
        strCatName = Article.Category;
        ArtCatId = Article.CatID;
        ArticleSection = constant.intArticle;

        //Get page title and keyword
        GetMetaTitleTagKeywords(strArtTitle, strCatName);

        //Get cooking article user rating
        GetUserCookingArticleRating();

        //Release allocated memory         
        Util = null;
        Article = null;
    }

    //Get cooking article user rating
    private void GetUserCookingArticleRating()
    {
        //Instantiate user article cookie rating object
        CookieRating GetCookie = new CookieRating(constant.intArticle, Article.ID, PlaceHolder2);

        //Get the user rating cookie
        GetCookie.GetUserCookieRating();

        //Release allocated memory
        GetCookie = null;
    }

    //Handle dynamic page title and keywords
    private void GetMetaTitleTagKeywords(string Title, string CategoryName)
    {
        //Assign page title and meta keywords
        Page.Header.Title = Title + "," + CategoryName + " article";
        HtmlMeta metaTag = new HtmlMeta();
        metaTag.Name = "Keywords";
        //You can add more keywords if you want.
        metaTag.Content = Title + "," + CategoryName + ", cooking article";
        this.Header.Controls.Add(metaTag);
    }
}
Position a rack about 6 inches from the broiler and heat the broiler to high.

Heat the oil in a 12-inch nonstick skillet over medium-high heat. Add the onion and cook, stirring, until soft, about 5 minutes. Add the pepper and continue cooking until the pepper is soft and the onion is lightly browned, about 4 minutes. Add the apple, mustard, sour cream, and parsley. Remove from the heat, cover, and keep warm.

Arrange the bratwurst on a foil-lined rimmed baking sheet and broil, turning once, until browned and fully cooked, 2 to 4 minutes. Transfer to a plate. Remove the foil from the baking sheet.

Put the rolls on the baking sheet cut side up and broil until lightly toasted. Set aside the tops of the rolls, sprinkle the bottoms with the cheese, and broil until the cheese melts, 1 to 2 minutes. Top with the bratwurst, the relish, and the tops of the rolls.The book deals ostensibly with the March on the Pentagon (the October 1967 anti-Vietnam War rally in Washington, D.C.) While Mailer dips into familiar territory, his fiction—self-portrait—the outlandish, third person account of himself along with self-descriptions such as a novelist/historian, anti-star/hero are made far more complex by the narrative's overall generic identification as a nonfiction novel. Two years before Armies was published, In Cold Blood by Truman Capote, who had just been called by George Plimpton (among others) the "inventor" of the nonfiction novel, claimed that the genre should exclude any mention of its subjectivity and refrain from the first person. While to some extent satirizing Capote's model, Mailer's role in center stage is hardly self-glamorizing, as the narrative recounts the events leading up to the March as well as his subsequent arrest and night in jail. The first section, "History as a Novel", begins: "From the outset, let us bring you news of your protagonist", with an account made by TIME about: "Washington's scruffy Ambassador Theater, normally a pad for psychedelic frolics, was the scene of an unscheduled scatological solo last week in support of the peace demonstrations. Its anti-star was author Norman Mailer, who proved even less prepared to explain Why Are We In Vietnam? than his current novel bearing that title." After citing the entire article, Mailer then closes, "1: Pen Pals" with "Now we may leave Time in order to find out what happened." What creates the difference between Mailer's example and Capote's is not only the autobiography of Armies, but the irony which guides the narrator towards the same objective of empiricism as that of In Cold Blood. The non-conformity which Mailer exhibits to Capote's criterion was the beginning of a feud that never resolved between the authors, and was ended with Capote's death in 1984.[citation needed]

The year Armies was published, 1968, Mailer would begin work on another project, Miami and the Siege of Chicago, after witnessing the Republican and Democratic National Conventions that year. Mailer's recounting, though quite different in terms of his self-portrait, takes on a comparable rhetorical approach to evoking what he saw as historical underpinnings.