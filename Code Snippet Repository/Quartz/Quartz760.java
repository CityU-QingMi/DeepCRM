    @Test
    public void testListBrokenAttributes() throws Exception {
        try {
            remoteScheduler.getContext();
            fail("Method was not exposed in MBean API");
        } catch (SchedulerException e) {
            // expected
        }

        try {
            remoteScheduler.getCurrentlyExecutingJobs();
            fail("Method had a different return type in MBean API");
        } catch (SchedulerException e) {
            // expected
        }

    }
