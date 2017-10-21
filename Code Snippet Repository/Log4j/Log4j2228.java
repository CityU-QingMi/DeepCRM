    @Test
    public void testMDCLoggerFields() throws Exception {
        for (final Appender appender : root.getAppenders().values()) {
            root.removeAppender(appender);
        }

        final LoggerFields[] loggerFields = new LoggerFields[] {
                LoggerFields.createLoggerFields(new KeyValuePair[] { new KeyValuePair("source", "%C.%M")}, null, null, false),
                LoggerFields.createLoggerFields(new KeyValuePair[] { new KeyValuePair("source2", "%C.%M")}, null, null, false)
        };

        // set up layout/appender
        final AbstractStringLayout layout = Rfc5424Layout.createLayout(Facility.LOCAL0, "Event", 3692, true, "RequestContext",
            null, null, true, null, "ATM", null, "key1, key2, locale", null, null, null, true, loggerFields, null);
        final ListAppender appender = new ListAppender("List", null, layout, true, false);
        appender.start();

        // set appender on root and set level to debug
        root.addAppender(appender);
        root.setLevel(Level.DEBUG);

        // output starting message
        root.info("starting logger fields test");

        try {

            final List<String> list = appender.getMessages();
            assertTrue("Not enough list entries", list.size() > 0);
            assertTrue("No class/method", list.get(0).contains("Rfc5424LayoutTest.testMDCLoggerFields"));

            appender.clear();
        } finally {
            root.removeAppender(appender);
            appender.stop();
        }
    }
