Here’s a polished **README.md** file for your repository that includes details from your *Experiment No. 1 PDF* and your **compatibility note** regarding Hadoop and Java versions 👇

---

# Big Data Analytics Lab (IT-513)

**Contributor:** *Priyanshu Baghel*
**Mentor:** *Dr. Abhishek Narwaria, Assistant Professor*

---

## Experiment 1: Hadoop Setup and Installation in HDFS

### **Aim**

Perform setup and installation of **Hadoop** in the **Hadoop Distributed File System (HDFS)** environment.

---

### **Theory**

Hadoop is an open-source, Java-based framework that enables the **storage and processing of large-scale data** across distributed computing environments. It breaks large workloads into smaller tasks that can be processed in parallel.

The Hadoop ecosystem consists of four core modules:

1. **Hadoop Distributed File System (HDFS):**
   Provides distributed storage with high-throughput access to application data.

2. **YARN (Yet Another Resource Negotiator):**
   Manages computing resources and job scheduling across the cluster.

3. **MapReduce:**
   A programming model for processing large datasets in parallel across nodes.

4. **Hadoop Common:**
   Includes shared libraries and utilities required by other Hadoop modules.

---

### **Procedure**

1. **Install Java (JDK 1.8):**

   * Create a folder `C:\Java`
   * Set up the environment variable:
     `JAVA_HOME = C:\Java\jdk1.8\bin`
   * Add the Java path to the system environment variables.

2. **Install Hadoop:**

   * Download **Apache Hadoop** and extract it to `C:\hadoop`.
   * Set environment variable:
     `HADOOP_HOME = C:\hadoop\bin`
   * Add `C:\hadoop\bin` and `C:\hadoop\sbin` to the system path.

3. **Configure Hadoop:**

   * Edit `hadoop-env.cmd` in `hadoop/etc/hadoop` and set the correct Java path.
   * Create a `data` directory with two subfolders:
     `namenode` and `datanode`.
   * Configure the following XML files:

     * `core-site.xml`
     * `hdfs-site.xml`
     * `mapred-site.xml`
     * `yarn-site.xml`

4. **Start Hadoop Services:**

   ```bash
   hadoop namenode -format
   cd /hadoop/sbin
   start-dfs.cmd
   start-yarn.cmd
   jps
   ```

5. **Verify Installation:**

   * Open a browser and visit:

     * **[http://localhost:9870](http://localhost:9870)** → NameNode Web UI
     * **[http://localhost:8088](http://localhost:8088)** → ResourceManager Web UI

---

### **Output**

*Snapshot 1:* HDFS Browser at `localhost:9870`
*Snapshot 2:* Cluster at `localhost:8088`

---

### **Conclusion**

Successfully installed and configured **Apache Hadoop** with **HDFS** and verified its functionality using local web interfaces.

---

##  Tools and Technologies

* **Apache Hadoop 3.3.6**
* **Java (JDK 1.8)**
* Windows OS (Pseudo-Distributed Mode)
* Command Line Interface

---

## Important Compatibility Note

> **Note (Source: Stack Overflow):**
> Hadoop **version 3.3.6** and **Java version 8** are **compatible** with each other.
> Using other or newer Java versions (such as Java 11 or 17) may cause errors — particularly **“postfix expression” compilation issues** in **MapReduce** code (seen in subsequent experiments).
>
> To avoid this, ensure your environment uses **JDK 1.8** for all Hadoop-based experiments.
> **Use this Command only ones hadoop namenode -format** because if you run this command more then one time you get error in datanode and namenode of ClusterId is not same. 

---

## Repository Structure

```
/
|-- Code/
|-- Screenshots/
|-- Videos/
|-- Experiment No. 1.pdf
|-- README.md
```

---

## 🎯 Learning Outcome

* Gained practical understanding of **Hadoop architecture**.
* Installed and configured a working **HDFS environment**.
* Verified Hadoop services and web-based cluster monitoring.

---

