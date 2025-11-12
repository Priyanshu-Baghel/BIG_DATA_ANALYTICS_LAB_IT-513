
# Big Data Analytics Lab (IT-513)

**Experiment 3 – Word Count & Word Search using MapReduce**

**Contributor:** *Priyanshu Baghel*
**Mentor:** *Dr. Abhishek Narwaria, Assistant Professor*

---

## **Aim**

1. Implement a **Word Count** MapReduce program to understand the **MapReduce paradigm**.
2. Find the **number of occurrences** of each word in an input file.
3. Perform a **Word Search Count** (look for specific keywords) using MapReduce.

---

## **Theory**

**MapReduce** is a programming model for distributed processing of large datasets in a cluster.
It involves two main phases:

1. **Map Phase:**
   Splits input data into key–value pairs.
   Each mapper processes a portion of input and produces intermediate results.

2. **Reduce Phase:**
   Aggregates intermediate results produced by mappers and produces the final output.

**Advantages:**

* Handles large data in parallel.
* Automatically manages data distribution and fault tolerance.
* Scales easily from one to thousands of nodes.

---

## **Procedure**

### **Step 1: Upload Input File**

Upload an input file containing sample sentences to HDFS:

```bash
hadoop fs -put "D:\BDA_Lab\Input.txt" /input
```

---

### **Step 2: Configure Dependencies in IntelliJ**

In the project’s `pom.xml`, add the following dependencies:

```xml
<dependencies>
  <dependency>
    <groupId>org.apache.hadoop</groupId>
    <artifactId>hadoop-common</artifactId>
    <version>3.3.3</version>
  </dependency>
  <dependency>
    <groupId>org.apache.hadoop</groupId>
    <artifactId>hadoop-mapreduce-client-core</artifactId>
    <version>3.3.3</version>
  </dependency>
</dependencies>
```

---

### **Step 3: Create Java Files**

Create three Java classes under package `org.priyanshu`:

#### `WC_Mapper.java`

```java
package org.priyanshu;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class WC_Mapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreTokens()) {
            word.set(tokenizer.nextToken());
            output.collect(word, one);
        }
    }
}
```

---

#### `WC_Reducer.java`

```java
package org.priyanshu;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class WC_Reducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        int sum = 0;
        while (values.hasNext()) {
            sum += values.next().get();
        }
        output.collect(key, new IntWritable(sum));
    }
}
```

---

#### `WC_Runner.java`

```java
package org.priyanshu;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class WC_Runner {
    public static void main(String[] args) throws IOException {
        JobConf conf = new JobConf(WC_Runner.class);
        conf.setJobName("WordCount");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        conf.setMapperClass(WC_Mapper.class);
        conf.setCombinerClass(WC_Reducer.class);
        conf.setReducerClass(WC_Reducer.class);
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
        JobClient.runJob(conf);
    }
}
```

---

### **Step 4: Run MapReduce Job**

Run the program in IntelliJ terminal or CMD:

```bash
hadoop jar target/wordcount-1.0-SNAPSHOT.jar org.priyanshu.WC_Runner /input /output
```

---

### **Step 5: View Output**

Display output stored in HDFS:

```bash
hadoop fs -cat /output/part-*
```

## **Snapshots:**

* Snapshot  – Running the WordCount code
* Snapshot  – WordCount output result

---

## **Output Example**

**Input File (`Input.txt`):**

```
Hadoop MapReduce is powerful
Hadoop MapReduce is scalable
Hadoop is open source
```

**Output (`/output/part-r-00000`):**

```
Hadoop 3
MapReduce 2
is 3
powerful 1
scalable 1
open 1
source 1
```

---

## **Result**

Successfully implemented and executed a **Word Count** MapReduce program.
Learned how the **Map** phase splits data and the **Reduce** phase aggregates results to produce final output.

---

## **Concepts Learned**

* Writing and executing MapReduce programs in Java.
* Understanding Mapper, Reducer, and Driver (Runner) roles.
* Using IntelliJ IDE with Hadoop dependencies.
* Performing word frequency analysis in a distributed system.

---

## **Tools and Technologies**

* **Apache Hadoop 3.3.6**
* **Java (JDK 1.8)**
* **IntelliJ IDEA / Eclipse**
* **Windows / Linux Environment**

---

## **Important Compatibility Note**

> **(Source: Stack Overflow)**
> Hadoop **3.3.6** is fully **compatible** with **Java 8 (JDK 1.8)**.
> Using newer Java versions (like Java 11 or 17) can lead to
> **“Postfix Expression Error”** or MapReduce build failures.
>
> **Recommended Setup:**
>
> * Hadoop 3.3.6
> * Java JDK 1.8

---

## **Repository Structure**

```
/

|-- Code/dependencies.txt
|-- Code/WC_Mapper.java
|-- Code/WC_Reducer.java
|-- Code/WC_Runner.java
|-- ScreenShot/
|-- Video/
|-- Input.txt
|-- Experiment3.pdf
|-- README.md
```

---

