#!/bin/bash

filename=$1
savefile=$filename"reads.fasta"

cd /home/ubuntu/vol/tmp_results/ || { echo "Failure"; exit 1; }

while read LINE; do
   echo "${LINE}" >> "$savefile"
done

centrifuge -f -k 1 -p 8 -x /home/ubuntu/vol/spool/p_compressed+h+v/p_compressed+h+v "$savefile"

rm -f "$savefile"