    @Test
    public void testGetLoggingLevel_UtilLevel()
    {
        Properties props = new Properties();
        props.setProperty("org.eclipse.jetty.util.LEVEL","DEBUG");

        // Default Levels
        Assert.assertEquals(StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,null));
        Assert.assertEquals(StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,""));
        Assert.assertEquals(StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty"));
        Assert.assertEquals(StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.server.BogusObject"));

        // Configured Level
        Assert.assertEquals(StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,StdErrLogTest.class.getName()));
        Assert.assertEquals(StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.util.Bogus"));
        Assert.assertEquals(StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.util"));
        Assert.assertEquals(StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.util.resource.FileResource"));
    }
