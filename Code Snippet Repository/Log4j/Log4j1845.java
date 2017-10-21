    @Test
    public void testGetNextTimeMonthlyReturnsFirstDayOfNextMonth() {
        final PatternProcessor pp = new PatternProcessor("logs/app-%d{yyyy-MM}.log.gz");
        final Calendar initial = Calendar.getInstance();
        initial.set(2014, Calendar.OCTOBER, 15, 10, 31, 59); // Oct 15th
        final long actual = pp.getNextTime(initial.getTimeInMillis(), 1, false);

        // We expect 1st day of next month
        final Calendar expected = Calendar.getInstance();
        expected.set(2014, Calendar.NOVEMBER, 1, 00, 00, 00);
        expected.set(Calendar.MILLISECOND, 0);
        assertEquals(format(expected.getTimeInMillis()), format(actual));
    }
