    @Test
    public void testLifecycleOperations() throws SchedulerException {
        try {
            remoteScheduler.startDelayed(60);
            fail("Method was not exposed in MBean API");
        } catch (SchedulerException e) {
            // expected
        }

        remoteScheduler.start();
        assertThat(remoteScheduler.isStarted(), is(true));
        assertThat(scheduler.isStarted(), is(true));

        remoteScheduler.standby();
        assertThat(remoteScheduler.isInStandbyMode(), is(true));
        assertThat(scheduler.isInStandbyMode(), is(true));

        try {
            remoteScheduler.shutdown(true);
            fail("Method was not exposed in MBean API");
        } catch (SchedulerException e) {
            // expected
        }

        remoteScheduler.shutdown();
        try {
            remoteScheduler.isShutdown();
            fail("Shutting down a scheduler un-registers it in JMX");
        } catch (SchedulerException e) {
            // expected
        }
        assertThat(scheduler.isShutdown(), is(true));

    }
