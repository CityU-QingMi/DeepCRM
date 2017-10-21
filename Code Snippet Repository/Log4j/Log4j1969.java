    @SuppressWarnings("")
    @Test
    public void testCreateMementoReturnsCopy() {
        final RingBufferLogEvent evt = new RingBufferLogEvent();
        final String loggerName = "logger.name";
        final Marker marker = MarkerManager.getMarker("marked man");
        final String fqcn = "f.q.c.n";
        final Level level = Level.TRACE;
        final Message data = new SimpleMessage("message");
        final Throwable t = new InternalError("not a real error");
        final ContextStack contextStack = new MutableThreadContextStack(Arrays.asList("a", "b"));
        final String threadName = "main";
        final StackTraceElement location = null;
        final long currentTimeMillis = 12345;
        final long nanoTime = 1;
        evt.setValues(null, loggerName, marker, fqcn, level, data, t, (StringMap) evt.getContextData(),
                contextStack, -1, threadName, -1, location, currentTimeMillis, nanoTime);
        ((StringMap) evt.getContextData()).putValue("key", "value");

        final LogEvent actual = evt.createMemento();
        assertEquals(evt.getLoggerName(), actual.getLoggerName());
        assertEquals(evt.getMarker(), actual.getMarker());
        assertEquals(evt.getLoggerFqcn(), actual.getLoggerFqcn());
        assertEquals(evt.getLevel(), actual.getLevel());
        assertEquals(evt.getMessage(), actual.getMessage());
        assertEquals(evt.getThrown(), actual.getThrown());
        assertEquals(evt.getContextMap(), actual.getContextMap());
        assertEquals(evt.getContextData(), actual.getContextData());
        assertEquals(evt.getContextStack(), actual.getContextStack());
        assertEquals(evt.getThreadName(), actual.getThreadName());
        assertEquals(evt.getTimeMillis(), actual.getTimeMillis());
        assertEquals(evt.getSource(), actual.getSource());
        assertEquals(evt.getThrownProxy(), actual.getThrownProxy());
    }
