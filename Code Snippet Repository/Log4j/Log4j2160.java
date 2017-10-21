    @Test
    public void testInitFromCopiesAllFields() {
//        private ThrowableProxy thrownProxy;
        final Log4jLogEvent source = Log4jLogEvent.newBuilder() //
                .setContextData(CONTEXT_DATA) //
                .setContextStack(STACK) //
                .setEndOfBatch(true) //
                .setIncludeLocation(true) //
                .setLevel(Level.FATAL) //
                .setLoggerFqcn("a.b.c.d.e") //
                .setLoggerName("my name is Logger") //
                .setMarker(MarkerManager.getMarker("on your marks")) //
                .setMessage(new SimpleMessage("msg in a bottle")) //
                .setNanoTime(1234567) //
                .setSource(new StackTraceElement("myclass", "mymethod", "myfile", 123)) //
                .setThreadId(100).setThreadName("threadname").setThreadPriority(10) //
                .setThrown(new RuntimeException("run")) //
                .setTimeMillis(987654321)
                .build();
        final MutableLogEvent mutable = new MutableLogEvent();
        mutable.initFrom(source);
        assertEquals("contextMap", CONTEXT_DATA, mutable.getContextData());
        assertEquals("stack", STACK, mutable.getContextStack());
        assertEquals("endOfBatch", true, mutable.isEndOfBatch());
        assertEquals("IncludeLocation()", true, mutable.isIncludeLocation());
        assertEquals("level", Level.FATAL, mutable.getLevel());
        assertEquals("LoggerFqcn()", source.getLoggerFqcn(), mutable.getLoggerFqcn());
        assertEquals("LoggerName", source.getLoggerName(), mutable.getLoggerName());
        assertEquals("marker", source.getMarker(), mutable.getMarker());
        assertEquals("msg", source.getMessage(), mutable.getMessage());
        assertEquals("nano", source.getNanoTime(), mutable.getNanoTime());
        assertEquals("src", source.getSource(), mutable.getSource());
        assertEquals("tid", source.getThreadId(), mutable.getThreadId());
        assertEquals("tname", source.getThreadName(), mutable.getThreadName());
        assertEquals("tpriority", source.getThreadPriority(), mutable.getThreadPriority());
        assertEquals("throwns", source.getThrown(), mutable.getThrown());
        assertEquals("proxy", source.getThrownProxy(), mutable.getThrownProxy());
        assertEquals("millis", source.getTimeMillis(), mutable.getTimeMillis());
    }
