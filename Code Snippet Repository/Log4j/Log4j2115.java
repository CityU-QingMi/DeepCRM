    @Test
    public void testThresholds() {
        final ThresholdFilter filter = ThresholdFilter.createFilter(Level.ERROR, null, null);
        filter.start();
        assertTrue(filter.isStarted());
        assertSame(Filter.Result.DENY, filter.filter(null, Level.DEBUG, null, (Object) null, (Throwable) null));
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.ERROR, null, (Object) null, (Throwable) null));
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("Test")) //
                .build();
        assertSame(Filter.Result.DENY, filter.filter(event));
        event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.ERROR) //
                .setMessage(new SimpleMessage("Test")) //
                .build();
        assertSame(Filter.Result.NEUTRAL, filter.filter(event));
    }
