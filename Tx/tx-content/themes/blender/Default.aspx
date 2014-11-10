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

POSTAL MAN: You want to see your monster? (GOSS NET 1) Tape 1/3 Page 3

00 00 08 22 CDR
AGS just got the mixture ratio shift.

00 00 08 24 CC
Roger. We got PU shift down here, too.

00 00 08 34 CDR
Well, it looks like a nice day for it. These thunderstorms down range is about all.

GRAND BAHAMA ISLANDS (REV 1)

00 00 08 52 CC
11, this is Houston. You are GO for staging. Over.

00 00 08 56 CDR
Understand, GO for staging. And - -

00 00 08 57 CC
Stand by for mode IV capability.

00 00 08 59 CDR
Okay. Mode IV.

00 00 09 00 CC
MARK.

00 00 09 01 CC
Mode IV capability.

00 00 09 15 CDR
Staging -

00 00 09 16 CDR
- And ignition.

00 00 09 19 CC
Ignition confirmed; thrust is GO, 11.

00 00 10 01 CC
Apollo 11, this is Houston. At 10 minutes, you are GO.

00 00 10 06 CDR
Roger. 11's GO.
About the Project

This project is an online interactive featuring the Eagle lunar landing. The presentation includes original Apollo 11 spaceflight video footage, communication audio, mission control room conversations, text transcripts, and telemetry data, all synchronized into an integrated audio-visual experience.

Until today, it has been impossible to comprehensively experience mankind's shining exploratory accomplishment in a singular experience. We have compiled hours of content available from public domain sources and various NASA websites. Thamtech staff and volunteers generously devoted their time to transcribe hours of speech to text. By using simultaneous space and land based audio and video, transcripts, images, spacecraft telemetry, and biomedical data—this synchronized presentation reveals the Moon Shot as experienced by the astronauts and flight controllers.

Our goal is to capture a moment in history so that generations may now relive the events with this interactive educational resource. The world remembers the moon landing as a major historical event but often fails to recognize the scale of the mission. This interactive resource aims to educate visitors while engaging them with the excitement of manned-spaceflight to build a passion for scientific exploration.

Visitors begin the experience by hearing the words of Buzz Aldrin while simultaneously viewing the moon through the lunar module window. Moments later, the audience hears capsule communicator Charlie Duke inform flight director Gene Kranz that the astronauts are on schedule to start the descent engine. Throughout the presentation, visitors are able to customize their experience by jumping to key moments in the timeline. The timeline guides visitors to the crucial moments in the mission, including: program alarms (computer alerts), famous Go No-Go polls in the control room, low level fuel milestones, and landing.

"The Eagle has Landed." Neil Armstrong's words signal a technical milestone and successful execution of John F. Kennedy's vision to land a man on the moon safely. Prior to these famous words, visitors see the synchronized audio communications, transcripts, video of the lunar module's casting a shadow on the lunar surface, and biomedical telemetry of Armstrong's heart rate surpassing 150 beats per minute!

The footprints from Buzz Aldrin and Neil Armstrong on July 20, 1969 paved the way for five additional successful trips to the lunar surface over the following years. Thamtech takes pride in providing visitors with a glimpse into this and mankind's enduring spirit for exploration.
- See more at: http://www.firstmenonthemoon.com/about.html#sthash.iXDZXDDA.dpufSun Nov  9 21:17:29 PST 2014
