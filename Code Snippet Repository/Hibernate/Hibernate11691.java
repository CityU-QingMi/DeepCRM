   TotalStats runSingleWork(long runningTimeout, final String name, Operation op) {
      final TotalStats perf = new TotalStats();

      ExecutorService exec = Executors.newFixedThreadPool(
            NUM_THREADS, new ThreadFactory() {
         volatile int i = 0;
         @Override
         public Thread newThread(Runnable r) {
            return new Thread(new ThreadGroup(name),
                  r, "worker-" + name + "-" + i++);
         }
      });

      try {
         List<Future<Void>> futures = new ArrayList<Future<Void>>(NUM_THREADS);
         CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS + 1);

         for (int i = 0; i < NUM_THREADS; i++)
            futures.add(exec.submit(
                  new WorkerThread(runningTimeout, perf, op, barrier)));

         try {
            barrier.await(); // wait for all threads to be ready
            barrier.await(); // wait for all threads to finish

            // Now check whether anything went wrong...
            for (Future<Void> future : futures) future.get();
         } catch (Exception e) {
            throw new RuntimeException(e);
         }

         return perf;
      } finally {
         exec.shutdown();
      }
   }
