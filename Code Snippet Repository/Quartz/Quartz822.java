    @Test
    public void testCronRepeatCount() throws Exception {
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("test")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();
        List<Long> scheduledTimes = Collections.synchronizedList(new LinkedList<Long>());
        scheduler.getContext().put(SCHEDULED_TIMES_KEY, scheduledTimes);
        JobDetail jobDetail = JobBuilder.newJob(TrackingJob.class).withIdentity("test").build();
        scheduler.scheduleJob(jobDetail, trigger);

        for (int i = 0; i < 20 && scheduledTimes.size() < 3; i++) {
          Thread.sleep(500);
        }
        assertThat(scheduledTimes, hasSize(greaterThanOrEqualTo(3)));
        
        Long[] times = scheduledTimes.toArray(new Long[scheduledTimes.size()]);
        long baseline = times[0];
        assertThat(baseline % 1000, is(0L));
        for (int i = 1; i < times.length; i++) {
          assertThat(times[i], is(baseline + TimeUnit.SECONDS.toMillis(i)));
        }
    }
