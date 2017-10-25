package com.silent.util;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * �õ��ļ����ƣ�������������ʽ�����ж��Ƿ�Ϸ�
 */
public class GetFileNames {
	// ��ʼ���ļ����б�
	List<String> fileNameList = new ArrayList<String> () ;
	public static void main(String[] args) {
	}
	/**
	 * �õ��ļ����б�
	 * 
	 */
	public List<String> getFileNames(String root) {
		if (null == root || "".equals(root)) {
			System.out.println("��ڲ�������Ϊ�գ�");
			return null ;
		}
		File file = new File(root) ;	// �½��ļ�
		getListFiles(file) ;
		// ���ļ�����������
		fileNameSort() ;
		return fileNameList ;
	}
	/*
	 * �г��ƶ�Ŀ¼�µ��ļ��б�ʹ�õݹ����
	 */
	public void getListFiles(File file) {
		if (!file.isDirectory() || !file.exists()) {	// ��ڲ��������ļ��л򲻴���
			System.out.println("����һ����ȷ��Ŀ¼�����ļ�·�������ڣ�");
			return ;
		}
		// �г�ָ��Ŀ¼�µ��ļ��б�
		File [] files = file.listFiles() ;
		String regex = "user[a-zA-Z]{3,5}\\d{4}.txt" ;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {	// ���ļ��б��������ļ���
				getListFiles(files[i]) ;	// �ݹ����
			}
			if (files[i].getName().matches(regex)) {	// �ļ�����user��ͷ���м���3~5λ����ĸ�����4λ�����֣����磺useraaa0001.txt
				fileNameList.add(files[i].getName()) ;	// ��ӵ��ļ����б���
			}
		}
	}
	/*
	 * ���ļ��������������� 
	 */
	public void fileNameSort() {
		Collections.sort(fileNameList) ;	// ����
		//Collections.shuffle(fileNameList) ;	// ����
		//Collections.reverse(fileNameList) ;	// ����
	}
	public List<String> getFileNameList() {
		return fileNameList;
	}
}