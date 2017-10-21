    @Test
    public void testDurableStorageFunctions() throws Exception {
        Scheduler sched = createScheduler("testDurableStorageFunctions", 2);
        try {
            // test basic storage functions of scheduler...

            JobDetail job = newJob()
                    .ofType(TestJob.class)
                    .withIdentity("j1")
                    .storeDurably()
                    .build();

            assertFalse("Unexpected existence of job named 'j1'.", sched.checkExists(jobKey("j1")));

            sched.addJob(job, false);

            assertTrue("Unexpected non-existence of job named 'j1'.", sched.checkExists(jobKey("j1")));

            JobDetail nonDurableJob = newJob()
                    .ofType(TestJob.class)
                    .withIdentity("j2")
                    .build();

            try {
                sched.addJob(nonDurableJob, false);
                fail("Storage of non-durable job should not have succeeded.");
            }
            catch(SchedulerException expected) {
                assertFalse("Unexpected existence of job named 'j2'.", sched.checkExists(jobKey("j2")));
            }

            sched.addJob(nonDurableJob, false, true);

            assertTrue("Unexpected non-existence of job named 'j2'.", sched.checkExists(jobKey("j2")));
        } finally {
            sched.shutdown(true);
        }
    }
