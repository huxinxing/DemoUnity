package com.ml.hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class wordCount {
	
	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException{
		
		//����job����
		Job job = Job.getInstance(new Configuration());
		
		//ָ���������
		job.setJarByClass(wordCount.class);
		
		//ָ���Զ����Mapper�׶ε���������
		job.setMapperClass(WCMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		//����HDFS�ļ���������ȡ����·��
		FileInputFormat.setInputPaths(job, new Path("/user/test/hadoop.txt"));
		
		//ָ���Զ����Reducer�׶ε���������
		job.setReducerClass(WCReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		//���������ϴ���HDFS����
		FileOutputFormat.setOutputPath(job, new Path("/user/test/wordcount.txt"));
		
		
		//ִ���ύjob����,ֱ����ɣ�����true��ӡ���Ⱥ�����
		job.waitForCompletion(true);
		System.out.println("Finished");
		
	}
	
}
