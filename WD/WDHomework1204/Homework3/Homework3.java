
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Homework3 {
    Pattern pattern = Pattern.compile("(\\w*)\\@(\\w*)\\.com");

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 5};
        int[] a2 = {11, 22, 33, 44, 55};
        var q = new Homework3();
        q.threadPrint(a1, a2);
        System.out.println(q.isStandardEmail("2967306689@qq.com"));
    }

    public void threadPrint(int[] a1, int[] a2) {
        final Object lock = new Object();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    super.run();
                    for (int i : a1) {
                        System.out.println(i);
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify();
                }
            }


        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    super.run();
                    for (int j : a2) {
                        System.out.println(j);
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    lock.notify();
                }
            }
        };

        t1.start();
        t2.start();
    }

    public boolean isStandardEmail(String email) {
        Matcher m = pattern.matcher(email);
        return m.matches();
    }
}
