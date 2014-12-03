tinyMCEPopup.requireLangPack();

var charmap = [
	['&nbsp;',    '&#160;',  true, 'no-break space'],
	['&amp;',     '&#38;',   true, 'ampersand'],
	['&quot;',    '&#34;',   true, 'quotation mark'],
// finance
	['&cent;',    '&#162;',  true, 'cent sign'],
	['&euro;',    '&#8364;', true, 'euro sign'],
	['&pound;',   '&#163;',  true, 'pound sign'],
	['&yen;',     '&#165;',  true, 'yen sign'],
// signs
	['&copy;',    '&#169;',  true, 'copyright sign'],
	['&reg;',     '&#174;',  true, 'registered sign'],
	['&trade;',   '&#8482;', true, 'trade mark sign'],
	['&permil;',  '&#8240;', true, 'per mille sign'],
	['&micro;',   '&#181;',  true, 'micro sign'],
	['&middot;',  '&#183;',  true, 'middle dot'],
	['&bull;',    '&#8226;', true, 'bullet'],
	['&hellip;',  '&#8230;', true, 'three dot leader'],
	['&prime;',   '&#8242;', true, 'minutes / feet'],
	['&Prime;',   '&#8243;', true, 'seconds / inches'],
	['&sect;',    '&#167;',  true, 'section sign'],
	['&para;',    '&#182;',  true, 'paragraph sign'],
	['&szlig;',   '&#223;',  true, 'sharp s / ess-zed'],
// quotations
	['&lsaquo;',  '&#8249;', true, 'single left-pointing angle quotation mark'],
	['&rsaquo;',  '&#8250;', true, 'single right-pointing angle quotation mark'],
	['&laquo;',   '&#171;',  true, 'left pointing guillemet'],
	['&raquo;',   '&#187;',  true, 'right pointing guillemet'],
	['&lsquo;',   '&#8216;', true, 'left single quotation mark'],
	['&rsquo;',   '&#8217;', true, 'right single quotation mark'],
	['&ldquo;',   '&#8220;', true, 'left double quotation mark'],
	['&rdquo;',   '&#8221;', true, 'right double quotation mark'],
	['&sbquo;',   '&#8218;', true, 'single low-9 quotation mark'],
	['&bdquo;',   '&#8222;', true, 'double low-9 quotation mark'],
	['&lt;',      '&#60;',   true, 'less-than sign'],
	['&gt;',      '&#62;',   true, 'greater-than sign'],
	['&le;',      '&#8804;', true, 'less-than or equal to'],
	['&ge;',      '&#8805;', true, 'greater-than or equal to'],
	['&ndash;',   '&#8211;', true, 'en dash'],
	['&mdash;',   '&#8212;', true, 'em dash'],
	['&macr;',    '&#175;',  true, 'macron'],
	['&oline;',   '&#8254;', true, 'overline'],
	['&curren;',  '&#164;',  true, 'currency sign'],
	['&brvbar;',  '&#166;',  true, 'broken bar'],
	['&uml;',     '&#168;',  true, 'diaeresis'],
	['&iexcl;',   '&#161;',  true, 'inverted exclamation mark'],
	['&iquest;',  '&#191;',  true, 'turned question mark'],
	['&circ;',    '&#710;',  true, 'circumflex accent'],
	['&tilde;',   '&#732;',  true, 'small tilde'],
	['&deg;',     '&#176;',  true, 'degree sign'],
	['&minus;',   '&#8722;', true, 'minus sign'],
	['&plusmn;',  '&#177;',  true, 'plus-minus sign'],
	['&divide;',  '&#247;',  true, 'division sign'],
	['&frasl;',   '&#8260;', true, 'fraction slash'],
	['&times;',   '&#215;',  true, 'multiplication sign'],
	['&sup1;',    '&#185;',  true, 'superscript one'],
	['&sup2;',    '&#178;',  true, 'superscript two'],
	['&sup3;',    '&#179;',  true, 'superscript three'],
	['&frac14;',  '&#188;',  true, 'fraction one quarter'],
	['&frac12;',  '&#189;',  true, 'fraction one half'],
	['&frac34;',  '&#190;',  true, 'fraction three quarters'],
// math / logical
	['&fnof;',    '&#402;',  true, 'function / florin'],
	['&int;',     '&#8747;', true, 'integral'],
	['&sum;',     '&#8721;', true, 'n-ary sumation'],
	['&infin;',   '&#8734;', true, 'infinity'],
	['&radic;',   '&#8730;', true, 'square root'],
	['&sim;',     '&#8764;', false,'similar to'],
	['&cong;',    '&#8773;', false,'approximately equal to'],
	['&asymp;',   '&#8776;', true, 'almost equal to'],
	['&ne;',      '&#8800;', true, 'not equal to'],
	['&equiv;',   '&#8801;', true, 'identical to'],
	['&isin;',    '&#8712;', false,'element of'],
	['&notin;',   '&#8713;', false,'not an element of'],
	['&ni;',      '&#8715;', false,'contains as member'],
	['&prod;',    '&#8719;', true, 'n-ary product'],
	['&and;',     '&#8743;', false,'logical and'],
	['&or;',      '&#8744;', false,'logical or'],
	['&not;',     '&#172;',  true, 'not sign'],
	['&cap;',     '&#8745;', true, 'intersection'],
	['&cup;',     '&#8746;', false,'union'],
	['&part;',    '&#8706;', true, 'partial differential'],
	['&forall;',  '&#8704;', false,'for all'],
	['&exist;',   '&#8707;', false,'there exists'],
	['&empty;',   '&#8709;', false,'diameter'],
	['&nabla;',   '&#8711;', false,'backward difference'],
	['&lowast;',  '&#8727;', false,'asterisk operator'],
	['&prop;',    '&#8733;', false,'proportional to'],
	['&ang;',     '&#8736;', false,'angle'],
// undefined
	['&acute;',   '&#180;',  true, 'acute accent'],
	['&cedil;',   '&#184;',  true, 'cedilla'],
	['&ordf;',    '&#170;',  true, 'feminine ordinal indicator'],
	['&ordm;',    '&#186;',  true, 'masculine ordinal indicator'],
	['&dagger;',  '&#8224;', true, 'dagger'],
	['&Dagger;',  '&#8225;', true, 'double dagger'],
// alphabetical special chars
	['&Agrave;',  '&#192;',  true, 'A - grave'],
	['&Aacute;',  '&#193;',  true, 'A - acute'],
	['&Acirc;',   '&#194;',  true, 'A - circumflex'],
	['&Atilde;',  '&#195;',  true, 'A - tilde'],
	['&Auml;',    '&#196;',  true, 'A - diaeresis'],
	['&Aring;',   '&#197;',  true, 'A - ring above'],
	['&AElig;',   '&#198;',  true, 'ligature AE'],
	['&Ccedil;',  '&#199;',  true, 'C - cedilla'],
	['&Egrave;',  '&#200;',  true, 'E - grave'],
	['&Eacute;',  '&#201;',  true, 'E - acute'],
	['&Ecirc;',   '&#202;',  true, 'E - circumflex'],
	['&Euml;',    '&#203;',  true, 'E - diaeresis'],
	['&Igrave;',  '&#204;',  true, 'I - grave'],
	['&Iacute;',  '&#205;',  true, 'I - acute'],
	['&Icirc;',   '&#206;',  true, 'I - circumflex'],
	['&Iuml;',    '&#207;',  true, 'I - diaeresis'],
	['&ETH;',     '&#208;',  true, 'ETH'],
	['&Ntilde;',  '&#209;',  true, 'N - tilde'],
	['&Ograve;',  '&#210;',  true, 'O - grave'],
	['&Oacute;',  '&#211;',  true, 'O - acute'],
	['&Ocirc;',   '&#212;',  true, 'O - circumflex'],
	['&Otilde;',  '&#213;',  true, 'O - tilde'],
	['&Ouml;',    '&#214;',  true, 'O - diaeresis'],
	['&Oslash;',  '&#216;',  true, 'O - slash'],
	['&OElig;',   '&#338;',  true, 'ligature OE'],
	['&Scaron;',  '&#352;',  true, 'S - caron'],
	['&Ugrave;',  '&#217;',  true, 'U - grave'],
	['&Uacute;',  '&#218;',  true, 'U - acute'],
	['&Ucirc;',   '&#219;',  true, 'U - circumflex'],
	['&Uuml;',    '&#220;',  true, 'U - diaeresis'],
	['&Yacute;',  '&#221;',  true, 'Y - acute'],
	['&Yuml;',    '&#376;',  true, 'Y - diaeresis'],
	['&THORN;',   '&#222;',  true, 'THORN'],
	['&agrave;',  '&#224;',  true, 'a - grave'],
	['&aacute;',  '&#225;',  true, 'a - acute'],
	['&acirc;',   '&#226;',  true, 'a - circumflex'],
	['&atilde;',  '&#227;',  true, 'a - tilde'],
	['&auml;',    '&#228;',  true, 'a - diaeresis'],
	['&aring;',   '&#229;',  true, 'a - ring above'],
	['&aelig;',   '&#230;',  true, 'ligature ae'],
	['&ccedil;',  '&#231;',  true, 'c - cedilla'],
	['&egrave;',  '&#232;',  true, 'e - grave'],
	['&eacute;',  '&#233;',  true, 'e - acute'],
	['&ecirc;',   '&#234;',  true, 'e - circumflex'],
	['&euml;',    '&#235;',  true, 'e - diaeresis'],
	['&igrave;',  '&#236;',  true, 'i - grave'],
	['&iacute;',  '&#237;',  true, 'i - acute'],
	['&icirc;',   '&#238;',  true, 'i - circumflex'],
	['&iuml;',    '&#239;',  true, 'i - diaeresis'],
	['&eth;',     '&#240;',  true, 'eth'],
	['&ntilde;',  '&#241;',  true, 'n - tilde'],
	['&ograve;',  '&#242;',  true, 'o - grave'],
	['&oacute;',  '&#243;',  true, 'o - acute'],
	['&ocirc;',   '&#244;',  true, 'o - circumflex'],
	['&otilde;',  '&#245;',  true, 'o - tilde'],
	['&ouml;',    '&#246;',  true, 'o - diaeresis'],
	['&oslash;',  '&#248;',  true, 'o slash'],
	['&oelig;',   '&#339;',  true, 'ligature oe'],
	['&scaron;',  '&#353;',  true, 's - caron'],
	['&ugrave;',  '&#249;',  true, 'u - grave'],
	['&uacute;',  '&#250;',  true, 'u - acute'],
	['&ucirc;',   '&#251;',  true, 'u - circumflex'],
	['&uuml;',    '&#252;',  true, 'u - diaeresis'],
	['&yacute;',  '&#253;',  true, 'y - acute'],
	['&thorn;',   '&#254;',  true, 'thorn'],
	['&yuml;',    '&#255;',  true, 'y - diaeresis'],
    ['&Alpha;',   '&#913;',  true, 'Alpha'],
	['&Beta;',    '&#914;',  true, 'Beta'],
	['&Gamma;',   '&#915;',  true, 'Gamma'],
	['&Delta;',   '&#916;',  true, 'Delta'],
	['&Epsilon;', '&#917;',  true, 'Epsilon'],
	['&Zeta;',    '&#918;',  true, 'Zeta'],
	['&Eta;',     '&#919;',  true, 'Eta'],
	['&Theta;',   '&#920;',  true, 'Theta'],
	['&Iota;',    '&#921;',  true, 'Iota'],
	['&Kappa;',   '&#922;',  true, 'Kappa'],
	['&Lambda;',  '&#923;',  true, 'Lambda'],
	['&Mu;',      '&#924;',  true, 'Mu'],
	['&Nu;',      '&#925;',  true, 'Nu'],
	['&Xi;',      '&#926;',  true, 'Xi'],
	['&Omicron;', '&#927;',  true, 'Omicron'],
	['&Pi;',      '&#928;',  true, 'Pi'],
	['&Rho;',     '&#929;',  true, 'Rho'],
	['&Sigma;',   '&#931;',  true, 'Sigma'],
	['&Tau;',     '&#932;',  true, 'Tau'],
	['&Upsilon;', '&#933;',  true, 'Upsilon'],
	['&Phi;',     '&#934;',  true, 'Phi'],
	['&Chi;',     '&#935;',  true, 'Chi'],
	['&Psi;',     '&#936;',  true, 'Psi'],
	['&Omega;',   '&#937;',  true, 'Omega'],
	['&alpha;',   '&#945;',  true, 'alpha'],
	['&beta;',    '&#946;',  true, 'beta'],
	['&gamma;',   '&#947;',  true, 'gamma'],
	['&delta;',   '&#948;',  true, 'delta'],
	['&epsilon;', '&#949;',  true, 'epsilon'],
	['&zeta;',    '&#950;',  true, 'zeta'],
	['&eta;',     '&#951;',  true, 'eta'],
	['&theta;',   '&#952;',  true, 'theta'],
	['&iota;',    '&#953;',  true, 'iota'],
	['&kappa;',   '&#954;',  true, 'kappa'],
	['&lambda;',  '&#955;',  true, 'lambda'],
	['&mu;',      '&#956;',  true, 'mu'],
	['&nu;',      '&#957;',  true, 'nu'],
	['&xi;',      '&#958;',  true, 'xi'],
	['&omicron;', '&#959;',  true, 'omicron'],
	['&pi;',      '&#960;',  true, 'pi'],
	['&rho;',     '&#961;',  true, 'rho'],
	['&sigmaf;',  '&#962;',  true, 'final sigma'],
	['&sigma;',   '&#963;',  true, 'sigma'],
	['&tau;',     '&#964;',  true, 'tau'],
	['&upsilon;', '&#965;',  true, 'upsilon'],
	['&phi;',     '&#966;',  true, 'phi'],
	['&chi;',     '&#967;',  true, 'chi'],
	['&psi;',     '&#968;',  true, 'psi'],
	['&omega;',   '&#969;',  true, 'omega'],
// symbols
	['&alefsym;', '&#8501;', false,'alef symbol'],
	['&piv;',     '&#982;',  false,'pi symbol'],
	['&real;',    '&#8476;', false,'real part symbol'],
	['&thetasym;','&#977;',  false,'theta symbol'],
	['&upsih;',   '&#978;',  false,'upsilon - hook symbol'],
	['&weierp;',  '&#8472;', false,'Weierstrass p'],
	['&image;',   '&#8465;', false,'imaginary part'],
// arrows
	['&larr;',    '&#8592;', true, 'leftwards arrow'],
	['&uarr;',    '&#8593;', true, 'upwards arrow'],
	['&rarr;',    '&#8594;', true, 'rightwards arrow'],
	['&darr;',    '&#8595;', true, 'downwards arrow'],
	['&harr;',    '&#8596;', true, 'left right arrow'],
	['&crarr;',   '&#8629;', false,'carriage return'],
	['&lArr;',    '&#8656;', false,'leftwards double arrow'],
	['&uArr;',    '&#8657;', false,'upwards double arrow'],
	['&rArr;',    '&#8658;', false,'rightwards double arrow'],
	['&dArr;',    '&#8659;', false,'downwards double arrow'],
	['&hArr;',    '&#8660;', false,'left right double arrow'],
	['&there4;',  '&#8756;', false,'therefore'],
	['&sub;',     '&#8834;', false,'subset of'],
	['&sup;',     '&#8835;', false,'superset of'],
	['&nsub;',    '&#8836;', false,'not a subset of'],
	['&sube;',    '&#8838;', false,'subset of or equal to'],
	['&supe;',    '&#8839;', false,'superset of or equal to'],
	['&oplus;',   '&#8853;', false,'circled plus'],
	['&otimes;',  '&#8855;', false,'circled times'],
	['&perp;',    '&#8869;', false,'perpendicular'],
	['&sdot;',    '&#8901;', false,'dot operator'],
	['&lceil;',   '&#8968;', false,'left ceiling'],
	['&rceil;',   '&#8969;', false,'right ceiling'],
	['&lfloor;',  '&#8970;', false,'left floor'],
	['&rfloor;',  '&#8971;', false,'right floor'],
	['&lang;',    '&#9001;', false,'left-pointing angle bracket'],
	['&rang;',    '&#9002;', false,'right-pointing angle bracket'],
	['&loz;',     '&#9674;', true,'lozenge'],
	['&spades;',  '&#9824;', false,'black spade suit'],
	['&clubs;',   '&#9827;', true, 'black club suit'],
	['&hearts;',  '&#9829;', true, 'black heart suit'],
	['&diams;',   '&#9830;', true, 'black diamond suit'],
	['&ensp;',    '&#8194;', false,'en space'],
	['&emsp;',    '&#8195;', false,'em space'],
	['&thinsp;',  '&#8201;', false,'thin space'],
	['&zwnj;',    '&#8204;', false,'zero width non-joiner'],
	['&zwj;',     '&#8205;', false,'zero width joiner'],
	['&lrm;',     '&#8206;', false,'left-to-right mark'],
	['&rlm;',     '&#8207;', false,'right-to-left mark'],
	['&shy;',     '&#173;',  false,'soft hyphen']
];

tinyMCEPopup.onInit.add(function() {
	tinyMCEPopup.dom.setHTML('charmapView', renderCharMapHTML());
});

function renderCharMapHTML() {
	var charsPerRow = 20, tdWidth=20, tdHeight=20, i;
	var html = '<table border="0" cellspacing="1" cellpadding="0" width="' + (tdWidth*charsPerRow) + '"><tr height="' + tdHeight + '">';
	var cols=-1;

	for (i=0; i<charmap.length; i++) {
		if (charmap[i][2]==true) {
			cols++;
			html += ''
				+ '<td class="charmap">'
				+ '<a onmouseover="previewChar(\'' + charmap[i][1].substring(1,charmap[i][1].length) + '\',\'' + charmap[i][0].substring(1,charmap[i][0].length) + '\',\'' + charmap[i][3] + '\');" onfocus="previewChar(\'' + charmap[i][1].substring(1,charmap[i][1].length) + '\',\'' + charmap[i][0].substring(1,charmap[i][0].length) + '\',\'' + charmap[i][3] + '\');" href="javascript:void(0)" onclick="insertChar(\'' + charmap[i][1].substring(2,charmap[i][1].length-1) + '\');" onclick="return false;" onmousedown="return false;" title="' + charmap[i][3] + '">'
				+ charmap[i][1]
				+ '</a></td>';
			if ((cols+1) % charsPerRow == 0)
				html += '</tr><tr height="' + tdHeight + '">';
		}
	 }

	if (cols % charsPerRow > 0) {
		var padd = charsPerRow - (cols % charsPerRow);
		for (var i=0; i<padd-1; i++)
			html += '<td width="' + tdWidth + '" height="' + tdHeight + '" class="charmap">&nbsp;</td>';
	}

	html += '</tr></table>';

	return html;
}

function insertChar(chr) {
	tinyMCEPopup.execCommand('mceInsertContent', false, '&#' + chr + ';');

	// Refocus in window
	if (tinyMCEPopup.isWindow)
		window.focus();

	tinyMCEPopup.editor.focus();
	tinyMCEPopup.close();
}

function previewChar(codeA, codeB, codeN) {
	var elmA = document.getElementById('codeA');
	var elmB = document.getElementById('codeB');
	var elmV = document.getElementById('codeV');
	var elmN = document.getElementById('codeN');

	if (codeA=='#160;') {
		elmV.innerHTML = '__';
	} else {
		elmV.innerHTML = '&' + codeA;
	}

	elmB.innerHTML = '&amp;' + codeA;
	elmA.innerHTML = '&amp;' + codeB;
	elmN.innerHTML = codeN;
}
The Gettysburg Address is a speech by U.S. President Abraham Lincoln, one of the best-known in American history.[4] It was delivered by Lincoln during the American Civil War, on the afternoon of Thursday, November 19, 1863, at the dedication of the Soldiers' National Cemetery in Gettysburg, Pennsylvania, four and a half months after the Union armies defeated those of the Confederacy at the Battle of Gettysburg.

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
M: Yes, they are.  Go look in the mirror and see for yourselves.“The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).

Everyone should begin collecting a private library in youth; the instinct of private property, which is fundamental in human beings, can here be cultivated with every advantage and no evils. One should have one's own bookshelves, which should not have doors, glass windows, or keys; they should be free and accessible to the hand as well as to the eye. The best of mural decorations is books; they are more varied in color and appearance than any wallpaper, they are more attractive in design, and they have the prime advantage of being separate personalities, so that if you sit alone in the room in the firelight, you are surrounded with intimate friends. The knowledge that they are there in plain view is both stimulating and refreshing. You do not have to read them all. Most of my indoor life is spent in a room containing six thousand books; and I have a stock answer to the invariable question that comes from strangers. "Have you read all of these books?"
"Some of them twice." This reply is both true and unexpected.MASK BOY: Well, that’s a start.

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