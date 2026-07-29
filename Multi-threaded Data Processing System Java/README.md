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

```text
JavaDataProcessingSystem/
├── Main.java
├── Task.java
├── Worker.java
├── SharedTaskQueue.java
├── ResultManager.java
└── EmptyQueueException.java
```


## How to Run

1. Compile the project:

```bash
javac *.java

2. Run the application
java Main
