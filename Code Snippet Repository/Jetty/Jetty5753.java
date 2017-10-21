    @Test
    public void testGetLoggingLevel_Default()
    {
        Properties props = new Properties();

        // Default Levels
        Assert.assertEquals("Default Logging Level",StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,null));
        Assert.assertEquals("Default Logging Level",StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,""));
        Assert.assertEquals("Default Logging Level",StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty"));
        Assert.assertEquals("Default Logging Level",StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,StdErrLogTest.class.getName()));
    }
