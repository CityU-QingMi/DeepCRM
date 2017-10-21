    @Test
    public void testFilter() {
        final KeyValuePair[] pairs = new KeyValuePair[] { new KeyValuePair("FromAccount", "211000"),
                                                    new KeyValuePair("ToAccount", "123456")};
        MapFilter filter = MapFilter.createFilter(pairs, "and", null, null);
        filter.start();
        StringMapMessage msg = new StringMapMessage();
        msg.put("ToAccount", "123456");
        msg.put("FromAccount", "211000");
        msg.put("Amount", "1000.00");
        assertTrue(filter.isStarted());
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.DEBUG, null, msg, null));
        msg.put("ToAccount", "111111");
        assertSame(Filter.Result.DENY, filter.filter(null, Level.ERROR, null, msg, null));
        filter = MapFilter.createFilter(pairs, "or", null, null);
        filter.start();
        msg = new StringMapMessage();
        msg.put("ToAccount", "123456");
        msg.put("FromAccount", "211000");
        msg.put("Amount", "1000.00");
        assertTrue(filter.isStarted());
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.DEBUG, null, msg, null));
        msg.put("ToAccount", "111111");
        assertSame(Filter.Result.NEUTRAL, filter.filter(null, Level.ERROR, null, msg, null));
    }
