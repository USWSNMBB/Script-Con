#region XD World Recipe V 2.8
// FileName: providerrecipesearch.cs
// Author: Dexter Zafra
// Date Created: 8/19/2008
// Website: www.ex-designz.net
#endregion
using System;
using System.Data;
using System.Collections.Generic;
using XDRecipe.Model.RecipeProviderEntity;
using XDRecipe.ExtendedCollections;
using BLL;

namespace GetRecipeSearchResult
{
    /// <summary>
    /// object in this class manages recipe category object List collection
    /// </summary>
    public class RecipeSearchProvider : recipeproviderentity
    {
        //Constructor
        public RecipeSearchProvider(string Keyword, int CatId, int OrderBy, int SortBy, int PageIndex, int PageSize)
        {
            this._CatID = CatId;
            this._Keyword = Keyword;
            this._OrderBy = OrderBy;
            this._SortBy = SortBy;
            this._Index = PageIndex;
            this._PageSize = PageSize;

            IDataReader dr = GetData;

            dr.Read();

            //Get category name and record count
            this._RecordCount = (int)dr["RCount"];
            this._Category = (string)dr["Category"];

            dr.Close();
        }

        /// <summary>
        /// Return Data
        /// </summary>
        /// <returns></returns>
        private IDataReader GetData
        {
            get
            {
                //Instantiate Action Stored Procedure object
                Blogic FetchData = new Blogic();

                //Get data
                IDataReader dr = FetchData.GetSearchResult(Keyword, CatID, OrderBy, Index, PageSize);

                return dr;

                FetchData = null;
            }
        }

        public ExtendedCollection<recipeproviderentity> GetRecipeSearchResult()
        {
            ExtendedCollection<recipeproviderentity> GetSearchRecipe = new ExtendedCollection<recipeproviderentity>();

            IDataReader dr = GetData;

            while (dr.Read())
            {
                recipeproviderentity item = new recipeproviderentity();

                item.ID = (int)dr["ID"];

                item.CatID = (int)dr["CAT_ID"];

                if (dr["Name"] != DBNull.Value)
                {
                    item.RecipeName = (string)dr["Name"];
                }
                if (dr["Category"] != DBNull.Value)
                {
                    item.Category = (string)dr["Category"];
                }
                if (dr["Author"] != DBNull.Value)
                {
                    item.Author = (string)dr["Author"];
                }
                if (dr["Rates"] != DBNull.Value)
                {
                    item.Rating = dr["Rates"].ToString();
                }
                if (dr["NO_RATES"] != DBNull.Value)
                {
                    item.NoRates = dr["NO_RATES"].ToString();
                }
                if (dr["Date"] != DBNull.Value)
                {
                    item.Date = (DateTime)(dr["Date"]);
                }
                if (dr["HITS"] != DBNull.Value)
                {
                    item.Hits = (int)dr["HITS"];
                }

                GetSearchRecipe.Add(item);
            }

            dr.Close();

            return GetSearchRecipe;
        }
    }
}
The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy’s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy’s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations – a behavior called “braying.” While female Grevy’s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy’s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female’s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy’s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother’s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.
