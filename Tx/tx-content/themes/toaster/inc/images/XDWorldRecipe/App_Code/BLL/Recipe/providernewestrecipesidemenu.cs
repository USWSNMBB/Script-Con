#region XD World Recipe V 2.8
// FileName: providernewestrecipesidemenu.cs
// Author: Dexter Zafra
// Date Created: 8/26/2008
// Website: www.ex-designz.net
#endregion
using System;
using System.Data;
using XDRecipe.ExtendedCollections;
using XDRecipe.Model;
using CacheData;
using BLL;

namespace GetNewestRecipeSideMenu
{
    /// <summary>
    /// object in this class manages newest recipe sidemenu object List collection
    /// </summary>
    public class NewestRecipeSideMenuProvider : Recipe
    {

        //Constructor
        public NewestRecipeSideMenuProvider(int CatId, int Top)
        {
            if (CatId != null)
            {
                this._CatID = CatId;
            }

            this._Top = Top;

            IDataReader dr = GetData;

            dr.Read();

            //Get the category name
            this._Category = (string)dr["Category"];

            dr.Close();
        }

        /// <summary>
        /// Return Recipe Category Data
        /// </summary>
        /// <returns></returns>
        private IDataReader GetData
        {
            get
            {
                //Instantiate Action Stored Procedure object
                Blogic FetchData = new Blogic();

                //Get data
                IDataReader dr = FetchData.GetNewestRecipesSideMenu(CatID, Top);

                return dr;

                FetchData = null;
            }
        }

        public ExtendedCollection<Recipe> GetNewestRecipe()
        {
            string Key = "Newest_RecipesSideMenu_" + CatID.ToString();

            ExtendedCollection<Recipe> GetRecipe = new ExtendedCollection<Recipe>();

            if (Caching.Cache[Key] != null)
            {
                GetRecipe = (ExtendedCollection<Recipe>)Caching.Cache[Key];
            }
            else
            {
                IDataReader dr = GetData;

                while (dr.Read())
                {
                    Recipe item = new Recipe();

                    item.ID = (int)dr["ID"];

                    if (dr["Name"] != DBNull.Value)
                    {
                        item.RecipeName = (string)dr["Name"];
                    }
                    if (dr["Category"] != DBNull.Value)
                    {
                        item.Category = (string)dr["Category"];
                    }
                    if (dr["Date"] != DBNull.Value)
                    {
                        item.Date = (DateTime)(dr["Date"]);
                    }
                    if (dr["HITS"] != DBNull.Value)
                    {
                        item.Hits = (int)dr["HITS"];
                    }

                    GetRecipe.Add(item);

                    Caching.CahceData(Key, GetRecipe);
                }

                dr.Close();
            }

            return GetRecipe;
        }
    }
}
�The poem,� said Henri Meschonnic, �is the place where one forgets, which is another way of remembering�; so recurrence in COTP: the engram, �memories� of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but� COTP teaches; as we encounter it (a declaration about time, in time) later, too: �memory and the present are not in opposition� (43) so I�m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or �When the anarchic excess has already been anticipated, what next?� (9)�is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: �Also you have aspired to a sincerity of skepticism�; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first �conditional� problem�but what I like is how LR isn�t ever mired in the problem of what to say or how to proceed� the only crisis�again, I think COTP teaches this�is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again� Later we get: �the form never extinguishes its own irony� (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: �an unknowing expands within your pronoun but it feels convivial� (12); I like this party being thrown for two pronouns that can�t really meet and therefore that�s their intimate rendezvous�like LR is saying (to herself, to a future I, to an us) just deal� and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: �you carried the great discovery of poetry as freedom, not form� (75).

-Or instead try this these love is �your muscles made into extremely fine and silky tools� or love is �what light did, how the trees freed it� or love is �an inversion in perception� or love is �the smell of rain, bread and exhaust mixed with tiredness.�

-�And if you yourself are incompatible with your view of the world?� (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR�s kinda sorta How to Write).(GOSS NET 1) Tape 1/3 Page 3

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
