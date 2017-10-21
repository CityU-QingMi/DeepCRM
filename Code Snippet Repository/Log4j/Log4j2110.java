    @Test
    public void testThresholds() throws Exception {
        RegexFilter filter = RegexFilter.createFilter(".* test .*", null, false, null, null);
        filter.start();
        assertTrue(filter.isStarted());
        assertSame(Filter.Result.NEUTRAL,
                filter.filter(null, Level.DEBUG, null, (Object) "This is a test message", (Throwable) null));
        assertSame(Filter.Result.DENY, filter.filter(null, Level.ERROR, null, (Object) "This is not a test",
                (Throwable) null));
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("Another test message")) //
                .build();
        assertSame(Filter.Result.NEUTRAL, filter.filter(event));
        event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.ERROR) //
                .setMessage(new SimpleMessage("test")) //
                .build();
        assertSame(Filter.Result.DENY, filter.filter(event));
        filter = RegexFilter.createFilter(null, null, false, null, null);
        assertNull(filter);
    }
