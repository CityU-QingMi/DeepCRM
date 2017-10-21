    @Test
    public void testFilter() {
        ThreadContext.put("userid", "testuser");
        ThreadContext.put("organization", "Apache");
        final KeyValuePair[] pairs = new KeyValuePair[] { new KeyValuePair("userid", "JohnDoe"),
                                                    new KeyValuePair("organization", "Apache")};
        ThreadContextMapFilter filter = ThreadContextMapFilter.createFilter(pairs, "and", null, null);
        filter.start();
        assertTrue(filter.isStarted());
        assertSame(Filter.Result.DENY, filter.filter(null, Level.DEBUG, null, (Object) null, (Throwable) null));
        ThreadContext.remove("userid");
        assertSame(Filter.Result.DENY, filter.filter(null, Level.DEBUG, null, (Object) null, (Throwable) null));
        ThreadContext.put("userid", "JohnDoe");
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.ERROR, null, (Object) null, (Throwable) null));
        ThreadContext.put("organization", "ASF");
        assertSame(Filter.Result.DENY, filter.filter(null, Level.DEBUG, null, (Object) null, (Throwable) null));
        ThreadContext.clearMap();
        filter = ThreadContextMapFilter.createFilter(pairs, "or", null, null);
        filter.start();
        assertTrue(filter.isStarted());
        ThreadContext.put("userid", "testuser");
        ThreadContext.put("organization", "Apache");
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.DEBUG, null, (Object) null, (Throwable) null));
        ThreadContext.put("organization", "ASF");
        assertSame(Filter.Result.DENY, filter.filter(null, Level.DEBUG, null, (Object) null, (Throwable) null));
        ThreadContext.remove("organization");
        assertSame(Filter.Result.DENY, filter.filter(null, Level.DEBUG, null, (Object) null, (Throwable) null));
        final KeyValuePair[] single = new KeyValuePair[] {new KeyValuePair("userid", "testuser")};
        filter = ThreadContextMapFilter.createFilter(single, null, null, null);
        filter.start();
        assertTrue(filter.isStarted());
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.DEBUG, null, (Object) null, (Throwable) null));
        ThreadContext.clearMap();
    }
