    @Test
    public void testSerializationDeserialization() throws IOException, ClassNotFoundException {
        final RingBufferLogEvent evt = new RingBufferLogEvent();
        final String loggerName = "logger.name";
        final Marker marker = null;
        final String fqcn = "f.q.c.n";
        final Level level = Level.TRACE;
        final Message data = new SimpleMessage("message");
        final Throwable t = new InternalError("not a real error");
        final ContextStack contextStack = null;
        final String threadName = "main";
        final StackTraceElement location = null;
        final long currentTimeMillis = 12345;
        final long nanoTime = 1;
        evt.setValues(null, loggerName, marker, fqcn, level, data, t, (StringMap) evt.getContextData(),
                contextStack, -1, threadName, -1, location, currentTimeMillis, nanoTime);
        ((StringMap) evt.getContextData()).putValue("key", "value");

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(evt);

        final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        final RingBufferLogEvent other = (RingBufferLogEvent) in.readObject();
        assertEquals(loggerName, other.getLoggerName());
        assertEquals(marker, other.getMarker());
        assertEquals(fqcn, other.getLoggerFqcn());
        assertEquals(level, other.getLevel());
        assertEquals(data, other.getMessage());
        assertNull("null after serialization", other.getThrown());
        assertEquals(new ThrowableProxy(t), other.getThrownProxy());
        assertEquals(evt.getContextData(), other.getContextData());
        assertEquals(contextStack, other.getContextStack());
        assertEquals(threadName, other.getThreadName());
        assertEquals(location, other.getSource());
        assertEquals(currentTimeMillis, other.getTimeMillis());
    }
