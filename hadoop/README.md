# Hadoop Zeppelin Project

* [Introduction](#Introduction)
* [Hadoop Cluster](#Hadoop-Cluster)
* [Hive Project](#Hive-Project)
* [Further Improvements](#Further-Improvements)

## Introduction

After a successful job with the Python data analytics, the Jarvis team is now looking to use Apache Hadoop to process big data.
We are tasked with analyzing and processing the World Development Indicators (WDI) dataset, which contains approximately 22 million data points.

Built using **Hadoop**, **Hive**, and . Hadoop cluster provisioned using the **Google Cloud Platform**. Results saved to a **Zeppelin** notebook.

## Hadoop Cluster
- cluster architecture diagram
    - 1 master and 2 workers nodes
    - HDFS, YARN, Zeppelin, Hive (hive Server, hive metastore, RDBMS), etc.
- Big data tools you evaluated (e.g. MapReduce, YARN, HDFS, Hive, Zeppelin, etc..)
- hardware specifications

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


* **Improvement 2**
  * A nice 


* **Improvement 3**
  * Rather than providing the instructions to improvement 3 test.