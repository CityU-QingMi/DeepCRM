    @Test
    public void testSubstituteStructuredData() {
        final String mdcId = "RequestContext";

        final String expectedToContain = String.format("ATM %s MSG-ID - Message", PROCESSID);

        for (final Appender appender : root.getAppenders().values()) {
            root.removeAppender(appender);
        }

        final AbstractStringLayout layout = Rfc5424Layout.createLayout(Facility.LOCAL0, "Event", 3692, false, mdcId,
                null, null, true, null, "ATM", "MSG-ID", "key1, key2, locale", null, null, null, false, null, null);
        final ListAppender appender = new ListAppender("List", null, layout, true, false);
        appender.start();

        root.addAppender(appender);
        root.setLevel(Level.DEBUG);

        root.info("Message");

        try {
            final List<String> list = appender.getMessages();
            assertTrue("Not enough list entries", list.size() > 0);
            final String message =  list.get(0);
            Assert.assertTrue("Not the expected message received", message.contains(expectedToContain));
            appender.clear();
        } finally {
            root.removeAppender(appender);
            appender.stop();
        }
    }
