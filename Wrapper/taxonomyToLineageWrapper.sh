#!/bin/bash

VAR=""
savefile=$1"LineageInput.txt"

cd /home/ubuntu/vol/tmp_results/ || { echo "Failure"; exit 1; }

while read LINE; do
	echo "${LINE}" >> "$savefile"
done

cd /home/ubuntu/ || { echo "Failure"; exit 1; }

./megan_taxon2lineage.pl -db taxonomy/ -f /home/ubuntu/vol/tmp_results/"$savefile"

rm -f /home/ubuntu/vol/tmp_results/"$savefile"