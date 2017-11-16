package com.ml.sparkTest;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

public class aggregate {
	
	public static void main(String args[]){
		
		SparkConf conf = new SparkConf().setMaster("local").setAppName("aggregate").setSparkHome("aggregate");
		JavaSparkContext sct = new JavaSparkContext(conf);
		
		List<Integer> data = Arrays.asList(5,1,1,4,4,2,2);
		
		JavaRDD<Integer> javaRDD = sct.parallelize(data,3);
		
		Integer aggregateRDD = javaRDD.aggregate(2, new Function2<Integer, Integer, Integer>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Integer call(Integer i1, Integer i2) throws Exception {
				// TODO Auto-generated method stub
				return i1 + i2;
			}
		}, new Function2<Integer, Integer, Integer>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Integer call(Integer i1, Integer i2) throws Exception {
				// TODO Auto-generated method stub
				return i1 + i2;
			}
		});
		
		
		System.out.println(aggregateRDD);
		
		
	}
	
	
}
