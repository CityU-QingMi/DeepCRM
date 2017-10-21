    @Test
    public void testGetLoggingLevel_Lowercase()
    {
        Properties props = new Properties();
        props.setProperty("log.LEVEL", "warn");
        props.setProperty("org.eclipse.jetty.util.LEVEL","info");

        // Default Level
        Assert.assertEquals("Lowercase Level",StdErrLog.LEVEL_WARN,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty"));
        // Specific Level
        Assert.assertEquals("Lowercase Level",StdErrLog.LEVEL_INFO,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.util"));
    }
