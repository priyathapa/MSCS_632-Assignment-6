import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ResultManager {
    private final List<String> results;
    private final ReentrantLock resultLock;

    public ResultManager() {
        results = new ArrayList<>();
        resultLock = new ReentrantLock();
    }

    /**
     * Saves a processed result safely.
     *
     * @param result processed task result
     */
    public void saveResult(String result) {
        resultLock.lock();

        try {
            results.add(result);
        } finally {
            resultLock.unlock();
        }
    }

    /**
     * Prints every processed result.
     */
    public void printResults() {
        resultLock.lock();

        try {
            System.out.println("\n========== FINAL RESULTS ==========");

            for (String result : results) {
                System.out.println(result);
            }

            System.out.println(
                    "Total processed results: " + results.size()
            );

            System.out.println("===================================");
        } finally {
            resultLock.unlock();
        }
    }

    /**
     * Writes all results to a text file.
     *
     * @param fileName name of the output file
     * @throws IOException if the file cannot be created or written
     */
    public void writeResultsToFile(String fileName) throws IOException {
        resultLock.lock();

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(fileName))) {

            writer.write("DATA PROCESSING SYSTEM RESULTS");
            writer.newLine();
            writer.write("==============================");
            writer.newLine();

            for (String result : results) {
                writer.write(result);
                writer.newLine();
            }

            writer.write("==============================");
            writer.newLine();
            writer.write(
                    "Total processed results: " + results.size()
            );
            writer.newLine();

        } finally {
            resultLock.unlock();
        }
    }

    public int getResultCount() {
        resultLock.lock();

        try {
            return results.size();
        } finally {
            resultLock.unlock();
        }
    }
}
