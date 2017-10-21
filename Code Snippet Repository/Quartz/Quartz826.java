    @Test
    public void testPauseAndResumeTriggers() throws Exception {
        JobDetail jobDetail = newJob(HelloJob.class)
                .withIdentity("test_1")
                .build();

        CronTrigger trigger = newTrigger()
                .withIdentity("test_1", "abc")
                .withSchedule(cronSchedule("* * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        Trigger.TriggerState state = scheduler.getTriggerState(TriggerKey.triggerKey("test_1", "abc"));
        assertThat(state, is(Trigger.TriggerState.NORMAL));
        assertThat(state, not(Trigger.TriggerState.PAUSED));

        scheduler.pauseTriggers(GroupMatcher.triggerGroupEquals("abc"));
        state = scheduler.getTriggerState(TriggerKey.triggerKey("test_1", "abc"));
        assertThat(state, is(Trigger.TriggerState.PAUSED));
        assertThat(state, not(Trigger.TriggerState.NORMAL));

        scheduler.resumeTriggers(GroupMatcher.triggerGroupEquals("abc"));
        state = scheduler.getTriggerState(TriggerKey.triggerKey("test_1", "abc"));
        assertThat(state, is(Trigger.TriggerState.NORMAL));
        assertThat(state, not(Trigger.TriggerState.PAUSED));
    }
