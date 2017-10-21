    @Test
    public void compositeLogger() {
        final LoggerContextRule lcr = new LoggerContextRule("classpath:log4j-comp-logger.xml,log4j-comp-logger.json");
        final Statement test = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                final CompositeConfiguration config = (CompositeConfiguration) lcr.getConfiguration();
                Map<String, Appender> appendersMap = config.getLogger("cat1").getAppenders();
                assertEquals("Expected 2 Appender references for cat1 but got " + appendersMap.size(), 2,
                        appendersMap.size());
                assertTrue(appendersMap.get("STDOUT") instanceof ConsoleAppender);

                appendersMap = config.getLogger("cat2").getAppenders();
                assertEquals("Expected 1 Appender reference for cat2 but got " + appendersMap.size(), 1,
                        appendersMap.size());
                assertTrue(appendersMap.get("File") instanceof FileAppender);

                appendersMap = config.getLogger("cat3").getAppenders();
                assertEquals("Expected 1 Appender reference for cat3 but got " + appendersMap.size(), 1,
                        appendersMap.size());
                assertTrue(appendersMap.get("File") instanceof FileAppender);

                appendersMap = config.getRootLogger().getAppenders();
                assertEquals("Expected 2 Appender references for the root logger but got " + appendersMap.size(), 2,
                        appendersMap.size());
                assertTrue(appendersMap.get("File") instanceof FileAppender);
                assertTrue(appendersMap.get("STDOUT") instanceof ConsoleAppender);
            }
        };
        runTest(lcr, test);
    }
