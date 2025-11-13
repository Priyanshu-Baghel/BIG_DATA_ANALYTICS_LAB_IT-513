
# Big Data Analytics Lab (IT-513)

## Experiment 10 – Install, Deploy, and Configure Apache Spark Cluster

**Contributor:** Priyanshu Baghel
**Mentor:** Dr. Abhishek Narwaria, Assistant Professor

---

## Aim

To install, deploy, and configure an Apache Spark cluster, and run a basic Spark application to verify successful setup.

---

## Introduction

Apache Spark is an open-source distributed computing framework designed for fast, scalable, and flexible big data processing. Spark provides high-performance in-memory computation and supports a wide variety of analytics tasks, including:

* Batch processing
* Real-time stream processing
* Machine Learning
* Graph processing
* SQL-based analytics

Spark supports multiple languages, such as Python, Scala, Java, and R, and works seamlessly with tools like Hadoop, Kafka, and Hive.

---

## Key Features of Apache Spark

### 1. Speed

Spark processes data in memory rather than writing intermediate results to disk, making it significantly faster than traditional MapReduce.

### 2. Ease of Use

Spark provides APIs in Python (PySpark), Scala, Java, and R, along with interactive shells for quick experimentation.

### 3. Versatility

Spark supports various workloads through its components:

* **Spark Core** – foundational computational engine
* **Spark Streaming** – stream processing
* **Spark SQL** – SQL querying and DataFrames
* **MLlib** – machine learning algorithms
* **GraphX** – graph computation

### 4. Unified Big Data Platform

Spark integrates with Hadoop HDFS, Hive, Kafka, and other frameworks to build end-to-end data processing pipelines.

### 5. Scalability

A Spark cluster can scale from a single machine to thousands of nodes, processing petabytes of data efficiently.

---

## Prerequisites

* Operating System: Linux, Windows, or macOS
* Java JDK 8 or later
* Python (optional, for PySpark)
* Hadoop installation (optional, if using HDFS as storage)
* Internet connection
* Sufficient system memory for Spark workloads

---

## Procedure

### Step 1: Download Spark

1. Visit the official Apache Spark website:
   [https://spark.apache.org/downloads.html](https://spark.apache.org/downloads.html)
2. Select a stable Spark version compatible with your system.
3. Download the pre-built binary package.

---

### Step 2: Extract Spark Files

Extract the downloaded `.tgz` or `.zip` file to a directory such as:

```
/opt/spark
```

---

### Step 3: Configure Environment Variables

Edit the `.bashrc` file:

```
nano ~/.bashrc
```

Add the following lines:

```
export SPARK_HOME=/opt/spark
export PATH=$SPARK_HOME/bin:$SPARK_HOME/sbin:$PATH
```

Save and reload the file:

```
source ~/.bashrc
```

---

### Step 4: Start Spark Master

Run:

```
start-master.sh
```

This launches the Spark master node.

The master UI can be accessed at:

```
http://localhost:8080
```

---

### Step 5: Start Spark Worker

Start a worker and connect it to the master:

```
start-slave.sh spark://<master-IP>:<master-port>
```

Replace `<master-IP>` and `<master-port>` with the values shown on the Spark master UI.

---

### Step 6: Run a Simple Spark Application

Example using Scala Spark Shell:

```
spark-shell
```

Test Spark functionality:

```scala
val data = sc.parallelize(Seq(1, 2, 3, 4, 5))
val result = data.map(x => x * 2).collect()
result.foreach(println)
```

This verifies cluster execution and parallel processing.

---

## Output

The output includes:

* Spark master UI running successfully
* Spark worker registered with the master
* Execution results from a basic Spark job
* Parallel data transformation using Spark Shell

(Refer to the output screenshots in the repository.)

---

## Conclusion

In this experiment, Apache Spark was successfully installed, configured, and deployed as a cluster. A sample Spark application was executed to verify cluster functionality.

Apache Spark's in-memory computation, scalability, multi-language support, and ability to process batch, stream, graph, and machine learning workloads make it one of the most powerful platforms for modern big data analytics.

---
