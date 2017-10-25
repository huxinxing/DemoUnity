package com.silent.test;
public class Customer implements Runnable {
	private ProductorQueue proQueue ;
	public Customer(ProductorQueue proQueue) {
		this.proQueue = proQueue ;
	}
	String content = "" ;
	int index = 0 ;
	double maxFee = 0.0 ;
	String temp = "" ;
	@Override
	public void run() {
		do {
			content = proQueue.poll() ;
			if (null != content && !"".equals(content)) {
				index = content.lastIndexOf("|") + 1 ;
				double fee = Double.parseDouble(content.substring(index)) ;
				// 比较费用的值,保留最大的记录
				if (fee > maxFee) {
					maxFee = fee ;
					temp = content ;
					System.out.println(Thread.currentThread().getName() +"消费金额最大的用户是" + temp);
				}
			}
		} while(content != null) ;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
}
