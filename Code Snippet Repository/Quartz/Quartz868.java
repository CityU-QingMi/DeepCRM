    public void testOverwriteJobClassNotFound() throws Exception {
        String DB_NAME = "XmlDeleteNonExistsJobTestDatasase";
        String SCHEDULER_NAME = "XmlDeleteNonExistsJobTestScheduler";
        JdbcQuartzTestUtilities.createDatabase(DB_NAME);

        JobStoreTX jobStore = new JobStoreTX();
        jobStore.setDataSource(DB_NAME);
        jobStore.setTablePrefix("QRTZ_");
        jobStore.setInstanceId("AUTO");
        DirectSchedulerFactory.getInstance().createScheduler(SCHEDULER_NAME, "AUTO", new SimpleThreadPool(4, Thread.NORM_PRIORITY), jobStore);
        Scheduler scheduler = SchedulerRepository.getInstance().lookup(SCHEDULER_NAME);
        try {
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    .withIdentity("job1", "DEFAULT")
                    .usingJobData("foo", "foo")
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("job1", "DEFAULT")
                    .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);

            JobDetail jobDetail2 = scheduler.getJobDetail(jobDetail.getKey());
            Trigger trigger2 = scheduler.getTrigger(trigger.getKey());
            Assert.assertThat(jobDetail2.getJobDataMap().getString("foo"), Matchers.is("foo"));
            Assert.assertThat(trigger2, Matchers.instanceOf(CronTrigger.class));

            modifyStoredJobClassName();

            ClassLoadHelper clhelper = new CascadingClassLoadHelper();
            clhelper.initialize();
            XMLSchedulingDataProcessor processor = new XMLSchedulingDataProcessor(clhelper);

            processor.processFileAndScheduleJobs("org/quartz/xml/overwrite-no-jobclass.xml", scheduler);

            jobDetail2 = scheduler.getJobDetail(jobDetail.getKey());
            trigger2 = scheduler.getTrigger(trigger.getKey());
            Assert.assertThat(jobDetail2.getJobDataMap().getString("foo"), Matchers.is("bar"));
            Assert.assertThat(trigger2, Matchers.instanceOf(SimpleTrigger.class));
        } finally {
            scheduler.shutdown(false);
            JdbcQuartzTestUtilities.destroyDatabase(DB_NAME);
        }
    }
