    @Test
    public void testGetNextTimeWeeklyReturnsFirstDayOfNextWeek_US() {
        final Locale old = Locale.getDefault();
        Locale.setDefault(Locale.US); // force 1st day of the week to be Sunday

        try {
            final PatternProcessor pp = new PatternProcessor("logs/app-%d{yyyy-MM-W}.log.gz");
            final Calendar initial = Calendar.getInstance();
            initial.set(2014, Calendar.MARCH, 4, 10, 31, 59); // Tue, March 4, 2014
            final long actual = pp.getNextTime(initial.getTimeInMillis(), 1, false);

            // expect Sunday, March 9, 2014
            final Calendar expected = Calendar.getInstance();
            expected.set(2014, Calendar.MARCH, 9, 00, 00, 00);
            expected.set(Calendar.MILLISECOND, 0);
            assertEquals(format(expected.getTimeInMillis()), format(actual));
        } finally {
            Locale.setDefault(old);
        }
    }
