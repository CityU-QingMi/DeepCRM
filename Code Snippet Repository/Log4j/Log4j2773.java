    @Test
    public void testFlowMessages() {
        final Logger flowLogger = Logger.getLogger("TestFlow");
        flowLogger.entering("com.example.TestSourceClass1", "testSourceMethod1(String)");
        flowLogger.entering("com.example.TestSourceClass2", "testSourceMethod2(String)", "TestParam");
        flowLogger.entering("com.example.TestSourceClass3", "testSourceMethod3(String)",
                new Object[] { "TestParam0", "TestParam1" });
        final List<LogEvent> events = flowAppender.getEvents();
        assertThat(events, hasSize(3));
        assertEquals("Enter", events.get(0).getMessage().getFormattedMessage());
        assertEquals("Enter params(TestParam)", events.get(1).getMessage().getFormattedMessage());
        assertEquals("Enter params(TestParam0, TestParam1)", events.get(2).getMessage().getFormattedMessage());
    }
