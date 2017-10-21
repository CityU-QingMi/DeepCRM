    @Test
    public void testEqual_TRUE()
        throws Exception
    {
        long requestCount = 10;
        long testValue = 5;

        EqualToAttrEventTrigger<Long> trigger =
            new EqualToAttrEventTrigger<Long>("org.eclipse.jetty.monitor:type=requestcounter,id=0", "counter",testValue);

        EventNotifier notifier = new ConsoleNotifier("%s");
        CounterAction action = new CounterAction(trigger, notifier, 500, 100);

        performTest(action, requestCount, 1000);

        ResultSet result = new ResultSet(testValue);
        assertEquals(result, action.getHits());
    }
