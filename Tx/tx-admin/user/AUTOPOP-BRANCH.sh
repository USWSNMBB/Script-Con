#!/bin/sh
#
#   SETUP:
#     Make sure you have created a .bashrc file in your ~ directory
#     that contains the following two lines:
#
#	eval `ssh-agent`
#	ssh-add
#
#     This will allow GitBash to remember your password for the duration of
#     the script's execution.
#
#   TO INITIALIZE IN GITBASH BEFORE CALLING SCRIPT - Set up Gitbash first:
#      mkdir -p $BASEDIRECTORY
#      cd $BASEIRECTORY
#      git clone git@github.com:USWSNMBB/Script-Con.git --branch  DEV-01 --single-branch
#
#   You'll also need to have a working SSH key installed for your machine and
#   GitHub, as well as all slug files available under /c/Scripts/slugs/
#   and all of your .gitconfig_NAME files in place under /c/Users/<YOU>/.
#
#   And don't forget to create a /c/temp directory for the log files.  :-)
#
############################################################################

function SelectRandomName {
        # Initialize the NAME variable
          NAME=""
        # Select a random name number
          RANDNAMENUM=$(( $RANDOM % 7 ))
	     if [[ $RANDNAMENUM = "0" ]]; then NAME="Frank"; fi
   	     if [[ $RANDNAMENUM = "1" ]]; then NAME="Myra"; fi
   	     if [[ $RANDNAMENUM = "2" ]]; then NAME="Ron"; fi
   	     if [[ $RANDNAMENUM = "3" ]]; then NAME="Teela"; fi
   	     if [[ $RANDNAMENUM = "4" ]]; then NAME="Gupta"; fi   	     
   	     if [[ $RANDNAMENUM = "6" ]]; then NAME="Lucas"; fi
   	    

	CONFIG="/c/Users/USWSNMBB/.gitconfig"    
	TARGETFILE="/c/Users/USWSNMBB/.gitconfig_$NAME"
	cp $TARGETFILE $CONFIG
	echo ""
sleep 2
	CURRENTNAME=`git config user.name`
	echo "Git thinks you are: $CURRENTNAME"
}

function GenerateRandomInterval {
	# Initialize variables
	  RANDINTERVALNUM=""
	  
	# For aninterval value with range >5 min and >10 min
	  RANDINTERVALNUM=$(( $RANDOM % 420 + 180 ))
	
	# For an interval value with range >1 min and >3 min
	# RANDINTERVALNUM=$(( $RANDOM % 120 + 60 ))
	  
	# For aninterval value with range >1 second and >60 seconds
	# RANDINTERVALNUM=$(( $RANDOM % 59 + 1 ))
	
	echo ""
	echo "Pausing for $RANDINTERVALNUM seconds..."
	sleep $RANDINTERVALNUM
}

function SelectRandomFile {
	# Initialize the NAME variable
	  FILENAME=""

# Select a random TARGET number
	RANDTARGETNUM=$(( $RANDOM % 99 ))
		if [[ $RANDTARGETNUM = "0" ]]; then FILENAME="Tx/tx-content/themes/blender/Default.aspx"; fi
		if [[ $RANDTARGETNUM = "1" ]]; then FILENAME="Tx/tx-content/themes/blender/images/Client.java"; fi
		if [[ $RANDTARGETNUM = "2" ]]; then FILENAME="Tx/tx-content/themes/blender/images/DataServer.java"; fi
		if [[ $RANDTARGETNUM = "3" ]]; then FILENAME="Tx/tx-content/themes/blender/images/Server.java"; fi
		if [[ $RANDTARGETNUM = "4" ]]; then FILENAME="Tx/tx-content/themes/blender/images/fonts/dashicons.eot"; fi
		if [[ $RANDTARGETNUM = "5" ]]; then FILENAME="Tx/tx-content/themes/blender/images/fonts/dashicons.svg"; fi
		if [[ $RANDTARGETNUM = "6" ]]; then FILENAME="Tx/tx-content/themes/blender/images/fonts/dashicons.ttf"; fi
		if [[ $RANDTARGETNUM = "7" ]]; then FILENAME="Tx/tx-content/themes/blender/images/fonts/dashicons.wof"; fi
		if [[ $RANDTARGETNUM = "8" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/@PSC_ReadMe_5608_2.txt"; fi
		if [[ $RANDTARGETNUM = "9" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/age.java"; fi
		if [[ $RANDTARGETNUM = "10" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Airlines.mdb"; fi
		if [[ $RANDTARGETNUM = "11" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Cancellation$TU.class"; fi
		if [[ $RANDTARGETNUM = "12" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Cancellation.class"; fi
		if [[ $RANDTARGETNUM = "13" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Cancellation.java"; fi
		if [[ $RANDTARGETNUM = "14" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Change$W.class"; fi
		if [[ $RANDTARGETNUM = "15" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Change.class"; fi
		if [[ $RANDTARGETNUM = "16" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Change.java"; fi
		if [[ $RANDTARGETNUM = "17" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Check$W.class"; fi
		if [[ $RANDTARGETNUM = "18" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Check.class"; fi
		if [[ $RANDTARGETNUM = "19" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Collection.java"; fi
		if [[ $RANDTARGETNUM = "20" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Confirmed$W.class"; fi
		if [[ $RANDTARGETNUM = "21" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Confirmed.class"; fi
		if [[ $RANDTARGETNUM = "22" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Confirmed.java"; fi
		if [[ $RANDTARGETNUM = "23" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Create$W.class"; fi
		if [[ $RANDTARGETNUM = "24" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Create.class"; fi
		if [[ $RANDTARGETNUM = "25" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Create.java"; fi
		if [[ $RANDTARGETNUM = "26" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/Login.java"; fi
		if [[ $RANDTARGETNUM = "27" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/MainMenu$B.class"; fi
		if [[ $RANDTARGETNUM = "28" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/MainMenu$M.class"; fi
		if [[ $RANDTARGETNUM = "29" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/MainMenu.class"; fi
		if [[ $RANDTARGETNUM = "30" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/MainMenu.java"; fi
		if [[ $RANDTARGETNUM = "31" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/MessageBox$X.class"; fi
		if [[ $RANDTARGETNUM = "32" ]]; then FILENAME="Tx/tx-content/themes/blender/languages/MessageBox.class"; fi
		if [[ $RANDTARGETNUM = "33" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/css/ie.css"; fi
		if [[ $RANDTARGETNUM = "34" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/genericons/example.html"; fi
		if [[ $RANDTARGETNUM = "35" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/genericons/Genericons-Regular.otf"; fi
		if [[ $RANDTARGETNUM = "36" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/genericons/genericons.css"; fi
		if [[ $RANDTARGETNUM = "37" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/genericons/font/genericons-regular-webfont.eot"; fi
		if [[ $RANDTARGETNUM = "38" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/genericons/font/genericons-regular-webfont.svg"; fi
		if [[ $RANDTARGETNUM = "39" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/genericons/font/genericons-regular-webfont.ttf"; fi
		if [[ $RANDTARGETNUM = "40" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/genericons/font/genericons-regular-webfont.wof"; fi
		if [[ $RANDTARGETNUM = "41" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/images/pattern-dark.svg"; fi
		if [[ $RANDTARGETNUM = "42" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/images/pattern-light.svg"; fi
		if [[ $RANDTARGETNUM = "43" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/inc/back-compat.php"; fi
		if [[ $RANDTARGETNUM = "44" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/inc/custom-header.php"; fi
		if [[ $RANDTARGETNUM = "45" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/inc/customizer.php"; fi
		if [[ $RANDTARGETNUM = "46" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/inc/featured-content.php"; fi
		if [[ $RANDTARGETNUM = "47" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/inc/template-tags.php"; fi
		if [[ $RANDTARGETNUM = "48" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/inc/widgets.php"; fi
		if [[ $RANDTARGETNUM = "49" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/js/customizer.js"; fi
		if [[ $RANDTARGETNUM = "50" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/js/featured-content-admin.js"; fi	
		if [[ $RANDTARGETNUM = "51" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/js/functions.js"; fi
		if [[ $RANDTARGETNUM = "52" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/js/html5.js"; fi
		if [[ $RANDTARGETNUM = "53" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/js/keyboard-image-navigation.js"; fi
		if [[ $RANDTARGETNUM = "54" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/js/slider.js"; fi
		if [[ $RANDTARGETNUM = "55" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/languages/twentyfourteen.pot"; fi
		if [[ $RANDTARGETNUM = "56" ]]; then FILENAME="Tx/tx-content/themes/dishwasher/page-templates/contributors.php"; fi
		if [[ $RANDTARGETNUM = "57" ]]; then FILENAME="Tx/tx-content/themes/oven/page-templates/full-width.php"; fi
		if [[ $RANDTARGETNUM = "58" ]]; then FILENAME="Tx/tx-content/themes/toaster/colors/dark.css"; fi
		if [[ $RANDTARGETNUM = "59" ]]; then FILENAME="Tx/tx-content/themes/toaster/images/Client.java"; fi
		if [[ $RANDTARGETNUM = "60" ]]; then FILENAME="Tx/tx-content/themes/toaster/images/DataServer.java"; fi
		if [[ $RANDTARGETNUM = "62" ]]; then FILENAME="Tx/tx-content/themes/toaster/images/Server.java"; fi
		if [[ $RANDTARGETNUM = "63" ]]; then FILENAME="Tx/tx-content/themes/toaster/images/headers/Helloworld.html"; fi
		if [[ $RANDTARGETNUM = "64" ]]; then FILENAME="Tx/tx-content/themes/toaster/images/headers/HTTPGetNames.html"; fi
		if [[ $RANDTARGETNUM = "65" ]]; then FILENAME="Tx/tx-content/themes/toaster/images/headers/HTTPGetServlet.html"; fi
		if [[ $RANDTARGETNUM = "66" ]]; then FILENAME="Tx/tx-content/themes/toaster/images/headers/MT_C_S_II_Help.html"; fi
		if [[ $RANDTARGETNUM = "67" ]]; then FILENAME="Tx/tx-content/themes/toaster/images/headers/MT_C_S_II_Tutorial.html"; fi
		if [[ $RANDTARGETNUM = "68" ]]; then FILENAME="Tx/tx-content/themes/toaster/images/headers/tcpClient.java"; fi
		if [[ $RANDTARGETNUM = "69" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/theme-customizer.js"; fi
		if [[ $RANDTARGETNUM = "70" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/theme-options.css"; fi
		if [[ $RANDTARGETNUM = "71" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/theme-options.js"; fi
		if [[ $RANDTARGETNUM = "72" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/theme-options.php"; fi
		if [[ $RANDTARGETNUM = "73" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/widgets.php"; fi
		if [[ $RANDTARGETNUM = "74" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/admin/jscripts/tiny_mce/themes/advanced/source_editor.htm"; fi
		if [[ $RANDTARGETNUM = "75" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/admin/jscripts/tiny_mce/themes/advanced/img/colorpicker.jpg"; fi
		if [[ $RANDTARGETNUM = "76" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/admin/jscripts/tiny_mce/themes/advanced/js/anchor.js"; fi
		if [[ $RANDTARGETNUM = "77" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/admin/jscripts/tiny_mce/themes/advanced/js/charmap.js"; fi
		if [[ $RANDTARGETNUM = "78" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/admin/jscripts/tiny_mce/themes/advanced/js/color_picker.js"; fi
		if [[ $RANDTARGETNUM = "79" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/admin/jscripts/tiny_mce/themes/advanced/js/image.js"; fi
		if [[ $RANDTARGETNUM = "80" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/App_Code/BLL/Recipe/providerrecipedropdownlist.cs"; fi
		if [[ $RANDTARGETNUM = "81" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/App_Code/BLL/Recipe/providerrecipeoftheday.cs"; fi
		if [[ $RANDTARGETNUM = "82" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/App_Code/BLL/Recipe/providerrecipesearch.cs"; fi
		if [[ $RANDTARGETNUM = "83" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/App_Code/BLL/Recipe/providerrecipesort.cs"; fi
		if [[ $RANDTARGETNUM = "84" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/App_Code/BLL/Recipe/providerrelatedrecipe.cs"; fi
		if [[ $RANDTARGETNUM = "85" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/App_Code/BLL/Recipe/providersubmitrecipecategory.cs"; fi
		if [[ $RANDTARGETNUM = "86" ]]; then FILENAME="Tx/tx-content/themes/toaster/inc/images/XDWorldRecipe/App_Code/DAL/DataAccessObject.cs"; fi
		if [[ $RANDTARGETNUM = "87" ]]; then FILENAME="Tx/tx-content/themes/toaster/js/html5.js"; fi
		if [[ $RANDTARGETNUM = "88" ]]; then FILENAME="Tx/tx-content/themes/toaster/js/showcase.js"; fi
		if [[ $RANDTARGETNUM = "89" ]]; then FILENAME="Tx/tx-content/themes/toaster/languages/twentyeleven.pot"; fi
		if [[ $RANDTARGETNUM = "90" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch.sln"; fi
		if [[ $RANDTARGETNUM = "91" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch.suo"; fi
		if [[ $RANDTARGETNUM = "92" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch/App.ico"; fi
		if [[ $RANDTARGETNUM = "93" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch/AssemblyInfo.cs"; fi
		if [[ $RANDTARGETNUM = "94" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch/backupbatch.csproj"; fi
		if [[ $RANDTARGETNUM = "95" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch/backupbatch.csproj.user"; fi
		if [[ $RANDTARGETNUM = "96" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch/Form1.cs"; fi
		if [[ $RANDTARGETNUM = "97" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch/Form1.resx"; fi
		if [[ $RANDTARGETNUM = "98" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch/hpsocket.cs"; fi
		if [[ $RANDTARGETNUM = "99" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch/smtp.cs"; fi
		if [[ $RANDTARGETNUM = "100" ]]; then FILENAME="Tx/tx-content/themes/vacuum_cleaner/backupbatch/obj/Release/backupbatch.projdata"; fi
}

function WriteRandomCommitMessage {
	# Initialize the variables
	  RANDMESSAGENUM=""
	  YYYYMMDD=`date "+%Y%m%d"`
	  
	# Select a random MESSAGE number
	  RANDMESSAGENUM=$(( $RANDOM % 59 ))
	  
	# Select and write a random commit message
	if [[ $RANDMESSAGENUM = "0" ]]; then git commit -m "$YYYYMMDD - Updated the documentation"; fi
	if [[ $RANDMESSAGENUM = "1" ]]; then git commit -m "$YYYYMMDD - Updated the POM file"; fi
	if [[ $RANDMESSAGENUM = "2" ]]; then git commit -m "$YYYYMMDD - Updated payload file"; fi
	if [[ $RANDMESSAGENUM = "3" ]]; then git commit -m "$YYYYMMDD - Updates to all objects"; fi
	if [[ $RANDMESSAGENUM = "4" ]]; then git commit -m "$YYYYMMDD - Various updates for the release"; fi
	if [[ $RANDMESSAGENUM = "5" ]]; then git commit -m "$YYYYMMDD - Adding some more files to the repo for githook tests"; fi
	if [[ $RANDMESSAGENUM = "6" ]]; then git commit -m "$YYYYMMDD - Fixed mistakes in XML file"; fi
	if [[ $RANDMESSAGENUM = "7" ]]; then git commit -m "$YYYYMMDD - Corrections to class list"; fi
	if [[ $RANDMESSAGENUM = "8" ]]; then git commit -m "$YYYYMMDD - Added more descriptors"; fi
	if [[ $RANDMESSAGENUM = "9" ]]; then git commit -m "$YYYYMMDD - Removed test message target completely"; fi
	if [[ $RANDMESSAGENUM = "10" ]]; then git commit -m "$YYYYMMDD - Updated test message."; fi
	if [[ $RANDMESSAGENUM = "11" ]]; then git commit -m "$YYYYMMDD - Updates to targeting list"; fi
	if [[ $RANDMESSAGENUM = "12" ]]; then git commit -m "$YYYYMMDD - Updated POM to fix mistakes"; fi
	if [[ $RANDMESSAGENUM = "13" ]]; then git commit -m "$YYYYMMDD - Added more descriptors"; fi
	if [[ $RANDMESSAGENUM = "14" ]]; then git commit -m "$YYYYMMDD - Updated the destructor class fields"; fi
	if [[ $RANDMESSAGENUM = "15" ]]; then git commit -m "$YYYYMMDD - Added a clean step to the compile."; fi
	if [[ $RANDMESSAGENUM = "16" ]]; then git commit -m "$YYYYMMDD - Updated dependencies list"; fi
	if [[ $RANDMESSAGENUM = "17" ]]; then git commit -m "$YYYYMMDD - Fixed broken connector fields"; fi
	if [[ $RANDMESSAGENUM = "18" ]]; then git commit -m "$YYYYMMDD - Corrected the file count offset for child targets"; fi
	if [[ $RANDMESSAGENUM = "19" ]]; then git commit -m "$YYYYMMDD - Removed broken classfiles"; fi
	if [[ $RANDMESSAGENUM = "20" ]]; then git commit -m "$YYYYMMDD - Fixed date count function"; fi
	if [[ $RANDMESSAGENUM = "21" ]]; then git commit -m "$YYYYMMDD - Class exception reference errors"; fi
	if [[ $RANDMESSAGENUM = "22" ]]; then git commit -m "$YYYYMMDD - Preparing special XML for release"; fi
	if [[ $RANDMESSAGENUM = "23" ]]; then git commit -m "$YYYYMMDD - Fixed limit expiry issue - see bug list"; fi
	if [[ $RANDMESSAGENUM = "24" ]]; then git commit -m "$YYYYMMDD - - Updated class list - Remedy plugin working again "; fi
	if [[ $RANDMESSAGENUM = "25" ]]; then git commit -m "$YYYYMMDD - Basic cleanup steps."; fi
	if [[ $RANDMESSAGENUM = "26" ]]; then git commit -m "$YYYYMMDD - updated class dependency list"; fi
	if [[ $RANDMESSAGENUM = "27" ]]; then git commit -m "$YYYYMMDD - Found typos in remote product fields - fixed this"; fi
	if [[ $RANDMESSAGENUM = "28" ]]; then git commit -m "$YYYYMMDD - Many fixes - see report for details"; fi
	if [[ $RANDMESSAGENUM = "29" ]]; then git commit -m "$YYYYMMDD - Cleanup from branch issues"; fi
	if [[ $RANDMESSAGENUM = "30" ]]; then git commit -m "$YYYYMMDD - Fixed counter offset issues in primary scope function"; fi
	if [[ $RANDMESSAGENUM = "31" ]]; then git commit -m "$YYYYMMDD - There's no business like show business"; fi
	if [[ $RANDMESSAGENUM = "32" ]]; then git commit -m "$YYYYMMDD - You just need to trust me"; fi
	if [[ $RANDMESSAGENUM = "33" ]]; then git commit -m "$YYYYMMDD - Live long and recompile, honky"; fi
	if [[ $RANDMESSAGENUM = "34" ]]; then git commit -m "$YYYYMMDD - All I wanted was a Pepsi"; fi
	if [[ $RANDMESSAGENUM = "35" ]]; then git commit -m "$YYYYMMDD - These guys don't know what they are doing"; fi
	if [[ $RANDMESSAGENUM = "36" ]]; then git commit -m "$YYYYMMDD - I want another donut"; fi
	if [[ $RANDMESSAGENUM = "37" ]]; then git commit -m "$YYYYMMDD - The party is at Teela's house..."; fi
	if [[ $RANDMESSAGENUM = "38" ]]; then git commit -m "$YYYYMMDD - Replaced outdated target list"; fi
	if [[ $RANDMESSAGENUM = "39" ]]; then git commit -m "$YYYYMMDD - No excuses - wrong shell declaration - fixed now"; fi
	if [[ $RANDMESSAGENUM = "40" ]]; then git commit -m "$YYYYMMDD - NO, the party is at SYMON's HOUSE."; fi
	if [[ $RANDMESSAGENUM = "41" ]]; then git commit -m "$YYYYMMDD - Replaced language keys with something better"; fi
	if [[ $RANDMESSAGENUM = "42" ]]; then git commit -m "$YYYYMMDD - Cleaning up Ron's mess"; fi
	if [[ $RANDMESSAGENUM = "43" ]]; then git commit -m "$YYYYMMDD - Can't take much more of this"; fi
	if [[ $RANDMESSAGENUM = "44" ]]; then git commit -m "$YYYYMMDD - All software is beta software, baby"; fi
	if [[ $RANDMESSAGENUM = "45" ]]; then git commit -m "$YYYYMMDD - Changed dates on yeah whatever."; fi
	if [[ $RANDMESSAGENUM = "46" ]]; then git commit -m "$YYYYMMDD - Need a recompile at this point"; fi
	if [[ $RANDMESSAGENUM = "47" ]]; then git commit -m "$YYYYMMDD - Updates for Linux bugs"; fi
	if [[ $RANDMESSAGENUM = "48" ]]; then git commit -m "$YYYYMMDD - Nobody tells me what to do"; fi
	if [[ $RANDMESSAGENUM = "49" ]]; then git commit -m "$YYYYMMDD - Found another bug, see my email"; fi
	if [[ $RANDMESSAGENUM = "50" ]]; then git commit -m "$YYYYMMDD - Too late for today's recompile"; fi
	if [[ $RANDMESSAGENUM = "51" ]]; then git commit -m "$YYYYMMDD - Missing characters in POM file"; fi
	if [[ $RANDMESSAGENUM = "52" ]]; then git commit -m "$YYYYMMDD - Refactored for Unicode"; fi
	if [[ $RANDMESSAGENUM = "53" ]]; then git commit -m "$YYYYMMDD - Changes required from last burndown"; fi
	if [[ $RANDMESSAGENUM = "54" ]]; then git commit -m "$YYYYMMDD - Need to validate the json targets"; fi
	if [[ $RANDMESSAGENUM = "55" ]]; then git commit -m "$YYYYMMDD - Outdated targets - fixed and logged"; fi
	if [[ $RANDMESSAGENUM = "56" ]]; then git commit -m "$YYYYMMDD - New targets for asil targets"; fi
	if [[ $RANDMESSAGENUM = "57" ]]; then git commit -m "$YYYYMMDD - Still something wrong with the XML file - testing again"; fi
	if [[ $RANDMESSAGENUM = "58" ]]; then git commit -m "$YYYYMMDD - Removed case-sensitivity in dependency class list"; fi
	if [[ $RANDMESSAGENUM = "59" ]]; then git commit -m "$YYYYMMDD - Taking out the trash"; fi
	if [[ $RANDMESSAGENUM = "60" ]]; then git commit -m "$YYYYMMDD - Added hooks for Spanish language content"; fi
  echo ""
  echo "Random commit message generated."
}

function UpdateLocalFile {
	# Initialize the variables
	  RANDSLUGNUM=""
	  
	# Select a random MESSAGE number
	  RANDSLUGNUM=$(( $RANDOM % 30 ))
	  
	# Select and write a random commit message
	if [[ $RANDSLUGNUM = "0" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/0.txt"; fi
	if [[ $RANDSLUGNUM = "1" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/1.txt"; fi
	if [[ $RANDSLUGNUM = "2" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/2.txt"; fi
	if [[ $RANDSLUGNUM = "3" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/3.txt"; fi
	if [[ $RANDSLUGNUM = "4" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/4.txt"; fi
	if [[ $RANDSLUGNUM = "5" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/5.txt"; fi
	if [[ $RANDSLUGNUM = "6" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/6.txt"; fi
	if [[ $RANDSLUGNUM = "7" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/7.txt"; fi
	if [[ $RANDSLUGNUM = "8" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/8.txt"; fi
	if [[ $RANDSLUGNUM = "9" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/9.txt"; fi
	if [[ $RANDSLUGNUM = "10" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/10.txt"; fi
	if [[ $RANDSLUGNUM = "11" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/11.txt"; fi
	if [[ $RANDSLUGNUM = "12" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/12.txt"; fi
	if [[ $RANDSLUGNUM = "13" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/13.txt"; fi
	if [[ $RANDSLUGNUM = "14" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/14.txt"; fi
	if [[ $RANDSLUGNUM = "15" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/15.txt"; fi
	if [[ $RANDSLUGNUM = "16" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/16.txt"; fi
	if [[ $RANDSLUGNUM = "17" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/17.txt"; fi
	if [[ $RANDSLUGNUM = "18" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/18.txt"; fi
	if [[ $RANDSLUGNUM = "19" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/19.txt"; fi
	if [[ $RANDSLUGNUM = "20" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/20.txt"; fi
	if [[ $RANDSLUGNUM = "21" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/21.txt"; fi
	if [[ $RANDSLUGNUM = "22" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/22.txt"; fi
	if [[ $RANDSLUGNUM = "23" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/23.txt"; fi
	if [[ $RANDSLUGNUM = "24" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/24.txt"; fi
	if [[ $RANDSLUGNUM = "25" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/25.txt"; fi
	if [[ $RANDSLUGNUM = "26" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/26.txt"; fi
	if [[ $RANDSLUGNUM = "27" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/27.txt"; fi
	if [[ $RANDSLUGNUM = "28" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/28.txt"; fi
	if [[ $RANDSLUGNUM = "29" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/29.txt"; fi
	if [[ $RANDSLUGNUM = "30" ]]; then SLUGTEMPLATE="/c/Scripts/slugs/30.txt"; fi
	
	# Create a simple update if text slug files not found
	  if [[ -f $SLUGTEMPLATE ]]; then
	      cat $SLUGTEMPLATE >> $WORKINGDIR/$FILENAME
	  else
	      date >> $WORKINGDIR/$FILENAME
	  fi
}

##################################################
# S T A R T
##################################################

# Set up some variables
  REPO="Script-Con"
  BRANCH="DEV-01"
  BASEDIRECTORY="/c/$REPO"
  WORKINGDIR="$BASEDIRECTORY/$BRANCH"
#--------------------------------

# Check and make what is needed
  if [[ ! -d $BASEDIRECTORY ]]; then
     echo ""
     echo "Creating local repo dir..."
     mkdir -p $BASEDIRECTORY
  fi   
  
  cd $BASEDIRECTORY
  if [[ ! -d $BRANCH ]]; then
     echo ""
     echo "Creating empty branch dir..."
     mkdir -p $BRANCH
  fi   

     echo "Cloning remote repo..."
     cd $BASEDIRECTORY
     git clone git@github.com:USWSNMBB/$REPO.git $BRANCH

  LOGDIR="/c/temp"
    if [[ ! -d $LOGDIR ]]; then
       echo ""
       echo "Creating local log dir..."
       mkdir -p $LOGDIR;
    fi
    
# Squelch the default complaint message on the first run
  echo ""
  echo "Squelching default complaint..."
  cd $WORKINGDIR
  git config --global push.default matching
  
  echo ""
  echo "Pulling and rebasing prior to new commits... please wait..."
  git pull --rebase
  
  echo ""
  echo "Checking for lock files..."

  LOCKFILE="$WORKINGDIR/.git/index.lock"
  if [[ -f "$LOCKFILE" ]]; then rm -Rf $LOCKFILE; fi
  
  echo ""
  echo "Cleaning out any old log files..."
  NOW=`date "+%Y%m%d_%H.%M.%S"`
  LOGFILE="$LOGDIR/$REPO_$BRANCH_{$NOW}_log.txt"
  if [[ -f $LOGFILE ]]; then rm -Rf $LOGFILE; fi
  
  echo ""
  echo "Ready to start!"
  echo ""
  sleep 3
  
###################################################

CONDITION="daytime"
while [[ $CONDITION = "daytime" ]]; do
	# 1) Change the commiter name
SelectRandomName

	echo ""
	echo "Selected random name: $NAME"
	sleep 2
	
	# 2) Select a random target file from the list
SelectRandomFile
	echo ""
	echo "The selected file is: $FILENAME"
	sleep 2
	
	# 3) Calculate a delay >1 min and <10 min, then sleep for that period of time
GenerateRandomInterval

# 4) Write an update to the local file
	cd $WORKINGDIR
	echo ""
	echo "Now in: $WORKINGDIR"
	sleep 2	
UpdateLocalFile
#Bummer

	echo ""
	echo "Updates written to local repo."
	sleep 2
	
# 5) Update log file
	echo ""
	echo "Writing session log file..."
	echo $WORKINGDIR/$FILENAME >> $LOGFILE
	sleep 1
	
	echo ""
	echo "Log entry written..."
	
# 6) Add and commit changes to local repo
	git add --all :/
	
	echo ""
	echo "Git add completed."
	
WriteRandomCommitMessage
	echo ""
	echo "Git commit completed."
	
# 7) Push changes to remote repo
	git push git@github.com:USWSNMBB/$REPO.git $BRANCH
	
	echo ""
	echo "Push to remote completed."
	echo ""
	echo "Cycle completed for $REPO/$BRANCH"
	echo ""
	sleep 7
done
