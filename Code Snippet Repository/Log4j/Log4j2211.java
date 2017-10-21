    @Test
    public void testHeader() {
        final ListAppender listApp = context.getListAppender("List");
        final Logger logger = context.getLogger(this.getClass().getName());
        logger.info("Hello World");
        final List<String> messages = listApp.getMessages();
        Assert.assertFalse(messages.isEmpty());
        final String messagesStr = messages.toString();
        Assert.assertEquals(messagesStr, "Header: value0", messages.get(0));
        listApp.stop();
        Assert.assertEquals(messagesStr, "Footer: value1", messages.get(2));
    }
