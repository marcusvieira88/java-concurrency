# Introduction

This project has examples of Java Concurrency API.

# Java Concurrency

The Java programming language and the Java virtual machine (JVM) have been designed to support concurrent programming, and all execution takes place in the context of threads. Objects and resources can be accessed by many separate threads; each thread has its own path of execution but can potentially access any object in the program. The programmer must ensure read and write access to objects is properly coordinated (or "synchronized") between threads. Thread synchronization ensures that objects are modified by only one thread at a time and that threads are prevented from accessing partially updated objects during modification by another thread. The Java language has built-in constructs to support this coordination.

# Threads and Runnables

All modern operating systems support [concurrency](https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/) both via processes and threads. Processes are instances of programs which typically run independent to each other, e.g. if you start a java program the operating system spawns a new process which runs in parallel to other programs. Inside those processes we can utilize threads to execute code concurrently, so we can make the most out of the available cores of the CPU.

Java supports Threads since JDK 1.0. Before starting a new thread you have to specify the code to be executed by this thread, often called the task. This is done by implementing Runnable - a functional interface defining a single void no-args method run().

# Executors

The Concurrency API introduces the concept of an ExecutorService as a higher level replacement for working with threads directly. Executors are capable of running asynchronous tasks and typically manage a pool of threads, so we don't have to create new threads manually.

# Callables and Futures

In addition to Runnable executors support another kind of task named Callable. Callables are functional interfaces just like runnables but instead of being void they return a value.

Executors summit method doesn't wait until the task completes, in this case we need to use Future.

executor.invokeAll(callables) - wait until all the futures are completed

# Synchronized

When writing such multi-threaded code you have to pay particular attention when accessing shared mutable variables concurrently from multiple threads.

# Locks (ReentrantLock, ReadWriteLock, StampedLock)

Instead of using implicit locking via the synchronized keyword the Concurrency API supports various explicit locks specified by the Lock interface. Locks support various methods for finer grained lock control thus are more expressive than implicit monitors.

# Atomic

The package java.concurrent.atomic contains many useful classes to perform atomic operations. An operation is atomic when you can safely perform the operation in parallel on multiple threads without using the synchronized keyword or locks.
Internally, the atomic classes make heavy use of compare-and-swap (CAS), an atomic instruction directly supported by most modern CPUs. Those instructions usually are much faster than synchronizing via locks. So my advice is to prefer atomic classes over locks in case you just have to change a single mutable variable concurrently.

# ConcurrentHashMap

All those methods above are part of the ConcurrentMap interface, thereby available to all implementations of that interface. In addition the most important implementation ConcurrentHashMap has been further enhanced with a couple of new methods to perform parallel operations upon the map.
Just like parallel streams those methods use a special ForkJoinPool available via ForkJoinPool.commonPool() in Java 8. This pool uses a preset parallelism which depends on the number of available cores 