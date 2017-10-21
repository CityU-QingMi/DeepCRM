    @Test
    public void testWithAuthentication() throws Exception {
        JobDetail job = newJob(SendMailJob.class)
                .withIdentity("job1", "group1").build();

        configureSendMailJob(job);

        Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
                .startNow().build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();

        jobListener.barrier.await(30, TimeUnit.SECONDS);
        
        assertAuthentication();
    }
