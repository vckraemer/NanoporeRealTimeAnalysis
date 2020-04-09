#!/bin/bash

filename=$1
savefile=$filename"reads.fasta"

cd /home/ubuntu/vol/spool/tmp_results/

sudo rm -f $filename"reads.fasta"
sudo rm -f $filename"LineageInput.txt"
