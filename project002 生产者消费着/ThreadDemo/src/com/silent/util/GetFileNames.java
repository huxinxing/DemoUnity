package com.silent.util;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 得到文件名称，并进行正则表达式进行判断是否合法
 */
public class GetFileNames {
	// 初始化文件名列表
	List<String> fileNameList = new ArrayList<String> () ;
	public static void main(String[] args) {
	}
	/**
	 * 得到文件名列表
	 * 
	 */
	public List<String> getFileNames(String root) {
		if (null == root || "".equals(root)) {
			System.out.println("入口参数不能为空！");
			return null ;
		}
		File file = new File(root) ;	// 新建文件
		getListFiles(file) ;
		// 按文件名升序排序
		fileNameSort() ;
		return fileNameList ;
	}
	/*
	 * 列出制定目录下的文件列表，使用递归操作
	 */
	public void getListFiles(File file) {
		if (!file.isDirectory() || !file.exists()) {	// 入口参数不是文件夹或不存在
			System.out.println("不是一个正确的目录或者文件路径不存在！");
			return ;
		}
		// 列出指定目录下的文件列表
		File [] files = file.listFiles() ;
		String regex = "user[a-zA-Z]{3,5}\\d{4}.txt" ;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {	// 此文件列表里面有文件夹
				getListFiles(files[i]) ;	// 递归调用
			}
			if (files[i].getName().matches(regex)) {	// 文件名以user开头，中间是3~5位的字母，最后4位是数字，例如：useraaa0001.txt
				fileNameList.add(files[i].getName()) ;	// 添加到文件名列表中
			}
		}
	}
	/*
	 * 将文件名进行升序排序 
	 */
	public void fileNameSort() {
		Collections.sort(fileNameList) ;	// 升序
		//Collections.shuffle(fileNameList) ;	// 混乱
		//Collections.reverse(fileNameList) ;	// 降序
	}
	public List<String> getFileNameList() {
		return fileNameList;
	}
}