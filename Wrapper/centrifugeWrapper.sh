#!/bin/bash

filename=$1
dbpath=$2
savefile=$filename"reads.fasta"

cd /home/ubuntu/vol/tmp_results/ || { echo "Failure"; exit 1; }

while read LINE; do
   echo "${LINE}" >> "$savefile"
done

if [ $dbpath == "" ]; then
    centrifuge -f -k 1 -p 8 -x /mnt/p_compressed+h+v/p_compressed+h+v "$savefile"
else
      centrifuge -f -k 1 -p 8 -x "$dbpath" "$savefile"
fi


rm -f "$savefile"