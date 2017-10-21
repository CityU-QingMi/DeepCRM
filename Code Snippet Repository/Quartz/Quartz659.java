    @Test
    public void testShutdownWithoutWaitIsUnclean() throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(2);
        Scheduler scheduler = createScheduler("testShutdownWithoutWaitIsUnclean", 8);
        try {
            scheduler.getContext().put(BARRIER, barrier);
            scheduler.start();
            scheduler.addJob(newJob().ofType(UncleanShutdownJob.class).withIdentity("job").storeDurably().build(), false);
            scheduler.scheduleJob(newTrigger().forJob("job").startNow().build());
            while (scheduler.getCurrentlyExecutingJobs().isEmpty()) {
                Thread.sleep(50);
            }
        } finally {
            scheduler.shutdown(false);
        }
        
        barrier.await(TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        
        Thread jobThread = (Thread) scheduler.getContext().get(JOB_THREAD);
        jobThread.join(TimeUnit.SECONDS.toMillis(TEST_TIMEOUT_SECONDS));
    }
