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

Beginning with the now-iconic phrase "Four score and seven years ago"�referring to the Declaration of Independence, written at the start of the American Revolution in 1776�Lincoln examined the founding principles of the United States in the context of the Civil War, and memorialized the sacrifices of those who gave their lives at Gettysburg and extolled virtues for the listeners (and the nation) to ensure the survival of America's representative democracy, that "government of the people, by the people, for the people, shall not perish from the earth."

Despite the speech's prominent place in the history and popular culture of the United States, the exact wording and location of the speech are disputed. The five known manuscripts of the Gettysburg Address differ in a number of details and also differ from contemporary newspaper reprints of the speech. Modern scholarship locates the speakers' platform 40 yards (or more) away from the Traditional Site within Soldiers' National Cemetery at the Soldiers' National Monument and entirely within private, adjacent Evergreen Cemetery.The book deals ostensibly with the March on the Pentagon (the October 1967 anti-Vietnam War rally in Washington, D.C.) While Mailer dips into familiar territory, his fiction�self-portrait�the outlandish, third person account of himself along with self-descriptions such as a novelist/historian, anti-star/hero are made far more complex by the narrative's overall generic identification as a nonfiction novel. Two years before Armies was published, In Cold Blood by Truman Capote, who had just been called by George Plimpton (among others) the "inventor" of the nonfiction novel, claimed that the genre should exclude any mention of its subjectivity and refrain from the first person. While to some extent satirizing Capote's model, Mailer's role in center stage is hardly self-glamorizing, as the narrative recounts the events leading up to the March as well as his subsequent arrest and night in jail. The first section, "History as a Novel", begins: "From the outset, let us bring you news of your protagonist", with an account made by TIME about: "Washington's scruffy Ambassador Theater, normally a pad for psychedelic frolics, was the scene of an unscheduled scatological solo last week in support of the peace demonstrations. Its anti-star was author Norman Mailer, who proved even less prepared to explain Why Are We In Vietnam? than his current novel bearing that title." After citing the entire article, Mailer then closes, "1: Pen Pals" with "Now we may leave Time in order to find out what happened." What creates the difference between Mailer's example and Capote's is not only the autobiography of Armies, but the irony which guides the narrator towards the same objective of empiricism as that of In Cold Blood. The non-conformity which Mailer exhibits to Capote's criterion was the beginning of a feud that never resolved between the authors, and was ended with Capote's death in 1984.[citation needed]

The year Armies was published, 1968, Mailer would begin work on another project, Miami and the Siege of Chicago, after witnessing the Republican and Democratic National Conventions that year. Mailer's recounting, though quite different in terms of his self-portrait, takes on a comparable rhetorical approach to evoking what he saw as historical underpinnings.Sun Nov 16 10:20:33 PST 2014
Johann Wolfgang von Goethe (/'g?rt?/;[1] German: ['jo?han 'v?lfga? f?n 'g�?t?] ( listen); 28 August 1749 � 22 March 1832) was a German writer and statesman. His body of work includes epic and lyric poetry written in a variety of metres and styles; prose and verse dramas; memoirs; an autobiography; literary and aesthetic criticism; treatises on botany, anatomy, and colour; and four novels. In addition, numerous literary and scientific fragments, more than 10,000 letters, and nearly 3,000 drawings by him are extant.

A literary celebrity by the age of 25, Goethe was ennobled by the Duke of Saxe-Weimar, Carl August in 1782 after first taking up residence there in November 1775 following the success of his first novel, The Sorrows of Young Werther. He was an early participant in the Sturm und Drang literary movement. During his first ten years in Weimar, Goethe served as a member of the Duke's privy council, sat on the war and highway commissions, oversaw the reopening of silver mines in nearby Ilmenau, and implemented a series of administrative reforms at the University of Jena. He also contributed to the planning of Weimar's botanical park and the rebuilding of its Ducal Palace, which in 1998 were together designated a UNESCO World Heritage Site.[2]

After returning from a tour of Italy in 1788, his first major scientific work, the Metamorphosis of Plants, was published. In 1791 he was made managing director of the theatre at Weimar, and in 1794 he began a friendship with the dramatist, historian, and philosopher Friedrich Schiller, whose plays he premiered until Schiller's death in 1805. During this period Goethe published his second novel, Wilhelm Meister's Apprenticeship, the verse epic Hermann and Dorothea, and, in 1808, the first part of his most celebrated drama, Faust. His conversations and various common undertakings throughout the 1790s with Schiller, Johann Gottlieb Fichte, Johann Gottfried Herder, Alexander von Humboldt, Wilhelm von Humboldt, and August and Friedrich Schlegel have, in later years, been collectively termed Weimar Classicism.

Arthur Schopenhauer cited Wilhelm Meister's Apprenticeship as one of the four greatest novels ever written[citation needed] and Ralph Waldo Emerson selected Goethe as one of six "representative men" in his work of the same name, along with Plato, Napoleon, and William Shakespeare. Goethe's comments and observations form the basis of several biographical works, most notably Johann Peter Eckermann's Conversations with Goethe. There are frequent references to Goethe's writings throughout the works of G. W. F. Hegel, Arthur Schopenhauer, Friedrich Nietzsche, Hermann Hesse, Thomas Mann, Sigmund Freud, and Carl Jung. Goethe's poems were set to music throughout the eighteenth and nineteenth centuries by a number of composers, including Wolfgang Amadeus Mozart, Ludwig van Beethoven, Franz Schubert, Robert Schumann, Johannes Brahms, Charles Gounod, Richard Wagner, Hugo Wolf, and Gustav Mahler.85% of millennials say they prefer urban-style living, and 68 percent of college-educated 25- to 34-year-olds say, first, they look for the place they want to live, then they look for a job.

Although it increases the risk of poor outcomes and raises the costs of care, cognitive impairment in hospitalized older adults is often neither accurately identified nor well managed. In conducting a two-phase, comparative-effectiveness clinical trial of the effects of three nursing interventions�augmented standard care, resource nurse care, and the transitional care model�on hospitalized older adults with cognitive deficits, a team of researchers encountered several challenges. For example, in assessing potential subjects for the study, they found that nearly half of those assessed had cognitive impairment, yet many family caregivers could not be identified or had no interest in participating in the study. One lesson the researchers learned was that research involving cognitively impaired older adults must actively engage clinicians, patients, and family caregivers, as well as address the complex process of managing postdischarge care.

