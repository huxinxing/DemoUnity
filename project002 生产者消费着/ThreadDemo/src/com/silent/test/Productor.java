package com.silent.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.silent.util.GetFileNames;

/**
 * 生产者，取得文件列表中的数据，每个线程读取一条记录后,比较费用的值,保留最大的记录
 * 
 */
public class Productor implements Runnable{
	private ProductorQueue proQueue ;	// 文件内容操作类
	private String root ;				// 文件路径
	public Productor(ProductorQueue proQueue, String root) {
		this.proQueue = proQueue ;
		this.root = root ;
	}
	@Override
	public void run() {
		GetFileNames gfn = new GetFileNames() ;
		// 文件路径
		String filePath = "" ;
		// 得到文件名列表
		List<String> fileNameList = gfn.getFileNames(root) ;
		for (String fileName : fileNameList) {
			filePath = root + File.separator + fileName ;
			FileInputStream fis = null;
			InputStreamReader isr = null;
			BufferedReader bufReader = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
			String msg = null ;
			try {
				fis = new FileInputStream(filePath) ;	// 从文件系统中的某个文件中获取字节
				isr = new InputStreamReader(fis);	// InputStreamReader 是字节流通向字符流的桥梁,
				bufReader = new BufferedReader(isr);		// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
				while ((msg = bufReader.readLine()) != null) {
					proQueue.offer(msg) ;	// 向文件操作类中添加数据
				}	
				
				bufReader.close() ;	// 关闭流
				isr.close() ;
				fis.close() ;

				//
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace() ;
			} catch (IOException e) {
				e.printStackTrace() ;
			}
		}
	}
}
