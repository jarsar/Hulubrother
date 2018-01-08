package nju.java;

public class SyncThread implements Runnable {
    private static int []count=new int[2];

    public SyncThread() {
        count[0] = 0;
    }

    public  void run() {
        synchronized(count) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count[0]++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCount() {
        return count[0];
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new SyncThread(), "SyncThread1");
        Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
