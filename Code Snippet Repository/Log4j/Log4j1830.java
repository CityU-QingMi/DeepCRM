    private void testBuilder() {
        // @formatter:off
        final RollingFileAppender raf = RollingFileAppender.newBuilder()
            .withName("test1")
            .withFileName("target/testcmd1.log")
            .withFilePattern("target/testcmd1.log.%d{yyyy-MM-dd}")
            .withPolicy(createPolicy())
            .withStrategy(createStrategy())
            .setConfiguration(configuration)
            .build();
        // @formatter:on
        Assert.assertNotNull(raf);
    }
