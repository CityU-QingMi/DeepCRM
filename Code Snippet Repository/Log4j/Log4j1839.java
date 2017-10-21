    @Test
    public void testGetNextTimeWeeklyReturnsFirstWeekInYear_US() {
        final Locale old = Locale.getDefault();
        Locale.setDefault(Locale.US); // force 1st day of the week to be Sunday
        try {
            final PatternProcessor pp = new PatternProcessor("logs/market_data_msg.log-%d{yyyy-MM-'W'W}");
            final Calendar initial = Calendar.getInstance();
            initial.set(2015, Calendar.DECEMBER, 28, 00, 00, 00); // Monday, December 28, 2015
            final long actual = pp.getNextTime(initial.getTimeInMillis(), 1, false);

            // expect Sunday January 3, 2016
            final Calendar expected = Calendar.getInstance();
            expected.set(2016, Calendar.JANUARY, 3, 00, 00, 00);
            expected.set(Calendar.MILLISECOND, 0);
            assertEquals(format(expected.getTimeInMillis()), format(actual));
        } finally {
            Locale.setDefault(old);
        }
    }
