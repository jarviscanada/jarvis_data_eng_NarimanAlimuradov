# Hadoop Zeppelin Project

* [Introduction](#Introduction)
* [Hadoop Cluster](#Hadoop-Cluster)
* [Hive Project](#Hive-Project)
* [Further Improvements](#Further-Improvements)

## Introduction

After a successful job with the Python data analytics project, the Jarvis team is now looking to use Apache Hadoop to process big data.
We were tasked with analyzing and processing the World Development Indicators (WDI) dataset, which contains approximately 22 million data points.

Built using **Hadoop** and **Hive**. Hadoop cluster provisioned using the **Google Cloud Platform**. Results saved to a **Zeppelin** notebook.

## Hadoop Cluster

> Basic architecture of the master and worker nodes.

![Architecture](assets/hadoop.png)

A brief rundown of the tools used:

| Tool  | Usage |
  | ------------- | ------------- |
| Hadoop | Open-source framework that lets you easily perform distributed computing tasks.  |
| HDFS | Hadoop's file system. Performs the administrative tasks that make all the nodes look like one system.  |
| YARN | Hadoop's resource negotiator. Manages jobs and distributes resources on your cluster. |
| Zeppelin | Online notebook that allows you to perform analytics on your Hadoop cluster. |
| Hive | Interface that abstracts away the MapReduce implementation and allows you to treat the cluster like an RDBMS. |
| MapReduce | Algorithm that lets you process data in batches using mappers (distribute and transform the data) and reducers (aggregate data for a result). |

The cluster was provisioned using Google Cloud Platform's **Dataproc**. 
Two worker nodes and a master node were used, each containing 12 GB of memory with 100 GB in disk size.

## Hive Project
When working with lots of data, any minor optimization can have a large impact on query efficiency. 
To speed up our queries, I **partitioned** the data tables so that if we need to access data that is contained within one partition, we can use only that partition rather than loading all the data into memory.
  
Furthermore, I used a **columnar file format** which stores our data by having columns be adjacent to each other rather than rows.
Queries that require us to get a value from each row in a table are much faster in a columnar file because they will be all found in one column. 
Had we not used a columnar file format, we would have to access each row in the table to get what we need.

> Below is my Zeppelin notebook containing the Hive queries and noteworthy comments.

![Zeppelin Notebook](assets/zeppelin.png)

## Further Improvements

* **Spark Engine**
  * Spark had a noticeably better performance than Apache Tez when tested. We can reduce our overall notebook runtime by using it for each query.


* **More Worker Nodes**
  * The Hadoop cluster currently uses one master and two worker nodes. Increasing the number of worker nodes on our cluster can allow for greater parallelization which will lead to faster execution times.


* **Further Efficiency Increases**
  * Hive and Hadoop offer several other ways to make your queries faster, such as map-side joins and indexing. 
    Every minor improvement in efficiency can go a long way when dealing with big data, so incorporating them into the notebook can shave off a lot of execution time. 