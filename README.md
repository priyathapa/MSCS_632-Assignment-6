# Multi-threaded Data Processing System

## Overview

This repository contains a multi-threaded Data Processing System implemented in both **Java** and **Go**. The project demonstrates how each language manages concurrency, synchronization, shared resources, error handling, and worker termination.

## Repository Structure

```text
Multi-threaded-Data-Processing-System/
├── Multi-threaded Data Processing System Java/
├── Multi-threaded Data Processing System Go/
└── README.md
```

## Implementations

### Java

The Java implementation uses:

- `ExecutorService`
- `Runnable`
- `ReentrantLock`
- Shared task queues
- Exception handling with `try-catch-finally`
- File output and logging

### Go

The Go implementation uses:

- Goroutines
- Channels
- `sync.WaitGroup`
- `sync.Mutex`
- Explicit error checking
- Resource cleanup with `defer`

## Purpose

The purpose of this project is to compare Java’s thread-based shared-memory concurrency model with Go’s goroutine and channel-based concurrency model. Both implementations process tasks concurrently while preventing race conditions, duplicate processing, missed tasks, and unsafe access to shared resources.
