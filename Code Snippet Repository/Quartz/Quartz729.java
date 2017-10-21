    @SuppressWarnings("")
    public void testDifferentPriority() throws Exception {
        Properties config = new Properties();
        config.setProperty("org.quartz.threadPool.threadCount", "1");
        config.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");

        Scheduler sched = new StdSchedulerFactory(config).getScheduler();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);

        MutableTrigger trig1 = new SimpleTriggerImpl("T1", null, cal.getTime());
        trig1.setPriority(5);

        MutableTrigger trig2 = new SimpleTriggerImpl("T2", null, cal.getTime());
        trig2.setPriority(10);

        JobDetail jobDetail = new JobDetailImpl("JD", null, TestJob.class);

        sched.scheduleJob(jobDetail, trig1);

        trig2.setJobKey(new JobKey(jobDetail.getKey().getName(), null));
        sched.scheduleJob(trig2);

        sched.start();

        latch.await();

        assertEquals("T2T1", result.toString());

        sched.shutdown();
    }
