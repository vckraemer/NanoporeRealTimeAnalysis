#!/bin/bash

VAR=""
savefile=$1"LineageInput.txt"

cd /home/ubuntu/vol/tmp_results
while read LINE; do
	echo "${LINE}" >> $savefile
done

cd /home/ubuntu/

./megan_taxon2lineage.pl -db taxonomy/ -f /home/ubuntu/vol/tmp_results/$savefile
