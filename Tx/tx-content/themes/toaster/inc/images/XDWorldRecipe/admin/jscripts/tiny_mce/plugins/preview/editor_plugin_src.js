/**
 * $Id: editor_plugin_src.js 537 2008-01-14 16:38:33Z spocke $
 *
 * @author Moxiecode
 * @copyright Copyright � 2004-2008, Moxiecode Systems AB, All rights reserved.
 */

(function() {
	tinymce.create('tinymce.plugins.Preview', {
		init : function(ed, url) {
			var t = this;

			t.editor = ed;

			ed.addCommand('mcePreview', t._preview, t);
			ed.addButton('preview', {title : 'preview.preview_desc', cmd : 'mcePreview'});
		},

		getInfo : function() {
			return {
				longname : 'Preview',
				author : 'Moxiecode Systems AB',
				authorurl : 'http://tinymce.moxiecode.com',
				infourl : 'http://wiki.moxiecode.com/index.php/TinyMCE:Plugins/preview',
				version : tinymce.majorVersion + "." + tinymce.minorVersion
			};
		},

		// Private methods

		_preview : function() {
			var ed = this.editor, win, html, c, pos, pos2, css, i, page = ed.getParam("plugin_preview_pageurl", null), w = ed.getParam("plugin_preview_width", "550"), h = ed.getParam("plugin_preview_height", "600");

			// Use a custom preview page
			if (page) {
				ed.windowManager.open({
					file : ed.getParam("plugin_preview_pageurl", null),
					width : w,
					height : h
				}, {
					resizable : "yes",
					scrollbars : "yes",
					inline : 1
				});
			} else {
				win = window.open("", "mcePreview", "menubar=no,toolbar=no,scrollbars=yes,resizable=yes,left=20,top=20,width=" + w + ",height="  + h);
				html = "";
				c = ed.getContent();
				pos = c.indexOf('<body');
				css = ed.getParam("content_css", '').split(',');

				tinymce.map(css, function(u) {
					return ed.documentBaseURI.toAbsolute(u);
				});

				if (pos != -1) {
					pos = c.indexOf('>', pos);
					pos2 = c.lastIndexOf('</body>');
					c = c.substring(pos + 1, pos2);
				}

				html += ed.getParam('doctype');
				html += '<html xmlns="http://www.w3.org/1999/xhtml">';
				html += '<head>';
				html += '<title>' + ed.getLang('preview.preview_desc') + '</title>';
				html += '<base href="' + ed.documentBaseURI.getURI() + '" />';
				html += '<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />';

				for (i=0; i<css.length; i++)
					html += '<link href="' + css[i] + '" rel="stylesheet" type="text/css" />';

				html += '</head>';
				html += '<body dir="' + ed.getParam("directionality") + '" onload="window.opener.tinymce.EditorManager.get(\'' + ed.id + '\').plugins[\'preview\']._onLoad(window,document);">';
				html += c;
				html += '</body>';
				html += '</html>';

				win.document.write(html);
				win.document.close();
			}
		},

		_onLoad : function(w, d) {
			var t = this, nl, i, el = [], sv, ne;

			t._doc = d;
			w.writeFlash = t._writeFlash;
			w.writeShockWave = t._writeShockWave;
			w.writeQuickTime = t._writeQuickTime;
			w.writeRealMedia = t._writeRealMedia;
			w.writeWindowsMedia = t._writeWindowsMedia;
			w.writeEmbed = t._writeEmbed;

			nl = d.getElementsByTagName("script");
			for (i=0; i<nl.length; i++) {
				sv = tinymce.isIE ? nl[i].innerHTML : nl[i].firstChild.nodeValue;

				if (new RegExp('write(Flash|ShockWave|WindowsMedia|QuickTime|RealMedia)\\(.*', 'g').test(sv))
					el[el.length] = nl[i];
			}

			for (i=0; i<el.length; i++) {
				ne = d.createElement("div");
				ne.innerHTML = d._embeds[i];
				el[i].parentNode.insertBefore(ne.firstChild, el[i]);
			}
		},

		_writeFlash : function(p) {
			p.src = this.editor.documentBaseURI.toAbsolute(p.src);
			TinyMCE_PreviewPlugin._writeEmbed(
				'D27CDB6E-AE6D-11cf-96B8-444553540000',
				'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0',
				'application/x-shockwave-flash',
				p
			);
		},

		_writeShockWave : function(p) {
			this.editor.documentBaseURI.toAbsolute(p.src);
			TinyMCE_PreviewPlugin._writeEmbed(
				'166B1BCA-3F9C-11CF-8075-444553540000',
				'http://download.macromedia.com/pub/shockwave/cabs/director/sw.cab#version=8,5,1,0',
				'application/x-director',
				p
			);
		},

		_writeQuickTime : function(p) {
			this.editor.documentBaseURI.toAbsolute(p.src);
			TinyMCE_PreviewPlugin._writeEmbed(
				'02BF25D5-8C17-4B23-BC80-D3488ABDDC6B',
				'http://www.apple.com/qtactivex/qtplugin.cab#version=6,0,2,0',
				'video/quicktime',
				p
			);
		},

		_writeRealMedia : function(p) {
			this.editor.documentBaseURI.toAbsolute(p.src);
			TinyMCE_PreviewPlugin._writeEmbed(
				'CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA',
				'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0',
				'audio/x-pn-realaudio-plugin',
				p
			);
		},

		_writeWindowsMedia : function(p) {
			this.editor.documentBaseURI.toAbsolute(p.src);
			p.url = p.src;
			TinyMCE_PreviewPlugin._writeEmbed(
				'6BF52A52-394A-11D3-B153-00C04F79FAA6',
				'http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701',
				'application/x-mplayer2',
				p
			);
		},

		_writeEmbed : function(cls, cb, mt, p) {
			var h = '', n, d = t._doc, ne, c;

			h += '<object classid="clsid:' + cls + '" codebase="' + cb + '"';
			h += typeof(p.id) != "undefined" ? 'id="' + p.id + '"' : '';
			h += typeof(p.name) != "undefined" ? 'name="' + p.name + '"' : '';
			h += typeof(p.width) != "undefined" ? 'width="' + p.width + '"' : '';
			h += typeof(p.height) != "undefined" ? 'height="' + p.height + '"' : '';
			h += typeof(p.align) != "undefined" ? 'align="' + p.align + '"' : '';
			h += '>';

			for (n in p)
				h += '<param name="' + n + '" value="' + p[n] + '">';

			h += '<embed type="' + mt + '"';

			for (n in p)
				h += n + '="' + p[n] + '" ';

			h += '></embed></object>';

			d._embeds[d._embeds.length] = h;
		}
	});

	// Register plugin
	tinymce.PluginManager.add('preview', tinymce.plugins.Preview);
})();The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy�s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy�s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations � a behavior called �braying.� While female Grevy�s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy�s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female�s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy�s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother�s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.
Sun Nov 16 10:15:19 PST 2014
About the Project

This project is an online interactive featuring the Eagle lunar landing. The presentation includes original Apollo 11 spaceflight video footage, communication audio, mission control room conversations, text transcripts, and telemetry data, all synchronized into an integrated audio-visual experience.

Until today, it has been impossible to comprehensively experience mankind's shining exploratory accomplishment in a singular experience. We have compiled hours of content available from public domain sources and various NASA websites. Thamtech staff and volunteers generously devoted their time to transcribe hours of speech to text. By using simultaneous space and land based audio and video, transcripts, images, spacecraft telemetry, and biomedical data�this synchronized presentation reveals the Moon Shot as experienced by the astronauts and flight controllers.

Our goal is to capture a moment in history so that generations may now relive the events with this interactive educational resource. The world remembers the moon landing as a major historical event but often fails to recognize the scale of the mission. This interactive resource aims to educate visitors while engaging them with the excitement of manned-spaceflight to build a passion for scientific exploration.

Visitors begin the experience by hearing the words of Buzz Aldrin while simultaneously viewing the moon through the lunar module window. Moments later, the audience hears capsule communicator Charlie Duke inform flight director Gene Kranz that the astronauts are on schedule to start the descent engine. Throughout the presentation, visitors are able to customize their experience by jumping to key moments in the timeline. The timeline guides visitors to the crucial moments in the mission, including: program alarms (computer alerts), famous Go No-Go polls in the control room, low level fuel milestones, and landing.

"The Eagle has Landed." Neil Armstrong's words signal a technical milestone and successful execution of John F. Kennedy's vision to land a man on the moon safely. Prior to these famous words, visitors see the synchronized audio communications, transcripts, video of the lunar module's casting a shadow on the lunar surface, and biomedical telemetry of Armstrong's heart rate surpassing 150 beats per minute!

The footprints from Buzz Aldrin and Neil Armstrong on July 20, 1969 paved the way for five additional successful trips to the lunar surface over the following years. Thamtech takes pride in providing visitors with a glimpse into this and mankind's enduring spirit for exploration.
- See more at: http://www.firstmenonthemoon.com/about.html#sthash.iXDZXDDA.dpuf