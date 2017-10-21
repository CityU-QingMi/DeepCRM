    @Test
    public void testJobClassNotFoundDoesntBlock() throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(2);
        scheduler.getContext().put(BARRIER_KEY, barrier);

        JobDetail goodJob = JobBuilder.newJob(GoodJob.class).withIdentity("good").build();
        JobDetail badJob = JobBuilder.newJob(BadJob.class).withIdentity("bad").build();

        long now = System.currentTimeMillis();
        Trigger goodTrigger = TriggerBuilder.newTrigger().withIdentity("good").forJob(goodJob)
                .startAt(new Date(now + 1))
                .build();
        
        Trigger badTrigger = TriggerBuilder.newTrigger().withIdentity("bad").forJob(badJob)
                .startAt(new Date(now))
                .build();

        Map<JobDetail, Set<? extends Trigger>> toSchedule = new HashMap<JobDetail, Set<? extends Trigger>>();
        toSchedule.put(badJob, Collections.singleton(badTrigger));
        toSchedule.put(goodJob, Collections.singleton(goodTrigger));
        scheduler.scheduleJobs(toSchedule, true);

        barrier.await(20, TimeUnit.SECONDS);
        
        assertThat(scheduler.getTriggerState(badTrigger.getKey()), is(Trigger.TriggerState.ERROR));
    }
