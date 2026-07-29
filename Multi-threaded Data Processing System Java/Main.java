import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        final int numberOfTasks = 12;
        final int numberOfWorkers = 4;

        System.out.println(
                "========== DATA PROCESSING SYSTEM =========="
        );

        System.out.println(
                "Creating " + numberOfTasks + " tasks..."
        );

        /*
         * Create the shared queue and shared result manager.
         */
        SharedTaskQueue taskQueue = new SharedTaskQueue();
        ResultManager resultManager = new ResultManager();

        /*
         * Add tasks before starting the worker threads.
         */
        for (int taskId = 1; taskId <= numberOfTasks; taskId++) {
            Task task = new Task(
                    taskId,
                    "Sample data for task " + taskId
            );

            taskQueue.addTask(task);
        }

        System.out.println(
                "\nStarting " + numberOfWorkers + " workers...\n"
        );

        /*
         * Create a fixed-size thread pool.
         */
        ExecutorService executorService =
                Executors.newFixedThreadPool(numberOfWorkers);

        /*
         * Submit each worker to the ExecutorService.
         */
        for (int workerId = 1;
             workerId <= numberOfWorkers;
             workerId++) {

            Worker worker = new Worker(
                    workerId,
                    taskQueue,
                    resultManager
            );

            executorService.submit(worker);
        }

        /*
         * Prevent the ExecutorService from accepting new workers.
         * Existing workers are allowed to finish.
         */
        executorService.shutdown();

        try {
            boolean completed =
                    executorService.awaitTermination(
                            2,
                            TimeUnit.MINUTES
                    );

            if (!completed) {
                System.err.println(
                        "Workers did not finish within the time limit."
                );

                /*
                 * Attempt to stop any workers that are still running.
                 */
                executorService.shutdownNow();
            } else {
                System.out.println(
                        "\nAll worker threads completed successfully."
                );
            }

        } catch (InterruptedException exception) {
            System.err.println(
                    "The main thread was interrupted while waiting "
                            + "for workers to finish: "
                            + exception.getMessage()
            );

            executorService.shutdownNow();

            /*
             * Restore the main thread's interrupted status.
             */
            Thread.currentThread().interrupt();
        }

        /*
         * Display all results after the workers have terminated.
         */
        resultManager.printResults();

        /*
         * Write the final results to a shared output file.
         */
        try {
            String outputFileName = "processing_results.txt";

            resultManager.writeResultsToFile(outputFileName);

            System.out.println(
                    "\nResults were saved to "
                            + outputFileName
                            + "."
            );

        } catch (IOException exception) {
            System.err.println(
                    "File-writing error: "
                            + exception.getMessage()
            );
        }

        /*
         * Verify that all tasks produced a result.
         */
        if (resultManager.getResultCount() == numberOfTasks) {
            System.out.println(
                    "Verification successful: every task was processed once."
            );
        } else {
            System.err.println(
                    "Verification failed. Expected "
                            + numberOfTasks
                            + " results, but found "
                            + resultManager.getResultCount()
                            + "."
            );
        }

        System.out.println(
                "========== PROGRAM FINISHED =========="
        );
    }
}
