#region XD World Recipe V 2.8
// FileName: recipecomment.cs
// Author: Dexter Zafra
// Date Created: 5/29/2008
// Website: www.ex-designz.net
#endregion
using System;
using System.Data;
using System.Web;
using System.Web.UI.WebControls;
using XDRecipe.Model;
using GetRecipeComments;
using BLL;

namespace RecipeComment
{
    /// <summary>
    /// Objects in this class manages get and show/hide recipe comments
    /// </summary>
    public sealed class RecipeComments : Comment
    {
        //Constructor
        public RecipeComments(int ID, Repeater RepeaterName, PlaceHolder Ph)
        {
            this._ID = ID;
            this._RepeaterName = RepeaterName;
            this._placeholder = Ph;
        }

#region Show/Hide Comment Method
        /// <summary>
     /// Show or hide recipe comment
    /// </summary>
    public override void fillup()
    {
        //Find control
        Panel Panel1 = (Panel)(placeholder.FindControl("Panel1"));
        Image CommentImg = (Image)(placeholder.FindControl("CommentImg"));
        HyperLink CommentLink = (HyperLink)(placeholder.FindControl("CommentLink"));

        //Instantiate Action Stored Procedure object
        Blogic FetchData = new Blogic();

        try
        {
            IDataReader dr = FetchData.GetConfiguration;

            dr.Read();

            //Show hide comment 
            if (dr["HideShowComment"] != DBNull.Value)
            {
                this._ShowHideComment = (int)dr["HideShowComment"];
            }

            dr.Close();
            dr = null;

            if (IsShowHideComment)
            {
                //If true, display recipe comments, else hide.
                //Get datasourse
                RepeaterName.DataSource = RecipeCommentProvider.GetComments(ID);
                RepeaterName.DataBind();
                Panel1.Visible = true;
            }
            else
            {
                Panel1.Visible = false;
                CommentImg.Visible = false;
                CommentLink.Visible = false;
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }

        FetchData = null;
    }
#endregion
}
}(GOSS NET 1) Tape 1/3 Page 3

00 00 08 22 CDR
AGS just got the mixture ratio shift.

00 00 08 24 CC
Roger. We got PU shift down here, too.

00 00 08 34 CDR
Well, it looks like a nice day for it. These thunderstorms down range is about all.

GRAND BAHAMA ISLANDS (REV 1)

00 00 08 52 CC
11, this is Houston. You are GO for staging. Over.

00 00 08 56 CDR
Understand, GO for staging. And - -

00 00 08 57 CC
Stand by for mode IV capability.

00 00 08 59 CDR
Okay. Mode IV.

00 00 09 00 CC
MARK.

00 00 09 01 CC
Mode IV capability.

00 00 09 15 CDR
Staging -

00 00 09 16 CDR
- And ignition.

00 00 09 19 CC
Ignition confirmed; thrust is GO, 11.

00 00 10 01 CC
Apollo 11, this is Houston. At 10 minutes, you are GO.

00 00 10 06 CDR
Roger. 11's GO.
