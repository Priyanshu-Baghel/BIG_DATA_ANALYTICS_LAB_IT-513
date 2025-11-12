package org.stopword;

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
    @Override protected void setup(Context context) throws java.io.IOException {
        URI[] stopWordsFiles = context.getCacheFiles();
        if (stopWordsFiles != null && stopWordsFiles.length > 0) {
            Path path = new Path(stopWordsFiles[0]);
            FileSystem fs = FileSystem.get(context.getConfiguration());
            // Use try-with-resources to ensure resources are closed
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
    public void map(LongWritable key, Text value, Context context) throws java.io.IOException, InterruptedException {
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
