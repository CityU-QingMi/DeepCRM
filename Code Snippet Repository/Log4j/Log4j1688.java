    private void testConsoleStreamManagerDoesNotClose(final PrintStream ps, final Target targetName, final SystemSetter systemSetter) {
        try {
            systemSetter.systemSet(psMock);
            final Layout<String> layout = PatternLayout.newBuilder().withAlwaysWriteExceptions(true).build();
            final ConsoleAppender app = ConsoleAppender.newBuilder().withLayout(layout).setTarget(targetName)
                    .withName("Console").withIgnoreExceptions(false).build();
            app.start();
            assertTrue("Appender did not start", app.isStarted());

            final LogEvent event = Log4jLogEvent.newBuilder() //
                    .setLoggerName("TestLogger") //
                    .setLoggerFqcn(ConsoleAppenderTest.class.getName()) //
                    .setLevel(Level.INFO) //
                    .setMessage(new SimpleMessage("Test")) //
                    .build();
            app.append(event);

            app.stop();
            assertFalse("Appender did not stop", app.isStarted());
        } finally {
            systemSetter.systemSet(ps);
        }
        then(psMock).should(atLeastOnce()).write(any(byte[].class), anyInt(), anyInt());
        then(psMock).should(atLeastOnce()).flush();
    }
