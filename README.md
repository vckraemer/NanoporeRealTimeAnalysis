# NanoporeRealTimeAnalysis

## Cluster Setup 

The cluster setup is performed using the BiBiGrid tool, when the application is deployed on an OpenStack cluster.

For installing BiBiGrid, the instruction in the documentation need to be followed (https://github.com/BiBiServ/bibigrid). The steps of the cluster setup are also described there (https://github.com/BiBiServ/bibigrid/blob/master/docs/README.md). When using BiBiGrid, it is important to include the here provided ansible roles 'general', 'mainnode' and 'othernodes' as tar.gz. The path to the archives must be specified in the BiBiGrid Configuration file.

## Setup on a Single Instance

First, the instance must be initialized. After initiolization, the in this repository provided 'singlecomputersetup.tar.gz' must be downloaded and the archive extracted. The archive contains the ansible playbook with all necessary dependencies for the streaming application. For execution of the playbook, ansible must be installed.
It is executed with:

```
ansible-playbook -h playbook/hosts playbook/site.yml
```

## Elasticsearch Database Setup 

The Elasticsearch databse can be set up by following the steps in the documentation (https://www.elastic.co/guide/en/elasticsearch/reference/current/targz.html).
Furthermore, Kibana must be initiated after the database setup is finished. The steps can also be found in the documentation (https://www.elastic.co/guide/en/kibana/current/targz.html).
When Settingup Elasticsearch and Kibana 
To use the Elasticsearch databse with the application, the instance must be in the same security group as the cluster or single machine. When enough resources are avaiable, it is possible to start the database on the same instance as the streaming application. 

## Start Application 

After the setup with the provided ansible roles or playbook, the streaming application can be directly executed. Depending on the cluster setup, the spark-submit call must contain specific options.

Cluster Setup:
```
./spark/spark-2.4.4-bin-hadoop2.7/bin/spark-submit 
  --class "Streaming" (main class of the streaming application)
  --master spark://MasterIP:PortOfSparkApplication (standard port is 7077)
  --conf spark.executor.memory=availableRAM (e.g. 32g) 
  --conf spark.driver.memory=availableRAM (e.g. 32g) 
  --executor-cores numberOfAvailableCores (e.g. 14)
  --conf spark.driver.maxResultSize=2g 
  --conf spark.streaming.concurrentJobs=2 
  --conf spark.default.parallelism=   (at least one time the number of avaiable cores, e.g. 14 for 14 cores) 
  --conf spark.scheduler.allocation.file=fair_pools.xml (xml with defined schedular pools, default the in this repository provided schedular file with one FIFO schedular)
  path/to/application/jar/npanalyse-1.0-SNAPSHOT-jar-with-dependencies.jar 
      -ip ElasticsearchIP 
      -port ElasticsearchPort
      -prefix ElasticsearchIndexPrefix 
      -f PathToMonitoredFolder (when using the provided ansible roles, a folder is created with the path /vol/spool/sequences)
      -ldb AntibtioticResistanceDatabase 
      -lp numberOfThreadsForLAST 
      -cp numberOfThreadsForCentrifuge
```
Single Instance Setup
```
./spark/spark-2.4.4-bin-hadoop2.7/bin/spark-submit 
    --class "Streaming" (main class of the streaming application)
    --master local[numberOfAvailableCores] 
    --conf spark.driver.maxResultSize=2g 
    --conf spark.scheduler.allocation.file=fair_pools.xml (xml with defined schedular pools, default the in this repository provided schedular file with one FIFO schedular)
    NanoRealTimeAnalysis/target/npanalyse-1.0-SNAPSHOT-jar-with-dependencies.jar 
      -ip ElasticsearchIP 
      -port ElasticsearchPort
      -prefix ElasticsearchIndexPrefix 
      -f PathToMonitoredFolder (when using the provided ansible roles, a folder is created with the path /vol/spool/sequences)
      -ldb AntibtioticResistanceDatabase 
      -lp numberOfThreadsForLAST 
      -cp numberOfThreadsForCentrifuge
```
Important notes:
- When choosing a different folder for monitoring, it is important, that all nodes can access this folder with the FASTQ files, because of the fault tolerance mechanisms in Spark. When using Spark on a single machine, the folder is normally always accessible, depending on the permission settings of the folder.
