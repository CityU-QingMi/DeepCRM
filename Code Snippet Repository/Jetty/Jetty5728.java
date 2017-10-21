    @Test
    public void testGetLoggingLevel_FQCN()
    {
        String name = StdErrLogTest.class.getName();
        Properties props = new Properties();
        props.setProperty(name + ".LEVEL","ALL");

        // Default Levels
        Assert.assertEquals(StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,null));
        Assert.assertEquals(StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,""));
        Assert.assertEquals(StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty"));

        // Specified Level
        Assert.assertEquals(StdErrLog.LEVEL_ALL,StdErrLog.getLoggingLevel(props,name));
    }
