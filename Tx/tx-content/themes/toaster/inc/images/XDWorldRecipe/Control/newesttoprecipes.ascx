<%@ Control Language="C#" AutoEventWireup="true" CodeFile="newesttoprecipes.ascx.cs" Inherits="newesttoprecipes" EnableViewState="false"%>
<!--Begin Random Recipe-->
<div class="nifty">
<b class="rtop"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>
<div class="dcntmenu"><span class="content3">Featured <asp:Label ID="lblrancatname" cssClass="content3" runat="server" EnableViewState="false" />Recipe</span></div>
</div>
<div class="contentdisplay">
<div class="contentdis5">
<span class="bluearrow">&raquo;</span>
<asp:HyperLink id="LinkRanName" cssClass="dt" runat="server" EnableViewState="false" />
<br />
<span class="content8">Category:</span> <asp:HyperLink id="LinkRanCat" cssClass="dt2" runat="server" EnableViewState="false" />
<br />
<span class="content8">Rating:&nbsp;<asp:Image id="ranrateimage" runat="server" ImageAlign="absmiddle" EnableViewState="false" />&nbsp;votes&nbsp;<asp:Label cssClass="cgr" runat="server" id="lblvotes" EnableViewState="false" /></span>
<br />
<span class="content8">Hits:</span> <asp:Label cssClass="cmaron2" runat="server" id="lblranhits" EnableViewState="false" />
</div>
</div>	
<!--End Random Recipe-->
<br />
<!--Begin 15 Newest Recipes-->
<div class="roundcont">
<div class="roundtop">
<img src="images/hleft2.gif" height="5" width="5" alt="" class="corner">
<div class="dcnt"><span class="content3"><asp:Label ID="lbTopCnt" cssClass="content3" runat="server" EnableViewState="false" />&nbsp;<asp:Label ID="lblcatname" cssClass="content3" runat="server" EnableViewState="false" />Newest Recipes</span></div> 
</div>
</div>
<div class="contentdisplay">
<div class="contentdis5">
<asp:Repeater id="RecipeNew" runat="server" OnItemDataBound="RecipeNew_ItemDataBound" EnableViewState="false">
   <ItemTemplate>
<div class="dcnt2">
<span class="arrowgr">&raquo;</span>
<a class="dt" title="Category (<%# Eval("Category") %>) - Hits (<%# Eval("Hits") %>) - <%# Eval("Date", "{0:M/d/yyyy}")%>" href='<%# Eval("ID", "recipedetail.aspx?id={0}") %>'>
<%# Eval("RecipeName") %></a>
<br />
<span class="content2"><asp:Label cssClass="content2" ID="lblgetdate" runat="server" EnableViewState="false" /></span>
</div>
      </ItemTemplate>
  </asp:Repeater>
</div>
</div>
<!--End 15 Newest Recipes-->
<br />
<!--Begin 15 Most Popular-->
   <div class="roundcont">
<div class="roundtop">
<img src="images/hleft2.gif" height="5" width="5" alt="" class="corner">
<div class="dcnt"><asp:Label ID="lblcatnamepop" cssClass="content3" runat="server" EnableViewState="false" /><asp:Label ID="lblpopcounter" cssClass="content3" runat="server" EnableViewState="false" /></div> 
</div>
</div>
<div class="contentdisplay">
<div class="contentdis5">
<asp:Repeater id="TopRecipe" runat="server" OnItemDataBound="TopRecipe_ItemDataBound" EnableViewState="false">
   <ItemTemplate>
<div class="dcnt2">
<asp:Label ID="lbseqnumber" cssClass="cyel2" runat="server" EnableViewState="false" /> <a class="dt" title="Category (<%# Eval("Category") %>) - Hits (<%# Eval("Hits","{0:#,###}") %>)" href='<%# Eval("ID", "recipedetail.aspx?id={0}") %>'>
<%# Eval("RecipeName")%></a>
</div>
      </ItemTemplate>
  </asp:Repeater>
</div>
</div>
<!--End 15 Most Popular-->
Position a rack about 6 inches from the broiler and heat the broiler to high.

Heat the oil in a 12-inch nonstick skillet over medium-high heat. Add the onion and cook, stirring, until soft, about 5 minutes. Add the pepper and continue cooking until the pepper is soft and the onion is lightly browned, about 4 minutes. Add the apple, mustard, sour cream, and parsley. Remove from the heat, cover, and keep warm.

Arrange the bratwurst on a foil-lined rimmed baking sheet and broil, turning once, until browned and fully cooked, 2 to 4 minutes. Transfer to a plate. Remove the foil from the baking sheet.

Put the rolls on the baking sheet cut side up and broil until lightly toasted. Set aside the tops of the rolls, sprinkle the bottoms with the cheese, and broil until the cheese melts, 1 to 2 minutes. Top with the bratwurst, the relish, and the tops of the rolls.