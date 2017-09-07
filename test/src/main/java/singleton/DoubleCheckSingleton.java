package singleton;

public class DoubleCheckSingleton {

    public static DoubleCheckSingleton singleton;

    private DoubleCheckSingleton(){}

    public static DoubleCheckSingleton getInstance(){
        if (null == singleton) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (DoubleCheckSingleton.class) {
                if (null == singleton) {
                    singleton = new DoubleCheckSingleton();
                    System.out.println(Thread.currentThread().getName() + singleton.hashCode());
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(DoubleCheckSingleton.getInstance().hashCode());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(DoubleCheckSingleton.getInstance().hashCode());
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                System.out.println(DoubleCheckSingleton.getInstance().hashCode());
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
