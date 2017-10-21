    @Test
    public void testClassName() {
        final Category category = Category.getInstance("TestCategory");
        final Layout<String> layout = PatternLayout.newBuilder().withPattern("%d %p %C{1.} [%t] %m%n").build();
        final ListAppender appender = new ListAppender("List2", null, layout, false, false);
        appender.start();
        category.setAdditivity(false);
        category.getLogger().addAppender(appender);
        category.error("Test Message");
        final List<String> msgs = appender.getMessages();
        assertTrue("Incorrect number of messages. Expected 1 got " + msgs.size(), msgs.size() == 1);
        final String msg = msgs.get(0);
        appender.clear();
        final String threadName = Thread.currentThread().getName();
        final String expected = "ERROR o.a.l.CategoryTest [" + threadName + "] Test Message" + Strings.LINE_SEPARATOR;
        assertTrue("Incorrect message " + Strings.dquote(msg) + " expected " + Strings.dquote(expected), msg.endsWith(expected));
    }
