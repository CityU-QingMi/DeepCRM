    @Test
    public void testRangeComposite()
        throws Exception
    {
        long requestCount = 10;
        long testRangeLow  = 4;
        long testRangeHigh = 7;

        GreaterThanAttrEventTrigger<Long> trigger1 =
            new GreaterThanAttrEventTrigger<Long>("org.eclipse.jetty.monitor:type=requestcounter,id=0", "counter",
                                                testRangeLow);
        LessThanOrEqualToAttrEventTrigger<Long> trigger2 =
            new LessThanOrEqualToAttrEventTrigger<Long>("org.eclipse.jetty.monitor:type=requestcounter,id=0", "counter",
                                                testRangeHigh);
        AndEventTrigger trigger = new AndEventTrigger(trigger1, trigger2);
        EventNotifier notifier = new ConsoleNotifier("%s");
        CounterAction action = new CounterAction(trigger, notifier, 500, 100);

        performTest(action, requestCount, 1000);

        ResultSet result = new ResultSet(testRangeLow+1,testRangeHigh);
        assertEquals(result, action.getHits());
    }
