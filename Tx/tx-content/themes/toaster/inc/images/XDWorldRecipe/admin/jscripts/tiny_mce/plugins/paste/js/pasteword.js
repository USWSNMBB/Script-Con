tinyMCEPopup.requireLangPack();

function saveContent() {
	var html = document.getElementById("frmData").contentWindow.document.body.innerHTML;

	if (html == ''){
		tinyMCEPopup.close();
		return false;
	}

	tinyMCEPopup.execCommand('mcePasteWord', false, html);
	tinyMCEPopup.close();
}

function onLoadInit() {
	tinyMCEPopup.resizeToInnerSize();

	// Fix for endless reloading in FF
	window.setTimeout(createIFrame, 10);
}

function createIFrame() {
	document.getElementById('iframecontainer').innerHTML = '<iframe id="frmData" name="frmData" class="sourceIframe" src="blank.htm" height="280" width="400" frameborder="0" style="background-color:#FFFFFF; width:100%;" dir="ltr" wrap="soft"></iframe>';
}

var wHeight=0, wWidth=0, owHeight=0, owWidth=0;

function initIframe(doc) {
	var dir = tinyMCEPopup.editor.settings.directionality;

	doc.body.dir = dir;

	// Remove Gecko spellchecking
	if (tinymce.isGecko)
		doc.body.spellcheck = tinyMCEPopup.getParam("gecko_spellcheck");

	resizeInputs();
}

function resizeInputs() {
	if (!tinymce.isIE) {
		wHeight = self.innerHeight - 80;
		wWidth = self.innerWidth - 18;
	} else {
		wHeight = document.body.clientHeight - 80;
		wWidth = document.body.clientWidth - 18;
	}

	var elm = document.getElementById('frmData');
	if (elm) {
		elm.style.height = Math.abs(wHeight) + 'px';
		elm.style.width  = Math.abs(wWidth) + 'px';
	}
}

tinyMCEPopup.onInit.add(onLoadInit);
You do somethin' to me,
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
The Gettysburg Address is a speech by U.S. President Abraham Lincoln, one of the best-known in American history.[4] It was delivered by Lincoln during the American Civil War, on the afternoon of Thursday, November 19, 1863, at the dedication of the Soldiers' National Cemetery in Gettysburg, Pennsylvania, four and a half months after the Union armies defeated those of the Confederacy at the Battle of Gettysburg.

Abraham Lincoln's carefully crafted address, secondary to other presentations that day, came to be regarded as one of the greatest speeches in American history. In just over two minutes, Lincoln reiterated the principles of human equality espoused by the Declaration of Independence[5] and proclaimed the Civil War as a struggle for the preservation of the Union sundered by the secession crisis,[6] with "a new birth of freedom",[7] that would bring true equality[8] to all of its citizens.[8] Lincoln also redefined the Civil War as a struggle not just for the Union, but also for the principle of human equality.[5]

Beginning with the now-iconic phrase "Four score and seven years ago"—referring to the Declaration of Independence, written at the start of the American Revolution in 1776—Lincoln examined the founding principles of the United States in the context of the Civil War, and memorialized the sacrifices of those who gave their lives at Gettysburg and extolled virtues for the listeners (and the nation) to ensure the survival of America's representative democracy, that "government of the people, by the people, for the people, shall not perish from the earth."

Despite the speech's prominent place in the history and popular culture of the United States, the exact wording and location of the speech are disputed. The five known manuscripts of the Gettysburg Address differ in a number of details and also differ from contemporary newspaper reprints of the speech. Modern scholarship locates the speakers' platform 40 yards (or more) away from the Traditional Site within Soldiers' National Cemetery at the Soldiers' National Monument and entirely within private, adjacent Evergreen Cemetery.The Gettysburg Address is a speech by U.S. President Abraham Lincoln, one of the best-known in American history.[4] It was delivered by Lincoln during the American Civil War, on the afternoon of Thursday, November 19, 1863, at the dedication of the Soldiers' National Cemetery in Gettysburg, Pennsylvania, four and a half months after the Union armies defeated those of the Confederacy at the Battle of Gettysburg.

Abraham Lincoln's carefully crafted address, secondary to other presentations that day, came to be regarded as one of the greatest speeches in American history. In just over two minutes, Lincoln reiterated the principles of human equality espoused by the Declaration of Independence[5] and proclaimed the Civil War as a struggle for the preservation of the Union sundered by the secession crisis,[6] with "a new birth of freedom",[7] that would bring true equality[8] to all of its citizens.[8] Lincoln also redefined the Civil War as a struggle not just for the Union, but also for the principle of human equality.[5]

Beginning with the now-iconic phrase "Four score and seven years ago"—referring to the Declaration of Independence, written at the start of the American Revolution in 1776—Lincoln examined the founding principles of the United States in the context of the Civil War, and memorialized the sacrifices of those who gave their lives at Gettysburg and extolled virtues for the listeners (and the nation) to ensure the survival of America's representative democracy, that "government of the people, by the people, for the people, shall not perish from the earth."

Despite the speech's prominent place in the history and popular culture of the United States, the exact wording and location of the speech are disputed. The five known manuscripts of the Gettysburg Address differ in a number of details and also differ from contemporary newspaper reprints of the speech. Modern scholarship locates the speakers' platform 40 yards (or more) away from the Traditional Site within Soldiers' National Cemetery at the Soldiers' National Monument and entirely within private, adjacent Evergreen Cemetery.