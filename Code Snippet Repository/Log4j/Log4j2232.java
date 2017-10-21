    @Test
    public void testParameterizedMessage() {
        for (final Appender appender : root.getAppenders().values()) {
            root.removeAppender(appender);
        }
        // set up appender
        final AbstractStringLayout layout = Rfc5424Layout.createLayout(Facility.LOCAL0, "Event", 3692, true, "RequestContext",
            null, null, true, null, "ATM", null, "key1, key2, locale", null, null, null, true, null, null);
        final ListAppender appender = new ListAppender("List", null, layout, true, false);

        appender.start();

        // set appender on root and set level to debug
        root.addAppender(appender);
        root.setLevel(Level.DEBUG);
        root.info("Hello {}", "World");
        try {
            final List<String> list = appender.getMessages();
            assertTrue("Not enough list entries", list.size() > 0);
            final String message =  list.get(0);
            assertTrue("Incorrect message. Expected - Hello World, Actual - " + message, message.contains("Hello World"));
        } finally {
            root.removeAppender(appender);
            appender.stop();
        }
    }
