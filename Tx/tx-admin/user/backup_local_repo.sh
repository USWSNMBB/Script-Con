#!/bin/sh
############################################################################
# Script      : backup_local_repo.sh                                       #
# Author      : Symon Michael Claims IT  818-876-3639                      #
# Date        : 18-SEP-14                                                  #
# Updated     :                                                            #
# Input args  : prompt-driven                                              #
# Input files : local repo content                                         #
# Output files: <ORGNAME><REPONAME>.YYYY-MM-DD.bundle                      #
# Return codes: 0 if successful, >1 if fails                               #
#                                                                          #
# Description : Creates a complete backup of local repo with all metadata. #
#               Bundle is checked for integrity.                           #
#               Output file is given unique date-based name and moved to   #
#               local storage dir.                                         #
#               Functions loop until valid directory info provided.        #
#                                                                          #
############################################################################

function AllDone {
	echo ""
	echo "All finished!"
	echo ""
	sleep 5
	exit 0
}


function DecideOnOverwrite {
PROCEED="" 
while [ "$PROCEED" = "" ]; do
        echo ""
        echo "OK to overwrite remote copy with new version? [y/n]?"
        read PROCEED
           if [[ "$PROCEED" != "y" ]] && [[ "$PROCEED" != "n" ]] ; then
              echo "Please enter 'y' for yes or 'n':"
              PROCEED=""
              continue
           fi
done
}

function ValidateOrg {
	if [[ $ORGNAME = "" ]]; then 
	   echo ""
	   echo "No Organization declared, nothing to do..."
	   sleep 3
	   exit 1
	fi 
	if  [[ ! -d $ORGDIR ]]; then
	    LOCATEDORG="false"
	else 
	    LOCATEDORG="true"
	fi
}


function ValidateRepo {
	if [[ $REPONAME = "" ]]; then 
	   echo ""
	   echo "No repo declared, nothing to do..."
	   sleep 3
	   exit 1
	fi   
	if [[ ! -d $REPODIR ]]; then
	    LOCATEDREPO="false"
	else 
	    LOCATEDREPO="true"
	fi
}

function TestBundle {
	echo ""
	echo "Testing bundle for integrity..."
	git bundle verify $BUNDLENAME 
	echo ""
}

##################################################
#                  S T A R T
##################################################
#> /dev/null 2>&1

# Reset variables
#-------------------------------

BASEDIR="/c"
ORGNAME=""
ORGDIR=""
REPONAME=""
LOCATEDORG=""
LOCATEDREPO=""

# Get the Organization name
#-------------------------------
echo ""
echo ""
      echo "Enter Organization name:"
      read ORGNAME     
      ORGDIR="$BASEDIR/$ORGNAME"

      ValidateOrg
      
    if [[ $LOCATEDORG = "false" ]]; then
       while [ $LOCATEDORG = "false" ]; do
	  echo ""
	  echo "Incomplete or invalid directory path: $ORGDIR" 
	  echo "Please enter Organization name again:"
	  read ORGNAME

	  ORGDIR="$BASEDIR/$ORGNAME"
	  ValidateOrg
       done
	  sleep 1
    fi

# Get the Repo name
#-------------------------------

      echo "Enter repo name under $ORGDIR:"
      read REPONAME
      REPODIR="$ORGDIR/$REPONAME"

      ValidateRepo
      
      if [[ $LOCATEDREPO = "false" ]]; then
	 while [ $LOCATEDREPO = "false" ]; do
	   echo ""
	   echo "Incomplete or invalid directory path: $REPODIR"
	   echo "Please enter Repo name again:"
	   read REPONAME
	   REPODIR="$ORGDIR/$REPONAME"

	   ValidateRepo
	 done
	   sleep 1
      fi
      
#----------------------------
echo ""
echo "Processing..."
echo "Creating full bundle for $ORGNAME-$REPONAME, please wait..."

# Make bundle
cd $REPODIR
git bundle create TEMP.bundle --all

# Rename bundle
echo ""
echo "Renaming bundle file..."
# x=`date +'%s%m'`
  x=`date +"%Y.%d.%m"`
  BUNDLENAME="OLEM-$x.bundle"
  mv TEMP.bundle $BUNDLENAME
  
echo ""
echo "New bundle name is: $BUNDLENAME"

TestBundle

# Move bundle to storage area
STORAGEDIR="/c/temp/$ORGNAME"
if [[ ! -d $STORAGEDIR ]]; then
    echo ""
    echo "Creating missing directory structure: $STORAGEDIR"
    mkdir -p $STORAGEDIR
    sleep 2
fi

# Give user a choice if remote file of same name already exists
if [[ -f $STORAGEDIR/$BUNDLENAME ]]; then
   OVERWRITE=""
   echo "Found file by same name in storage location:"
   echo "$BUNDLENAME"
   
   DecideOnOverwrite
   
   	if [[ $PROCEED = "y" ]];then
      	   echo ""
      	   echo "Overwriting remote file with new version..."
           rm -Rf  $STORAGEDIR/$BUNDLENAME
      	   sleep 1
      	   mv $BUNDLENAME $STORAGEDIR
      	   echo ""
      	   echo "File is here:"
      	   echo "$STORAGEDIR/$BUNDLENAME"
      	   AllDone
   	else
   	   echo ""
   	   echo "Keeping remote file,"
   	   echo "Leaving new version in:"
   	   echo "$REPODIR"
   	   AllDone
        fi
fi
     
# If none of the above conditions apply, just move the file
      echo ""
      echo "Moving bundle to storage area:"
      echo "$STORAGEDIR"
      mv $BUNDLENAME $STORAGEDIR
      AllDone
fi

