    @SuppressWarnings("")
    public void testSameDefaultPriority() throws Exception {
        Properties config = new Properties();
        config.setProperty("org.quartz.threadPool.threadCount", "1");
        config.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");

        Scheduler sched = new StdSchedulerFactory(config).getScheduler();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);

        MutableTrigger trig1 = new SimpleTriggerImpl("T1", null, cal.getTime());
        MutableTrigger trig2 = new SimpleTriggerImpl("T2", null, cal.getTime());

        JobDetail jobDetail = new JobDetailImpl("JD", null, TestJob.class);

        sched.scheduleJob(jobDetail, trig1);

        trig2.setJobKey(new JobKey(jobDetail.getKey().getName()));
        sched.scheduleJob(trig2);

        sched.start();

        latch.await();

        assertEquals("T1T2", result.toString());

        sched.shutdown();
    }
