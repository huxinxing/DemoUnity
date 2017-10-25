package com.silent.test;
import java.util.concurrent.ArrayBlockingQueue;
/**
 * �ļ����ݲ�����
 * 
 * ��������������ģ���У�Ҫ��֤���¼��㣺
 * 1 ͬһʱ����ֻ����һ������������		������������sychronized
 * 2 ͬһʱ����ֻ����һ������������		���ѷ�������sychronized
 * 3 ������������ͬʱ�����߲�������		������������sychronized
 * 4 ���������ѵ�ͬʱ�����߲�������		���ѷ�������sychronized
 * 5 ����ռ��ʱ�����߲��ܼ�������		����ǰѭ���ж��Ƿ�Ϊ�գ��յĻ������߳�wait���ͷ�����������ͬ������ִ��
 * 6 ����ռ���ʱ�����߲��ܼ�������		����ǰѭ���ж��Ƿ�Ϊ�������Ļ������߳�wait���ͷ�����������ͬ������ִ��   
 *
 */
public class ProductorQueue {
	private ArrayBlockingQueue<String> contentQueue = new ArrayBlockingQueue<String>(10) ;
	/*
	 * ���������������������ݴ�ŵ�������ȥ
	 */
	public synchronized void offer(String data) {
		try {
			while (contentQueue.size() >= 10) {
				System.out.println("!!!!!!!!!!�������ˣ���ȴ�!!!!!!!!!!!");
				this.wait() ;
			}
			this.notifyAll() ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		contentQueue.offer(data) ;
		System.out.println("���������������Ĳ�Ʒ����Ϊ��" + data);
		try {
			Thread.sleep((long)(Math.random() * 5));	// sleep����(1-5��,����������һ��1-5��������)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*
	 * ���ļ����ݴӶ������Ƴ�
	 * 
	 */
	public synchronized String poll() {
		try {
			while (contentQueue.size() == 0) {
				System.out.println("!!!!!!!!!!���ѹ��ˣ���ȴ�!!!!!!!!!!!");
				this.wait() ;
			}
			this.notifyAll() ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String message = contentQueue.poll() ;
		//System.out.println("����������һ����Ʒ����Ϊ��" + message);
		try {
			Thread.sleep((long)(Math.random() * 5));	// sleep����(1-5��,����������һ��1-5��������)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return message ;
	}
	
	public ArrayBlockingQueue<String> getContentQueue() {
		return this.contentQueue;
	}
}
