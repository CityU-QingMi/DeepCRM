    @Test
    public void testLowerLimit()
        throws Exception
    {
        long requestCount = 10;
        long testRangeLow = 5;

        GreaterThanAttrEventTrigger<Long> trigger =
            new GreaterThanAttrEventTrigger<Long>("org.eclipse.jetty.monitor:type=requestcounter,id=0", "counter",
                                                  testRangeLow);

        EventNotifier notifier = new ConsoleNotifier("%s");
        CounterAction action = new CounterAction(trigger, notifier, 500, 100);

        performTest(action, requestCount, 1000);

        ResultSet result = new ResultSet(6,10);
        assertEquals(result, action.getHits());
    }
