    @Test
    public void testTime() {
        final TimeFilter filter = TimeFilter.createFilter("02:00:00", "03:00:00", "America/LosAngeles", null, null);
        filter.start();
        assertTrue(filter.isStarted());
        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/LosAngeles"));
        cal.set(Calendar.HOUR_OF_DAY, 2);
        CLOCKTIME = cal.getTimeInMillis();
        LogEvent event = Log4jLogEvent.newBuilder().setTimeMillis(CLOCKTIME).build();
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.ERROR, null, (Object) null, (Throwable) null));
        assertSame(Filter.Result.NEUTRAL, filter.filter(event));

        cal.roll(Calendar.DAY_OF_MONTH, true);
        CLOCKTIME = cal.getTimeInMillis();
        event = Log4jLogEvent.newBuilder().setTimeMillis(CLOCKTIME).build();
        assertSame(Filter.Result.NEUTRAL, filter.filter(event));
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.ERROR, null, (Object) null, (Throwable) null));

        cal.set(Calendar.HOUR_OF_DAY, 4);
        CLOCKTIME = cal.getTimeInMillis();
        event = Log4jLogEvent.newBuilder().setTimeMillis(CLOCKTIME).build();
        assertSame(Filter.Result.DENY, filter.filter(null, Level.ERROR, null, (Object) null, (Throwable) null));
        assertSame(Filter.Result.DENY, filter.filter(event));
    }
