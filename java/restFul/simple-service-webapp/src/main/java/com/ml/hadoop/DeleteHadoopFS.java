package com.ml.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class DeleteHadoopFS {
	
	public boolean deleteHadoopfs(){
		Boolean flag = false;
		try {
			FileSystem fs = FileSystem.get(new URI("hdfs://ml:9000"), new Configuration(), "root");
			Boolean flag2 = fs.delete(new Path("/user/test/wordcount.txt"),false);
			if(flag2){
				System.out.println("文件删除成功" + flag2);
				flag = flag2;
			}else{
				System.out.println("文件删除失败");
			}
			
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
		
		return flag;
	}
	
}
