    @Test
    public void testGetNextTimeHourlyReturnsFirstMinuteOfNextYear() {
        final PatternProcessor pp = new PatternProcessor("logs/app-%d{yyyy-MM-dd-HH}.log.gz");
        final Calendar initial = Calendar.getInstance();
        initial.set(2015, Calendar.DECEMBER, 31, 23, 31, 59);
        final long actual = pp.getNextTime(initial.getTimeInMillis(), 1, false);

        final Calendar expected = Calendar.getInstance();
        expected.set(2016, Calendar.JANUARY, 1, 0, 0, 0);
        expected.set(Calendar.MILLISECOND, 0);
        assertEquals(format(expected.getTimeInMillis()), format(actual));
    }
