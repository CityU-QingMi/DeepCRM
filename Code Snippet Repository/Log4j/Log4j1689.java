    private void testFollowSystemPrintStream(final PrintStream ps, final Target target, final SystemSetter systemSetter) {
        final ConsoleAppender app = ConsoleAppender.newBuilder().setTarget(target).setFollow(true)
                .withIgnoreExceptions(false).withName("test").build();
        Assert.assertEquals(target, app.getTarget());
        app.start();
        try {
            final LogEvent event = Log4jLogEvent.newBuilder() //
                    .setLoggerName("TestLogger") //
                    .setLoggerFqcn(ConsoleAppenderTest.class.getName()) //
                    .setLevel(Level.INFO) //
                    .setMessage(new SimpleMessage("Test")) //
                    .build();

            assertTrue("Appender did not start", app.isStarted());
            systemSetter.systemSet(new PrintStream(baos));
            try {
                app.append(event);
            } finally {
                systemSetter.systemSet(ps);
            }
            final String msg = baos.toString();
            assertNotNull("No message", msg);
            assertTrue("Incorrect message: \"" + msg + "\"", msg.endsWith("Test" + Strings.LINE_SEPARATOR));
        } finally {
            app.stop();
        }
        assertFalse("Appender did not stop", app.isStarted());
    }
