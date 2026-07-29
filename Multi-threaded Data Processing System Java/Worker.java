public class Worker implements Runnable {
    private final int workerId;
    private final SharedTaskQueue taskQueue;
    private final ResultManager resultManager;

    public Worker(
            int workerId,
            SharedTaskQueue taskQueue,
            ResultManager resultManager
    ) {
        this.workerId = workerId;
        this.taskQueue = taskQueue;
        this.resultManager = resultManager;
    }

    @Override
    public void run() {
        String workerName = "Worker-" + workerId;

        System.out.println(workerName + " started.");

        while (true) {
            try {
                /*
                 * Only one worker can remove a task from the queue
                 * at a time because getTask() uses a lock.
                 */
                Task task = taskQueue.getTask();

                System.out.println(
                        workerName
                                + " retrieved Task "
                                + task.getTaskId()
                                + "."
                );

                System.out.println(
                        workerName
                                + " is processing Task "
                                + task.getTaskId()
                                + "..."
                );

                String processedResult = task.process();

                /*
                 * Add the worker name so the output identifies
                 * which thread processed the task.
                 */
                String completeResult =
                        workerName + ": " + processedResult;

                resultManager.saveResult(completeResult);

                System.out.println(
                        workerName
                                + " completed Task "
                                + task.getTaskId()
                                + "."
                );

            } catch (EmptyQueueException exception) {
                /*
                 * The queue is empty, so this worker can terminate
                 * normally.
                 */
                System.out.println(
                        workerName
                                + " found no remaining tasks."
                );

                break;

            } catch (InterruptedException exception) {
                /*
                 * Restore the interrupted status of the thread.
                 */
                Thread.currentThread().interrupt();

                System.err.println(
                        workerName
                                + " was interrupted while processing a task: "
                                + exception.getMessage()
                );

                break;

            } catch (Exception exception) {
                /*
                 * Prevent an unexpected task-processing error from
                 * terminating the entire application.
                 */
                System.err.println(
                        workerName
                                + " encountered an unexpected error: "
                                + exception.getMessage()
                );
            }
        }

        System.out.println(workerName + " finished.");
    }
}
