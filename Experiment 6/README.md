
# Big Data Analytics Lab (IT-513)

## Experiment 6 – Analysis of Purchases Dataset using Apache Spark

**Contributor:** Priyanshu Baghel
**Mentor:** Dr. Abhishek Narwaria, Assistant Professor

---

## Aim

To import and analyze the `purchases.txt` dataset using Apache Spark.
The tasks performed are:

1. Load the Purchases dataset into Spark and create a DataFrame.
2. Compute total sales for the product categories:

   * Toys
   * Consumer Electronics
3. Find the highest individual sale value for the following stores:

   * Reno
   * Toledo
   * Chandler

---

## Dataset

The `purchases.txt` file (Kaggle dataset) contains records in the following format:

```
Date    Time    Store_Name    Product_Category    Sale_Value    Payment_Method
```

Each field is separated by a tab character (`\t`).

---

## Step 1: Load Dataset into Spark and Create DataFrame

### 1. Define file path

```scala
val filePath = "D:\\purchases.txt"
```

### 2. Create a case class for purchase records

```scala
case class Purchase(Date: String, Time: String, Store_Name: String,
                    Product_Category: String, Sale_Value: Double,
                    Payment_Method: String)
```

### 3. Load dataset as RDD

```scala
val purchasesRDD = sc.textFile(filePath)
```

### 4. Transform RDD to case class objects

```scala
val purchases = purchasesRDD.map(line => {
  val cols = line.split("\t")
  Purchase(cols(0), cols(1), cols(2), cols(3), cols(4).toDouble, cols(5))
})
```

### 5. Import implicit conversions

```scala
import spark.implicits._
```

### 6. Convert RDD to DataFrame

```scala
val purchasesDF = purchases.toDF()
```

### 7. Display sample data

```scala
purchasesDF.show()
```

**Output:** The top 20 rows of the DataFrame are displayed.

---

## Step 2: Calculate Total Sales for Product Categories

### 1. Group by Product Category and calculate total sales

```scala
val categorySales = purchasesDF.groupBy("Product_Category")
  .sum("Sale_Value")
  .orderBy($"sum(Sale_Value)".desc)
```

```scala
categorySales.show()
```

### 2. Total sales for Toys

```scala
val toysSales = purchasesDF.filter($"Product_Category" === "Toys")
  .agg(Map("Sale_Value" -> "sum"))
  .first.get(0)

println(s"Total Sales for Toys: $$${toysSales}")
```

### 3. Total sales for Consumer Electronics

```scala
val electronicsSales = purchasesDF.filter($"Product_Category" === "Consumer Electronics")
  .agg(Map("Sale_Value" -> "sum"))
  .first.get(0)

println(s"Total Sales for Consumer Electronics: $$${electronicsSales}")
```

**Output:** Shows total sales for Toys and Consumer Electronics.

---

## Step 3: Highest Individual Sale per Store

### 1. Group by store and find the highest sale value

```scala
val highestSalePerStore = purchasesDF.groupBy("Store_Name")
  .max("Sale_Value")
  .orderBy($"max(Sale_Value)".desc)
```

```scala
highestSalePerStore.show()
```

### 2. Highest sale for Reno

```scala
val renoSale = purchasesDF.filter($"Store_Name" === "Reno")
  .agg(Map("Sale_Value" -> "max"))
  .first.get(0)

println(s"Highest Sale in Reno: $$${renoSale}")
```

### 3. Highest sale for Toledo

```scala
val toledoSale = purchasesDF.filter($"Store_Name" === "Toledo")
  .agg(Map("Sale_Value" -> "max"))
  .first.get(0)

println(s"Highest Sale in Toledo: $$${toledoSale}")
```

### 4. Highest sale for Chandler

```scala
val chandlerSale = purchasesDF.filter($"Store_Name" === "Chandler")
  .agg(Map("Sale_Value" -> "max"))
  .first.get(0)

println(s"Highest Sale in Chandler: $$${chandlerSale}")
```

**Output:**
Displays highest sale value per store and specifically for Reno, Toledo, and Chandler.

---

## Conclusion

In this experiment:

* The Purchases dataset was loaded into Apache Spark and converted into a structured DataFrame.
* Total sales were computed for selected product categories.
* The highest individual sale was identified for each store and specifically for Reno, Toledo, and Chandler.

This experiment demonstrates how Spark handles structured and semi-structured retail transaction data using transformations, aggregations, and DataFrame operations.

---

