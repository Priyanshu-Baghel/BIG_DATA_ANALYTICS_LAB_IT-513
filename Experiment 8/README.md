# Big Data Analytics Lab (IT-513)

## Experiment 8 – TF-IDF Computation using Apache Pig

**Contributor:** Priyanshu Baghel
**Mentor:** Dr. Abhishek Narwaria, Assistant Professor

---

## Aim

To compute the **Term Frequency – Inverse Document Frequency (TF-IDF)** values for a book dataset (`book.txt`) stored in the local file system using **Apache Pig**.

TF-IDF is used in information retrieval and text mining to measure how important a word is within a document relative to an entire collection of documents.

---

## Introduction

TF-IDF is a statistical measure widely used in Natural Language Processing (NLP). It consists of:

* **Term Frequency (TF):** How often a word appears in a document.
* **Document Frequency (DF):** How many documents contain that word.
* **Inverse Document Frequency (IDF):**
  `IDF = log(N / DF)`
  where **N** is the total number of documents.

High TF-IDF values indicate terms that are important to specific documents but rare across the entire dataset.

Apache Pig provides the ability to process large datasets using Pig Latin scripting.

---

## Procedure

### Step 1: Preprocess the Data

1. Store `book.txt` in the local file system.
2. Ensure the text file is properly formatted with one line per document (or paragraph).

---

### Step 2: Start Pig in Local Mode

Run Pig locally using:

```
pig -x local
```

---

### Step 3: Load the Dataset

```pig
docs = LOAD 'book.txt' AS (line: chararray);
```

---

### Step 4: Assign Unique Document IDs

```pig
docs_with_id = RANK docs;
```

Each document receives an auto-generated document ID.

---

### Step 5: Tokenize the Text into Words

```pig
docs_split = FOREACH docs_with_id
              GENERATE RANK AS doc_id,
              FLATTEN(TOKENIZE(line)) AS word;
```

---

### Step 6: Calculate Term Frequency (TF)

1. Group by document ID and word:

```pig
grouped = GROUP docs_split BY (doc_id, word);
```

2. Count occurrences of each word:

```pig
word_count = FOREACH grouped
              GENERATE FLATTEN(group) AS (doc_id, word),
              COUNT(docs_split) AS term_frequency;
```

---

### Step 7: Calculate Document Frequency (DF)

1. Remove duplicates:

```pig
unique_words = DISTINCT docs_split;
```

2. Count the number of documents containing each word:

```pig
doc_freq = FOREACH (GROUP unique_words BY word)
            GENERATE group AS word,
            COUNT(unique_words) AS document_frequency;
```

---

### Step 8: Calculate Total Number of Documents (N)

```pig
total_docs = FOREACH (GROUP docs_with_id ALL)
              GENERATE COUNT(docs_with_id) AS total_documents;
```

---

### Step 9: Compute TF-IDF

Join all required datasets, then compute:

```pig
tfidf = FOREACH joined
         GENERATE word_count::doc_id,
                  word_count::word,
                  word_count::term_frequency *
                  LOG(total_docs.total_documents /
                  doc_freq::document_frequency) AS tfidf_value;
```

---

### Step 10: Store the Output

```pig
STORE tfidf INTO 'tfidf_results' USING PigStorage(',');
```

The results are stored in the folder `tfidf_results`.

---

## Output

The output contains:

```
document_id, word, tfidf_value
```

This file represents the weighted importance of each word in each document.

(Refer to the repository screenshots for visual output.)

---

## Results and Conclusion

* TF-IDF values for all words in the dataset were successfully computed using Apache Pig.
* Words with high TF-IDF values are more meaningful in context because they appear frequently in specific documents but rarely across all documents.
* This experiment demonstrates how Pig Latin simplifies text-processing and information retrieval tasks traditionally implemented using MapReduce.

---
