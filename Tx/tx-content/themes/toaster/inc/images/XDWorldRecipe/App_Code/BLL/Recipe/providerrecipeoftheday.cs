#region XD World Recipe V 2.8
// FileName: providerrecipeoftheday.cs
// Author: Dexter Zafra
// Date Created: 5/29/2008
// Website: www.ex-designz.net
#endregion
using System;
using System.Data;
using System.Text;
using BLL;
using Util;

namespace XDRecipe.Model.RecipeDay
{
    /// <summary>
    /// Objects in this class manages recipe of the day
    /// </summary>
    public sealed class RecipeoftheDay : Recipe
    {
        ///<summary>Default Constructor</summary>
        public RecipeoftheDay()
        {
        }

#region Initialize Database Field
        /// <summary>
        /// Get recipe name, author, date, hits, rating, ingredients, instructions and other field from the DB matching the Recipe ID provided.
        /// </summary>
        /// <param name="ID">Recipe ID</param>
        public override void fillup()
          {
              //Instantiate Action Stored Procedure object
              Blogic FetchData = new Blogic();

              try
              {
                  IDataReader dr = FetchData.GetHomepageRecipeoftheDay;

                  dr.Read();

                  if (dr["ID"] != DBNull.Value)
                  {
                      this._ID = (int)dr["ID"];
                  }
                  if (dr["Name"] != DBNull.Value)
                  {
                      this._RecipeName = (string)dr["Name"];
                  }
                  if (dr["CAT_ID"] != DBNull.Value)
                  {
                      this._CatID = (int)dr["CAT_ID"];
                  }
                  if (dr["NO_RATES"] != DBNull.Value)
                  {
                      this._NoRates = dr["NO_RATES"].ToString();
                  }
                  if (dr["HITS"] != DBNull.Value)
                  {
                      this._Hits = (int)dr["HITS"];
                  }
                  if (dr["Rates"] != DBNull.Value)
                  {
                      this._Rating = dr["Rates"].ToString();
                  }
                  if (dr["Category"] != DBNull.Value)
                  {
                      this._Category = (string)dr["Category"];
                  }
                  if (dr["Ingredients"] != DBNull.Value)
                  {
                      this._Ingredients = (string)dr["Ingredients"];
                  }
                  if (dr["Instructions"] != DBNull.Value)
                  {
                      this._Instructions = (string)dr["Instructions"];
                  }

                  //Release allocated memory
                  dr.Close();
                  dr = null;
              }
              catch (Exception ex)
              {
                  throw ex;
              }

              FetchData = null;
        }
#endregion
    }
}The Gettysburg Address is a speech by U.S. President Abraham Lincoln, one of the best-known in American history.[4] It was delivered by Lincoln during the American Civil War, on the afternoon of Thursday, November 19, 1863, at the dedication of the Soldiers' National Cemetery in Gettysburg, Pennsylvania, four and a half months after the Union armies defeated those of the Confederacy at the Battle of Gettysburg.

Abraham Lincoln's carefully crafted address, secondary to other presentations that day, came to be regarded as one of the greatest speeches in American history. In just over two minutes, Lincoln reiterated the principles of human equality espoused by the Declaration of Independence[5] and proclaimed the Civil War as a struggle for the preservation of the Union sundered by the secession crisis,[6] with "a new birth of freedom",[7] that would bring true equality[8] to all of its citizens.[8] Lincoln also redefined the Civil War as a struggle not just for the Union, but also for the principle of human equality.[5]

Beginning with the now-iconic phrase "Four score and seven years ago"—referring to the Declaration of Independence, written at the start of the American Revolution in 1776—Lincoln examined the founding principles of the United States in the context of the Civil War, and memorialized the sacrifices of those who gave their lives at Gettysburg and extolled virtues for the listeners (and the nation) to ensure the survival of America's representative democracy, that "government of the people, by the people, for the people, shall not perish from the earth."

Despite the speech's prominent place in the history and popular culture of the United States, the exact wording and location of the speech are disputed. The five known manuscripts of the Gettysburg Address differ in a number of details and also differ from contemporary newspaper reprints of the speech. Modern scholarship locates the speakers' platform 40 yards (or more) away from the Traditional Site within Soldiers' National Cemetery at the Soldiers' National Monument and entirely within private, adjacent Evergreen Cemetery.The book deals ostensibly with the March on the Pentagon (the October 1967 anti-Vietnam War rally in Washington, D.C.) While Mailer dips into familiar territory, his fiction—self-portrait—the outlandish, third person account of himself along with self-descriptions such as a novelist/historian, anti-star/hero are made far more complex by the narrative's overall generic identification as a nonfiction novel. Two years before Armies was published, In Cold Blood by Truman Capote, who had just been called by George Plimpton (among others) the "inventor" of the nonfiction novel, claimed that the genre should exclude any mention of its subjectivity and refrain from the first person. While to some extent satirizing Capote's model, Mailer's role in center stage is hardly self-glamorizing, as the narrative recounts the events leading up to the March as well as his subsequent arrest and night in jail. The first section, "History as a Novel", begins: "From the outset, let us bring you news of your protagonist", with an account made by TIME about: "Washington's scruffy Ambassador Theater, normally a pad for psychedelic frolics, was the scene of an unscheduled scatological solo last week in support of the peace demonstrations. Its anti-star was author Norman Mailer, who proved even less prepared to explain Why Are We In Vietnam? than his current novel bearing that title." After citing the entire article, Mailer then closes, "1: Pen Pals" with "Now we may leave Time in order to find out what happened." What creates the difference between Mailer's example and Capote's is not only the autobiography of Armies, but the irony which guides the narrator towards the same objective of empiricism as that of In Cold Blood. The non-conformity which Mailer exhibits to Capote's criterion was the beginning of a feud that never resolved between the authors, and was ended with Capote's death in 1984.[citation needed]

The year Armies was published, 1968, Mailer would begin work on another project, Miami and the Siege of Chicago, after witnessing the Republican and Democratic National Conventions that year. Mailer's recounting, though quite different in terms of his self-portrait, takes on a comparable rhetorical approach to evoking what he saw as historical underpinnings.Sun Nov 16 10:20:33 PST 2014
