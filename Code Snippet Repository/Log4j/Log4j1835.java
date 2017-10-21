    @Test
    public void testGetNextTimeMonthlyReturnsFirstDayOfNextYear() {
        final PatternProcessor pp = new PatternProcessor("logs/app-%d{yyyy-MM}.log.gz");
        final Calendar initial = Calendar.getInstance();
        initial.set(2015, Calendar.DECEMBER, 28, 0, 0, 0);
        final long actual = pp.getNextTime(initial.getTimeInMillis(), 1, false);

        // We expect 1st day of next month
        final Calendar expected = Calendar.getInstance();
        expected.set(2016, Calendar.JANUARY, 1, 00, 00, 00);
        expected.set(Calendar.MILLISECOND, 0);
        assertEquals(format(expected.getTimeInMillis()), format(actual));
    }
