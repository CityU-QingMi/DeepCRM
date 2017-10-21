    public void testJobInterruption() throws Exception {
        
        // create a simple scheduler
        
        Properties config = new Properties();
        config.setProperty("org.quartz.scheduler.instanceName", "InterruptableJobTest_Scheduler");
        config.setProperty("org.quartz.scheduler.instanceId", "AUTO");
        config.setProperty("org.quartz.threadPool.threadCount", "2");
        config.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        Scheduler sched = new StdSchedulerFactory(config).getScheduler();
        sched.start();

        // add a job with a trigger that will fire immediately
        
        JobDetail job = newJob()
            .ofType(TestInterruptableJob.class)
            .withIdentity("j1")
            .build();

        Trigger trigger = newTrigger()
            .withIdentity("t1")
            .forJob(job)
            .startNow()
            .build();

        sched.scheduleJob(job, trigger);
        
        sync.await();  // make sure the job starts running...
        
        List<JobExecutionContext> executingJobs = sched.getCurrentlyExecutingJobs();
        
        assertTrue("Number of executing jobs should be 1 ", executingJobs.size() == 1);
        
        JobExecutionContext jec = executingJobs.get(0);
        
        boolean interruptResult = sched.interrupt(jec.getFireInstanceId());
        
        sync.await(); // wait for the job to terminate

        assertTrue("Expected successful result from interruption of job ", interruptResult);

        assertTrue("Expected interrupted flag to be set on job class ", TestInterruptableJob.interrupted.get());
        
        sched.clear();

        sched.shutdown();
    }
