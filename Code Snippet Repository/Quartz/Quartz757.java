    @Test
    public void testCalendarOperations() throws Exception {
        try {
            remoteScheduler.addCalendar("testCal", new BaseCalendar(), true, true);
            fail("Method was not exposed in MBean API");
        } catch (SchedulerException e) {
            // expected
        }

        try {
            remoteScheduler.getCalendar("test");
            fail("Method was not exposed in MBean API");
        } catch (SchedulerException e) {
            // expected
        }

        remoteScheduler.deleteCalendar(CALENDAR_KEY);
        assertThat(scheduler.getCalendar(CALENDAR_KEY), nullValue());
    }
