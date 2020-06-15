#!/usr/bin/env python3
#-*- coding: utf-8 -*-
from ete3 import NCBITaxa
import sys

ncbi = NCBITaxa()
lineage_dict = {}

for line in sys.stdin:
       ids = line.split(';')
       lineage_dict[ids[0]] = ids[1]

for key in lineage_dict:
        lineage = ncbi.get_lineage(lineage_dict[key])
        names = ncbi.get_taxid_translator(lineage)
        lineagenames = '|'.join(str(x) for x in names.values())
        print (key +'|'+ lineagenames)