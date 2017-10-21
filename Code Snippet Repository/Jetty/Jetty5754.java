    @Test
    public void testGetLoggingLevel_Bad()
    {
        Properties props = new Properties();
        props.setProperty("log.LEVEL", "WARN");
        props.setProperty("org.eclipse.jetty.bad.LEVEL","EXPECTED_BAD_LEVEL");

        // Default Level (because of bad level value)
        Assert.assertEquals("Bad Logging Level",StdErrLog.LEVEL_WARN,StdErrLog.getLoggingLevel(props,"org.eclipse.jetty.bad"));
    }
