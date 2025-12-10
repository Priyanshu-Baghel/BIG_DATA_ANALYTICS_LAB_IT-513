# BIG DATA ANALYTICS LAB (IT-513)

**Contributor:** *Priyanshu Baghel*
**Mentor:** *Dr. Abhishek Narwaria, Assistant Professor*

This repository contains **all 12 experiments** for the **Big Data Analytics Laboratory (IT-513)**.
Each experiment includes:

* Complete theory
* Step-by-step procedure
* Commands / code
* Outputs
* PDF documentation
* Screenshots / Source code (where applicable)

This repository is designed to serve as a **complete lab manual + implementation guide** for students learning Hadoop, Spark, Hive, Pig, Power BI, and Big Data ecosystems.

---

# Lab Experiments Overview

## **Experiment 1 – Hadoop Installation & HDFS Setup**

* Install Hadoop
* Configure Java + Hadoop paths
* Set up NameNode & DataNode directories
* Start HDFS & YARN
* Access NameNode UI (9870) and ResourceManager UI (8088)

**Outputs:** Hadoop installation verified, cluster running.

---

## **Experiment 2 – HDFS File Management & Benchmarking**

* Create, upload, move, and delete files in HDFS
* Run benchmarking tools:

  * TeraGen
  * TeraSort
  * TeraValidate
* Performance testing of HDFS

**Outputs:** Benchmark results stored in HDFS.

---

## **Experiment 3 – MapReduce: Word Count & Word Search**

* Implement Mapper, Reducer, Driver in Java
* Run MapReduce word count job
* View output in HDFS

**Outputs:** Word frequency results.

---

## **Experiment 4 – Stop Word Elimination (MapReduce)**

* Load stopwords from DistributedCache
* Remove stopwords from input text
* Output cleaned sentences

**Outputs:** Stopword-filtered text file.

---

## **Experiment 5 – Weather Data Analysis (MapReduce)**

* Parse monthly temperature data
* Compute:

  * Minimum temperature
  * Maximum temperature
  * Average temperature
* Output yearly temperature summary

**Outputs:** Year → Min, Max, Avg temperature.

---

## **Experiment 6 – Purchases Dataset Analysis using Spark**

* Load purchases dataset
* Convert to Spark DataFrame
* Compute category-wise total sales
* Find highest sales for specific stores
* Use groupBy, agg, and transformations

**Outputs:** Sales summary table.

---

## **Experiment 7 – Pig Installation & Pig Latin Scripts**

* Install Apache Pig
* Run in local mode
* Pig Latin operations:

  * LOAD
  * FILTER
  * GROUP
  * ORDER
  * FOREACH GENERATE

**Outputs:** Sorted, grouped, and filtered data.

---

## **Experiment 8 – TF-IDF Computation using Pig**

* Load text documents
* Tokenize text
* Compute TF, DF, IDF
* Produce TF-IDF values

**Outputs:** TF-IDF result file.

---

## **Experiment 9 – Hive Installation & HiveQL Operations**

* Install and configure Hive
* Configure MySQL metastore
* HiveQL operations:

  * CREATE / USE / DROP DATABASE
  * CREATE / ALTER / RENAME TABLE
  * INSERT records
  * CREATE / DROP VIEW
  * CREATE / DROP INDEX
  * Add and use UDF

**Outputs:** Structured Hive tables and results.

---

## **Experiment 10 – Apache Spark Cluster Installation**

* Download Spark
* Set SPARK_HOME
* Start master & worker nodes
* Run simple RDD program
* Validate distributed execution

**Outputs:** Spark UI + working Spark job.

---

## **Experiment 11 – Frequent Product Pair Analysis (Spark)**

* Use Amazon Fine Food Reviews dataset
* Group products by user
* Generate product pairs
* Count co-occurrence frequency
* Filter pairs occurring more than once

**Outputs:** List of frequent product pairs.

---

## **Experiment 12 – Power BI Visualization (Donut Chart & Tree Map)**

* Load population dataset
* Create Donut Chart
* Morph Donut Chart into Tree Map
* Visualize hierarchical population distribution

**Outputs:** Power BI .pbix file with visuals.

---

# Technologies Used

* **Apache Hadoop (3.x)**
* **Apache Spark (Scala / PySpark)**
* **Apache Pig**
* **Apache Hive**
* **YARN / HDFS**
* **Java (JDK 8)**
* **Scala**
* **Power BI**
* **Windows / Linux**

---

# Repository Structure

```
/
│-- Experiment1/
│-- Experiment2/
│-- Experiment3/
│-- Experiment4/
│-- Experiment5/
│-- Experiment6/
│-- Experiment7/
│-- Experiment8/
│-- Experiment9/
│-- Experiment10/
│-- Experiment11/
│-- Experiment12/
│-- README.md
```

Each folder contains:

* PDF
* Code (Java / Scala / Pig / HiveQL)
* Screenshots
* Input files
* Output files
* Installation Video's

---

# Conclusion

This repository provides a **complete practical implementation** of Big Data Analytics concepts using Hadoop, Spark, Hive, Pig, and Power BI. It covers the entire workflow from distributed storage, MapReduce programming, and ETL scripting to advanced analytics and visualization.

It is designed as a full reference for IT-513 students, enabling them to understand both the theoretical and hands-on components of Big Data systems.

---

# Special Thanks

A special thanks to our senior **Faizan Sir & Udesh Sir**, whose continuous guidance, support, and motivation played an important role in completing this Big Data Analytics Lab work.
His mentorship helped in understanding complex concepts, troubleshooting issues, and maintaining consistency throughout all 12 experiments.

