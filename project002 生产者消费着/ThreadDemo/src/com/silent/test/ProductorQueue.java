package com.silent.test;
import java.util.concurrent.ArrayBlockingQueue;
/**
 * 文件内容操作类
 * 
 * 生产者与消费者模型中，要保证以下几点：
 * 1 同一时间内只能有一个生产者生产		生产方法加锁sychronized
 * 2 同一时间内只能有一个消费者消费		消费方法加锁sychronized
 * 3 生产者生产的同时消费者不能消费		生产方法加锁sychronized
 * 4 消费者消费的同时生产者不能生产		消费方法加锁sychronized
 * 5 共享空间空时消费者不能继续消费		消费前循环判断是否为空，空的话将该线程wait，释放锁允许其他同步方法执行
 * 6 共享空间满时生产者不能继续生产		生产前循环判断是否为满，满的话将该线程wait，释放锁允许其他同步方法执行   
 *
 */
public class ProductorQueue {
	private ArrayBlockingQueue<String> contentQueue = new ArrayBlockingQueue<String>(10) ;
	/*
	 * 将生产者生产出来的数据存放到队列中去
	 */
	public synchronized void offer(String data) {
		try {
			while (contentQueue.size() >= 10) {
				System.out.println("!!!!!!!!!!生成满了，请等待!!!!!!!!!!!");
				this.wait() ;
			}
			this.notifyAll() ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		contentQueue.offer(data) ;
		System.out.println("生产者生产出来的产品内容为：" + data);
		try {
			Thread.sleep((long)(Math.random() * 5));	// sleep几秒(1-5秒,这里请设置一个1-5秒的随机数)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 将文件内容从队列中移除
	 * 
	 */
	public synchronized String poll() {
		try {
			while (contentQueue.size() == 0) {
				System.out.println("!!!!!!!!!!消费光了，请等待!!!!!!!!!!!");
				this.wait() ;
			}
			this.notifyAll() ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String message = contentQueue.poll() ;
		//System.out.println("消费者消费一个产品内容为：" + message);
		try {
			Thread.sleep((long)(Math.random() * 5));	// sleep几秒(1-5秒,这里请设置一个1-5秒的随机数)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return message ;
	}
	
	public ArrayBlockingQueue<String> getContentQueue() {
		return this.contentQueue;
	}
}
