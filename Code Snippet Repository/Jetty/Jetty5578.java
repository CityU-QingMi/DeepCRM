    private void testQueues(final int readers, final int writers, final int iterations, List<Queue<Runnable>> queues, final boolean blocking) throws Exception
    {
        final int runs = 8;
        int threads = readers + writers;
        final CyclicBarrier barrier = new CyclicBarrier(threads + 1);

        for (final Queue<Runnable> queue : queues)
        {
            for (int r = 0; r < runs; ++r)
            {
                for (int i = 0; i < readers; ++i)
                {
                    Thread thread = new Thread()
                    {
                        @Override
                        public void run()
                        {
                            await(barrier);
                            consume(queue, writers, blocking);
                            await(barrier);
                        }
                    };
                    thread.start();
                }
                for (int i = 0; i < writers; ++i)
                {
                    Thread thread = new Thread()
                    {
                        @Override
                        public void run()
                        {
                            await(barrier);
                            produce(queue, readers, iterations);
                            await(barrier);
                        }
                    };
                    thread.start();
                }

                await(barrier);
                long begin = System.nanoTime();
                await(barrier);
                long end = System.nanoTime();
                long elapsed = TimeUnit.NANOSECONDS.toMillis(end - begin);
                logger.info("{} Readers/Writers: {}/{} => {} ms", queue.getClass().getSimpleName(), readers, writers, elapsed);
            }
        }
    }
