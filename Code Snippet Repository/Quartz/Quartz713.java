    public void testAddJobNoTrigger() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetailImpl jobDetail = new JobDetailImpl();
        jobDetail.setName("testjob");

        try {
            scheduler.addJob(jobDetail, false);
        } catch (SchedulerException e) {
            assertThat(e.getMessage(), containsString("durable"));
        }

        jobDetail.setDurability(true);
        scheduler.addJob(jobDetail, false);
    }
