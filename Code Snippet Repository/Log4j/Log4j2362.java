    @Test
    public void testSequenceIncreases() throws Exception {
        final Logger logger = ctx.getLogger();
        logger.info("Message 1");
        logger.info("Message 2");
        logger.info("Message 3");
        logger.info("Message 4");
        logger.info("Message 5");

        final ListAppender app = ctx.getListAppender("List");
        final List<String> messages = app.getMessages();
        assertThat(messages, contains("1", "2", "3", "4", "5"));
    }
