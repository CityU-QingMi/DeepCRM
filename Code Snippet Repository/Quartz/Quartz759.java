    @Test
    public void testUnsupportedMethods() {
        try {
            remoteScheduler.getListenerManager();
            fail("Operation should not be supported");
        } catch (SchedulerException e) {
            // expected
        }

        try {
            remoteScheduler.setJobFactory(null);
            fail("Operation should not be supported");
        } catch (SchedulerException e) {
            // expected
        }

    }
