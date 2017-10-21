    @Test
    public void testLongLivedConnections() throws Exception
    {
        Worker.totalIterations.set(0);

        int mebiByte = 1048510;
        int clients = 1;
        int iterations = 2;
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(clients, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        threadPool.prestartAllCoreThreads();
        Worker[] workers = new Worker[clients];
        Future[] tasks = new Future[clients];
        for (int i = 0; i < clients; ++i)
        {
            workers[i] = new Worker(sslContext, iterations, false, mebiByte, 64 * mebiByte);
            workers[i].open();
            tasks[i] = threadPool.submit(workers[i]);
        }

        long start = System.currentTimeMillis();
        while (true)
        {
            Thread.sleep(1000);
            boolean done = true;
            for (Future task : tasks)
                done &= task.isDone();
            //System.err.print("\rIterations: " + Worker.totalIterations.get() + "/" + clients * iterations);
            if (done)
                break;
        }
        long end = System.currentTimeMillis();
        //System.err.println();
        //System.err.println("Elapsed time: " + TimeUnit.MILLISECONDS.toSeconds(end - start) + "s");

        for (Worker worker : workers)
            worker.close();

        threadPool.shutdown();

        // Throw exceptions if any
        for (Future task : tasks)
            task.get();

        // Keep the JVM running
//        new CountDownLatch(1).await();
    }
