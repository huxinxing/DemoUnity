package com.ml.sparkTest;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

public class test01 {
	
	public static void main(String args[]){
		
		test01 test = new test01();
		test.RDDOperator();
		
		
	}
	
	
	public void RDDOperator(){
		
		
		SparkConf conf = new SparkConf().setAppName("ConnectionSpark").setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		JavaRDD<String> lines = sc.textFile("source/people.txt");
		JavaRDD<Integer> lineLengths = lines.map(s -> {
			System.out.println("nihao");
			return s.length();
		});
		int totalLength = lineLengths.reduce((a,b) -> {
			System.out.println(a + " " + b);
			return a+b;
		});
		
		
		
		for (String str : lines.collect()) {
			System.out.println(str);
		}
		
		System.out.println("*************************************");
		System.out.println(totalLength);
		System.out.println("**************************************");
		
		
	}
	
	
	
	
	
	
	
}

