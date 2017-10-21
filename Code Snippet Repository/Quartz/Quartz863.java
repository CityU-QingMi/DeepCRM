    public void testDirectivesOverwriteWithNoIgnoreDups() throws Exception {
        Scheduler scheduler = null;
        try {
            StdSchedulerFactory factory = new StdSchedulerFactory("org/quartz/xml/quartz-test.properties");
            scheduler = factory.getScheduler();

            // Setup existing job with same names as in xml data.
            JobDetail job = newJob(MyJob.class).withIdentity("job1").build();
            Trigger trigger = newTrigger().withIdentity("job1").withSchedule(repeatHourlyForever()).build();
            scheduler.scheduleJob(job, trigger);

            job = newJob(MyJob.class).withIdentity("job2").build();
            trigger = newTrigger().withIdentity("job2").withSchedule(repeatHourlyForever()).build();
            scheduler.scheduleJob(job, trigger);

            // Now load the xml data with directives: overwrite-existing-data=false, ignore-duplicates=true
            ClassLoadHelper clhelper = new CascadingClassLoadHelper();
            clhelper.initialize();
            XMLSchedulingDataProcessor processor = new XMLSchedulingDataProcessor(clhelper);
            processor.processFileAndScheduleJobs("org/quartz/xml/directives_overwrite_no-ignoredups.xml", scheduler);
            assertEquals(2, scheduler.getJobKeys(GroupMatcher.jobGroupEquals("DEFAULT")).size());
            assertEquals(2, scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals("DEFAULT")).size());
        } finally {
            if (scheduler != null)
                scheduler.shutdown();
        }
    }
