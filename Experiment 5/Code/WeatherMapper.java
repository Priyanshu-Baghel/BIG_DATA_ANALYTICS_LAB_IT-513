package org.priyanshu;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class WeatherMapper extends Mapper<Object, Text, Text, Text> {

    private boolean headerSkipped = false;
    private Text yearKey = new Text();
    private Text tempValues = new Text();

    @Override
    protected void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString().trim();
        if (line.isEmpty()) return;

        // Skip header line
        if (!headerSkipped && line.toLowerCase().contains("year")) {
            headerSkipped = true;
            return;
        }

        // Split CSV by comma
        String[] fields = line.split(",");

        // Expecting: Year, Annual, Winter, Summer, Monsoon, Post_Monsoon
        if (fields.length >= 5) {
            try {
                String year = fields[0].trim();
                yearKey.set(year);

                // Combine all temperature values into one string
                StringBuilder temps = new StringBuilder();
                for (int i = 1; i < fields.length; i++) {
                    if (!fields[i].trim().isEmpty())
                        temps.append(fields[i].trim()).append(",");
                }
                // Remove trailing comma
                String tempData = temps.toString().replaceAll(",$", "");
                tempValues.set(tempData);

                context.write(yearKey, tempValues);
            } catch (Exception e) {
                // Skip invalid rows
            }
        }
    }
}

