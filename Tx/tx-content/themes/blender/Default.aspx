<%@ Page Language="VB" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<script runat="server">

    Protected Sub CreateEventButton_Click(ByVal sender As Object, ByVal e As System.EventArgs)
        Dim CalendarItem As String = CreateEntry(Day_TextBox.Text, TimeStart_TextBox.Text & ":00", TimeEnd_TextBox.Text & ":00", SUMMARY_TextBox.Text, DESCRIPTION_TextBox.Text, LOCATION_TextBox.Text, UID_TextBox.Text)
        Dim bSourceData As Byte() = System.Text.Encoding.ASCII.GetBytes(CalendarItem)

        Response.Clear()
        Response.ClearHeaders()
        Response.AddHeader("Content-Disposition", "attachment; filename=calendar.ics")
        Response.AddHeader("Content-Length", bSourceData.Length.ToString)
        Response.ContentType = "application/octet-stream"
        Response.BinaryWrite(bSourceData)
        Response.End()
    End Sub
    
    Public Function CreateEntry(ByVal startDate As String, ByVal startTime As String, ByVal endTime As String, ByVal summary As String, ByVal description As String, ByVal location As String, ByVal organizerEmail As String) As String
        Const c_strTimeFormat As String = "yyyyMMdd\THHmmss\Z"
        Dim strStartTime As String = ""
        Dim strEndTime As String = ""
        Dim strTimeStamp As String = ""
        Dim strTempStartTime As String = ""
        Dim strTempEndTime As String = ""
        Dim iCalendarFile As String = ""

        ' iCalendar Format.
        Const iCAL_FILE As String = "BEGIN:VCALENDAR " & vbCrLf & _
        "PRODID:-//Calendar Updates//EN " & vbCrLf & _
        "VERSION:2.0 " & vbCrLf & _
        "METHOD:PUBLISH " & vbCrLf & _
        "BEGIN:VEVENT " & vbCrLf & _
        "DTSTAMP{0} " & vbCrLf & _
        "DTSTART{0} " & vbCrLf & _
        "DTEND{1} " & vbCrLf & _
        "TRANSP:TRANSPARENT " & vbCrLf & _
        "CATEGORIES:Appointment " & vbCrLf & _
        "SUMMARY:{2} " & vbCrLf & _
        "DESCRIPTION:{3} " & vbCrLf & _
        "LOCATION:{4} " & vbCrLf & _
        "UID:{5} " & vbCrLf & _
        "PRIORITY:0" & vbCrLf & _
        "End:VEVENT " & vbCrLf & _
        "End:VCALENDAR "

        Dim dtmStartDate As DateTime = DateTime.Parse(startDate.ToString)
        Dim dtmStartTime As DateTime = DateTime.Parse(startDate + " " + startTime.ToString)
        Dim dtmEndTime As DateTime = DateTime.Parse(startDate + " " + endTime.ToString)
        strTempStartTime = String.Format("{0} {1}", dtmStartDate.ToShortDateString, dtmStartTime.ToLongTimeString)
        strTempEndTime = String.Format("{0} {1}", dtmStartDate.ToShortDateString, dtmEndTime.ToLongTimeString)
        strTimeStamp = (DateTime.Parse(strTempStartTime)).ToUniversalTime.ToString(c_strTimeFormat)
        strStartTime = String.Format(":{0}", strTimeStamp)
        strEndTime = String.Format(":{0}", (DateTime.Parse(strTempEndTime)).ToUniversalTime.ToString(c_strTimeFormat))

        iCalendarFile = String.Format(iCAL_FILE, strStartTime, strEndTime, summary, description, location, organizerEmail.Trim)

        Return iCalendarFile
    End Function
</script>

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Create an Add to my Calendar Button</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <h2>
        Create a Calendar Event<br />
        </h2>
        <p>
            Day
            <asp:TextBox ID="Day_TextBox" runat="server" Width="84px">6/1/2006</asp:TextBox></p>
        <p>
            Time Start
            <asp:TextBox ID="TimeStart_TextBox" runat="server" Width="48px">11:00</asp:TextBox></p>
        <p>
            Time End
            <asp:TextBox ID="TimeEnd_TextBox" runat="server" Width="48px">13:30</asp:TextBox></p>
        <p>
            SUMMARY
            <asp:TextBox ID="SUMMARY_TextBox" runat="server" Width="206px">This is a Calendar entry</asp:TextBox></p>
        <p>
            DESCRIPTION
            <asp:TextBox ID="DESCRIPTION_TextBox" runat="server" Rows="2" TextMode="MultiLine">This is an example of a test calendar entry.</asp:TextBox></p>
        <p>
            LOCATION
            <asp:TextBox ID="LOCATION_TextBox" runat="server">Los Angeles</asp:TextBox></p>
        <p>
            UID
            <asp:TextBox ID="UID_TextBox" runat="server">Joe User</asp:TextBox></p>
        <p>
            <asp:Button ID="CreateEventButton" runat="server" OnClick="CreateEventButton_Click"
                Text="Add This Event To My Calendar" />&nbsp;</p>
    
    </div>
    </form>
</body>
</html>MASK BOY: Well, that’s a start.

CHER: Well, what about your news?

MASK BOY: Oh, it’s no big deal. I met a girl. We’re going out.

CHER: Come on. Tell us.

MASK BOY: Well, her name is Diana ….

CHER: Yeah?

MASK BOY: And she’s beautiful. She’s got long blond hair. (A friend whistles.) She rides horses and she’s beautiful and she’s smart and she loves me.

CHER: What’s not to love, baby?

SCENE 14 (OLD MAN enters his kitchen. Sitting at the table, he looks through a photo album filled with pictures of himself and his son, the Great Mutato as a child.. POLLIDORI enters, angry.)

POLLIDORI: Tell me it isn’t true. You didn’t. You wouldn’t. Why?

OLD MAN: Because I can.

(POLLIDORI attacks OLD MAN)

(Grunting as we see the shadows of the two men struggling, POLLIDORI strangling the OLD MAN.)

(Commercial.)

SCENE 15 (J.J.’S diner. MULDER enters. Everyone is hostile. MULDER defensive and over it. Exact opposite of earlier scene. LARGE MAN sticks his leg out as if to trip MULDER.)

LARGE MAN: (sarcastically) Excuse me.

MULDER: (also sarcastic) Not a problem.

(Another hostile diner flips a spoonful of grits? at MULDER which lands on the back of his neck. MULDER reacts with disgust. He sits down and wipes the offending matter away. In the kitchen, J.J. spits loudly onto a plate of barely cooked eggs and hands it to the WAITRESS. The WAITRESS puts it down in front of MULDER.)

MULDER: (looking at disgusting eggs) What’s this?

WAITRESS: (saccharine) Compliments of J.J. Coffee?

MULDER: (looking away) Sure.

(WAITRESS spills hot coffee in MULDER’S lap. He jumps up, brushing off his crotch.)

MULDER: Whoa! That’s not a place you want to burn a guy.

(WAITRESS flounces away. MULDER looks as newspaper headline : "FBI Agents Say Monster A ‘Hoax’.")

(Commotion outside. Lots of people running past the diner. MULDER follows them to where there is a large gathering outside the post office.)

POSTAL MAN: You want to see your monster? 