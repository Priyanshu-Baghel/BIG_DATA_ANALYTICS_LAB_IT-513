package org.stopword;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import java.io.IOException;
public class StopWordReducer extends Reducer<NullWritable, Text, NullWritable, Text> {
    @Override protected void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException,
            InterruptedException {
        for (Text sentence : values) {
            context.write(NullWritable.get(), sentence);
        }
    }
}
