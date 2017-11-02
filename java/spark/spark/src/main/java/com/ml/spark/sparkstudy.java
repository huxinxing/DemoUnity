package com.ml.spark;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class sparkstudy {
	
	public static void main(String args[]){
		
		/*
		 * ´´½¨spark RDD
		 */
		SparkConf sparkConf = new SparkConf().setAppName("sparkTest").setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		
		List<String> data = Arrays.asList("1", "2", "3", "5", "6");
		JavaRDD<String> distData = sc.parallelize(data);
		
//		JavaRDD<String> lines = sc.textFile("/home/huxinxing/Desktop/wyp.txt");
		
		JavaRDD<Integer> lineLengths = distData.map(s -> s.length());
		
		int totalLength = lineLengths.reduce((a,b) -> a + b);
		
		System.out.println("totalLength:"  + totalLength);
		
		
		

		
	}
	
}
