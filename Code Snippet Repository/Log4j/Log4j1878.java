    @Test
    public void testDefaultLayout() throws Exception {
        // @formatter:off
        Assert.assertNotNull(RollingFileAppender.newBuilder()
                .withName(RollingFileAppenderLayoutTest.class.getName())
                .setConfiguration(new DefaultConfiguration())
                .withFileName("log.txt")
                .withFilePattern("FilePattern")
                .withPolicy(OnStartupTriggeringPolicy.createPolicy(1))
                .withCreateOnDemand(true) // no need to clutter up test folder with another file
                .build().getLayout());
        // @formatter:on
    }
