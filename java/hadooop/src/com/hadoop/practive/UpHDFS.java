package com.hadoop.practive;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class UpHDFS {
	
	static FileSystem fs = null;
	
	public UpHDFS() throws IOException, InterruptedException, URISyntaxException{
		fs = FileSystem.get(new URI("hdfs://huxinxing:9000"), new Configuration(),"root");
	}
	
	
	public static void main(String args[]){
		try {
			UpHDFS upHdfs = new UpHDFS();
			InputStream in = new FileInputStream("C://Users/huxinxing/Desktop/hadoopTest.txt");
			OutputStream out = fs.create(new Path("/tmp/test/hadoopTest.txt"));
			IOUtils.copyBytes(in, out, 4096,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
