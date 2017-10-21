    @Test
    public void testAppenderRefFilterMerge() {
        final LoggerContextRule lcr = new LoggerContextRule(
                "classpath:log4j-comp-logger-ref.xml,log4j-comp-logger-ref.json");
        final Statement test = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                final CompositeConfiguration config = (CompositeConfiguration) lcr.getConfiguration();

                final List<AppenderRef> appenderRefList = config.getLogger("cat1").getAppenderRefs();
                final AppenderRef appenderRef = getAppenderRef(appenderRefList, "STDOUT");
                assertTrue("Expected cat1 STDOUT appenderRef to have a regex filter",
                        appenderRef.getFilter() != null && appenderRef.getFilter() instanceof RegexFilter);
            }
        };
        runTest(lcr, test);
    }
