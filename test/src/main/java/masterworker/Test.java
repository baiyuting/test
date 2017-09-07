package masterworker;

import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Master master = new Master(new Worker(), 10);
        for (int i = 1; i <= 20; i++) {
            master.submit(new Task(i, "任务" + Integer.toString(i)));
        }
        long start = System.currentTimeMillis();
        master.execute();
        while (true) {
            if (master.isComplete()) {
                break;
            }
        }
        int sum = 0;
        for (Map.Entry<String, Object> entry : master.getResult().entrySet()) {
            Integer res = (Integer) entry.getValue();
            sum += res;
        }
        System.out.println("总和是:" + sum);
        System.out.println("用时:" + (System.currentTimeMillis() - start));
    }
}
