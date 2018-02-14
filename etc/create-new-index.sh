#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "No arguments supplied"
    echo "Needs to be local, devqa, int, or live."
    exit 1
fi

ENV=$1
echo

if [ $1 = "local" ]; then
  ESHOST=http://localhost:9200
elif [ $1 = "devqa" ]; then
  ESHOST=http://search-esmedloada-medicaldiscover-d-hni6gso7xwouu4breodpdppfdu.us-east-1.es.amazonaws.com
elif [ $1 = "int" ]; then
  ESHOST=https://search-esmedloada-medicaldiscover-i-xmsvmvqlxyddnsza56ev6fjotq.us-east-1.es.amazonaws.com
elif [ $1 = "live" ]; then
  ESHOST=https://search-esmedloada-medicaldiscover-l-knuzps5mvuwmywumcgl4rmq5aa.us-east-1.es.amazonaws.com
else
  echo "Inappropriate environment argument specified.  Needs to be local, devqa, int, or live."
  exit $?
fi

DH_INDEX=dmp2
DATE=$(date "+%Y%m%d%M%S")
DH_NEW_INDEX=${DH_INDEX}_${DATE}
DH_INDEX_URL=${ESHOST}/${DH_NEW_INDEX}
JSON_DIRECTORY=../src/main/resources/

echo
echo "Connecting to ${ESHOST}..."
echo
############################ Create the new index #######################
echo
echo "Creating new DH index: ${DH_NEW_INDEX}.."
echo
curl -s -XPUT "${DH_INDEX_URL}?pretty" -d @${JSON_DIRECTORY}dmp2-settings-v2.json
if [ $? -ne 0 ]; then
  echo "Error when creating new index" 1>&2
  exit $?
fi


############################ Add the mapping to the index ###############
echo
echo "Adding the mapping to the new index: ${DH_NEW_INDEX}.."
echo
curl -s -XPUT "${DH_INDEX_URL}/_mapping/dmp2?pretty" -d @${JSON_DIRECTORY}dmp2-mappings.json
if [ $? -ne 0 ]; then
  echo "Error when applying mapping to new index" 1>&2
  exit $?
fi

echo
echo "The new index has been created."
echo ${DH_INDEX_URL}/_mappings
echo ${DH_INDEX_URL}/_settings
echo ${DH_INDEX_URL}/_count
echo ${DH_INDEX_URL}/_search
