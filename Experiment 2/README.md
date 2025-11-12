# Big Data Analytics Lab (IT-513)

**Experiment 2 – Hadoop File Management and Benchmarking**

**Contributor:** *Priyanshu Baghel*
**Mentor:** *Dr. Abhishek Narwaria, Assistant Professor*

---

## **Aim**

1. Implement file management tasks in Hadoop:

   * Adding files and directories
   * Retrieving files
   * Deleting files

2. Perform **benchmarking** and **stress testing** of an Apache Hadoop cluster to evaluate performance and scalability.

---

## **Theory**

Hadoop provides the **Hadoop Distributed File System (HDFS)** to manage and process large-scale datasets in a distributed environment.
HDFS allows users to perform file operations (add, retrieve, delete) via the **Hadoop File System (FS) shell commands**.

Benchmarking and stress testing are essential for evaluating the performance, reliability, and throughput of a Hadoop cluster.
They involve generating workloads, measuring metrics like throughput and latency, and pushing the cluster to its limits to analyze performance bottlenecks.

---



# Hadoop Basic Commands

This section covers all fundamental Hadoop File System (HDFS) commands used for **file management**, **cluster monitoring**, **job execution**, and **administration**.

---

## **1. HDFS File Management Commands**

These commands are used to interact with files and directories in the Hadoop Distributed File System (HDFS).

| **Command**                               | **Description**                                                  | **Example**                                           |
| ----------------------------------------- | ---------------------------------------------------------------- | ----------------------------------------------------- |
| `hadoop fs -ls /`                         | Lists files and directories in the root of HDFS                  | `hadoop fs -ls /`                                     |
| `hadoop fs -mkdir /dir_name`              | Creates a new directory in HDFS                                  | `hadoop fs -mkdir /input`                             |
| `hadoop fs -put <local_path> <hdfs_path>` | Uploads a file from local system to HDFS                         | `hadoop fs -put input.txt /input`                     |
| `hadoop fs -get <hdfs_path> <local_path>` | Downloads a file from HDFS to local system                       | `hadoop fs -get /input/input.txt D:\Data`             |
| `hadoop fs -cat <file_path>`              | Displays contents of a file in HDFS                              | `hadoop fs -cat /input/input.txt`                     |
| `hadoop fs -copyFromLocal <src> <dst>`    | Copies a local file to HDFS                                      | `hadoop fs -copyFromLocal data.txt /input`            |
| `hadoop fs -copyToLocal <src> <dst>`      | Copies a file from HDFS to local                                 | `hadoop fs -copyToLocal /output/result.txt D:\Output` |
| `hadoop fs -mv <src> <dst>`               | Moves or renames a file/directory                                | `hadoop fs -mv /input/data.txt /archive/data.txt`     |
| `hadoop fs -cp <src> <dst>`               | Copies a file/directory within HDFS                              | `hadoop fs -cp /input/data.txt /backup/data.txt`      |
| `hadoop fs -rm <file_path>`               | Deletes a file                                                   | `hadoop fs -rm /input/data.txt`                       |
| `hadoop fs -rm -r <dir_path>`             | Deletes a directory recursively                                  | `hadoop fs -rm -r /input`                             |
| `hadoop fs -rmdir <dir_path>`             | Removes an empty directory                                       | `hadoop fs -rmdir /temp`                              |
| `hadoop fs -du <dir_path>`                | Displays disk usage statistics for files/directories             | `hadoop fs -du /input`                                |
| `hadoop fs -count <path>`                 | Displays the count of directories, files, and bytes under a path | `hadoop fs -count /input`                             |
| `hadoop fs -tail <file_path>`             | Shows the last 1KB of a file                                     | `hadoop fs -tail /logs/app.log`                       |
| `hadoop fs -text <file_path>`             | Displays file contents in text format                            | `hadoop fs -text /output/result.seq`                  |
| `hadoop fs -chmod <permissions> <path>`   | Changes permissions of a file/directory                          | `hadoop fs -chmod 755 /input`                         |
| `hadoop fs -chown <user>:<group> <path>`  | Changes ownership of a file/directory                            | `hadoop fs -chown hadoopuser:hadoop /input`           |
| `hadoop fs -test -e <path>`               | Tests existence of a file or directory                           | `hadoop fs -test -e /input/data.txt`                  |

---

## **2. Cluster Setup & Management Commands**

Used for managing Hadoop daemons and checking cluster health.

| **Command**                     | **Description**                                               |
| ------------------------------- | ------------------------------------------------------------- |
| `start-dfs.cmd`                 | Starts NameNode and DataNode daemons (Windows)                |
| `start-yarn.cmd`                | Starts ResourceManager and NodeManager daemons (Windows)      |
| `stop-dfs.cmd`                  | Stops NameNode and DataNode daemons                           |
| `stop-yarn.cmd`                 | Stops ResourceManager and NodeManager daemons                 |
| `jps`                           | Lists all active Hadoop daemons (Java processes)              |
| `hdfs namenode -format`         | Formats the NameNode (used during first-time setup)           |
| `hdfs dfsadmin -report`         | Displays detailed information about cluster storage and nodes |
| `hdfs dfsadmin -safemode get`   | Checks current safemode status                                |
| `hdfs dfsadmin -safemode enter` | Puts NameNode into safemode (read-only mode)                  |
| `hdfs dfsadmin -safemode leave` | Takes NameNode out of safemode                                |
| `hdfs fsck /`                   | Checks the health of the Hadoop file system                   |
| `hdfs dfs -du -h /`             | Shows disk usage in human-readable format                     |

---

## **3. MapReduce Job Commands**

Used for running and managing MapReduce programs.

| **Command**                                           | **Description**                        | **Example**                                                       |
| ----------------------------------------------------- | -------------------------------------- | ----------------------------------------------------------------- |
| `hadoop jar <jar_file> <main_class> <input> <output>` | Runs a MapReduce job using a JAR file  | `hadoop jar wordcount.jar WordCount /input /output`               |
| `yarn jar <jar_file> <main_class> <input> <output>`   | Runs a job using YARN resource manager | `yarn jar hadoop-mapreduce-examples.jar wordcount /input /output` |
| `mapred job -list`                                    | Lists all running MapReduce jobs       |                                                                   |
| `mapred job -status <job_id>`                         | Displays status of a specific job      |                                                                   |
| `mapred job -kill <job_id>`                           | Terminates a running job               |                                                                   |
| `mapred queue -list`                                  | Lists all available job queues         |                                                                   |

---

## **4. Benchmarking & Stress Testing Commands**

Used to test Hadoop cluster performance.

| **Command**                                                                                                                | **Purpose**                            |
| -------------------------------------------------------------------------------------------------------------------------- | -------------------------------------- |
| `hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.3.6.jar teragen <num_rows> <output_dir>`       | Generates large input data for testing |
| `hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.3.6.jar terasort <input_dir> <output_dir>`     | Sorts the generated data               |
| `hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.3.6.jar teravalidate <input_dir> <output_dir>` | Validates sorted data                  |

---

## **5. Web Interfaces**

| **Component**      | **Default URL**         | **Purpose**                                              |
| ------------------ | ----------------------- | -------------------------------------------------------- |
| NameNode UI        | `http://localhost:9870` | View HDFS directories, data blocks, and node information |
| ResourceManager UI | `http://localhost:8088` | Monitor running YARN applications                        |
| DataNode UI        | `http://localhost:9864` | View DataNode status                                     |
| NodeManager UI     | `http://localhost:8042` | Monitor NodeManager resource usage                       |

---

## **Repository Structure**

```
/
|-- Experiment2.pdf
|-- Code/
|-- Screenshots/
|-- Videos/
|-- README.md
```

## **Result**

The commands for **file management**, **benchmarking**, and **stress testing** were successfully executed in the Hadoop environment.
Performance metrics such as operation throughput and execution time were observed for validation.

---

## **Snapshots:**

* Snapshot 1 – File management commands execution
* Snapshot 2 – Browsing results in HDFS
* Snapshot 3 – Benchmark and stress test results in HDFS

---

## **Concepts Learned**

* Hands-on experience with **HDFS file operations**.
* Understanding of **benchmarking and performance testing** in distributed systems.
* Use of **MapReduce examples** for evaluating Hadoop cluster efficiency.

---

## **Tools and Technologies**

* **Apache Hadoop 3.3.6**
* **Java (JDK 1.8)**
* **Windows OS / Linux Environment**
* **Command Line Interface (CLI)**

---