    @Stress("")
    @Test
    public void testQueues() throws Exception
    {
        int cores = Runtime.getRuntime().availableProcessors();
        Assume.assumeTrue(cores > 1);

        final int readers = cores / 2;
        final int writers = readers;
        final int iterations = 16 * 1024 * 1024;

        final List<Queue<Runnable>> queues = new ArrayList<>();
        queues.add(new ConcurrentLinkedQueue<>()); // JDK lock-free queue, allocating nodes
        queues.add(new ArrayBlockingQueue<>(iterations * writers)); // JDK lock-based, circular array queue
        queues.add(new BlockingArrayQueue<>(iterations * writers)); // Jetty lock-based, circular array queue

        testQueues(readers, writers, iterations, queues, false);
    }
