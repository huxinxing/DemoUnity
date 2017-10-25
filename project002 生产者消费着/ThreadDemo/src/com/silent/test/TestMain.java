package com.silent.test;

/**
 * 现有某个目录，目录下有多个文件（只有文件，没有子目录），现在需要读取此目录下的所有文件，
 * 文件名以user开头，中间是3~5位的字母，最后4位是数字，以.txt结尾的文件。如：useraaa0001.txt，useraBcd0002.txt 都是符合要求的文件名。要求用正则进行判断。
 * 找到符合要求的文件后，按文件名的升序进行排序，依次读取每一个文件。 读取文件时，请用GBK的格式进行读取。 文件体的格式如下： 手机号码|费用 13811013125|56.01 13101212457|12.11
 *
 * 实现一个队列，用生产者消费者的模式，读取文件的线程每读入一条记录，就放入到队列中，模拟生产者 队列大小为10，模拟两个消费者，用两个线程不断的从队列中读取记录,每从队列中读取一条记录,
 * 此线程就sleep几秒(1-5秒,这里请设置一个1-5秒的随机数) 每个线程读取一条记录后,比较费用的值,保留最大的记录,当所有的文件全部读取完,队列中的记录都处理完后,
 * 各线程打印所保留的费用最大的手机号和费用（直接用System.out.println打印即可）。 然后程序正常结束。
 *
 * 考核点为：文件列表，正则的判断，排序，文件的读取，多线程中的生产消费模式，多线程的退出。
 *
 * 如下是代码框架，请在此基础上添加代码实现。<br>
 * 不允许删除或修改现有的代码，否则考试得0分。<br>
 * 注意不要出现内存泄露，否则倒扣10分。<br>
 * 对试题有任何疑问，请联系孔俊<br>
 * 重要提醒：<br>
 * 请认真阅读现有代码，在充分理解的基础上增加代码<br>
 * 满足评分的入口是“编译通过”，如果编译不通过以0分计算<br>
 * 所以请时刻保证编译通过。<br>
 * 评分标准<br>
 * 1. 程序符合编程规范，严格按照编码规范文档执行(20分)<br>
 * 2. 程序编译通过,没有错误和告警(10分)<br>
 * 3. 程序符合题目要求，运行结果正确(40分)<br>
 * 4. 程序对异常分支的处理全面合理(15分)<br>
 * 5. 通过FindBugs检查（5分）<br>
 * 6. 代码圈复杂度满足要求（5分）<br>
 * 7. 进阶要求：函数、算法、数据结构、类设计合理。（5分）<br>
 *
 *
 * @author liudong
 * @version [版本号, 2015-5-2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TestMain {
	public static void main(String[] args) {
		ProductorQueue proQueue = new ProductorQueue() ;
		// String root = "data/user001.txt" ;
		String root = "data" ;
		Thread product = new Thread(new Productor(proQueue, root));
		product.start() ;
		Thread c1 = new Thread(new Customer(proQueue));
		Thread c2 = new Thread(new Customer(proQueue));
		c1.start() ;
		c2.start() ;
        try {
            Thread.sleep(10000);
    		product.stop();
    		c1.stop();
    		c2.stop();
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
	}
}
