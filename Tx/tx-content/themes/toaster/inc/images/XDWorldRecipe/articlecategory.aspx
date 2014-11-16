<%@ Page Language="C#" MasterPageFile="~/SiteTemplate2.master" AutoEventWireup="true" CodeFile="articlecategory.aspx.cs" Inherits="articlecategory" Title="Untitled Page" %>
<%@ Register TagPrefix="ucl" TagName="rsssidemenu" Src="Control/rsssidemenu.ascx" %>
<%@ Register TagPrefix="ucl" TagName="sidemenu" Src="Control/sidemenu.ascx" %>
<%@ Register TagPrefix="ucl" TagName="newestarticle" Src="Control/newestarticle.ascx" %>
<%@ Register TagPrefix="ucl" TagName="articategorylistsidemenu" Src="Control/articategorylistsidemenu.ascx" %>
<%@ Register TagPrefix="ucl" TagName="articlesearchtab" Src="Control/articlesearchtab.ascx" %>
<%@ Register TagPrefix="ucl" TagName="articlesortoptionlinks" Src="Control/articlesortoptionlinks.ascx" %>

<asp:Content ID="Content1" ContentPlaceHolderID="LeftPanel" Runat="Server">
    <ucl:sidemenu id="menu1" runat="server"></ucl:sidemenu>
    <div style="clear: both;"></div>
    <ucl:newestarticle id="nart" runat="server"></ucl:newestarticle>
    <br />
    <ucl:articategorylistsidemenu id="artcat" runat="server"></ucl:articategorylistsidemenu>
    <br />
    <ucl:rsssidemenu id="rsscont" runat="server"></ucl:rsssidemenu>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" Runat="Server">
    <ucl:articlesearchtab id="searchcont" runat="server"></ucl:articlesearchtab>
    <div style="margin-left: 10px; margin-right: 12px; background-color: #FFF9EC; margin-top: 0px;">
    &nbsp;&nbsp;<a href="default.aspx" class="dsort" title="Back to recipe homepage">Home</a>&nbsp;<span class="bluearrow">»</span>&nbsp;
    <asp:Label cssClass="content2" id="lbcatname" runat="server" Font-Bold="True" EnableViewState="false" /> <span class="content2"><asp:Label cssClass="content2" id="lbcount" runat="server" EnableViewState="false" /></span>
    <asp:Label cssClass="content2" id="lblsortname" runat="server" Font-Bold="True" EnableViewState="false" />
    </div>
    <asp:PlaceHolder id="PlaceHolder1" runat="server">
    <div style="margin-top: 32px; margin-left: 5px; margin-right: 5px;">
    <!--Begin sort category links-->
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td align="left" style="width: 79%">
    <ucl:articlesortoptionlinks id="artsortoptlink" runat="server"></ucl:articlesortoptionlinks>
    </td>
        <td width="20%" align="right">
        <div style="margin-right: 20px;">
    <asp:label ID="lblRecpagetop" runat="server" cssClass="content2" EnableViewState="false" />
    </div>
    </td>
      </tr>
    </table>
    <!--End sort category links-->
    </div>
    <div style="margin-bottom: 10px; border-top: solid 1px #D5E6FF; margin-right: 18px; margin-left: 9px;"></div>
    <div style="margin-left: 8px; margin-right: 180px; margin-top: 12px;">
    <asp:Repeater id="ArticleCat" OnItemDataBound="ArticleCat_ItemDataBound" runat="server" EnableViewState="false">
          <ItemTemplate>
        <div class="divwrap">
           <div class="divhd">
    <img src="images/arrow.gif" alt="" />
    <a class="artcat" title="Read full details of <%# Eval("Title")%> article." href='<%# Eval("ID", "articledetail.aspx?aid={0}") %>'><%# Eval("Title") %></a> 
    <asp:Label ID="lblpopular" cssClass="hot" runat="server" EnableViewState="false" /> <asp:Image ID="newimg" runat="server" EnableViewState="false" /><asp:Image id="thumbsup" runat="server" AlternateText = "Thumsb up" EnableViewState="false" />
    </div> 
    <div class="divbd">
    <div style="margin-bottom: 3px;">
    <span class="content12"><%# Eval("Summary") %></span>
    </div>
    <div style="margin-bottom: 1px;">
    <span class="content16">
    Rating:&nbsp;<img src="images/<%# Eval("Rating", "{0:0.0}")%>.gif" />&nbsp;(<span class="cgr"><%# Eval("Rating", "{0:0.0}")%></span>) votes <span class="cyel"><%# Eval("NoRates")%></span>
    &nbsp;&nbsp;Hits: <span class="cmaron3"><%# Eval("Hits", "{0:#,###}")%></span>&nbsp;&nbsp;Posted: <span class="cyel"><%# Eval("Date", "{0:MMMM dd, yyyy}")%></span>
    &nbsp;&nbsp;Author:&nbsp;<%# Eval("Author") %>
    </span>
    </div>
    </div>
    <div style="margin: 15px;"></div>
          </ItemTemplate>
      </asp:Repeater>
    </div>
    <!--Begin Record count,page count and paging link-->
    <div style="margin-left: 12px; margin-top: 22px;">
    <asp:label ID="lblRecpage"
      Runat="server"
      cssClass="content2" EnableViewState="false" />
    <div style="margin-top: 10px;">
    <asp:Label cssClass="content2" id="lbPagerLink" runat="server" Font-Bold="True" EnableViewState="false" />
    </div>
    </div>
    <!--End Record count,page count and paging link-->
    </asp:PlaceHolder>
</asp:Content>

“The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).