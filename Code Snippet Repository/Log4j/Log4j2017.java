    @Test
    public void testAttributeCheckWhenMergingConfigurations() {
        final LoggerContextRule lcr = new LoggerContextRule("classpath:log4j-comp-root-loggers.xml,log4j-comp-logger.json");
        final Statement test = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    final CompositeConfiguration config = (CompositeConfiguration) lcr.getConfiguration();
                    Assert.assertNotNull(config);
                } catch (final NullPointerException e) {
                    fail("Should not throw NullPointerException when there are different nodes.");
                }
            }
        };
        runTest(lcr, test);
    }
