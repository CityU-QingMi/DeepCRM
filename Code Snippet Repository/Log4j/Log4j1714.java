    private void assertMessage(final String string, final InMemoryAppender app, final String header) {
        final LogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("TestLogger") //
                .setLoggerFqcn(InMemoryAppenderTest.class.getName()) //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Test")) //
                .build();
        app.start();
        assertTrue("Appender did not start", app.isStarted());
        app.append(event);
        app.append(event);
        final String msg = app.toString();
        assertNotNull("No message", msg);
        final String expectedHeader = header == null ? "" : header;
        final String expected = expectedHeader + "Test" + Strings.LINE_SEPARATOR + "Test" + Strings.LINE_SEPARATOR;
        assertTrue("Incorrect message: " + msg, msg.equals(expected));
        app.stop();
        assertFalse("Appender did not stop", app.isStarted());
    }
