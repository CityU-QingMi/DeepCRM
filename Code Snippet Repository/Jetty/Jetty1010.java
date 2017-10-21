    private boolean run(Session session, int iterations)
    {
        try
        {
            CountDownLatch latch = new CountDownLatch(iterations);
            int factor = (logger.isDebugEnabled() ? 25 : 1) * 100;

            // Dumps the state of the client if the test takes too long.
            final Thread testThread = Thread.currentThread();
            Scheduler.Task task = client.getScheduler().schedule(() ->
            {
                logger.warn("Interrupting test, it is taking too long{}Server:{}{}{}Client:{}{}",
                        System.lineSeparator(), System.lineSeparator(), server.dump(),
                        System.lineSeparator(), System.lineSeparator(), client.dump());
                testThread.interrupt();
            }, iterations * factor, TimeUnit.MILLISECONDS);

            long successes = 0;
            long begin = System.nanoTime();
            for (int i = 0; i < iterations; ++i)
            {
                boolean success = test(session, latch);
                if (success)
                    ++successes;
            }

            Assert.assertTrue(latch.await(iterations, TimeUnit.SECONDS));
            long end = System.nanoTime();
            Assert.assertThat(successes, Matchers.greaterThan(0L));
            task.cancel();
            long elapsed = TimeUnit.NANOSECONDS.toMillis(end - begin);
            logger.info("{} requests in {} ms, {}/{} success/failure, {} req/s",
                    iterations, elapsed,
                    successes, iterations - successes,
                    elapsed > 0 ? iterations * 1000 / elapsed : -1);
            return true;
        }
        catch (Exception x)
        {
            x.printStackTrace();
            return false;
        }
    }
