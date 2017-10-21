    @Test
    @SuppressWarnings("")
    public void testLog() {
        final PatternLayout layout = PatternLayout.newBuilder().withPattern("%d %C %L %m").build();
        final ListAppender appender = new ListAppender("List", null, layout, false, false);
        appender.start();
        final Logger root = Logger.getRootLogger();
        try {
            root.getLogger().addAppender(appender);
            root.setLevel(Level.INFO);
            final MyLogger log = new MyLogger(root);
            log.logInfo("This is a test", null);
            root.log(Priority.INFO, "Test msg2", null);
            root.log(Priority.INFO, "Test msg3");
            final List<String> msgs = appender.getMessages();
            assertTrue("Incorrect number of messages", msgs.size() == 3);
            final String msg = msgs.get(0);
            assertTrue("Message contains incorrect class name: " + msg, msg.contains(LoggerTest.class.getName()));
            appender.stop();
        } finally {
            root.getLogger().removeAppender(appender);
        }
    }
