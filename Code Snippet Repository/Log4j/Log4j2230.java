    @Test
    public void testDiscardEmptyLoggerFields() {
        final String mdcId = "RequestContext";

        Arrays.asList(
                "[BAZ@32473 baz=\"org.apache.logging.log4j.core.layout.Rfc5424LayoutTest.testLoggerFields\"]"  +
                        "[RequestContext@3692 bar=\"org.apache.logging.log4j.core.layout.Rfc5424LayoutTest.testLoggerFields\"]"
        );

        for (final Appender appender : root.getAppenders().values()) {
            root.removeAppender(appender);
        }

        final LoggerFields[] loggerFields = new LoggerFields[] {
                LoggerFields.createLoggerFields(new KeyValuePair[] { new KeyValuePair("dummy", Strings.EMPTY),
                        new KeyValuePair("empty", Strings.EMPTY)}, "SD-ID", "32473", true),
                LoggerFields.createLoggerFields(new KeyValuePair[] { new KeyValuePair("baz", "%C.%M"),
                        new KeyValuePair("baz", "%C.%M") }, "BAZ", "32473", false),
                LoggerFields.createLoggerFields(new KeyValuePair[] { new KeyValuePair("bar", "%C.%M")}, null, null, false)
        };

        final AbstractStringLayout layout = Rfc5424Layout.createLayout(Facility.LOCAL0, "Event", 3692, true, mdcId,
                null, null, true, null, "ATM", null, "key1, key2, locale", null, null, null, false, loggerFields, null);
        final ListAppender appender = new ListAppender("List", null, layout, true, false);
        appender.start();

        root.addAppender(appender);
        root.setLevel(Level.DEBUG);

        root.info("starting logger fields test");

        try {

            final List<String> list = appender.getMessages();
            assertTrue("Not enough list entries", list.size() > 0);
            final String message =  list.get(0);
            Assert.assertTrue("SD-ID should have been discarded", !message.contains("SD-ID"));
            Assert.assertTrue("BAZ should have been included", message.contains("BAZ"));
            Assert.assertTrue(mdcId + "should have been included", message.contains(mdcId));
            appender.clear();
        } finally {
            root.removeAppender(appender);
            appender.stop();
        }
    }
