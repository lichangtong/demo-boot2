package com.example.demo.demoboot2;

/**
 * @Auther: lichangtong
 * @Date: 2019-10-24 18:01
 * @Description:
 */
public class AZ {
    public static void main(String[] args) {
        Object obj = new Object();
        Thread1 t1 = new Thread1(obj);
        t1.start();
        Thread2 t2 = new Thread2(obj);
        Thread thread = new Thread(t2);
        thread.start();
    }

}


class Thread1 extends Thread {
    private Object obj;

    public Thread1(Object obj) {
        this.obj = obj;
    }


    @Override
    public void run() {
        synchronized (obj) {
            for (int i = 1; i <= 26; i++) {
                System.out.print( i+ " ");
//                System.out.print(2 * i + " ");
                obj.notifyAll();
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}

class Thread2 implements Runnable {
    private Object obj;

    public Thread2(Object obj) {
        this.obj = obj;
    }

    public void run() {
        synchronized (obj) {

            for (char c = 'A'; c <= 'Z'; c++) {
                System.out.print(c + " ");
                obj.notifyAll();
                try {
                    obj.wait();
                } catch (InterruptedException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    }

}
