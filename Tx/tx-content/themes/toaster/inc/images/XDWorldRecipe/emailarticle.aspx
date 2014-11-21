<%@ Page Language="C#" AutoEventWireup="true" CodeFile="emailarticle.aspx.cs" Inherits="emailarticle" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head id="Head1" runat="server">
    <title>Sending <%=Request.QueryString["n"]%> Article to a Friend</title>
    <link href="CSS/cssreciaspx.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    <div>
    <asp:Panel ID="Panel1" runat="server" Width="400">
<table align="center" cellspacing="0" cellpadding="0" width="100%">
<tr><td>
<br />
<div align="center"><h2>Sending <%=Request.QueryString["n"]%> Article to a Friend</h2></div>
<table border="0" cellspacing="1" cellpadding="1" width="100%">
  <tr>
    <td valign="top" align="right" class="content6"><b>Your Name:</b></td>
    <td>
      <asp:TextBox id="txtFromName" width="180" cssClass="textbox" runat="server" />
      <asp:RequiredFieldValidator runat="server"
        id="validNameRequired" ControlToValidate="txtFromName"
        cssClass="cred2" errormessage="* Name:<br />"
        display="Dynamic" />
    </td>
  </tr>
  <tr>
    <td valign="top" align="right" class="content6"><b>Your Email:</b></td>
    <td>
      <asp:TextBox id="txtFromEmail" width="180" cssClass="textbox" runat="server" />
      <asp:RequiredFieldValidator runat="server"
        id="validFromEmailRequired" ControlToValidate="txtFromEmail"
        cssClass="cred2" errormessage="* Email:<br />"
        display="Dynamic" />
      <asp:RegularExpressionValidator runat="server"
        id="validFromEmailRegExp" ControlToValidate="txtFromEmail"
        ValidationExpression="^[\w-]+@[\w-]+\.(com|net|org|edu|mil)$"
        cssClass="cred2" errormessage="Not Valid"
        Display="Dynamic" />    
    </td>
  </tr>
<tr>
    <td valign="top" align="right" class="content6"><b>Friend's Name:</b></td>
    <td>
      <asp:TextBox id="toname" width="180" cssClass="textbox" runat="server" />
      <asp:RequiredFieldValidator runat="server"
        id="validFriendNameRequired" ControlToValidate="toname"
        cssClass="cred2" errormessage="* Name:<br />"
        display="Dynamic" />
    </td>
  </tr>
  <tr>
    <td valign="top" align="right" class="content6"><b>Friend's Email:</b></td>
    <td>
      <asp:TextBox id="txtToEmail" width="180" cssClass="textbox" runat="server" />
      <asp:RequiredFieldValidator runat="server"
        id="validToEmailRequired" ControlToValidate="txtToEmail"
        cssClass="cred2" errormessage="* Email:<br />"
        display="Dynamic" />
      <asp:RegularExpressionValidator runat="server"
        id="validToEmailRegExp" ControlToValidate="txtToEmail"
        ValidationExpression="^[\w-]+@[\w-]+\.(com|net|org|edu|mil)$"
        cssClass="cred2" errormessage="Not Valid:"
        Display="Dynamic" /> 
        <br />
        <asp:Button runat="server" Text="Submit" id="Sendrec" cssClass="submit" OnClick="SendingArticle" /> 
    </td>
  </tr>
</table>
</td></tr>
</table>
</asp:Panel>
<div style="text-align: center;" class="content2"><asp:HyperLink ID="HyperLink1" runat="server" NavigateUrl="JavaScript:onClick= window.close()" class="content2">Close Window</asp:HyperLink></div>
<br />
<br />
<asp:Label cssClass="content2" id="lblsentmsg" runat="server" />
    </div>
    </form>
</body>
</html>You do somethin' to me,
Somethin' that simply mystifies me,
Tell me, why should it be?
You have the power to hypnotize me...

Let me live 'neath your spell,
Do do that 'voodoo' that you do so well!
For you do somethin' to me,
That nobody else could do!

You... do... somethin' to me,
Somethin' that simply mystifies me,

Tell... me... why should it be?
You have the power to hypnotize me...

Let me live 'neath your spell,
Do do that 'voodoo' that you do so well!
For you do somethin' to me,
That nobody else could do!

That nobody else could do...
What did the Civil War’s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians — including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many – to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers’ deaths in the Civil War – approximately 750,000 – was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. 