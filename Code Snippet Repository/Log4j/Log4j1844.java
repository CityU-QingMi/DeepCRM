    @Test
    public void testGetNextTimeMinutelyReturnsFirstSecondOfNextMinute() {
        final PatternProcessor pp = new PatternProcessor("logs/app-%d{yyyy-MM-dd-HH-mm}.log.gz");
        final Calendar initial = Calendar.getInstance();
        initial.set(2014, Calendar.MARCH, 4, 10, 31, 59); // Tue, March 4, 2014, 10:31
        initial.set(Calendar.MILLISECOND, 0);
        assertEquals("2014/03/04 10:31:59.000", format(initial.getTimeInMillis()));
        final long actual = pp.getNextTime(initial.getTimeInMillis(), 1, false);

        // expect Tue, March 4, 2014, 10:32
        final Calendar expected = Calendar.getInstance();
        expected.set(2014, Calendar.MARCH, 4, 10, 32, 00);
        expected.set(Calendar.MILLISECOND, 0);
        assertEquals(format(expected.getTimeInMillis()), format(actual));
    }
