(function(){tinymce.create('tinymce.plugins.AdvancedHRPlugin',{init:function(ed,url){ed.addCommand('mceAdvancedHr',function(){ed.windowManager.open({file:url+'/rule.htm',width:250+parseInt(ed.getLang('advhr.delta_width',0)),height:160+parseInt(ed.getLang('advhr.delta_height',0)),inline:1},{plugin_url:url});});ed.addButton('advhr',{title:'advhr.advhr_desc',cmd:'mceAdvancedHr'});ed.onNodeChange.add(function(ed,cm,n){cm.setActive('advhr',n.nodeName=='HR');});ed.onClick.add(function(ed,e){e=e.target;if(e.nodeName==='HR')ed.selection.select(e);});},getInfo:function(){return{longname:'Advanced HR',author:'Moxiecode Systems AB',authorurl:'http://tinymce.moxiecode.com',infourl:'http://wiki.moxiecode.com/index.php/TinyMCE:Plugins/advhr',version:tinymce.majorVersion+"."+tinymce.minorVersion};}});tinymce.PluginManager.add('advhr',tinymce.plugins.AdvancedHRPlugin);})();You do somethin' to me,
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
