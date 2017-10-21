    @Test
    public void testAbilityToFireImmediatelyWhenStartedBeforeWithTriggerJob() throws Exception {
    	
		List<Long> jobExecTimestamps = Collections.synchronizedList(new ArrayList<Long>());
		CyclicBarrier barrier = new CyclicBarrier(2);
    	
        Scheduler sched = createScheduler("testAbilityToFireImmediatelyWhenStartedBeforeWithTriggerJob", 5);
        sched.getContext().put(BARRIER, barrier);
        sched.getContext().put(DATE_STAMPS, jobExecTimestamps);

        sched.start();
        
        Thread.yield();

        JobDetail job1 = JobBuilder.newJob(TestJobWithSync.class).withIdentity("job1").storeDurably().build();
		sched.addJob(job1, false);
		
		long sTime = System.currentTimeMillis();
		
		sched.triggerJob(job1.getKey());
		
	    barrier.await(TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS);

	    sched.shutdown(true);

		long fTime = jobExecTimestamps.get(0);
		
		assertTrue("Immediate trigger did not fire within a reasonable amount of time.", (fTime - sTime  < 7000L));  // This is dangerously subjective!  but what else to do?
    }
