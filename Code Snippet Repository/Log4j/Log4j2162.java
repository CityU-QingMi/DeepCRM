    @Test
    public void testJavaIoSerializableWithThrown() throws Exception {
        new InternalError("test error");
        final MutableLogEvent evt = new MutableLogEvent();
        evt.setContextData(CONTEXT_DATA);
        evt.setContextStack(STACK);
        evt.setEndOfBatch(true);
        evt.setIncludeLocation(true);
        evt.setLevel(Level.WARN);
        evt.setLoggerFqcn(getClass().getName());
        evt.setLoggerName("loggername");
        evt.setMarker(MarkerManager.getMarker("marked man"));
        //evt.setMessage(new ParameterizedMessage("message in a {}", "bottle")); // TODO ParameterizedMessage serialization
        evt.setMessage(new SimpleMessage("peace for all"));
        evt.setNanoTime(1234);
        evt.setThreadId(987);
        evt.setThreadName("ito");
        evt.setThreadPriority(9);
        evt.setThrown(new Exception());
        evt.setTimeMillis(56789);

        final byte[] binary = serialize(evt);
        final Log4jLogEvent evt2 = deserialize(binary);

        assertEquals(evt.getTimeMillis(), evt2.getTimeMillis());
        assertEquals(evt.getLoggerFqcn(), evt2.getLoggerFqcn());
        assertEquals(evt.getLevel(), evt2.getLevel());
        assertEquals(evt.getLoggerName(), evt2.getLoggerName());
        assertEquals(evt.getMarker(), evt2.getMarker());
        assertEquals(evt.getContextData(), evt2.getContextData());
        assertEquals(evt.getContextMap(), evt2.getContextMap());
        assertEquals(evt.getContextStack(), evt2.getContextStack());
        assertEquals(evt.getMessage(), evt2.getMessage());
        assertNotNull(evt2.getSource());
        assertEquals(evt.getSource(), evt2.getSource());
        assertEquals(evt.getThreadName(), evt2.getThreadName());
        assertNull(evt2.getThrown());
        assertNotNull(evt2.getThrownProxy());
        assertEquals(evt.getThrownProxy(), evt2.getThrownProxy());
        assertEquals(evt.isEndOfBatch(), evt2.isEndOfBatch());
        assertEquals(evt.isIncludeLocation(), evt2.isIncludeLocation());

        assertNotEquals(evt.getNanoTime(), evt2.getNanoTime()); // nano time is transient in log4j log event
        assertEquals(0, evt2.getNanoTime());
    }
