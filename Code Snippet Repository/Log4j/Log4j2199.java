    static void testLayoutNormalApi(final Logger root, final AbstractCsvLayout layout, final boolean messageApi)
            throws Exception {
        removeAppenders(root);
        // set up appender
        final ListAppender appender = new ListAppender("List", null, layout, true, false);
        appender.start();

        appender.countDownLatch = new CountDownLatch(4);

        // set appender on root and set level to debug
        root.addAppender(appender);
        root.setLevel(Level.DEBUG);

        // output messages
        if (messageApi) {
            logDebugObjectArrayMessage(root);
        } else {
            logDebugNormalApi(root);
        }
        final int msgCount = 4;
        if (appender.getMessages().size() < msgCount) {
            // wait until background thread finished processing
            appender.countDownLatch.await(10, TimeUnit.SECONDS);
        }
        assertEquals("Background thread did not finish processing: msg count", msgCount, appender.getMessages().size());

        // don't stop appender until background thread is done
        appender.stop();

        final List<String> list = appender.getMessages();
        final char d = layout.getFormat().getDelimiter();
        Assert.assertEquals("1" + d + "2" + d + "3", list.get(0));
        Assert.assertEquals("2" + d + "3", list.get(1));
        Assert.assertEquals("5" + d + "6", list.get(2));
        Assert.assertEquals("7" + d + "8" + d + "9" + d + "10", list.get(3));
    }
