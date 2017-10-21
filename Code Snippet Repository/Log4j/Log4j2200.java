    @Test
    public void testLogJsonArgument() throws InterruptedException {
        final ListAppender appender = (ListAppender) init.getAppender("List");
        appender.countDownLatch = new CountDownLatch(4);
        appender.clear();
        final Logger logger = (Logger) LogManager.getRootLogger();
        final String json = "{\"id\":10,\"name\":\"Alice\"}";
        logger.error("log:{}", json);
        // wait until background thread finished processing
        final int msgCount = 1;
        if (appender.getMessages().size() < msgCount) {
            appender.countDownLatch.await(5, TimeUnit.SECONDS);
        }
        assertEquals("Background thread did not finish processing: msg count", msgCount, appender.getMessages().size());

        // don't stop appender until background thread is done
        appender.stop();
        final List<String> list = appender.getMessages();
        final String eventStr = list.get(0).toString();
        Assert.assertTrue(eventStr, eventStr.contains(json));
    }
