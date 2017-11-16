package com.hadoop.practive;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class DeleteHDFS {
	
	static FileSystem fs = null;
	
	public DeleteHDFS() throws IOException, InterruptedException, URISyntaxException{
		fs = FileSystem.get(new URI("hdfs://ml:9000"), new Configuration(), "root");
	}
	
	public static void main(String args[]) throws IllegalArgumentException, IOException, InterruptedException, URISyntaxException{
		
		DeleteHDFS deleteHdfs = new DeleteHDFS();
		
//		Boolean flag1 = fs.delete(new Path("/user/test/wordcount.txt"),false);
//		System.out.println("文件删除成功:" + flag1);
//		
		Boolean flag2 = fs.mkdirs(new Path("/user/test/wordcount.txt"));
		System.out.println("文件创建成功:" + flag2);
		
		/*
		 * ɾ�������ĵڶ����������Ϊtrue��ʹ�ļ����������ļ�Ҳ����ɾ�������Ϊfalse��ô�Ͳ��ܹ�ɾ��
		 */
//		Boolean flag3 = fs.delete(new Path("/tmp/test2"), true);
//		System.out.println("ɾ���ļ��гɹ�" + flag3);
		
		
		
	}
	
	
}
