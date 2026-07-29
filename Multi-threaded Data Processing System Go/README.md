# Go Data Processing System

## Overview

This project implements a concurrent Data Processing System in Go. Multiple worker goroutines retrieve tasks from a shared channel, process them concurrently, and store the completed results in a shared collection. The program demonstrates Go's concurrency model, synchronization techniques, error handling, logging, and safe resource management.

## Features

- Concurrent task processing using goroutines
- Shared task queue implemented with a channel
- Worker coordination using `sync.WaitGroup`
- Thread-safe result storage using `sync.Mutex`
- Simulated processing delay using `time.Sleep()`
- Explicit error handling for file operations
- Resource cleanup using `defer`
- Logging of worker activity and task completion
- Results written to `processing_results.txt`
- Verification that every task was processed exactly once

## Technologies Used

- Go
- Goroutines
- Channels
- `sync.WaitGroup`
- `sync.Mutex`
- Standard Go libraries (`fmt`, `os`, `sync`, `time`, `strings`, `math/rand`)

## Project Structure

```text
GoDataProcessingSystem/
└── main.go
```

All application components, including task management, worker goroutines, synchronization, file handling, and program execution, are implemented within `main.go`.

## How the Program Works

1. A shared channel is created to store tasks.
2. Multiple worker goroutines are launched.
3. Tasks are added to the channel.
4. Each worker retrieves and processes tasks concurrently.
5. Processed results are stored safely using a mutex.
6. The channel is closed after all tasks have been submitted.
7. A `WaitGroup` waits for all workers to complete.
8. The final results are displayed and written to `processing_results.txt`.

## How to Run

Compile and run the program using:

```bash
go run main.go
```
