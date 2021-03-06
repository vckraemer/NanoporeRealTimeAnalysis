#!/usr/bin/env python3
#-*- coding: utf-8 -*-
from ete3 import NCBITaxa
import sys
import re

ncbi = NCBITaxa()
lineage_dict = {}

for line in sys.stdin:
        if ";" in line:
            ids = line.split(';')
            if len(ids) is 2:
                lineage_dict[ids[0]] = ids[1].strip()

for key in lineage_dict:
        if re.match(r"^[1-9][0-9]*$",lineage_dict[key]):
            try:
                lineage = ncbi.get_lineage(lineage_dict[key])
                names = ncbi.get_taxid_translator(lineage)
                lineagenames = '|'.join(str(names[x])+'_'+str(ncbi.get_rank([x])[x]) for x in names)
                print (key +'|'+ lineagenames)
            except:
                print (key+'|unclassified')
                pass
        else:
            print (key+'|unclassified')