#!/bin/bash

VAR=""
savefile=$1"LineageInput.txt"

cd /home/ubuntu/vol/spool/tmp_results
while read LINE; do
	echo "${LINE}" >> $savefile
done

cd /home/ubuntu/

./megan_taxon2lineage.pl -db taxonomy/ -f vol/spool/tmp_results/$savefile
