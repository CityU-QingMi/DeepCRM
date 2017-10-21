      @Override
      public Void call() throws Exception {
         // TODO: Extend barrier to capture start time
         barrier.await();
         try {
            long startNanos = System.nanoTime();
            long endNanos = startNanos + runningTimeout;
            int runs = 0;
            long missCount = 0;
            while (callOperation(endNanos, runs)) {
               boolean hit = op.call(runs);
               if (!hit) missCount++;
               runs++;
            }

            // TODO: Extend barrier to capture end time
            perf.addStats(op.name, runs,
                  System.nanoTime() - startNanos, missCount);
         } finally {
            barrier.await();
         }
         return null;
      }
