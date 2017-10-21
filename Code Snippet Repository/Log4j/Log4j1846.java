    @Test
    public void testGetNextTimeMonthlyReturnsFirstDayOfNextMonth2() {
        final PatternProcessor pp = new PatternProcessor("logs/app-%d{yyyy-MM}.log.gz");
        final Calendar initial = Calendar.getInstance();
        initial.set(2014, Calendar.JANUARY, 31, 10, 31, 59); // 2014 Jan 31st
        final long actual = pp.getNextTime(initial.getTimeInMillis(), 1, false);

        // Expect 1st of next month: 2014 Feb 1st
        final Calendar expected = Calendar.getInstance();
        expected.set(2014, Calendar.FEBRUARY, 1, 00, 00, 00);
        expected.set(Calendar.MILLISECOND, 0);
        assertEquals(format(expected.getTimeInMillis()), format(actual));
    }
