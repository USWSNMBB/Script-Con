#region XD World Recipe V 2.8
// FileName: providermaincourserecipecategory.cs
// Author: Dexter Zafra
// Date Created: 8/26/2008
// Website: www.ex-designz.net
#endregion
using System;
using System.Web;
using System.Data;
using XDRecipe.ExtendedCollections;
using XDRecipe.Model;
using CacheData;
using BLL;

namespace GetMainCourseRecipeCategory
{
    /// <summary>
    /// object in this class manages main course recipe category object List collection
    /// </summary>
    public class MainCourseRecipeCategoryProvider
    {
        /// <summary>
        /// Return Recipe Main Course Category
        /// </summary>
        /// <returns></returns>
        public static ExtendedCollection<Recipe> GetMainCourseCategory()
        {
            //Instantiate Action Stored Procedure object
            Blogic FetchData = new Blogic();

            ExtendedCollection<Recipe> GetRecipe = new ExtendedCollection<Recipe>();

            string Key = "MainCourse_RecipeCategory";
         
            if (Caching.Cache[Key] != null)
            {
                GetRecipe = (ExtendedCollection<Recipe>)Caching.Cache[Key];
            }
            else
            {
                IDataReader dr = FetchData.GetHomepageMainCourseCategory;

                while (dr.Read())
                {
                    Recipe item = new Recipe();

                    item.CatID = (int)dr["CAT_ID"];

                    if (dr["CAT_TYPE"] != DBNull.Value)
                    {
                        item.Category = (string)dr["CAT_TYPE"];
                    }
                    if (dr["REC_COUNT"] != DBNull.Value)
                    {
                        item.RecordCount = (int)(dr["REC_COUNT"]);
                    }

                    GetRecipe.Add(item);

                    Caching.CahceData(Key, GetRecipe);
                }

                dr.Close();
            }

            return GetRecipe;

            FetchData = null;
        }
    }
}About the Project

This project is an online interactive featuring the Eagle lunar landing. The presentation includes original Apollo 11 spaceflight video footage, communication audio, mission control room conversations, text transcripts, and telemetry data, all synchronized into an integrated audio-visual experience.

Until today, it has been impossible to comprehensively experience mankind's shining exploratory accomplishment in a singular experience. We have compiled hours of content available from public domain sources and various NASA websites. Thamtech staff and volunteers generously devoted their time to transcribe hours of speech to text. By using simultaneous space and land based audio and video, transcripts, images, spacecraft telemetry, and biomedical data�this synchronized presentation reveals the Moon Shot as experienced by the astronauts and flight controllers.

Our goal is to capture a moment in history so that generations may now relive the events with this interactive educational resource. The world remembers the moon landing as a major historical event but often fails to recognize the scale of the mission. This interactive resource aims to educate visitors while engaging them with the excitement of manned-spaceflight to build a passion for scientific exploration.

Visitors begin the experience by hearing the words of Buzz Aldrin while simultaneously viewing the moon through the lunar module window. Moments later, the audience hears capsule communicator Charlie Duke inform flight director Gene Kranz that the astronauts are on schedule to start the descent engine. Throughout the presentation, visitors are able to customize their experience by jumping to key moments in the timeline. The timeline guides visitors to the crucial moments in the mission, including: program alarms (computer alerts), famous Go No-Go polls in the control room, low level fuel milestones, and landing.

"The Eagle has Landed." Neil Armstrong's words signal a technical milestone and successful execution of John F. Kennedy's vision to land a man on the moon safely. Prior to these famous words, visitors see the synchronized audio communications, transcripts, video of the lunar module's casting a shadow on the lunar surface, and biomedical telemetry of Armstrong's heart rate surpassing 150 beats per minute!

The footprints from Buzz Aldrin and Neil Armstrong on July 20, 1969 paved the way for five additional successful trips to the lunar surface over the following years. Thamtech takes pride in providing visitors with a glimpse into this and mankind's enduring spirit for exploration.
- See more at: http://www.firstmenonthemoon.com/about.html#sthash.iXDZXDDA.dpuf