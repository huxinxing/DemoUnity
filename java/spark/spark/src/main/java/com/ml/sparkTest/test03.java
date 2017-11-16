package com.ml.sparkTest;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

public class test03 {
	
	public static void main(String args[]){
		
		SparkConf conf = new SparkConf().setMaster("local").setAppName("action").setSparkHome("test03");
		JavaSparkContext cxt = new JavaSparkContext(conf);
		
		
		System.out.println(cxt.getSparkHome());
		
		
		JavaRDD<String> lines = cxt.textFile("source/people.txt");
		
		System.out.println(lines.count());
		
		List<String> list = new ArrayList<>();
		
		lines.foreach(new VoidFunction<String>() {
			
			@Override
			public void call(String s) throws Exception {
				// TODO Auto-generated methsod stub
				System.out.println(s);
			}
		});
		

		
		
	}
	
	
	
}
