<%@ Page Language="C#" MasterPageFile="~/SiteTemplate.master" EnableViewState="false" AutoEventWireup="true" CodeFile="default.aspx.cs" Inherits="_default" Title="Untitled Page" %>
<%@ Register TagPrefix="ucl" TagName="alphaletter" Src="Control/alphaletter.ascx" %>
<%@ Register TagPrefix="ucl" TagName="searchtab" Src="Control/searchtab.ascx" %>
<%@ Register TagPrefix="ucl" TagName="recipeoftheday" Src="Control/recipeoftheday.ascx" %>
<%@ Register TagPrefix="ucl" TagName="Controllastviewedrecipe" Src="Control/lastviewedrecipe.ascx" %>
<%@ Register TagPrefix="ucl" TagName="newestarticle" Src="Control/newestarticle.ascx" %>
<%@ Register TagPrefix="ucl" TagName="sidemenu" Src="Control/sidemenu.ascx" %>
<%@ Register TagPrefix="ucl" TagName="articategorylistsidemenu" Src="Control/articategorylistsidemenu.ascx" %>
<%@ Register TagPrefix="ucl" TagName="rsssidemenu" Src="Control/rsssidemenu.ascx" %>

<asp:Content ID="Content2" ContentPlaceHolderID="LeftPanel" Runat="Server">
    <ucl:sidemenu id="menu1" runat="server"></ucl:sidemenu>
    <div style="clear: both;"></div>
    <ucl:newestarticle id="nart" runat="server"></ucl:newestarticle>
    <br />
    <ucl:articategorylistsidemenu id="artcat" runat="server"></ucl:articategorylistsidemenu>
    <br />
    <ucl:rsssidemenu id="rsscont" runat="server"></ucl:rsssidemenu>
</asp:Content>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" Runat="Server">
    <ucl:searchtab id="searchcont" runat="server"></ucl:searchtab>
    <div style="padding: 2px; text-align: left; margin-left: 40px; margin-bottom: 15px; margin-top: 16px; margin-right: 40px;">
    <asp:Image id="Myranimage" runat="server"
     Width = 107 Height = 74
     AlternateText = "Recipe Random Image" Style="float:left; padding-right: 5px;"
    />
    <span class="drecipe">
    You need a new dish in a hurry? Stumped by how to make that something special? We can help with your busy lifestyle. Take a look around and review our hundreds of free recipes or submit one of your own favorites.
    </span>
    </div>
    <br />
    <br />
    <div style="padding: 2px; text-align: center; margin-left: 26px; margin-bottom: 12px; margin-right: 26px;">
    <ucl:alphaletter id="alpha1" runat="server"></ucl:alphaletter>
    </div>
    <div style="text-align: center; padding-top: 3px;"><asp:Label cssClass="content2" runat="server" id="lbltotalRecipe" />
    </div>
    <br />
    <div style="text-align: center;  padding-bottom: 5px;"><span style="font-family: verdana,arial; font-size: 17px; color: #CC3300;"><b>Main Course Recipe</b></span></div>
    <asp:DataList id="MainCourseCategory" RepeatColumns="3" RepeatDirection="Horizontal" runat="server" HorizontalAlign="Center">
          <ItemTemplate>
         <div style="margin-left: 60px; margin-top: 3px; margin-bottom: 3px; margin-right: 10px;">  
    <span class="bluearrow">&raquo;</span> <a class="catlink" title="Browse all <%# Eval("Category") %> recipe" href='<%# Eval("CatID", "category.aspx?catid={0}") %>'><%# Eval("Category")%></a> <span class="catcount"><i>(<%# Eval("RecordCount")%>)</i></span>
           </div>
          </ItemTemplate>
      </asp:DataList>
      <br />
      <div style="clear:both;"></div>
      <div style="text-align: center;  padding-bottom: 5px;"><span style="font-family: verdana,arial; font-size: 17px; color: #CC3300;"><b>Ethnic &amp; Regional Cuisine</b></span></div>
    <asp:DataList id="EthnicRegionalCat" RepeatColumns="3" RepeatDirection="Horizontal" runat="server" HorizontalAlign="Center">
          <ItemTemplate>
         <div style="margin-left: 60px; margin-top: 3px; margin-bottom: 3px; margin-right: 10px;">  
    <span class="bluearrow">&raquo;</span> <a class="catlink" title="Browse all <%# Eval("Category") %> recipe" href='<%# Eval("CatID", "category.aspx?catid={0}") %>'><%# Eval("Category")%></a> <span class="catcount"><i>(<%# Eval("RecordCount")%>)</i></span>
           </div>
          </ItemTemplate>
      </asp:DataList>
      <div style="clear:both; margin-top: 16px;"></div>
     <!--Begin Today and last 8 hours recipe block--> 
      <div style="margin-left: 70px; margin-right: 70px; width: auto;">
    <ucl:recipeoftheday id="recday" runat="server"></ucl:recipeoftheday>
    <ucl:Controllastviewedrecipe id="lastviewed" runat="server"></ucl:Controllastviewedrecipe>
      </div>
       <!--End Today and last 8 hours recipe block---> 
</asp:Content>

What did the Civil War’s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians — including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many – to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers’ deaths in the Civil War – approximately 750,000 – was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. 