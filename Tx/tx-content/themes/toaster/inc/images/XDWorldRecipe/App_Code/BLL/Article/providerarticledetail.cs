#region XD World Recipe V 2.8
// FileName: providerarticledetail.cs
// Author: Dexter Zafra
// Date Created: 5/29/2008
// Website: www.ex-designz.net
#endregion
using System;
using System.Data;
using Util;
using BLL;
using ConstantVar;

namespace XDRecipe.Model.ArticleDetail
{
    /// <summary>
    /// Objects in this class manages return the article details
    /// </summary>
    public sealed class ArticleDetail : Article
    {
        ///<summary>Default Constructor</summary>
        public ArticleDetail()
        {
        }

#region Initialize Database Field
        /// <summary>
        /// Get article title, author, date, hits, rating and content from the DB matching the Article ID provided.
        /// </summary>
        public override void fillup()
        {
            //Instantiate Action Stored Procedure object
            Blogic FetchData = new Blogic();

            //Instantiate object
            Utility Util = new Utility();

            //Parameter 1 = we are dealing with the articledetail.aspx not the admin article update  which is 2.
            IDataReader dr = FetchData.GetArticleDetail(ID, WhatPageID);

            dr.Read();

            if (WhatPageID == constant.intArticleDetails) //Populate articledetail.aspx
                {
                    try
                    {
                        if (dr["Title"] != DBNull.Value)
                        {
                            this._Title = (string)dr["Title"];
                        }
                        if (dr["Author"] != DBNull.Value)
                        {
                            this._Author = (string)dr["Author"];
                        }
                        if (dr["No_Rates"] != DBNull.Value)
                        {
                            this._NoRates = dr["No_Rates"].ToString();
                        }
                        if (dr["HITS"] != DBNull.Value)
                        {
                            this._Hits = (int)dr["HITS"];
                        }
                        if (dr["Rates"] != DBNull.Value)
                        {
                            this._Rating = dr["Rates"].ToString();
                        }
                        if (dr["Content"] != DBNull.Value)
                        {
                            this._Content = (string)dr["Content"];
                        }
                        if (dr["CAT_NAME"] != DBNull.Value)
                        {
                            this._Category = (string)dr["CAT_NAME"];
                        }
                        if (dr["CAT_ID"] != DBNull.Value)
                        {
                            this._CatID = (int)dr["CAT_ID"];
                        }
                        if (dr["Post_Date"] != DBNull.Value)
                        {
                            this._Date = (DateTime)(dr["Post_Date"]);
                        }
                    }
                    catch
                    {
                        //Redirect to page not found.
                        //1 = pagenotfound.aspx
                        Util.PageRedirect(1);
                    }

                    return;
                }
                else if (WhatPageID == constant.intArticleAdminUpdate) //Populate Admin/updatearticle.aspx
                {
                    try
                    {
                        if (dr["Title"] != DBNull.Value)
                        {
                            this._Title = (string)dr["Title"];
                        }
                        if (dr["Author"] != DBNull.Value)
                        {
                            this._Author = (string)dr["Author"];
                        }
                        if (dr["CAT_NAME"] != DBNull.Value)
                        {
                            this._Category = (string)dr["CAT_NAME"];
                        }
                        if (dr["Content"] != DBNull.Value)
                        {
                            this._Content = (string)dr["Content"];
                        }
                        if (dr["Summary"] != DBNull.Value)
                        {
                            this._Summary = (string)dr["Summary"];
                        }
                        if (dr["Keyword"] != DBNull.Value)
                        {
                            this._Keyword = (string)dr["Keyword"];
                        }
                        if (dr["CAT_ID"] != DBNull.Value)
                        {
                            this._CatID = (int)dr["CAT_ID"];
                        }
                        if (dr["Post_Date"] != DBNull.Value)
                        {
                            this._Date = (DateTime)(dr["Post_Date"]);
                        }
                    }

                    catch
                    {
                        //Redirect to page not found.
                        //1 = pagenotfound.aspx
                        Util.PageRedirect(1);
                    }

                    return;
                }
                else if (WhatPageID == constant.intArticleAdminPreview) //Populate Admin/articlepreview.aspx
                {
                    try
                    {
                        if (dr["Title"] != DBNull.Value)
                        {
                            this._Title = (string)dr["Title"];
                        }
                        if (dr["Content"] != DBNull.Value)
                        {
                            this._Content = (string)dr["Content"];
                        }
                    }
                    catch
                    {
                        //Redirect to page not found.
                        //1 = pagenotfound.aspx
                        Util.PageRedirect(1);
                    }

                    return;
                }

                //Release allocated memory
                dr.Close();
                dr = null;
                FetchData = null;
                Util = null;
        }

#endregion
    }
}The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy’s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy’s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations – a behavior called “braying.” While female Grevy’s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy’s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female’s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy’s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother’s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.
