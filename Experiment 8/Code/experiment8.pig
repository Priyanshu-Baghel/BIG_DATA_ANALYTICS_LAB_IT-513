docs = LOAD 'book.txt' AS (line:chararray);

docs_with_id = RANK docs;

words = FOREACH docs_with_id GENERATE
        $0 AS doc_id,
        FLATTEN(TOKENIZE(line)) AS word;

group_words = GROUP words BY (doc_id, word);
tf = FOREACH group_words GENERATE
     FLATTEN(group) AS (doc_id, word),
     COUNT(words) AS tf;

unique_words = DISTINCT words;
group_unique = GROUP unique_words BY word;
df = FOREACH group_unique GENERATE
     group AS word,
     COUNT(unique_words) AS df;

tot_docs = FOREACH (GROUP docs_with_id ALL)
            GENERATE COUNT(docs_with_id) AS total;

joined = JOIN tf BY word, df BY word;

tfidf = FOREACH joined GENERATE
        tf::doc_id AS doc_id,
        tf::word AS word,
        (tf::tf * LOG(tot_docs.total / df::df)) AS tfidf_value;

STORE tfidf INTO 'C:/PigData/tfidf_output' USING PigStorage(',');