tinyMCEPopup.requireLangPack();

var detail = 50, strhex = "0123456789abcdef", i, isMouseDown = false, isMouseOver = false;

var colors = [
	"#000000","#000033","#000066","#000099","#0000cc","#0000ff","#330000","#330033",
	"#330066","#330099","#3300cc","#3300ff","#660000","#660033","#660066","#660099",
	"#6600cc","#6600ff","#990000","#990033","#990066","#990099","#9900cc","#9900ff",
	"#cc0000","#cc0033","#cc0066","#cc0099","#cc00cc","#cc00ff","#ff0000","#ff0033",
	"#ff0066","#ff0099","#ff00cc","#ff00ff","#003300","#003333","#003366","#003399",
	"#0033cc","#0033ff","#333300","#333333","#333366","#333399","#3333cc","#3333ff",
	"#663300","#663333","#663366","#663399","#6633cc","#6633ff","#993300","#993333",
	"#993366","#993399","#9933cc","#9933ff","#cc3300","#cc3333","#cc3366","#cc3399",
	"#cc33cc","#cc33ff","#ff3300","#ff3333","#ff3366","#ff3399","#ff33cc","#ff33ff",
	"#006600","#006633","#006666","#006699","#0066cc","#0066ff","#336600","#336633",
	"#336666","#336699","#3366cc","#3366ff","#666600","#666633","#666666","#666699",
	"#6666cc","#6666ff","#996600","#996633","#996666","#996699","#9966cc","#9966ff",
	"#cc6600","#cc6633","#cc6666","#cc6699","#cc66cc","#cc66ff","#ff6600","#ff6633",
	"#ff6666","#ff6699","#ff66cc","#ff66ff","#009900","#009933","#009966","#009999",
	"#0099cc","#0099ff","#339900","#339933","#339966","#339999","#3399cc","#3399ff",
	"#669900","#669933","#669966","#669999","#6699cc","#6699ff","#999900","#999933",
	"#999966","#999999","#9999cc","#9999ff","#cc9900","#cc9933","#cc9966","#cc9999",
	"#cc99cc","#cc99ff","#ff9900","#ff9933","#ff9966","#ff9999","#ff99cc","#ff99ff",
	"#00cc00","#00cc33","#00cc66","#00cc99","#00cccc","#00ccff","#33cc00","#33cc33",
	"#33cc66","#33cc99","#33cccc","#33ccff","#66cc00","#66cc33","#66cc66","#66cc99",
	"#66cccc","#66ccff","#99cc00","#99cc33","#99cc66","#99cc99","#99cccc","#99ccff",
	"#cccc00","#cccc33","#cccc66","#cccc99","#cccccc","#ccccff","#ffcc00","#ffcc33",
	"#ffcc66","#ffcc99","#ffcccc","#ffccff","#00ff00","#00ff33","#00ff66","#00ff99",
	"#00ffcc","#00ffff","#33ff00","#33ff33","#33ff66","#33ff99","#33ffcc","#33ffff",
	"#66ff00","#66ff33","#66ff66","#66ff99","#66ffcc","#66ffff","#99ff00","#99ff33",
	"#99ff66","#99ff99","#99ffcc","#99ffff","#ccff00","#ccff33","#ccff66","#ccff99",
	"#ccffcc","#ccffff","#ffff00","#ffff33","#ffff66","#ffff99","#ffffcc","#ffffff"
];

var named = {
	'#F0F8FF':'AliceBlue','#FAEBD7':'AntiqueWhite','#00FFFF':'Aqua','#7FFFD4':'Aquamarine','#F0FFFF':'Azure','#F5F5DC':'Beige',
	'#FFE4C4':'Bisque','#000000':'Black','#FFEBCD':'BlanchedAlmond','#0000FF':'Blue','#8A2BE2':'BlueViolet','#A52A2A':'Brown',
	'#DEB887':'BurlyWood','#5F9EA0':'CadetBlue','#7FFF00':'Chartreuse','#D2691E':'Chocolate','#FF7F50':'Coral','#6495ED':'CornflowerBlue',
	'#FFF8DC':'Cornsilk','#DC143C':'Crimson','#00FFFF':'Cyan','#00008B':'DarkBlue','#008B8B':'DarkCyan','#B8860B':'DarkGoldenRod',
	'#A9A9A9':'DarkGray','#A9A9A9':'DarkGrey','#006400':'DarkGreen','#BDB76B':'DarkKhaki','#8B008B':'DarkMagenta','#556B2F':'DarkOliveGreen',
	'#FF8C00':'Darkorange','#9932CC':'DarkOrchid','#8B0000':'DarkRed','#E9967A':'DarkSalmon','#8FBC8F':'DarkSeaGreen','#483D8B':'DarkSlateBlue',
	'#2F4F4F':'DarkSlateGray','#2F4F4F':'DarkSlateGrey','#00CED1':'DarkTurquoise','#9400D3':'DarkViolet','#FF1493':'DeepPink','#00BFFF':'DeepSkyBlue',
	'#696969':'DimGray','#696969':'DimGrey','#1E90FF':'DodgerBlue','#B22222':'FireBrick','#FFFAF0':'FloralWhite','#228B22':'ForestGreen',
	'#FF00FF':'Fuchsia','#DCDCDC':'Gainsboro','#F8F8FF':'GhostWhite','#FFD700':'Gold','#DAA520':'GoldenRod','#808080':'Gray','#808080':'Grey',
	'#008000':'Green','#ADFF2F':'GreenYellow','#F0FFF0':'HoneyDew','#FF69B4':'HotPink','#CD5C5C':'IndianRed','#4B0082':'Indigo','#FFFFF0':'Ivory',
	'#F0E68C':'Khaki','#E6E6FA':'Lavender','#FFF0F5':'LavenderBlush','#7CFC00':'LawnGreen','#FFFACD':'LemonChiffon','#ADD8E6':'LightBlue',
	'#F08080':'LightCoral','#E0FFFF':'LightCyan','#FAFAD2':'LightGoldenRodYellow','#D3D3D3':'LightGray','#D3D3D3':'LightGrey','#90EE90':'LightGreen',
	'#FFB6C1':'LightPink','#FFA07A':'LightSalmon','#20B2AA':'LightSeaGreen','#87CEFA':'LightSkyBlue','#778899':'LightSlateGray','#778899':'LightSlateGrey',
	'#B0C4DE':'LightSteelBlue','#FFFFE0':'LightYellow','#00FF00':'Lime','#32CD32':'LimeGreen','#FAF0E6':'Linen','#FF00FF':'Magenta','#800000':'Maroon',
	'#66CDAA':'MediumAquaMarine','#0000CD':'MediumBlue','#BA55D3':'MediumOrchid','#9370D8':'MediumPurple','#3CB371':'MediumSeaGreen','#7B68EE':'MediumSlateBlue',
	'#00FA9A':'MediumSpringGreen','#48D1CC':'MediumTurquoise','#C71585':'MediumVioletRed','#191970':'MidnightBlue','#F5FFFA':'MintCream','#FFE4E1':'MistyRose','#FFE4B5':'Moccasin',
	'#FFDEAD':'NavajoWhite','#000080':'Navy','#FDF5E6':'OldLace','#808000':'Olive','#6B8E23':'OliveDrab','#FFA500':'Orange','#FF4500':'OrangeRed','#DA70D6':'Orchid',
	'#EEE8AA':'PaleGoldenRod','#98FB98':'PaleGreen','#AFEEEE':'PaleTurquoise','#D87093':'PaleVioletRed','#FFEFD5':'PapayaWhip','#FFDAB9':'PeachPuff',
	'#CD853F':'Peru','#FFC0CB':'Pink','#DDA0DD':'Plum','#B0E0E6':'PowderBlue','#800080':'Purple','#FF0000':'Red','#BC8F8F':'RosyBrown','#4169E1':'RoyalBlue',
	'#8B4513':'SaddleBrown','#FA8072':'Salmon','#F4A460':'SandyBrown','#2E8B57':'SeaGreen','#FFF5EE':'SeaShell','#A0522D':'Sienna','#C0C0C0':'Silver',
	'#87CEEB':'SkyBlue','#6A5ACD':'SlateBlue','#708090':'SlateGray','#708090':'SlateGrey','#FFFAFA':'Snow','#00FF7F':'SpringGreen',
	'#4682B4':'SteelBlue','#D2B48C':'Tan','#008080':'Teal','#D8BFD8':'Thistle','#FF6347':'Tomato','#40E0D0':'Turquoise','#EE82EE':'Violet',
	'#F5DEB3':'Wheat','#FFFFFF':'White','#F5F5F5':'WhiteSmoke','#FFFF00':'Yellow','#9ACD32':'YellowGreen'
};

function init() {
	var inputColor = convertRGBToHex(tinyMCEPopup.getWindowArg('input_color'));

	tinyMCEPopup.resizeToInnerSize();

	generatePicker();

	if (inputColor) {
		changeFinalColor(inputColor);

		col = convertHexToRGB(inputColor);

		if (col)
			updateLight(col.r, col.g, col.b);
	}
}

function insertAction() {
	var color = document.getElementById("color").value, f = tinyMCEPopup.getWindowArg('func');

	tinyMCEPopup.restoreSelection();

	if (f)
		f(color);

	tinyMCEPopup.close();
}

function showColor(color, name) {
	if (name)
		document.getElementById("colorname").innerHTML = name;

	document.getElementById("preview").style.backgroundColor = color;
	document.getElementById("color").value = color.toLowerCase();
}

function convertRGBToHex(col) {
	var re = new RegExp("rgb\\s*\\(\\s*([0-9]+).*,\\s*([0-9]+).*,\\s*([0-9]+).*\\)", "gi");

	if (!col)
		return col;

	var rgb = col.replace(re, "$1,$2,$3").split(',');
	if (rgb.length == 3) {
		r = parseInt(rgb[0]).toString(16);
		g = parseInt(rgb[1]).toString(16);
		b = parseInt(rgb[2]).toString(16);

		r = r.length == 1 ? '0' + r : r;
		g = g.length == 1 ? '0' + g : g;
		b = b.length == 1 ? '0' + b : b;

		return "#" + r + g + b;
	}

	return col;
}

function convertHexToRGB(col) {
	if (col.indexOf('#') != -1) {
		col = col.replace(new RegExp('[^0-9A-F]', 'gi'), '');

		r = parseInt(col.substring(0, 2), 16);
		g = parseInt(col.substring(2, 4), 16);
		b = parseInt(col.substring(4, 6), 16);

		return {r : r, g : g, b : b};
	}

	return null;
}

function generatePicker() {
	var el = document.getElementById('light'), h = '', i;

	for (i = 0; i < detail; i++){
		h += '<div id="gs'+i+'" style="background-color:#000000; width:15px; height:3px; border-style:none; border-width:0px;"'
		+ ' onclick="changeFinalColor(this.style.backgroundColor)"'
		+ ' onmousedown="isMouseDown = true; return false;"'
		+ ' onmouseup="isMouseDown = false;"'
		+ ' onmousemove="if (isMouseDown && isMouseOver) changeFinalColor(this.style.backgroundColor); return false;"'
		+ ' onmouseover="isMouseOver = true;"'
		+ ' onmouseout="isMouseOver = false;"'
		+ '></div>';
	}

	el.innerHTML = h;
}

function generateWebColors() {
	var el = document.getElementById('webcolors'), h = '', i;

	if (el.className == 'generated')
		return;

	h += '<table border="0" cellspacing="1" cellpadding="0">'
		+ '<tr>';

	for (i=0; i<colors.length; i++) {
		h += '<td bgcolor="' + colors[i] + '" width="10" height="10">'
			+ '<a href="javascript:insertAction();" onfocus="showColor(\'' + colors[i] +  '\');" onmouseover="showColor(\'' + colors[i] +  '\');" style="display:block;width:10px;height:10px;overflow:hidden;">'
			+ '</a></td>';
		if ((i+1) % 18 == 0)
			h += '</tr><tr>';
	}

	h += '</table>';

	el.innerHTML = h;
	el.className = 'generated';
}

function generateNamedColors() {
	var el = document.getElementById('namedcolors'), h = '', n, v, i = 0;

	if (el.className == 'generated')
		return;

	for (n in named) {
		v = named[n];
		h += '<a href="javascript:insertAction();" onmouseover="showColor(\'' + n +  '\',\'' + v + '\');" style="background-color: ' + n + '"><!-- IE --></a>'
	}

	el.innerHTML = h;
	el.className = 'generated';
}

function dechex(n) {
	return strhex.charAt(Math.floor(n / 16)) + strhex.charAt(n % 16);
}

function computeColor(e) {
	var x, y, partWidth, partDetail, imHeight, r, g, b, coef, i, finalCoef, finalR, finalG, finalB;

	x = e.offsetX ? e.offsetX : (e.target ? e.clientX - e.target.x : 0);
	y = e.offsetY ? e.offsetY : (e.target ? e.clientY - e.target.y : 0);

	partWidth = document.getElementById('colors').width / 6;
	partDetail = detail / 2;
	imHeight = document.getElementById('colors').height;

	r = (x >= 0)*(x < partWidth)*255 + (x >= partWidth)*(x < 2*partWidth)*(2*255 - x * 255 / partWidth) + (x >= 4*partWidth)*(x < 5*partWidth)*(-4*255 + x * 255 / partWidth) + (x >= 5*partWidth)*(x < 6*partWidth)*255;
	g = (x >= 0)*(x < partWidth)*(x * 255 / partWidth) + (x >= partWidth)*(x < 3*partWidth)*255	+ (x >= 3*partWidth)*(x < 4*partWidth)*(4*255 - x * 255 / partWidth);
	b = (x >= 2*partWidth)*(x < 3*partWidth)*(-2*255 + x * 255 / partWidth) + (x >= 3*partWidth)*(x < 5*partWidth)*255 + (x >= 5*partWidth)*(x < 6*partWidth)*(6*255 - x * 255 / partWidth);

	coef = (imHeight - y) / imHeight;
	r = 128 + (r - 128) * coef;
	g = 128 + (g - 128) * coef;
	b = 128 + (b - 128) * coef;

	changeFinalColor('#' + dechex(r) + dechex(g) + dechex(b));
	updateLight(r, g, b);
}

function updateLight(r, g, b) {
	var i, partDetail = detail / 2, finalCoef, finalR, finalG, finalB, color;

	for (i=0; i<detail; i++) {
		if ((i>=0) && (i<partDetail)) {
			finalCoef = i / partDetail;
			finalR = dechex(255 - (255 - r) * finalCoef);
			finalG = dechex(255 - (255 - g) * finalCoef);
			finalB = dechex(255 - (255 - b) * finalCoef);
		} else {
			finalCoef = 2 - i / partDetail;
			finalR = dechex(r * finalCoef);
			finalG = dechex(g * finalCoef);
			finalB = dechex(b * finalCoef);
		}

		color = finalR + finalG + finalB;

		document.getElementById('gs' + i).style.backgroundColor = '#'+color;
	}
}

function changeFinalColor(color) {
	if (color.indexOf('#') == -1)
		color = convertRGBToHex(color);

	document.getElementById('preview').style.backgroundColor = color;
	document.getElementById('color').value = color;
}

tinyMCEPopup.onInit.add(init);
Replicating nanorobots

MNT nanofacturing is popularly linked with the idea of swarms of coordinated nanoscale robots working together, a popularization of an early proposal by K. Eric Drexler in his 1986 discussions of MNT, but superseded in 1992. In this early proposal, sufficiently capable nanorobots would construct more nanorobots in an artificial environment containing special molecular building blocks.

Critics have doubted both the feasibility of self-replicating nanorobots and the feasibility of control if self-replicating nanorobots could be achieved: they cite the possibility of mutations removing any control and favoring reproduction of mutant pathogenic variations. Advocates address the first doubt by pointing out that the first macroscale autonomous machine replicator, made of Lego blocks, was built and operated experimentally in 2002.[8] While there are sensory advantages present at the macroscale compared to the limited sensorium available at the nanoscale, proposals for positionally controlled nanoscale mechanosynthetic fabrication systems employ dead reckoning of tooltips combined with reliable reaction sequence design to ensure reliable results, hence a limited sensorium is no handicap; similar considerations apply to the positional assembly of small nanoparts. Advocates address the second doubt by arguing that bacteria are (of necessity) evolved to evolve, while nanorobot mutation could be actively prevented by common error-correcting techniques. Similar ideas are advocated in the Foresight Guidelines on Molecular Nanotechnology,[9] and a map of the 137-dimensional replicator design space[10] recently published by Freitas and Merkle provides numerous proposed methods by which replicators could, in principle, be safely controlled by good design.

However, the concept of suppressing mutation raises the question: How can design evolution occur at the nanoscale without a process of random mutation and deterministic selection? Critics argue that MNT advocates have not provided a substitute for such a process of evolution in this nanoscale arena where conventional sensory-based selection processes are lacking. The limits of the sensorium available at the nanoscale could make it difficult or impossible to winnow successes from failures. Advocates argue that design evolution should occur deterministically and strictly under human control, using the conventional engineering paradigm of modeling, design, prototyping, testing, analysis, and redesign.Sun Nov  9 20:57:11 PST 2014
% Marge steps in.

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
M: Yes, they are.  Go look in the mirror and see for yourselves.Thu Dec  4 16:13:10 PST 2014
Sat Dec  6 17:18:07 PST 2014
Replicating nanorobots

MNT nanofacturing is popularly linked with the idea of swarms of coordinated nanoscale robots working together, a popularization of an early proposal by K. Eric Drexler in his 1986 discussions of MNT, but superseded in 1992. In this early proposal, sufficiently capable nanorobots would construct more nanorobots in an artificial environment containing special molecular building blocks.

Critics have doubted both the feasibility of self-replicating nanorobots and the feasibility of control if self-replicating nanorobots could be achieved: they cite the possibility of mutations removing any control and favoring reproduction of mutant pathogenic variations. Advocates address the first doubt by pointing out that the first macroscale autonomous machine replicator, made of Lego blocks, was built and operated experimentally in 2002.[8] While there are sensory advantages present at the macroscale compared to the limited sensorium available at the nanoscale, proposals for positionally controlled nanoscale mechanosynthetic fabrication systems employ dead reckoning of tooltips combined with reliable reaction sequence design to ensure reliable results, hence a limited sensorium is no handicap; similar considerations apply to the positional assembly of small nanoparts. Advocates address the second doubt by arguing that bacteria are (of necessity) evolved to evolve, while nanorobot mutation could be actively prevented by common error-correcting techniques. Similar ideas are advocated in the Foresight Guidelines on Molecular Nanotechnology,[9] and a map of the 137-dimensional replicator design space[10] recently published by Freitas and Merkle provides numerous proposed methods by which replicators could, in principle, be safely controlled by good design.

However, the concept of suppressing mutation raises the question: How can design evolution occur at the nanoscale without a process of random mutation and deterministic selection? Critics argue that MNT advocates have not provided a substitute for such a process of evolution in this nanoscale arena where conventional sensory-based selection processes are lacking. The limits of the sensorium available at the nanoscale could make it difficult or impossible to winnow successes from failures. Advocates argue that design evolution should occur deterministically and strictly under human control, using the conventional engineering paradigm of modeling, design, prototyping, testing, analysis, and redesign.

The three then travel to Rebma, a reflection of Amber underwater, and there they meet their sister Llewella and Moire, the queen of Rebma. Moire originally believes that the three of them came to Rebma to seek support to defeat Eric but Deirdre explains their true intentions. Because Rebma is a reflection of Amber, there is also a reflection of the Pattern in Rebma, which Corwin is to walk to restore his memory. Although Random is impounded for past crimes in Rebma and sentenced to wed a blind girl named Vialle, Corwin convinces the queen Moire to allow him to walk the Pattern. After receiving advice from Random and Deirdre, he walks the Pattern, reliving all of his past life, which stretches back to his time in Amber and when Eric deposited him in Elizabethan England on our Earth. He remembers the powers which his heritage and the Pattern grant him - the power to walk through shadow, and to pronounce a powerful curse before dying. After he completes the Pattern he uses its power to project himself into the Castle of Amber, from which he finds a safe spot and rests.Shoe making is the process of making Foot Wear. Originally, shoes were made one at a time by hand. Traditional handicraft shoemaking has now been largely superseded in volume of shoes produced by industrial mass production of footwear, but not necessarily in quality, attention to detail, or craftsmanship.

Shoemakers or cordwainers (cobblers being those who repair shoes) may produce a range of footwear items, including shoes, boots, sandals, clogs and moccasins. Such items are generally made of leather, wood, rubber, plastic, jute or other plant material, and often consist of multiple parts for better durability of the sole, stitched to a leather upper.

I am not reciting these facts for the purpose of recrimination. That I judge to be utterly futile and even harmful. We cannot afford it. I recite them in order to explain why it was we did not have, as we could have had, between twelve and fourteen British divisions fighting in the line in this great battle instead of only three. Now I put all this aside. I put it on the shelf, from which the historians, when they have time, will select their documents to tell their stories. We have to think of the future and not of the past. This also applies in a small way to our own affairs at home. There are many who would hold an inquest in the House of Commons on the conduct of the Governments--and of Parliaments, for they are in it, too--during the years which led up to this catastrophe. They seek to indict those who were responsible for the guidance of our affairs. This also would be a foolish and pernicious process. There are too many in it. Let each man search his conscience and search his speeches. I frequently search mine. Modern Furniture designed for Modern Living.
Welcome To Viesso, we�re really glad you found us! We believe modern furniture isn�t just about the modern style. It�s also about products that appeal to and enhance our modern lives. Smart design, custom options, Green furniture materials, innovative functions. It all adds up to giving you exciting solutions for your space. We've scoured the marketplace to bring you home furnishings that meet these modern ideals.

Our exclusive Viesso line, which is American built, offers numerous options throughout the shopping process so that you can create custom furniture that is completely unique. It�s contemporary furniture only sold here. Designed by us. Customized by you. Choose size, finish, filling, and more!

All of our modern furniture products can be priced and purchased either in our Los Angeles showroom or online, where we encourage you to request material samples and ask us for advice in choosing the perfect pieces for your space. We hope you enjoy exploring our website, and please don�t hesitate to contact us with any questions along the way.Sun Apr 12 13:35:57 PDT 2015
