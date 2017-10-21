    @Test
    public void testGetNextTimeHourlyReturnsFirstMinuteOfNextHour2() {
        final PatternProcessor pp = new PatternProcessor("logs/app-%d{yyyy-MM-dd-HH}.log.gz");
        final Calendar initial = Calendar.getInstance();
        initial.set(2014, Calendar.MARCH, 4, 23, 31, 59); // Tue, March 4, 2014, 23:31
        final long actual = pp.getNextTime(initial.getTimeInMillis(), 1, false);

        // expect Wed, March 5, 2014, 00:00
        final Calendar expected = Calendar.getInstance();
        expected.set(2014, Calendar.MARCH, 5, 00, 00, 00);
        expected.set(Calendar.MILLISECOND, 0);
        assertEquals(format(expected.getTimeInMillis()), format(actual));
    }
