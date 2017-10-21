    public void testJobAutoInterruption() throws Exception {
        
        // create a simple scheduler
        
        Properties config = new Properties();
        config.setProperty("org.quartz.scheduler.instanceName", "InterruptableJobTest_Scheduler");
        config.setProperty("org.quartz.scheduler.instanceId", "AUTO");
        config.setProperty("org.quartz.threadPool.threadCount", "2");
        config.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        config.setProperty("org.quartz.plugin.jobInterruptor.class", "org.quartz.plugins.interrupt.JobInterruptMonitorPlugin");
        config.setProperty("org.quartz.plugin.jobInterruptor.defaultMaxRunTime", "1000");
        Scheduler sched = new StdSchedulerFactory(config).getScheduler();
        sched.start();

        // add a job with a trigger that will fire immediately
        
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(JobInterruptMonitorPlugin.AUTO_INTERRUPTIBLE, "true");
        JobDetail job = newJob()
            .ofType(TestInterruptableJob.class)
            .withIdentity("j1")
            .setJobData(jobDataMap)
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
                
        sync.await(); // wait for the job to terminate


        assertTrue("Expected interrupted flag to be set on job class ", TestInterruptableJob.interrupted.get());
        
        sched.clear();

        sched.shutdown();
    }
