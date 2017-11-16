package com.hadoop.practive;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class DownHDFS {
	
	//该方法为从hdfs下载文件
	
	public static void main(String args[]) throws IOException, URISyntaxException{
		
		FileSystem fs = FileSystem.get(new URI("hdfs://ml:9000"),new Configuration());
		InputStream in = fs.open(new Path("/tmp/test/ml.js"));
		OutputStream out = new FileOutputStream("C://Users/huxinxing/Desktop/hadoop.txt");
		IOUtils.copyBytes(in, out, 4096,true);
		
		
	}
	
}
