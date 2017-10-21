    @Test
    public void testGetNextTimeMonthlyReturnsFirstDayOfNextMonth3() {
        final PatternProcessor pp = new PatternProcessor("logs/app-%d{yyyy-MM}.log.gz");
        final Calendar initial = Calendar.getInstance();
        initial.set(2014, Calendar.DECEMBER, 31, 10, 31, 59); // 2014 Dec 31st
        final long actual = pp.getNextTime(initial.getTimeInMillis(), 1, false);

        // Expect 1st of next month: 2015 Jan 1st
        final Calendar expected = Calendar.getInstance();
        expected.set(2015, Calendar.JANUARY, 1, 00, 00, 00);
        expected.set(Calendar.MILLISECOND, 0);
        assertEquals(format(expected.getTimeInMillis()), format(actual));
    }
