    @Test
    public void testException() throws Exception {
        for (final Appender appender : root.getAppenders().values()) {
            root.removeAppender(appender);
        }
        // set up layout/appender
        final AbstractStringLayout layout = Rfc5424Layout.createLayout(Facility.LOCAL0, "Event", 3692, true, "RequestContext",
            null, null, true, null, "ATM", null, "key1, key2, locale", null, "loginId", "%xEx", true, null, null);
        final ListAppender appender = new ListAppender("List", null, layout, true, false);
        appender.start();

        // set appender on root and set level to debug
        root.addAppender(appender);
        root.setLevel(Level.DEBUG);

        ThreadContext.put("loginId", "JohnDoe");

        // output starting message
        root.debug("starting mdc pattern test", new IllegalArgumentException("Test"));

        try {

            final List<String> list = appender.getMessages();

            assertTrue("Not enough list entries", list.size() > 1);
            final String string = list.get(1);
			      assertTrue("No Exception in " + string, string.contains("IllegalArgumentException"));

            appender.clear();
        } finally {
            root.removeAppender(appender);
            appender.stop();
        }
    }
