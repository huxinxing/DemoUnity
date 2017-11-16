package com.ml.sparkTest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class test02 {
	
	public static void main(String args[]){
		
		SparkConf conf = new SparkConf().setMaster("local").setAppName("test");
		JavaSparkContext cxt = new JavaSparkContext(conf);
		
		System.out.println(cxt.getSparkHome());
		
		JavaRDD<String> lines = cxt.textFile("source/people.txt");
		
 		List<String> line = lines.collect();
 		for(String val:line){
 			System.out.println(val);
 		}
 		
 		JavaRDD<String> containsE = lines.filter(new Function<String, Boolean>() {
			
			@Override
			public Boolean call(String s) throws Exception {
				// TODO Auto-generated method stub
				return (s.contains("they"));
			}
		});
 		
 		JavaRDD<Integer> lineLengths = lines.map(new Function<String, Integer>() {

			@Override
			public Integer call(String s) throws Exception {
				// TODO Auto-generated method stub
				return s.length();
			}
		});
 		
 		Integer totatl = lineLengths.reduce(new Function2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer i1, Integer i2) throws Exception {
				// TODO Auto-generated method stub
				return i1 + i2;
			}
		});
 		
 		System.out.println("--------------------next filter's result------------------");
 		
 		line = containsE.collect();
 		for(String val:line){
 			System.out.println(val);
 		}
		
 		
 		JavaRDD<String> sampletest = lines.sample(false, 0.1,5);
 		System.out.println("-------next sample----------------");
 		line = sampletest.collect();
 		for (String str : line) {
			System.out.println(str);
		}
		
 		
 		System.out.println("----------------flatMap---------------");
 		  JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {  
 	            @Override  
 	            public Iterator<String> call(String s) {  
 	                 String[] words=s.split(",");  
 	                  return Arrays.asList(words).iterator();  
 	            }  
 	        });  
 		
 		line = words.collect();
 		for (String str: line) {
			System.out.println(str);
		}
 		
 		
 		
 		System.out.println("------------------spark键值对------------------");
 		JavaPairRDD<String, Integer> ones = words.mapToPair(new PairFunction<String, String, Integer>() {

			@Override
			public Tuple2<String, Integer> call(String s) throws Exception {
				// TODO Auto-generated method stub
				
				return new Tuple2<String, Integer>(s, 1);
			}
		});
 		
 		
 		Map<String, Integer> map =  ones.collectAsMap();
 		
 		Iterator entries = map.entrySet().iterator();
 		while(entries.hasNext()){
 			 Map.Entry entry = (Map.Entry) entries.next(); 
 			System.out.println(entry.getKey() + " " + entry.getValue());
 		}
 		
 		JavaPairRDD<String, Integer> counts = ones.reduceByKey( new Function2<Integer, Integer, Integer>(){

			@Override
			public Integer call(Integer i1, Integer i2) throws Exception {
				// TODO Auto-generated method stub
				return i1 + i2;
			}
 			
 		});
 		
 		
 		Map<String, Integer> MapCounts = counts.collectAsMap();
 		for (String key : MapCounts.keySet()) {
			System.out.println(key + " " + MapCounts.get(key));
		}
 		
 		
 		JavaPairRDD<String, Integer> sorts = counts.sortByKey();
		
 		List<Tuple2<String, Integer>> output = sorts.collect();
 		
 		for (Tuple2<String, Integer> tuple2 : output) {
			System.out.println(tuple2._1 + " "  + tuple2._2 + " " + "huxinxing");
		}
 		
	}
	
	
}
