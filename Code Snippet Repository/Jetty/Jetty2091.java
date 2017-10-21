    @Test
    public void testUpperLimit()
        throws Exception
    {
        long requestCount = 10;
        long testRangeHigh = 5;

        LessThanAttrEventTrigger<Long> trigger =
            new LessThanAttrEventTrigger<Long>("org.eclipse.jetty.monitor:type=requestcounter,id=0", "counter",
                                                  testRangeHigh);

        EventNotifier notifier = new ConsoleNotifier("%s");
        CounterAction action = new CounterAction(trigger, notifier, 500, 100);

        performTest(action, requestCount, 1000);

        ResultSet result = new ResultSet(1,4);
        assertEquals(result, action.getHits());
    }
