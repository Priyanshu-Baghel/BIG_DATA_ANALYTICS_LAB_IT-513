package org.priyanshu;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class WeatherReducer extends Reducer<Text, Text, Text, Text> {

    private Text result = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        double minTemp = Double.MAX_VALUE;
        double maxTemp = Double.MIN_VALUE;
        double sumTemp = 0.0;
        int count = 0;

        for (Text val : values) {
            String[] temps = val.toString().split(",");
            for (String temp : temps) {
                if (!temp.isEmpty()) {
                    try {
                        double t = Double.parseDouble(temp);
                        minTemp = Math.min(minTemp, t);
                        maxTemp = Math.max(maxTemp, t);
                        sumTemp += t;
                        count++;
                    } catch (NumberFormatException e) {
                        // ignore malformed temperature
                    }
                }
            }
        }

        double avgTemp = (count == 0) ? 0.0 : sumTemp / count;
        String output = String.format("Min: %.2f, Max: %.2f, Avg: %.2f", minTemp, maxTemp, avgTemp);
        result.set(output);
        context.write(key, result);
    }
}
