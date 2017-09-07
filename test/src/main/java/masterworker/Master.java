package masterworker;


import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

    //任务队列
    private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<Task>();

    //工人
    private HashMap<String, Thread> workers = new HashMap<String, Thread>();

    //结果集
    private ConcurrentHashMap<String, Object> result = new ConcurrentHashMap<String, Object>();

    public void submit(Task task) {
        taskQueue.offer(task);
    }

    public Master(Worker worker, int workerCount) {
        worker.setTaskQueue(taskQueue);
        worker.setResult(result);
        for (int i = 1; i <= workerCount; i++) {
            workers.put(Integer.toString(i), new Thread(worker));
        }
    }

    public void execute() {
        for (Map.Entry<String, Thread> entry : workers.entrySet()) {
            entry.getValue().start();
        }
    }

    public boolean isComplete(){
        for (Map.Entry<String, Thread> entry: workers.entrySet()) {
            if (!entry.getValue().getState().equals(Thread.State.TERMINATED)) {
                return false;
            }
        }
        return true;
    }

    public ConcurrentHashMap<String, Object> getResult() {
        return result;
    }
}
