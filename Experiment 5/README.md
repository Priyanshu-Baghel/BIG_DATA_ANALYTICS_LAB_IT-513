Below is a **clean, professional, GitHub-ready README file** for **Experiment 5 – Weather Data Analysis using Hadoop MapReduce**.
As requested: **no emojis**, fully formal, and structured for academic or repository documentation.

---

# Big Data Analytics Lab (IT-513)

## Experiment 5 – Weather Data Analysis using MapReduce

**Contributor:** Priyanshu Baghel
**Mentor:** Dr. Abhishek Narwaria, Assistant Professor

---

## Aim

To write a MapReduce program that analyzes weather data from the NCDC/IMD dataset and computes the minimum, maximum, and average temperature for each year. The weather dataset is semi-structured and record-oriented, making it suitable for processing with Hadoop MapReduce.

---

## Dataset Information

Reference:
[https://www.data.gov.in/resource/seasonal-and-annual-mean-temperature-series-period-1901-2021](https://www.data.gov.in/resource/seasonal-and-annual-mean-temperature-series-period-1901-2021)

The dataset is sourced from the India Meteorological Department (IMD). IMD is India’s primary agency responsible for weather forecasting, climate monitoring, and meteorological data collection. It collects temperature, rainfall, humidity, and other weather records through a large national network of weather stations.

The dataset contains yearly temperature records with monthly values. Each row generally contains:

* Year
* Temperature values for all twelve months

The data fields are typically tab-separated.

---

## Objective

Using Hadoop MapReduce:

1. Read the yearly temperature dataset.
2. Extract the year and all twelve monthly temperature values.
3. Compute:

   * Minimum temperature of the year
   * Maximum temperature of the year
   * Average temperature of the year
4. Output results in the format:
   `Year   Min: value, Max: value, Avg: value`

---

## Program Files

This experiment uses three Java classes:

1. WeatherDriver.java
2. WeatherMapper.java
3. WeatherReducer.java

All classes are described below.

---

## WeatherDriver.java

```java
package org.priyanshu;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WeatherDriver {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: WeatherDriver <input path> <output path>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Weather Analysis");

        job.setJarByClass(WeatherDriver.class);
        job.setMapperClass(WeatherMapper.class);
        job.setReducerClass(WeatherReducer.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
```

---

## WeatherMapper.java

```java
package org.priyanshu;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WeatherMapper extends Mapper<Object, Text, IntWritable, Text> {
    private IntWritable year = new IntWritable();
    private Text tempData = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("\t");

        if (fields.length == 13) { // year + 12 months
            try {
                year.set(Integer.parseInt(fields[0]));

                StringBuilder temperatures = new StringBuilder();
                for (int i = 1; i <= 12; i++) {
                    temperatures.append(fields[i]).append(",");
                }

                tempData.set(temperatures.toString().replaceAll(",$", ""));
                context.write(year, tempData);
            } catch (NumberFormatException e) {
                // skip malformed lines
            }
        }
    }
}
```

---

## WeatherReducer.java

```java
package org.priyanshu;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WeatherReducer extends Reducer<IntWritable, Text, IntWritable, Text> {
    private Text result = new Text();

    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        double minTemp = Double.MAX_VALUE;
        double maxTemp = Double.MIN_VALUE;
        double sumTemp = 0.0;
        int count = 0;

        for (Text val : values) {
            String[] temps = val.toString().split(",");
            for (String temp : temps) {
                double tempValue = Double.parseDouble(temp);
                minTemp = Math.min(minTemp, tempValue);
                maxTemp = Math.max(maxTemp, tempValue);
                sumTemp += tempValue;
                count++;
            }
        }

        double avgTemp = sumTemp / count;
        result.set("Min: " + minTemp + ", Max: " + maxTemp + ", Avg: " + avgTemp);
        context.write(key, result);
    }
}
```

---

## Running the Program

Upload the dataset to HDFS:

```bash
hadoop fs -put TEMP_ANNUAL_SEASONAL_MEAN.csv /weather
```

Run the MapReduce job:

```bash
hadoop jar WeatherAnalysis.jar org.priyanshu.WeatherDriver /weather /weather_output
```

View the results:

```bash
hadoop fs -cat /weather_output/part-r-00000
```

---

## Output

The output displays the minimum, maximum, and average temperature for each year in the following format:

```
1901    Min: X, Max: Y, Avg: Z
1902    Min: X, Max: Y, Avg: Z
```

Exact values depend on the dataset.

---

## Conclusion

In this experiment, weather data was processed using MapReduce. The temperature values were extracted and analyzed using Mapper and Reducer logic. The experiment demonstrates how to parse structured or semi-structured records and compute yearly temperature statistics efficiently using the Hadoop distributed computing framework.


