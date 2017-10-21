    @Test
    public void testGetLoggingLevel_Root()
    {
        Properties props = new Properties();
        props.setProperty("log.LEVEL","DEBUG");

        // Default Levels
        Assert.assertEquals("Default Logging Level",StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,null));
        Assert.assertEquals("Default Logging Level",StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,""));
        Assert.assertEquals("Default Logging Level",StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty"));
        Assert.assertEquals("Default Logging Level",StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,StdErrLogTest.class.getName()));
    }
