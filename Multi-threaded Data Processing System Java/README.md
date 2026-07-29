# Java Data Processing System

## Overview

This project implements a multi-threaded Data Processing System in Java that demonstrates concurrent task execution, synchronization, and exception handling. Multiple worker threads retrieve tasks from a shared queue, process them in parallel, and store the results in a shared resource while ensuring thread-safe access.

## Features

- Multi-threaded task processing using `ExecutorService`
- Shared task queue protected with `ReentrantLock`
- Multiple worker threads implementing the `Runnable` interface
- Simulated task processing using `Thread.sleep()`
- Thread-safe storage of processed results
- Exception handling for empty queue access, thread interruptions, and file I/O operations
- Logging of worker activities, task processing, and errors
- Output written to a text file (`processing_results.txt`)

## Technologies Used

- Java 21
- ExecutorService
- Runnable
- ReentrantLock
- BufferedWriter
- Exception Handling (`try-catch-finally`)

## Project Structure

- `Main.java` – Starts the application and manages the worker threads.
- `Task.java` – Represents an individual processing task.
- `Worker.java` – Implements the worker thread logic.
- `SharedTaskQueue.java` – Provides a thread-safe shared task queue.
- `ResultManager.java` – Stores processed results and writes them to a file.
- `EmptyQueueException.java` – Custom exception thrown when the task queue is empty.

## How to Run

1. Compile the project:

```bash
javac *.java
```

2. Run the application:

```bash
java Main
```
