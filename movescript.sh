#!/bin/bash
while true; do
        ls -1 /path/origin/sequence/files | while read file
        do
                cp $file /path/monitored/folder
                sleep 120
        done
done

exit 0
