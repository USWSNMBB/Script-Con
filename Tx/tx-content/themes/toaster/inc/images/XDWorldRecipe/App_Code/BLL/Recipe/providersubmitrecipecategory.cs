#region XD World Recipe V 2.8
// FileName: providersubmitrecipecategory.cs
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

namespace GetSubmitRecipeCategory
{
    /// <summary>
    /// object in this class manages submission recipe category object List collection
    /// </summary>
    public class submitrecipecategoryprovider : Recipe
    {
        public submitrecipecategoryprovider()
        {
        }

        /// <summary>
        /// Return GetData
        /// </summary>
        /// <returns></returns>
        private IDataReader GetData
        {
            get
            {
                //Instantiate Action Stored Procedure object
                Blogic FetchData = new Blogic();

                //Get data
                IDataReader dr = FetchData.GetCategoryHomePage;

                return dr;

                FetchData = null;
            }
        }

        public ExtendedCollection<Recipe> GetSubmitCategory()
        {
            string Key = "Submission_RecipeCategory";

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

                    item.CatID = (int)dr["CAT_ID"];

                    if (dr["CAT_TYPE"] != DBNull.Value)
                    {
                        item.Category = (string)dr["CAT_TYPE"];
                    }

                    GetRecipe.Add(item);

                    Caching.CahceData(Key, GetRecipe);
                }

                dr.Close();
            }

            return GetRecipe;
        }
    }
}The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy’s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy’s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations – a behavior called “braying.” While female Grevy’s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy’s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female’s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy’s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother’s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.
“The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).The Gettysburg Address is a speech by U.S. President Abraham Lincoln, one of the best-known in American history.[4] It was delivered by Lincoln during the American Civil War, on the afternoon of Thursday, November 19, 1863, at the dedication of the Soldiers' National Cemetery in Gettysburg, Pennsylvania, four and a half months after the Union armies defeated those of the Confederacy at the Battle of Gettysburg.

Abraham Lincoln's carefully crafted address, secondary to other presentations that day, came to be regarded as one of the greatest speeches in American history. In just over two minutes, Lincoln reiterated the principles of human equality espoused by the Declaration of Independence[5] and proclaimed the Civil War as a struggle for the preservation of the Union sundered by the secession crisis,[6] with "a new birth of freedom",[7] that would bring true equality[8] to all of its citizens.[8] Lincoln also redefined the Civil War as a struggle not just for the Union, but also for the principle of human equality.[5]

Beginning with the now-iconic phrase "Four score and seven years ago"—referring to the Declaration of Independence, written at the start of the American Revolution in 1776—Lincoln examined the founding principles of the United States in the context of the Civil War, and memorialized the sacrifices of those who gave their lives at Gettysburg and extolled virtues for the listeners (and the nation) to ensure the survival of America's representative democracy, that "government of the people, by the people, for the people, shall not perish from the earth."

Despite the speech's prominent place in the history and popular culture of the United States, the exact wording and location of the speech are disputed. The five known manuscripts of the Gettysburg Address differ in a number of details and also differ from contemporary newspaper reprints of the speech. Modern scholarship locates the speakers' platform 40 yards (or more) away from the Traditional Site within Soldiers' National Cemetery at the Soldiers' National Monument and entirely within private, adjacent Evergreen Cemetery.% Marge steps in.

M: Are you kids making ugly faces?
B: Maybe.
M: Well, you know.  If you keep making those faces, you'll freeze that way.
   You'll be stuck with a horrible face forever.

% Marge leaves.

L: Do you think Mom was telling the truth?
B: Mom wouldn't lie.

% The kids consider this for a few seconds.

B+L+A: All right!

% [End of Act One.  Time: 0:30]
%
% [Act Two cut in syndication.  They continue making faces, and Marge
% returns to tell them to knock it off.  When she leaves, Bart and Lisa
% try to imitate her scowl; their attempts pale next to Maggie's
% recreation (``Look at this!'').]
%
% Same as before.

B: Watch this scary face.

% Bart makes a scary face.

L: Oh yeah?  That's not scary.

% Lisa makes a scary face.

B: Oh yeah?  That's not scary.

% Bart makes another scary face.

L: Oh yeah?  That's not...

% The door creaks open.  It's Marge, with a very scary angry face.

B+L: Waaauugh!  Scary!!

% [End of Act Three.  Time: 0:46]

L: I call this face, ``The Howler Monkey''.

% Lisa does a pretty good semblance.

B: I call this face, ``Dad''.

% Bart does a very good job.
%
% Marge bursts in.

M: All right.  I told you, if you kept making those horrible faces,
   they'd freeze that way.  And now they have.
L: They have!?
B: Our faces are frozen?
M: Yes, they are.  Go look in the mirror and see for yourselves.Position a rack about 6 inches from the broiler and heat the broiler to high.

Heat the oil in a 12-inch nonstick skillet over medium-high heat. Add the onion and cook, stirring, until soft, about 5 minutes. Add the pepper and continue cooking until the pepper is soft and the onion is lightly browned, about 4 minutes. Add the apple, mustard, sour cream, and parsley. Remove from the heat, cover, and keep warm.

Arrange the bratwurst on a foil-lined rimmed baking sheet and broil, turning once, until browned and fully cooked, 2 to 4 minutes. Transfer to a plate. Remove the foil from the baking sheet.

Put the rolls on the baking sheet cut side up and broil until lightly toasted. Set aside the tops of the rolls, sprinkle the bottoms with the cheese, and broil until the cheese melts, 1 to 2 minutes. Top with the bratwurst, the relish, and the tops of the rolls.