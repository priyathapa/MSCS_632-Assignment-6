import java.util.concurrent.ThreadLocalRandom;

public class Task {
    private final int taskId;
    private final String data;

    public Task(int taskId, String data) {
        this.taskId = taskId;
        this.data = data;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getData() {
        return data;
    }

    /**
     * Simulates computational work by pausing the current thread.
     *
     * @return the processed task result
     * @throws InterruptedException if the worker thread is interrupted
     */
    public String process() throws InterruptedException {
        int processingTime =
                ThreadLocalRandom.current().nextInt(500, 1501);

        Thread.sleep(processingTime);

        return "Task " + taskId
                + " processed successfully. Original data: "
                + data
                + ", Processed data: "
                + data.toUpperCase();
    }

    @Override
    public String toString() {
        return "Task{id=" + taskId + ", data='" + data + "'}";
    }
}
