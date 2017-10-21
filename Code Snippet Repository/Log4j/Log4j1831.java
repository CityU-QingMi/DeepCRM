    @Test
    public void testRollingRandomAccessFileAppender() {
        // @formatter:off
        RollingRandomAccessFileAppender.newBuilder()
            .withName("test2")
            .withFileName("target/testcmd2.log")
            .withFilePattern("target/testcmd2.log.%d{yyyy-MM-dd}")
            .withPolicy(createPolicy())
            .withStrategy(createStrategy())
            .setConfiguration(configuration)
            .build();
        // @formatter:on
    }
