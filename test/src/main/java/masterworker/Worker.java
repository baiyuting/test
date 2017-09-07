package masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {

    private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<Task>();

    private ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<String, Object>();

    public void run() {
        while (true) {
            Task task = taskQueue.poll();
            if (null == task) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object object = handleTask(task);
            result.put(Integer.toString(task.getId()), object);
        }
    }

    private Object handleTask(Task task) {
        return 1;
    }

    public void setTaskQueue(ConcurrentLinkedQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void setResult(ConcurrentHashMap<String,Object> result) {
        this.result = result;
    }
}
