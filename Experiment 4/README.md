# Big Data Analytics Lab (IT-513)

## Experiment 4 – Stop Word Elimination using Hadoop MapReduce

**Contributor:** Priyanshu Baghel
**Mentor:** Dr. Abhishek Narwaria, Assistant Professor

---

## Aim

To remove stop words from a large textual file using Hadoop MapReduce.
The program takes:

* A large input text file (one sentence per line).
* A stop word file (one stop word per line).

The output contains the same sentences but without any stop words.

---

## Theory

Stop words are commonly used words in natural language (such as *the*, *is*, *at*, *and*) which are often removed in preprocessing steps of text mining, NLP, and information retrieval.

There is no universal stop word list; each application chooses its own list based on context.

Removing stop words helps reduce noise and improves the efficiency of text processing tasks such as classification, clustering, and keyword extraction.

This experiment uses the Hadoop MapReduce framework to eliminate stop words in a distributed manner.

---

## Procedure

### Step 1: Prepare Inputs

Create two files:

1. `largefile.txt` – contains sentences (one sentence per line).
2. `stopword.txt` – contains stop words (one per line).

Upload both files to HDFS:

```bash
hadoop fs -put "local_file_path" /input
```

---

### Step 2: Add Required Maven Dependencies

Include the following dependencies in `pom.xml`:

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
    <dependency>
        <groupId>org.apache.hadoop</groupId>
        <artifactId>hadoop-hdfs</artifactId>
        <version>3.3.3</version>
    </dependency>
</dependencies>
```

---

### Step 3: Create Java Files

Create three Java classes under package `org.priyanshu`:

---

### StopWordMapper.java

```java
package org.priyanshu;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashSet;

public class StopWordMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

    private final HashSet<String> stopWords = new HashSet<>();

    @Override
    protected void setup(Context context) throws java.io.IOException {
        URI[] stopWordsFiles = context.getCacheFiles();
        if (stopWordsFiles != null && stopWordsFiles.length > 0) {
            Path path = new Path(stopWordsFiles[0]);
            FileSystem fs = FileSystem.get(context.getConfiguration());

            try (FSDataInputStream fis = fs.open(path);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

                String word;
                while ((word = reader.readLine()) != null) {
                    stopWords.add(word.trim().toLowerCase());
                }
            }
        }
    }

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws java.io.IOException, InterruptedException {

        String[] words = value.toString().split("\\s+");
        StringBuilder filteredSentence = new StringBuilder();

        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) {
                filteredSentence.append(word).append(" ");
            }
        }

        context.write(NullWritable.get(), new Text(filteredSentence.toString().trim()));
    }
}
```

---

### StopWordReducer.java

```java
package org.priyanshu;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class StopWordReducer extends Reducer<NullWritable, Text, NullWritable, Text> {

    @Override
    protected void reduce(NullWritable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        for (Text sentence : values) {
            context.write(NullWritable.get(), sentence);
        }
    }
}
```

---

### StopWordElimination.java

```java
package org.priyanshu;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

public class StopWordElimination {

    public static void main(String[] args) throws Exception {

        if (args.length < 3) {
            System.err.println("Usage: StopWordElimination <input path> <output path> <stop words file>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Stop Word Elimination");

        job.setJarByClass(StopWordElimination.class);
        job.setMapperClass(StopWordMapper.class);
        job.setReducerClass(StopWordReducer.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.addCacheFile(new URI(args[2]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
```

---

### Step 4: Build and Run the Program

1. Open Maven terminal inside IntelliJ.
2. Run:

```bash
mvn clean install
```

3. Execute the generated jar file using Hadoop:

```bash
hadoop jar target/StopWord-1.0-SNAPSHOT.jar \
org.priyanshu.StopWordElimination \
<hdfs_large_file_path> \
/output \
<hdfs_stopword_file_path>
```

---

## Output

The output folder in HDFS will contain a file where all sentences appear without any stop words.

To view the result:

```bash
hadoop fs -cat /output/part-r-00000
```

---

## **Repository Structure**

```
/

|-- Code/dependencies.txt
|-- Code/WC_Mapper.java
|-- Code/WC_Reducer.java
|-- Code/WC_Runner.java
|-- ScreenShot/
|-- Input.txt
|-- Experiment No. 4.pdf
|-- Stopwords.txt
|-- README.md
```

---

## Conclusion

This experiment demonstrates the implementation of a custom MapReduce job to remove stop words from textual data. It illustrates how to read distributed cache files, how to load stop words efficiently, and how to write MapReduce programs that operate on large text datasets stored in HDFS.


