    @Test
    public void testNoCondition()
        throws Exception
    {
        long requestCount = 10;

        AttrEventTrigger<Long> trigger =
            new AttrEventTrigger<Long>("org.eclipse.jetty.monitor:type=requestcounter,id=0", "counter");

        EventNotifier notifier = new ConsoleNotifier("%s");
        CounterAction action = new CounterAction(trigger, notifier, 500, 100);

        performTest(action, requestCount, 1000);

        ResultSet result = new ResultSet(1,requestCount);
        assertEquals(result, action.getHits());
    }
