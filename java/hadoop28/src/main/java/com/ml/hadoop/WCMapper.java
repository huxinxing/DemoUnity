package com.ml.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WCMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
	
	protected void map(LongWritable key,Text value,Mapper<LongWritable, Text, Text, LongWritable>.Context context){
		try{

			String[] words = value.toString().split(" ");
			
	
			for(String word : words){
				context.write(new Text(word), new LongWritable(1));
			}
		}catch(IOException e){
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
