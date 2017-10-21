   private void doTest(boolean warmup) throws Exception {
      ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
      try {
         CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS + 1);
         List<Future<String>> futures = new ArrayList<Future<String>>(NUM_THREADS);
         for (int i = 0; i < NUM_THREADS; i++) {
            Future<String> future = executor.submit(
                  new SelectQueryRunner(barrier, warmup, i + 1));
            futures.add(future);
            Thread.sleep(LAUNCH_INTERVAL_MILLIS);
         }
         barrier.await(); // wait for all threads to be ready

         long timeout = warmup ? WARMUP_TIME_SECS : RUNNING_TIME_SECS;
         TimeUnit unit = TimeUnit.SECONDS;

         Thread.sleep(unit.toMillis(timeout)); // Wait for the duration of the test
         run.set(false); // Instruct tests to stop doing work
         barrier.await(2, TimeUnit.MINUTES); // wait for all threads to finish

         log.infof("[%s] All threads finished, check for exceptions", title(warmup));
         for (Future<String> future : futures) {
            String opsPerMS = future.get();
            if (!warmup)
               log.infof("[%s] Operations/ms: %s", title(warmup), opsPerMS);
         }
         log.infof("[%s] All future gets checked", title(warmup));
      } catch (Exception e) {
         log.errorf(e, "Error in one of the execution threads during %s", title(warmup));
         throw e;
      } finally {
         executor.shutdownNow();
      }
   }
