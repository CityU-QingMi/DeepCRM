    @Test
    public void testWithProps() {
        final ListAppender listAppender = context.getListAppender("List");
        assertNotNull("No List Appender", listAppender);

        try {
            assertThat(context.getConfiguration(), is(instanceOf(XmlConfiguration.class)));
            Logger logger = LogManager.getLogger(XmlLoggerPropsTest.class);
            logger.debug("Test with props");
            logger = LogManager.getLogger("tiny.bubbles");
            logger.debug("Test on root");
            final List<String> events = listAppender.getMessages();
            assertTrue("No events", events.size() > 0);
            assertTrue("Incorrect number of events", events.size() == 2);
            assertThat(events.get(0), allOf(
                containsString("user="),
                containsString("phrasex=****"),
                containsString("test=test"),
                containsString("test2=test2default"),
                containsString("test3=Unknown"),
                containsString("test4=test"),
                containsString("test5=test"),
                containsString("attribKey=attribValue"),
                containsString("duplicateKey=nodeValue")
            ));
            assertThat(events.get(1), allOf(
                containsString("user="),
                containsString("phrasex=****"),
                containsString("test=test"),
                containsString("test2=test2default"),
                containsString("test3=Unknown"),
                containsString("test4=test"),
                containsString("test5=test"),
                containsString("attribKey=attribValue"),
                containsString("duplicateKey=nodeValue")
            ));
        } finally {
            System.clearProperty("test");
        }
    }
