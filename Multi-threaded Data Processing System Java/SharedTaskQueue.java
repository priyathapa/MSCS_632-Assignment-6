import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class SharedTaskQueue {
    private final Queue<Task> taskQueue;
    private final ReentrantLock queueLock;

    public SharedTaskQueue() {
        taskQueue = new LinkedList<>();
        queueLock = new ReentrantLock();
    }

    /**
     * Adds a task to the shared queue safely.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        queueLock.lock();

        try {
            taskQueue.offer(task);

            System.out.println(
                    "Main added Task "
                            + task.getTaskId()
                            + " to the queue."
            );
        } finally {
            /*
             * The lock is always released, even if an exception occurs.
             */
            queueLock.unlock();
        }
    }

    /**
     * Retrieves and removes the next task from the queue.
     *
     * @return the next available task
     * @throws EmptyQueueException if there are no remaining tasks
     */
    public Task getTask() throws EmptyQueueException {
        queueLock.lock();

        try {
            if (taskQueue.isEmpty()) {
                throw new EmptyQueueException(
                        "No tasks remain in the shared queue."
                );
            }

            return taskQueue.poll();
        } finally {
            queueLock.unlock();
        }
    }

    /**
     * Returns the number of tasks remaining in the queue.
     *
     * @return queue size
     */
    public int getRemainingTaskCount() {
        queueLock.lock();

        try {
            return taskQueue.size();
        } finally {
            queueLock.unlock();
        }
    }
}
