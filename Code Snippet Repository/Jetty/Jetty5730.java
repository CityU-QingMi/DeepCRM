    @Test
    public void testGetLoggingLevel_MixedLevels()
    {
        Properties props = new Properties();
        props.setProperty("log.LEVEL","DEBUG");
        props.setProperty("org.eclipse.jetty.util.LEVEL","WARN");
        props.setProperty("org.eclipse.jetty.util.ConcurrentHashMap.LEVEL","ALL");

        // Default Levels
        Assert.assertEquals(StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,null));
        Assert.assertEquals(StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,""));
        Assert.assertEquals(StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty"));
        Assert.assertEquals(StdErrLog.LEVEL_DEBUG,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.server.ServerObject"));

        // Configured Level
        Assert.assertEquals(StdErrLog.LEVEL_WARN,StdErrLog.getLoggingLevel(props,StdErrLogTest.class.getName()));
        Assert.assertEquals(StdErrLog.LEVEL_WARN,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.util.MagicUtil"));
        Assert.assertEquals(StdErrLog.LEVEL_WARN,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.util"));
        Assert.assertEquals(StdErrLog.LEVEL_WARN,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.util.resource.FileResource"));
        Assert.assertEquals(StdErrLog.LEVEL_ALL,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.util.ConcurrentHashMap"));
    }
