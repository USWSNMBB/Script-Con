#!/bin/sh
############################################################################
# Script      : auto_commit_BRANCH.sh                                      #
# Author      : Symon Michael Claims IT  818-876-3639                      #
# Date        : 01-OCT-14                                                  #
# Updated     :                                                            #
# Input args  : none                                                       #
# Input files : none                                                       #
# Output files:                                                            #
# Return codes: 0 if successful, >0 if fails                               #
#                                                                          #
# Description : Generates commits for one of several user profiles         #
#               Uses pseudo-random number generators to fake randomness    #
#                                                                          #
############################################################################
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

function ShallWeContinue {
PROCEED="" 
while [ "$PROCEED" = "" ]; do
        echo ""
        echo "Basic setup completed, please review output."
        echo "Do you want to continue with updates? [y/n]?"
        read PROCEED
           if [[ "$PROCEED" != "y" ]] && [[ "$PROCEED" != "n" ]] ; then
              echo "Please enter 'y' for yes or 'n':"
              PROCEED=""
              continue
           fi
        if [[ $PROCEED = "n" ]]; then
           echo ""
           echo "Exiting w/o making any updates."
           sleep 3
           exit 0
        else 
           echo ""
           echo "Proceeding to create updates..."
        fi
done
}

function SelectRandomName {
        # Initialize the NAME variable
          NAME=""
        # Select a random name number
          RANDNAMENUM=$(( $RANDOM % 3 ))
	     if [[ $RANDNAMENUM = "0" ]]; then NAME="User1"; fi
	     if [[ $RANDNAMENUM = "1" ]]; then NAME="User2"; fi
	     if [[ $RANDNAMENUM = "2" ]]; then NAME="User3"; fi
	     
	echo ""
	echo "NAME value is: $NAME"
	sleep 2

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
	RANDTARGETNUM=$(( $RANDOM % 40 ))
		if [[ $RANDTARGETNUM = "0" ]]; then FILENAME="CheckDefaultDayOffBucketxml.asp"; fi
		if [[ $RANDTARGETNUM = "1" ]]; then FILENAME="CheckDefaultDayOffBucketxml.asp"; fi
		if [[ $RANDTARGETNUM = "2" ]]; then FILENAME="CheckForPTO.asp"; fi
		if [[ $RANDTARGETNUM = "3" ]]; then FILENAME="CheckPTO.asp"; fi
		if [[ $RANDTARGETNUM = "4" ]]; then FILENAME="CheckScheduleforFloatingHoliday.asp"; fi
		if [[ $RANDTARGETNUM = "5" ]]; then FILENAME="CheckScheduleforLunch.asp"; fi
		if [[ $RANDTARGETNUM = "6" ]]; then FILENAME="Check_PendingRequestKey.asp"; fi
		if [[ $RANDTARGETNUM = "7" ]]; then FILENAME="EmployeeQueue_Grouped.asp"; fi
		if [[ $RANDTARGETNUM = "8" ]]; then FILENAME="EmployeeView.asp"; fi
		if [[ $RANDTARGETNUM = "9" ]]; then FILENAME="EmployeeViewCarrierList.asp"; fi
		if [[ $RANDTARGETNUM = "10" ]]; then FILENAME="EmployeeViewContact.asp"; fi
		if [[ $RANDTARGETNUM = "11" ]]; then FILENAME="EmployeeViewIDs.asp"; fi
		if [[ $RANDTARGETNUM = "12" ]]; then FILENAME="EmployeeViewLicense.asp"; fi
		if [[ $RANDTARGETNUM = "13" ]]; then FILENAME="EmployeeViewOther.asp"; fi
		if [[ $RANDTARGETNUM = "14" ]]; then FILENAME="EmployeeViewPerms.asp"; fi
		if [[ $RANDTARGETNUM = "15" ]]; then FILENAME="DailyActivity/calendar.css"; fi
		if [[ $RANDTARGETNUM = "16" ]]; then FILENAME="DailyActivity/CheckScheduleAvailablexml.asp"; fi
		if [[ $RANDTARGETNUM = "17" ]]; then FILENAME="DailyActivity/DailyActivity.asp"; fi
		if [[ $RANDTARGETNUM = "18" ]]; then FILENAME="DailyActivity/DailyActivity.aspx"; fi
		if [[ $RANDTARGETNUM = "19" ]]; then FILENAME="DailyActivity/DailyActivity.aspx.cs"; fi
		if [[ $RANDTARGETNUM = "20" ]]; then FILENAME="DailyActivity/Function_CreateHiddenForm.asp"; fi
		if [[ $RANDTARGETNUM = "21" ]]; then FILENAME="DailyActivity/Function_GetDayView.asp"; fi
		if [[ $RANDTARGETNUM = "22" ]]; then FILENAME="DailyActivity/Function_GetParentCode.asp"; fi
		if [[ $RANDTARGETNUM = "23" ]]; then FILENAME="DailyActivity/Function_ReloadBucket.asp"; fi
		if [[ $RANDTARGETNUM = "24" ]]; then FILENAME="DailyActivity/Function_ReloadCalendar.asp"; fi
		if [[ $RANDTARGETNUM = "25" ]]; then FILENAME="DailyActivity/Function_ReloadStartEndTimes.asp"; fi
		if [[ $RANDTARGETNUM = "26" ]]; then FILENAME="DailyActivity/Function_SearchSiteSup.asp"; fi
		if [[ $RANDTARGETNUM = "27" ]]; then FILENAME="DailyActivity/Handler_ClearBucket.asp"; fi
		if [[ $RANDTARGETNUM = "28" ]]; then FILENAME="DailyActivity/Handler_SubmitBucket.asp"; fi
		if [[ $RANDTARGETNUM = "29" ]]; then FILENAME="DailyActivity/Handler_SubmitBucket_argh.asp"; fi
		if [[ $RANDTARGETNUM = "30" ]]; then FILENAME="DailyActivity/ScriptDailyActivity.js"; fi
		if [[ $RANDTARGETNUM = "31" ]]; then FILENAME="DailyActivity/StyleDailyActivity.css"; fi
		if [[ $RANDTARGETNUM = "32" ]]; then FILENAME="DefaultActivity/AdminTool_DefaultBucket.asp"; fi
		if [[ $RANDTARGETNUM = "33" ]]; then FILENAME="DefaultActivity/AdminTool_DefaultBucketHandler.asp"; fi
		if [[ $RANDTARGETNUM = "34" ]]; then FILENAME="DelegateChangeRequestApprovals/CheckOverlap.asp"; fi
		if [[ $RANDTARGETNUM = "35" ]]; then FILENAME="DelegateChangeRequestApprovals/DelegateApprovals.aspx"; fi
		if [[ $RANDTARGETNUM = "36" ]]; then FILENAME="DelegateChangeRequestApprovals/DelegateApprovals.aspx.cs"; fi
		if [[ $RANDTARGETNUM = "37" ]]; then FILENAME="DelegateChangeRequestApprovals/DelegateCRApprovals.css"; fi
		if [[ $RANDTARGETNUM = "38" ]]; then FILENAME="DelegateChangeRequestApprovals/Function_ReloadCalendar.asp"; fi
		if [[ $RANDTARGETNUM = "39" ]]; then FILENAME="DelegateChangeRequestApprovals/Function_SearchFromSup.asp"; fi
		if [[ $RANDTARGETNUM = "40" ]]; then FILENAME="DelegateChangeRequestApprovals/Function_SearchSiteSup.asp"; fi
		if [[ $RANDTARGETNUM = "41" ]]; then FILENAME="DelegateChangeRequestApprovals/GetEmpNameForOverlap.asp"; fi
		if [[ $RANDTARGETNUM = "42" ]]; then FILENAME="DelegateChangeRequestApprovals/ScriptDelegate.js"; fi
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
	if [[ $RANDMESSAGENUM = "24" ]]; then git commit -m "$YYYYMMDD - Updated class list - Remedy plugin working again "; fi
	if [[ $RANDMESSAGENUM = "25" ]]; then git commit -m "$YYYYMMDD - Basic cleanup steps."; fi
	if [[ $RANDMESSAGENUM = "26" ]]; then git commit -m "$YYYYMMDD - updated class dependency list"; fi
	if [[ $RANDMESSAGENUM = "27" ]]; then git commit -m "$YYYYMMDD - Found typos in remote product fields - fixed this"; fi
	if [[ $RANDMESSAGENUM = "28" ]]; then git commit -m "$YYYYMMDD - Many fixes - see report for details"; fi
	if [[ $RANDMESSAGENUM = "29" ]]; then git commit -m "$YYYYMMDD - Cleanup from branch issues"; fi
	if [[ $RANDMESSAGENUM = "30" ]]; then git commit -m "$YYYYMMDD - Fixed counter offset issues in primary scope function"; fi
	if [[ $RANDMESSAGENUM = "31" ]]; then git commit -m "$YYYYMMDD - There's no business like show business"; fi
	if [[ $RANDMESSAGENUM = "32" ]]; then git commit -m "$YYYYMMDD - You just need to trust me"; fi
	if [[ $RANDMESSAGENUM = "33" ]]; then git commit -m "$YYYYMMDD - Live long and recompile"; fi
	if [[ $RANDMESSAGENUM = "34" ]]; then git commit -m "$YYYYMMDD - All I wanted was a Pepsi"; fi
	if [[ $RANDMESSAGENUM = "35" ]]; then git commit -m "$YYYYMMDD - These guys don't know what they are doing"; fi
	if [[ $RANDMESSAGENUM = "36" ]]; then git commit -m "$YYYYMMDD - I want another donut"; fi
	if [[ $RANDMESSAGENUM = "37" ]]; then git commit -m "$YYYYMMDD - A quick fix here"; fi
	if [[ $RANDMESSAGENUM = "38" ]]; then git commit -m "$YYYYMMDD - Replaced outdated target list"; fi
	if [[ $RANDMESSAGENUM = "39" ]]; then git commit -m "$YYYYMMDD - No excuses - wrong shell declaration - fixed now"; fi
	if [[ $RANDMESSAGENUM = "40" ]]; then git commit -m "$YYYYMMDD - Scope issue fixed"; fi
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
    REPO="OLEM"
    BRANCH="5.1"
    BASEDIRECTORY="/c/CLAIMS-IT-1"
    SETUPDIR="$BASEDIRECTORY/$REPO/$BRANCH"
#--------------------------------

# Check and make what is needed
  if [[ ! -d $WORKINGDIR ]]; then
     echo ""
     echo "Creating local repo dir..."
     mkdir -p $SETUPDIR
  fi   
     echo "Cloning remote BRANCH..."
     cd $SETUPDIR
     git clone git@10.141.252.223:CLAIMS-IT-01/OLEM.git --branch 5.1 --single-branch

  LOGDIR="/c/temp"
    if [[ ! -d $LOGDIR ]]; then
       echo ""
       echo "Creating local log dir..."
       mkdir -p $LOGDIR;
    fi
    
# VERY IMPORTANT:
  WORKINGDIR="$SETUPDIR/$REPO"

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
  
ShallWeContinue
  
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
#GenerateRandomInterval
sleep 3

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
	git push
	
	echo ""
	echo "Push to remote completed - this is the Symon script."
	echo ""
	echo "Cycle completed for $REPO/$BRANCH"
	echo ""
	sleep 2
done
