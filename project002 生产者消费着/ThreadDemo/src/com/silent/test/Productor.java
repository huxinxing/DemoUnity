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
 * �����ߣ�ȡ���ļ��б��е����ݣ�ÿ���̶߳�ȡһ����¼��,�ȽϷ��õ�ֵ,�������ļ�¼
 * 
 */
public class Productor implements Runnable{
	private ProductorQueue proQueue ;	// �ļ����ݲ�����
	private String root ;				// �ļ�·��
	public Productor(ProductorQueue proQueue, String root) {
		this.proQueue = proQueue ;
		this.root = root ;
	}
	@Override
	public void run() {
		GetFileNames gfn = new GetFileNames() ;
		// �ļ�·��
		String filePath = "" ;
		// �õ��ļ����б�
		List<String> fileNameList = gfn.getFileNames(root) ;
		for (String fileName : fileNameList) {
			filePath = root + File.separator + fileName ;
			FileInputStream fis = null;
			InputStreamReader isr = null;
			BufferedReader bufReader = null; //���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
			String msg = null ;
			try {
				fis = new FileInputStream(filePath) ;	// ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
				isr = new InputStreamReader(fis);	// InputStreamReader ���ֽ���ͨ���ַ���������,
				bufReader = new BufferedReader(isr);		// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new InputStreamReader�Ķ���
				while ((msg = bufReader.readLine()) != null) {
					proQueue.offer(msg) ;	// ���ļ����������������
				}	
				
				bufReader.close() ;	// �ر���
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
