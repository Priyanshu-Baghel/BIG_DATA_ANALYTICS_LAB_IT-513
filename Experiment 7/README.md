# Big Data Analytics Lab (IT-513)

## Experiment 7 – Apache Pig Installation and Pig Latin Scripting

**Contributor:** Priyanshu Baghel
**Mentor:** Dr. Abhishek Narwaria, Assistant Professor

---

## Aim

To install and run Apache Pig, and to write Pig Latin scripts to perform data operations such as sorting, grouping, joining, projecting, and filtering.

---

## Introduction

Apache Pig is a high-level platform for creating MapReduce programs used with Hadoop.
It provides **Pig Latin**, a simplified scripting language that allows developers and data analysts to perform complex data transformations without manually writing Java MapReduce code.

Pig can run in two modes:

1. Local Mode
2. MapReduce Mode (on Hadoop cluster)

In this experiment, Pig is run in **local mode** for demonstration purposes.

---

## Installation Procedure

Follow the steps below to install and configure Apache Pig:

### 1. Download Pig

* Visit: [https://downloads.apache.org/pig/latest/](https://downloads.apache.org/pig/latest/)
* Download the file: `pig-0.18.0.tar.gz`

### 2. Extract the Archive

* Use 7-Zip or WinRAR to extract the `.tar.gz` file.
* Extract to: `C:\pig-0.18.0`

### 3. Set the PIG_HOME Environment Variable

* Open **System Environment Variables**
* Create a new User Variable:

  * **Variable Name:** `PIG_HOME`
  * **Variable Value:** `C:\pig-0.18.0`

### 4. Update the System PATH

Add the following to the PATH environment variable:

```
C:\pig-0.18.0\bin
```

### 5. Modify HADOOP_BIN_PATH in pig.cmd

Navigate to:

```
C:\pig-0.18.0\bin
```

Edit the file `pig.cmd`:

* Replace:

  ```
  set HADOOP_BIN_PATH=%HADOOP_HOME%\bin
  ```
* With:

  ```
  set HADOOP_BIN_PATH=%HADOOP_HOME%\libexec
  ```

This ensures compatibility with newer Hadoop versions.

### 6. Run Pig in Local Mode

Open Command Prompt and run:

```
pig -x local
```

This starts the Pig shell.

---

## Pig Latin Scripts

### a. Load Data

```pig
students = LOAD 'students.csv'
USING PigStorage(',')
AS (student_id:int, name:chararray, age:int, subject:chararray, score:int, grade_level:int);
```

### b. Filter Data

```pig
high_performers = FILTER students BY score > 85;
```

### c. Group Data

```pig
grouped_by_subject = GROUP students BY subject;
```

### d. Project Columns

```pig
name_score = FOREACH students GENERATE name, score;
```

### e. Sort Data

```pig
sorted_by_score = ORDER students BY score DESC;
```

---

## Output

Output includes:

* Confirmation of successful Apache Pig installation
* Execution of Pig Latin scripts
* Result sets showing filtered, grouped, projected, and sorted datasets

(Refer to attached screenshots in the repository for visual output.)

---

## Conclusion

Apache Pig was successfully installed and configured in local mode.
Using Pig Latin, operations such as sorting, grouping, joining, projecting, and filtering were performed on the dataset.
This experiment demonstrates the ease of data manipulation using Pig compared to traditional MapReduce programming.

---

