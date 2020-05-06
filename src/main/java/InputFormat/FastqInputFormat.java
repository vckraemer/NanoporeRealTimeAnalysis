package InputFormat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.JobContext;

public class FastqInputFormat extends TextInputFormat {

    @Override
    public RecordReader<LongWritable, Text> createRecordReader(
            InputSplit inputSplit,
            TaskAttemptContext taskAttemptContext) {
        return new FastqRecordReader();
    }

    @Override
    public boolean isSplitable(JobContext context, Path file) {
        return false;
    }

}
