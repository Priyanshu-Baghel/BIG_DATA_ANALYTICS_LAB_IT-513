# Big Data Analytics Lab (IT-513)

## Experiment 11 – Frequent Product Pair Analysis using Apache Spark

**Contributor:** Priyanshu Baghel
**Mentor:** Dr. Abhishek Narwaria, Assistant Professor

---

## Aim

To perform data analytics using Apache Spark on the **Amazon Food Reviews dataset** in order to identify pairs of products that are frequently reviewed together by the same users.

---

## Introduction

Apache Spark is a distributed computing engine designed for large-scale data processing. It supports resilient distributed datasets (RDDs) and DataFrame APIs, enabling parallel computation across multiple cluster nodes.

The **Amazon Fine Food Reviews dataset** contains millions of user reviews, including:

* User IDs
* Product IDs

The goal of this experiment is to identify **product pairs** that appear together in reviews by the same users. Such pairs show strong co-occurrence patterns, making them useful for recommendation systems, association mining, and product market analysis.

---

## Concept / Theory

Finding frequently co-reviewed product pairs involves the following analytical steps:

1. **Transform raw data**
   Convert `(UserId, ProductId)` entries into grouped format:
   `UserId → [P1, P2, P3, ...]`

2. **Generate product pairs**
   From each user's product list, create all possible 2-item combinations:
   `(P1, P2), (P1, P3), (P2, P3), …`

3. **Count pair occurrences**
   Count how many different users reviewed each product pair.

4. **Filter frequent pairs**
   Keep only those product pairs reviewed together more than once.

5. **Sort results**
   Sort pairs in descending order of frequency to identify strong relationships.

### Spark Transformations Used

* `map()`
* `flatMap()`
* `groupByKey()`
* `reduceByKey()`
* `filter()`
* `sortBy()`

### DataFrame Functions (alternative approach)

* `groupBy()`
* `collect_list()`
* `explode()`
* `count()`

This experiment demonstrates Spark’s power in performing high-volume pairwise computations efficiently.

---

## Procedure

### Step 1: Download the Dataset

Download the **Amazon Fine Food Reviews** dataset from:

[https://snap.stanford.edu/data/web-FineFoods.html](https://snap.stanford.edu/data/web-FineFoods.html)

The dataset includes fields such as:

* UserId
* ProductId
* Review Content
* Rating

For this experiment, only `UserId` and `ProductId` are required.

---

### Step 2: Load Data into Apache Spark

Load the TSV/CSV file into Spark using RDD or DataFrame API:

```scala
val data = spark.read.option("header", "true").option("sep", "\t")
                     .csv("FineFoods.txt")
                     .select("UserId", "ProductId")
```

---

### Step 3: Extract User–Product Pairs

Convert each row into a pair:

```scala
val userProductRDD = data.rdd.map(row => (row.getString(0), row.getString(1)))
```

---

### Step 4: Group Products by User

```scala
val grouped = userProductRDD.groupByKey()
```

This results in:

```
UserId → [P1, P2, P3, ...]
```

---

### Step 5: Generate Product Pairs

```scala
val productPairs = grouped.flatMap {
  case (_, products) =>
    val list = products.toList.distinct
    for {
      i <- list.indices
      j <- i + 1 until list.length
    } yield ((list(i), list(j)), 1)
}
```

---

### Step 6: Count Pair Frequencies

```scala
val pairCounts = productPairs.reduceByKey(_ + _)
```

---

### Step 7: Filter and Sort Results

Keep pairs reviewed together more than once:

```scala
val frequentPairs = pairCounts.filter(_._2 > 1)
                              .sortBy(_._2, ascending = false)
```

---

### Step 8: Save the Output

```scala
frequentPairs.saveAsTextFile("output_frequent_pairs")
```

---

## Output

The output displays frequently co-reviewed product pairs with their frequencies. Example:

```
(B001E4KFG0, B00813GRG4) → 12
(B00474GHKQ, B00813GRG4) → 9
(B0078LX05C, B00474GHKQ) → 8
```

This identifies product combinations commonly reviewed by the same users.

---

## Conclusion

In this experiment, Apache Spark was used to analyze a large e-commerce dataset to identify frequently co-reviewed product pairs. By utilizing Spark transformations such as grouping, pair generation, reduction, and filtering, we efficiently computed meaningful product associations at scale.
This demonstrates Spark’s effectiveness in real-world recommendation tasks and large-scale analytical workloads.

---

