    @Test
    public void testShutdownWithWaitIsClean() throws Exception {
        final AtomicBoolean shutdown = new AtomicBoolean(false);
        List<Long> jobExecTimestamps = Collections.synchronizedList(new ArrayList<Long>());
        CyclicBarrier barrier = new CyclicBarrier(2);
        final Scheduler scheduler = createScheduler("testShutdownWithWaitIsClean", 8);
        try {
            scheduler.getContext().put(BARRIER, barrier);
            scheduler.getContext().put(DATE_STAMPS, jobExecTimestamps);
            scheduler.start();
            scheduler.addJob(newJob().ofType(TestJobWithSync.class).withIdentity("job").storeDurably().build(), false);
            scheduler.scheduleJob(newTrigger().forJob("job").startNow().build());
            while (scheduler.getCurrentlyExecutingJobs().isEmpty()) {
                Thread.sleep(50);
            }
        } finally {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        scheduler.shutdown(true);
                        shutdown.set(true);
                    } catch (SchedulerException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            };
            t.start();
            Thread.sleep(1000);
            assertFalse(shutdown.get());
            barrier.await(TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            t.join();
        }
    }
