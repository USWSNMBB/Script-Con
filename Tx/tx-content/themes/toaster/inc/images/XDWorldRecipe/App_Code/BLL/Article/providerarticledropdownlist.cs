#region XD World Recipe V 2.8
// FileName: providerarticledropdownlist.cs - Populate article category dropdown menu
// Author: Dexter Zafra
// Date Created: 6/19/2008
// Website: www.ex-designz.net
#endregion
using System;
using System.Data;
using System.Web.UI.WebControls;
using System.Collections;
using BLL;
using ConstantVar;

namespace ProviderDropdownList
{
    /// <summary>
    /// Object in this class populate article category dropdownlist.
    /// </summary>
    public static class ProviderArticleCategoryDropdown
    {
        private static Hashtable _CategoryListArticle;

        /// <summary>
        /// Return a hashtable of article category
        /// </summary>
        public static Hashtable categoryListArticle
        {
            get
            {
                if (_CategoryListArticle == null)
                    createCategoryListArticle();
                return _CategoryListArticle;
            }
        }

        /// <summary>
        /// Create a hashtable for Article Category dropdownlist
        /// </summary>
        private static void createCategoryListArticle()
        {
            //Instantiate Action Stored Procedure object
            Blogic FetchData = new Blogic();

            IDataReader dr = FetchData.GetArticleCategoryList;

            try
            {
                Hashtable ht = new Hashtable();
                while (dr.Read())
                {
                    ht.Add(dr["CAT_ID"].ToString(), dr["CAT_NAME"].ToString());

                    _CategoryListArticle = ht;
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }

            //Release allocated memory
            dr.Close();

            FetchData = null;
        }
    }
}MASK BOY: Well, that’s a start.

CHER: Well, what about your news?

MASK BOY: Oh, it’s no big deal. I met a girl. We’re going out.

CHER: Come on. Tell us.

MASK BOY: Well, her name is Diana ….

CHER: Yeah?

MASK BOY: And she’s beautiful. She’s got long blond hair. (A friend whistles.) She rides horses and she’s beautiful and she’s smart and she loves me.

CHER: What’s not to love, baby?

SCENE 14 (OLD MAN enters his kitchen. Sitting at the table, he looks through a photo album filled with pictures of himself and his son, the Great Mutato as a child.. POLLIDORI enters, angry.)

POLLIDORI: Tell me it isn’t true. You didn’t. You wouldn’t. Why?

OLD MAN: Because I can.

(POLLIDORI attacks OLD MAN)

(Grunting as we see the shadows of the two men struggling, POLLIDORI strangling the OLD MAN.)

(Commercial.)

SCENE 15 (J.J.’S diner. MULDER enters. Everyone is hostile. MULDER defensive and over it. Exact opposite of earlier scene. LARGE MAN sticks his leg out as if to trip MULDER.)

LARGE MAN: (sarcastically) Excuse me.

MULDER: (also sarcastic) Not a problem.

(Another hostile diner flips a spoonful of grits? at MULDER which lands on the back of his neck. MULDER reacts with disgust. He sits down and wipes the offending matter away. In the kitchen, J.J. spits loudly onto a plate of barely cooked eggs and hands it to the WAITRESS. The WAITRESS puts it down in front of MULDER.)

MULDER: (looking at disgusting eggs) What’s this?

WAITRESS: (saccharine) Compliments of J.J. Coffee?

MULDER: (looking away) Sure.

(WAITRESS spills hot coffee in MULDER’S lap. He jumps up, brushing off his crotch.)

MULDER: Whoa! That’s not a place you want to burn a guy.

(WAITRESS flounces away. MULDER looks as newspaper headline : "FBI Agents Say Monster A ‘Hoax’.")

(Commotion outside. Lots of people running past the diner. MULDER follows them to where there is a large gathering outside the post office.)

POSTAL MAN: You want to see your monster? 