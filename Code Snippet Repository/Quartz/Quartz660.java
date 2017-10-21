        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            try {
                SchedulerContext schedulerContext = context.getScheduler().getContext();
                schedulerContext.put(JOB_THREAD, Thread.currentThread());
                CyclicBarrier barrier =  (CyclicBarrier) schedulerContext.get(BARRIER);
                barrier.await(TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            } catch (Throwable e) {
                e.printStackTrace();
                throw new AssertionError("Await on barrier was interrupted: " + e.toString());
            } 
        }
