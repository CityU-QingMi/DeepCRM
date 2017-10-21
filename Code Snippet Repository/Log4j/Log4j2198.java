    private void testLayout(final CSVFormat format, final AbstractCsvLayout layout, final String header, final String footer) {
        final Map<String, Appender> appenders = root.getAppenders();
        for (final Appender appender : appenders.values()) {
            root.removeAppender(appender);
        }
        // set up appender
        final ListAppender appender = new ListAppender("List", null, layout, true, false);
        appender.start();

        // set appender on root and set level to debug
        root.addAppender(appender);
        root.setLevel(Level.DEBUG);

        root.debug("one={}, two={}, three={}", 1, 2, 3);
        root.info("Hello");
        appender.stop();

        final List<String> list = appender.getMessages();
        final boolean hasHeaderSerializer = layout.getHeaderSerializer() != null;
        final boolean hasFooterSerializer = layout.getFooterSerializer() != null;
        final int headerOffset = hasHeaderSerializer ? 1 : 0;
        final String event0 = list.get(0 + headerOffset);
        final String event1 = list.get(1 + headerOffset);
        final char del = format.getDelimiter();
        Assert.assertTrue(event0, event0.contains(del + "DEBUG" + del));
        final String quote = del == ',' ? "\"" : "";
        Assert.assertTrue(event0, event0.contains(del + quote + "one=1, two=2, three=3" + quote + del));
        Assert.assertTrue(event1, event1.contains(del + "INFO" + del));
        
        if (hasHeaderSerializer && header == null) {
            Assert.fail();
        }
        if (!hasHeaderSerializer && header != null) {
            Assert.fail();
        }
        if (hasFooterSerializer && footer == null) {
            Assert.fail();
        }
        if (!hasFooterSerializer && footer != null) {
            Assert.fail();
        }
        if (hasHeaderSerializer) {
            Assert.assertEquals(list.toString(), header, list.get(0));
        }
        if (hasFooterSerializer) {
            Assert.assertEquals(list.toString(), footer, list.get(list.size() - 1));
        }
    }
