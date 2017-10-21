    @Test
    public void testRangeOuter()
        throws Exception
    {
        long requestCount = 10;
        long testRangeLow  = 4;
        long testRangeHigh = 7;

        LessThanOrEqualToAttrEventTrigger<Long> trigger1 =
            new LessThanOrEqualToAttrEventTrigger<Long>("org.eclipse.jetty.monitor:type=requestcounter,id=0", "counter",
                                                testRangeLow);
        GreaterThanAttrEventTrigger<Long> trigger2 =
            new GreaterThanAttrEventTrigger<Long>("org.eclipse.jetty.monitor:type=requestcounter,id=0", "counter",
                                                testRangeHigh);
        OrEventTrigger trigger = new OrEventTrigger(trigger1, trigger2);
        EventNotifier notifier = new ConsoleNotifier("%s");
        CounterAction action = new CounterAction(trigger, notifier, 500, 100);

        performTest(action, requestCount, 1000);

        ResultSet result = new ResultSet(1,testRangeLow,testRangeHigh+1, requestCount);
        assertEquals(result, action.getHits());
    }
