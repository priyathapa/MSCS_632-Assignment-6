# Data Processing System – Java & Go

## Overview

This repository contains a concurrent Data Processing System implemented in both **Java** and **Go**. The project demonstrates how each language handles concurrency, synchronization, exception/error handling, and shared resource management while processing tasks in parallel.

## Project Objectives

The objective of this project is to:

- Implement a multi-threaded data processing system in Java and Go.
- Demonstrate safe concurrent task processing using each language's concurrency model.
- Prevent race conditions and deadlocks when accessing shared resources.
- Apply appropriate exception and error handling techniques.
- Compare the concurrency approaches used by Java and Go.

## Repository Structure

```text
DataProcessingSystem/
├── Java/
│   ├── Main.java
│   ├── Task.java
│   ├── Worker.java
│   ├── SharedTaskQueue.java
│   ├── ResultManager.java
│   └── EmptyQueueException.java
│
├── Go/
│   ├── main.go
│   └── go.mod
│
└── README.md
```

## Technologies Used

- Java (ExecutorService, Runnable, ReentrantLock)
- Go (Goroutines, Channels, WaitGroup, Mutex)

## Learning Outcomes

This project demonstrates the implementation of concurrent programming concepts in two different programming languages. It highlights the differences between Java's thread-based concurrency model and Go's goroutine and channel-based model while emphasizing thread safety, synchronization, and reliable error handling.
