#!/bin/bash

filename=$1
dbpath=$2
threads=$3
savefile=$filename"reads.fasta"

cd /mnt/tmp_results/ || { echo "Failure"; exit 1; }

while read LINE; do
   echo "${LINE}" >> "$savefile"
done

if [ "$dbpath" == "provided" ]; then
    centrifuge -f -k 1 -p "$threads" -x /mnt/p+h+v/p+h+v "$savefile"
else
    centrifuge -f -k 1 -p "$threads" -x "$dbpath" "$savefile"
fi


rm -f "$savefile"