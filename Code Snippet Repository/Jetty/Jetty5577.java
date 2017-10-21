    @Stress("")
    @Test
    public void testBlockingQueues() throws Exception
    {
        int cores = Runtime.getRuntime().availableProcessors();
        Assume.assumeTrue(cores > 1);

        final int readers = cores / 2;
        final int writers = readers;
        final int iterations = 16 * 1024 * 1024;

        final List<Queue<Runnable>> queues = new ArrayList<>();
        queues.add(new LinkedBlockingQueue<>());
        queues.add(new ArrayBlockingQueue<>(iterations * writers));
        queues.add(new BlockingArrayQueue<>(iterations * writers));

        testQueues(readers, writers, iterations, queues, true);
    }
