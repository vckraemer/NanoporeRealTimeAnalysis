#!/bin/bash

savefile=$1"LineageInput.txt"

cd /mnt/tmp_results/ || { echo "Failure"; exit 1; }

while read LINE; do
	echo "${LINE}" >> "$savefile"
done

cd /home/ubuntu/ || { echo "Failure"; exit 1; }

./megan_taxon2lineage.pl -db taxonomy/ -f /mnt/tmp_results/"$savefile"

rm -f /mnt/tmp_results/"$savefile"