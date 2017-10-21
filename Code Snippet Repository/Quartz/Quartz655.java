    @Test
    public void testAbilityToFireImmediatelyWhenStartedBefore() throws Exception {
    	
		List<Long> jobExecTimestamps = Collections.synchronizedList(new ArrayList<Long>());
		CyclicBarrier barrier = new CyclicBarrier(2);
    	
        Scheduler sched = createScheduler("testAbilityToFireImmediatelyWhenStartedBefore", 5);
        sched.getContext().put(BARRIER, barrier);
        sched.getContext().put(DATE_STAMPS, jobExecTimestamps);
        sched.start();
        
        Thread.yield();
        
		JobDetail job1 = JobBuilder.newJob(TestJobWithSync.class).withIdentity("job1").build();
		Trigger trigger1 = TriggerBuilder.newTrigger().forJob(job1).build(); 
		
		long sTime = System.currentTimeMillis();
		
		sched.scheduleJob(job1, trigger1);
		
	    barrier.await(TEST_TIMEOUT_SECONDS, TimeUnit.SECONDS);

	    sched.shutdown(true);

		long fTime = jobExecTimestamps.get(0);
		
		assertTrue("Immediate trigger did not fire within a reasonable amount of time.", (fTime - sTime  < 7000L));  // This is dangerously subjective!  but what else to do?
    }
