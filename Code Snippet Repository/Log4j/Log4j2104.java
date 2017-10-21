    @Test
    public void testFilterWorksWhenParamsArePassedAsArguments() {
        ThreadContext.put("userid", "testuser");
        ThreadContext.put("organization", "apache");
        final KeyValuePair[] pairs = new KeyValuePair[] {
                new KeyValuePair("testuser", "DEBUG"),
                new KeyValuePair("JohnDoe", "warn") };
        final DynamicThresholdFilter filter = DynamicThresholdFilter.createFilter("userid", pairs, Level.ERROR, Filter.Result.ACCEPT, Filter.Result.NEUTRAL);
        filter.start();
        assertTrue(filter.isStarted());
        final Object [] replacements = {"one", "two", "three"};
        assertSame(Filter.Result.ACCEPT, filter.filter(null, Level.DEBUG, null, "some test message", replacements)); 
        assertSame(Filter.Result.ACCEPT, filter.filter(null, Level.DEBUG, null, "some test message", "one", "two", "three")); 
        ThreadContext.clearMap();
    }
