    @Test
    public void testLayoutLoggerName() {
        final XmlLayout layout = XmlLayout.newBuilder()
                .setLocationInfo(false)
                .setProperties(true)
                .setComplete(true)
                .setCompact(false)
                .setIncludeStacktrace(true)
                .build();

        final Log4jLogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("a.B") //
                .setLoggerFqcn("f.q.c.n") //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("M")) //
                .setThreadName("threadName") //
                .setTimeMillis(1).build();
        final String str = layout.toSerializable(event);
        assertTrue(str, str.contains("loggerName=\"a.B\""));
    }
