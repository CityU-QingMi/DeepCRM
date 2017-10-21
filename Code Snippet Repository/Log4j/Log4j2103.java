    @Test
    public void testFilter() {
        ThreadContext.put("userid", "testuser");
        ThreadContext.put("organization", "apache");
        final KeyValuePair[] pairs = new KeyValuePair[] {
                new KeyValuePair("testuser", "DEBUG"),
                new KeyValuePair("JohnDoe", "warn") };
        final DynamicThresholdFilter filter = DynamicThresholdFilter.createFilter("userid", pairs, Level.ERROR, null,
                null);
        filter.start();
        assertTrue(filter.isStarted());
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.DEBUG, null, (Object) null, (Throwable) null));
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.ERROR, null, (Object) null, (Throwable) null));
        ThreadContext.clearMap();
        ThreadContext.put("userid", "JohnDoe");
        ThreadContext.put("organization", "apache");
        LogEvent event = Log4jLogEvent.newBuilder().setLevel(Level.DEBUG).setMessage(new SimpleMessage("Test")).build();
        assertSame(Filter.Result.DENY, filter.filter(event));
        event = Log4jLogEvent.newBuilder().setLevel(Level.ERROR).setMessage(new SimpleMessage("Test")).build();
        assertSame(Filter.Result.NEUTRAL, filter.filter(event));
        ThreadContext.clearMap();
    }
