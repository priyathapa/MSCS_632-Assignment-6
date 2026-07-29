package main

import (
	"fmt"
	"math/rand"
	"os"
	"strings"
	"sync"
	"time"
)

// Task represents a single processing task.
type Task struct {
	ID   int
	Data string
}

// ResultManager stores processed results safely.
type ResultManager struct {
	results []string
	mutex   sync.Mutex
}

// SaveResult safely adds a result.
func (rm *ResultManager) SaveResult(result string) {
	rm.mutex.Lock()
	defer rm.mutex.Unlock()

	rm.results = append(rm.results, result)
}

// PrintResults displays all processed results.
func (rm *ResultManager) PrintResults() {
	rm.mutex.Lock()
	defer rm.mutex.Unlock()

	fmt.Println("\n========== FINAL RESULTS ==========")

	for _, result := range rm.results {
		fmt.Println(result)
	}

	fmt.Printf("Total processed results: %d\n", len(rm.results))
	fmt.Println("===================================")
}

// WriteToFile saves results to a text file.
func (rm *ResultManager) WriteToFile(filename string) error {
	rm.mutex.Lock()
	defer rm.mutex.Unlock()

	file, err := os.Create(filename)
	if err != nil {
		return err
	}
	defer file.Close()

	file.WriteString("DATA PROCESSING SYSTEM RESULTS\n")
	file.WriteString("==============================\n")

	for _, result := range rm.results {
		file.WriteString(result + "\n")
	}

	file.WriteString("==============================\n")
	file.WriteString(fmt.Sprintf("Total processed results: %d\n", len(rm.results)))

	return nil
}

// Worker processes tasks from the shared channel.
func worker(id int, tasks <-chan Task, results *ResultManager, wg *sync.WaitGroup) {
	defer wg.Done()

	workerName := fmt.Sprintf("Worker-%d", id)

	fmt.Println(workerName, "started.")

	for task := range tasks {

		fmt.Printf("%s retrieved Task %d\n", workerName, task.ID)
		fmt.Printf("%s is processing Task %d...\n", workerName, task.ID)

		processingTime := time.Duration(rand.Intn(1000)+500) * time.Millisecond
		time.Sleep(processingTime)

		processed := fmt.Sprintf(
			"%s: Task %d processed successfully. Original data: %s, Processed data: %s",
			workerName,
			task.ID,
			task.Data,
			strings.ToUpper(task.Data),
		)

		results.SaveResult(processed)

		fmt.Printf("%s completed Task %d\n", workerName, task.ID)
	}

	fmt.Println(workerName, "found no remaining tasks.")
	fmt.Println(workerName, "finished.")
}

func main() {

	rand.Seed(time.Now().UnixNano())

	const numberOfTasks = 12
	const numberOfWorkers = 4

	fmt.Println("========== DATA PROCESSING SYSTEM ==========")

	// Shared task queue
	tasks := make(chan Task)

	// Shared results
	results := &ResultManager{}

	// WaitGroup
	var wg sync.WaitGroup

	// Start workers
	for i := 1; i <= numberOfWorkers; i++ {
		wg.Add(1)
		go worker(i, tasks, results, &wg)
	}

	// Add tasks
	for i := 1; i <= numberOfTasks; i++ {

		task := Task{
			ID:   i,
			Data: fmt.Sprintf("Sample data for task %d", i),
		}

		fmt.Printf("Main added Task %d to the queue.\n", i)

		tasks <- task
	}

	// Close channel
	close(tasks)

	// Wait for workers
	wg.Wait()

	fmt.Println("\nAll worker threads completed successfully.")

	results.PrintResults()

	err := results.WriteToFile("processing_results.txt")
	if err != nil {
		fmt.Println("File writing error:", err)
		return
	}

	fmt.Println("\nResults were saved to processing_results.txt.")

	if len(results.results) == numberOfTasks {
		fmt.Println("Verification successful: every task was processed once.")
	} else {
		fmt.Println("Verification failed.")
	}

	fmt.Println("========== PROGRAM FINISHED ==========")
}
