package com.ml.sparkSql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Sql {
	
	public static void main(String agrs[]){
		
		SparkSession sparkSession = new SparkSession.Builder().appName("Java Spark SQL basic example").config("spark.some.config.option", "some-value").getOrCreate();
		
		Dataset<Row> df = sparkSession.read().json("/usr/soft/spark-2.2.0-bin-hadoop2.7/examples/src/main/resources/people.json");
		
		
		df.show();
		
		
		
	}
	
	
}
