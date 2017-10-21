    @Test
    public void testLayout() throws Exception {
        for (final Appender appender : root.getAppenders().values()) {
            root.removeAppender(appender);
        }
        // set up appender
        // @formatter:off
        final SyslogLayout layout = SyslogLayout.newBuilder()
                .setFacility(Facility.LOCAL0)
                .setIncludeNewLine(true)
                .build();
        // @formatter:on
        //ConsoleAppender appender = new ConsoleAppender("Console", layout);
        final ListAppender appender = new ListAppender("List", null, layout, true, false);
        appender.start();

        // set appender on root and set level to debug
        root.addAppender(appender);
        root.setLevel(Level.DEBUG);

        // output starting message
        root.debug("starting mdc pattern test");

        root.debug("empty mdc");

        ThreadContext.put("key1", "value1");
        ThreadContext.put("key2", "value2");

        root.debug("filled mdc");

        ThreadContext.put("loginId", "JohnDoe");
        ThreadContext.put("ipAddress", "192.168.0.120");
        ThreadContext.put("locale", Locale.US.getDisplayName());
        final StructuredDataMessage msg = new StructuredDataMessage("Transfer@18060", "Transfer Complete", "Audit");
        msg.put("ToAccount", "123456");
        msg.put("FromAccount", "123457");
        msg.put("Amount", "200.00");
        root.info(MarkerManager.getMarker("EVENT"), msg);

        appender.stop();

        final List<String> list = appender.getMessages();

        assertTrue("Expected line 1 to end with: " + line1 + " Actual " + list.get(0), list.get(0).endsWith(line1));
        assertTrue("Expected line 2 to end with: " + line2 + " Actual " + list.get(1), list.get(1).endsWith(line2));
        assertTrue("Expected line 3 to end with: " + line3 + " Actual " + list.get(2), list.get(2).endsWith(line3));
        assertTrue("Expected line 4 to end with: " + line4 + " Actual " + list.get(3), list.get(3).endsWith(line4));
    }
