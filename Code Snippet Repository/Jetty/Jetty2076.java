    @Test
    public void testRangeInclusive()
        throws Exception
    {
        long requestCount = 10;
        long testRangeLow  = 3;
        long testRangeHigh = 8;

        RangeInclAttrEventTrigger<Long> trigger =
            new RangeInclAttrEventTrigger<Long>("org.eclipse.jetty.monitor:type=requestcounter,id=0", "counter",
                                                testRangeLow, testRangeHigh);

        EventNotifier notifier = new ConsoleNotifier("%s");
        CounterAction action = new CounterAction(trigger, notifier, 500, 100);

        performTest(action, requestCount, 1000);

        ResultSet result = new ResultSet(testRangeLow,testRangeHigh);
        assertEquals(result, action.getHits());
    }
