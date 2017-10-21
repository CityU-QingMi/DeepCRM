    @Test
    public void testGetNextTimeSecondlyReturnsFirstMillisecOfNextSecond() {
        final PatternProcessor pp = new PatternProcessor("logs/app-%d{yyyy-MM-dd-HH-mm-ss}.log.gz");
        final Calendar initial = Calendar.getInstance();
        initial.set(2014, Calendar.MARCH, 4, 10, 31, 53); // Tue, March 4, 2014, 10:31:53
        initial.set(Calendar.MILLISECOND, 123);
        assertEquals("2014/03/04 10:31:53.123", format(initial.getTimeInMillis()));
        final long actual = pp.getNextTime(initial.getTimeInMillis(), 1, false);

        // expect Tue, March 4, 2014, 10:31:54
        final Calendar expected = Calendar.getInstance();
        expected.set(2014, Calendar.MARCH, 4, 10, 31, 54);
        expected.set(Calendar.MILLISECOND, 0);
        assertEquals(format(expected.getTimeInMillis()), format(actual));
    }
