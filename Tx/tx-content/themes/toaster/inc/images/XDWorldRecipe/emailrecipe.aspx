<%@ Page Language="C#" AutoEventWireup="true" CodeFile="emailrecipe.aspx.cs" Inherits="emailrecipe" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Sending <%=Request.QueryString["n"]%> Recipe to a Friend</title>
    <link href="CSS/cssreciaspx.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    <div>
    <asp:Panel ID="Panel1" runat="server" Width="400">
<table align="center" cellspacing="0" cellpadding="0" width="100%">
<tr><td>
<br />
<div align="center"><h2>Sending <%=Request.QueryString["n"]%> Recipe to a Friend</h2></div>
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
        <asp:Button runat="server" Text="Submit" id="Sendrec" cssClass="submit" OnClick="SendingRecipe" /> 
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
</html>
Johann Wolfgang von Goethe (/'g?rt?/;[1] German: ['jo?han 'v?lfga? f?n 'gø?t?] ( listen); 28 August 1749 – 22 March 1832) was a German writer and statesman. His body of work includes epic and lyric poetry written in a variety of metres and styles; prose and verse dramas; memoirs; an autobiography; literary and aesthetic criticism; treatises on botany, anatomy, and colour; and four novels. In addition, numerous literary and scientific fragments, more than 10,000 letters, and nearly 3,000 drawings by him are extant.

A literary celebrity by the age of 25, Goethe was ennobled by the Duke of Saxe-Weimar, Carl August in 1782 after first taking up residence there in November 1775 following the success of his first novel, The Sorrows of Young Werther. He was an early participant in the Sturm und Drang literary movement. During his first ten years in Weimar, Goethe served as a member of the Duke's privy council, sat on the war and highway commissions, oversaw the reopening of silver mines in nearby Ilmenau, and implemented a series of administrative reforms at the University of Jena. He also contributed to the planning of Weimar's botanical park and the rebuilding of its Ducal Palace, which in 1998 were together designated a UNESCO World Heritage Site.[2]

After returning from a tour of Italy in 1788, his first major scientific work, the Metamorphosis of Plants, was published. In 1791 he was made managing director of the theatre at Weimar, and in 1794 he began a friendship with the dramatist, historian, and philosopher Friedrich Schiller, whose plays he premiered until Schiller's death in 1805. During this period Goethe published his second novel, Wilhelm Meister's Apprenticeship, the verse epic Hermann and Dorothea, and, in 1808, the first part of his most celebrated drama, Faust. His conversations and various common undertakings throughout the 1790s with Schiller, Johann Gottlieb Fichte, Johann Gottfried Herder, Alexander von Humboldt, Wilhelm von Humboldt, and August and Friedrich Schlegel have, in later years, been collectively termed Weimar Classicism.

Arthur Schopenhauer cited Wilhelm Meister's Apprenticeship as one of the four greatest novels ever written[citation needed] and Ralph Waldo Emerson selected Goethe as one of six "representative men" in his work of the same name, along with Plato, Napoleon, and William Shakespeare. Goethe's comments and observations form the basis of several biographical works, most notably Johann Peter Eckermann's Conversations with Goethe. There are frequent references to Goethe's writings throughout the works of G. W. F. Hegel, Arthur Schopenhauer, Friedrich Nietzsche, Hermann Hesse, Thomas Mann, Sigmund Freud, and Carl Jung. Goethe's poems were set to music throughout the eighteenth and nineteenth centuries by a number of composers, including Wolfgang Amadeus Mozart, Ludwig van Beethoven, Franz Schubert, Robert Schumann, Johannes Brahms, Charles Gounod, Richard Wagner, Hugo Wolf, and Gustav Mahler.