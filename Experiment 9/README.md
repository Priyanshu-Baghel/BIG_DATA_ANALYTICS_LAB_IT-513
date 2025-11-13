# Big Data Analytics Lab (IT-513)

## Experiment 9 – Installation of Hive and Execution of HiveQL Operations

**Contributor:** Priyanshu Baghel
**Mentor:** Dr. Abhishek Narwaria, Assistant Professor

---

## Aim

To install and run Apache Hive, and to use HiveQL to create, alter, and drop databases, tables, views, functions, and indexes.

---

## Introduction

Apache Hive is a data warehouse infrastructure built on top of Hadoop. It enables:

* Querying and managing large datasets stored in HDFS
* Using a SQL-like language called **HiveQL**
* Translating HiveQL queries internally into MapReduce jobs

Hive behaves similarly to a traditional SQL database but operates over distributed storage, making it suitable for Big Data processing rather than real-time transactional workloads.

Hive uses a **metastore** (Derby or MySQL) to store metadata about databases, tables, columns, indexes, and partitions.

---

## Prerequisites

Before installing Hive, the following components must be installed:

1. Java
2. Hadoop

---

## Installation Procedure

### 1. Download Hive

Download the latest stable version of Apache Hive from:

```
https://downloads.apache.org/hive/
```

### 2. Extract the Hive Files

Extract the downloaded archive and move it to a preferred directory, such as:

```
/usr/local/hive
```

### 3. Configure Environment Variables

Add Hive environment variables:

* Set `HIVE_HOME`
* Add `$HIVE_HOME/bin` to the system PATH

### 4. Configure Hadoop Path in hive-env.sh

Edit:

```
$HIVE_HOME/conf/hive-env.sh
```

Ensure the Hadoop home directory is correctly set.

### 5. Configure hive-site.xml

Modify the file:

```
$HIVE_HOME/conf/hive-site.xml
```

Sample configuration (MySQL Metastore):

```xml
<configuration>

    <property>
        <name>javax.jdo.option.ConnectionURL</name>
        <value>jdbc:mysql://localhost:3306/metastore?createDatabaseIfNotExist=true</value>
        <description>MySQL connection URL for Hive Metastore</description>
    </property>

    <property>
        <name>javax.jdo.option.ConnectionDriverName</name>
        <value>com.mysql.cj.jdbc.Driver</value>
        <description>MySQL JDBC driver</description>
    </property>

    <property>
        <name>javax.jdo.option.ConnectionUserName</name>
        <value>root</value>
        <description>Username for MySQL</description>
    </property>

    <property>
        <name>javax.jdo.option.ConnectionPassword</name>
        <value>password</value>
        <description>Password for MySQL</description>
    </property>

    <property>
        <name>hive.metastore.warehouse.dir</name>
        <value>/user/hive/warehouse</value>
        <description>Default warehouse directory</description>
    </property>

    <property>
        <name>hive.metastore.uris</name>
        <value>thrift://localhost:9083</value>
        <description>Metastore Thrift URI</description>
    </property>

</configuration>
```

### 6. Initialize the Derby Metastore (Default)

```
bin/schematool -initSchema -dbType derby
```

### 7. Launch Hive

```
hive
```

---

# Hive Operations

Below are the queries executed in this experiment.

### 1. Create a Database

```sql
CREATE DATABASE sample_db;
```

### 2. Use the Database

```sql
USE sample_db;
```

### 3. Create a Table

```sql
CREATE TABLE employee (
    id INT,
    name STRING,
    age INT,
    salary FLOAT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ',';
```

### 4. Insert Data

```sql
INSERT INTO TABLE employee VALUES
(1, 'John Doe', 30, 50000),
(2, 'Jane Smith', 25, 60000);
```

### 5. Alter Table – Add Column

```sql
ALTER TABLE employee ADD COLUMNS (department STRING);
```

### 6. Rename Table

```sql
ALTER TABLE employee RENAME TO staff;
```

### 7. Create a View

```sql
CREATE VIEW senior_employees AS
SELECT name, age FROM staff WHERE age > 28;
```

### 8. Drop the View

```sql
DROP VIEW senior_employees;
```

### 9. Add a Custom Function (UDF)

```sql
ADD JAR /path/to/custom-udf.jar;
```

```sql
CREATE FUNCTION to_upper AS 'com.example.udf.ToUpper';
```

### 10. Drop the Function

```sql
DROP FUNCTION to_upper;
```

### 11. Create an Index

```sql
CREATE INDEX idx_salary
ON TABLE staff (salary)
AS 'COMPACT'
WITH DEFERRED REBUILD;
```

### 12. Drop the Index

```sql
DROP INDEX idx_salary ON staff;
```

### 13. Drop the Table

```sql
DROP TABLE staff;
```

### 14. Drop the Database

```sql
DROP DATABASE sample_db CASCADE;
```

---

## Conclusion

In this experiment, Apache Hive was installed and configured successfully. Using HiveQL, several database operations were performed, including the creation, alteration, and deletion of databases, tables, views, functions, and indexes.
The experiment demonstrates how Hive provides a SQL-like interface for processing and analyzing large datasets stored in Hadoop.

---
