    @Test
    public void testAttributeMergeForLoggers() {
        final LoggerContextRule lcr = new LoggerContextRule("classpath:log4j-comp-logger-root.xml,log4j-comp-logger-attr-override.json");
        final Statement test = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                final CompositeConfiguration config = (CompositeConfiguration) lcr.getConfiguration();
                //Test for Root log level override
                assertEquals("Expected Root logger log level to be WARN", Level.WARN, config.getRootLogger().getLevel());

                //Test for cat2 level override
                final LoggerConfig cat2 = config.getLogger("cat2");
                assertEquals("Expected cat2 log level to be INFO", Level.INFO, cat2.getLevel());

                //Test for cat2 additivity override
                assertTrue("Expected cat2 additivity to be true", cat2.isAdditive());

                //Regression
                //Check level on cat3 (not present in root config)
                assertEquals("Expected cat3 log level to be ERROR", Level.ERROR, config.getLogger("cat3").getLevel());
                //Check level on cat1 (not present in overriden config)
                assertEquals("Expected cat1 log level to be DEBUG", Level.DEBUG, config.getLogger("cat1").getLevel());
            }
        };
        runTest(lcr, test);
    }
