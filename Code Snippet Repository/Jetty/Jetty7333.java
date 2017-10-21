    private void run(int iterations)
    {
        CountDownLatch latch = new CountDownLatch(iterations);
        List<String> failures = new ArrayList<>();

        int factor = (logger.isDebugEnabled() ? 25 : 1) * 100;

        // Dumps the state of the client if the test takes too long
        final Thread testThread = Thread.currentThread();
        Scheduler.Task task = client.getScheduler().schedule(() ->
        {
            logger.warn("Interrupting test, it is taking too long{}{}{}{}",
                    System.lineSeparator(), server.dump(),
                    System.lineSeparator(), client.dump());
            testThread.interrupt();
        }, iterations * factor, TimeUnit.MILLISECONDS);

        long begin = System.nanoTime();
        for (int i = 0; i < iterations; ++i)
        {
            test(latch, failures);
//            test("http", "localhost", "GET", false, false, 64 * 1024, false, latch, failures);
        }
        Assert.assertTrue(await(latch, iterations, TimeUnit.SECONDS));
        long end = System.nanoTime();
        task.cancel();
        long elapsed = TimeUnit.NANOSECONDS.toMillis(end - begin);
        logger.info("{} requests in {} ms, {} req/s", iterations, elapsed, elapsed > 0 ? iterations * 1000 / elapsed : -1);

        for (String failure : failures)
            logger.info("FAILED: {}", failure);

        Assert.assertTrue(failures.toString(), failures.isEmpty());
    }
