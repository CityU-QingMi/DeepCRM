    @Test
    public void testConfiguredAndSetDebugEnabled()
    {
        Properties props = new Properties();
        props.setProperty("org.eclipse.jetty.util.LEVEL","WARN");
        props.setProperty("org.eclipse.jetty.io.LEVEL", "WARN");

        StdErrLog root = new StdErrLog("", props);
        assertLevel(root,StdErrLog.LEVEL_INFO); // default

        StdErrLog log = (StdErrLog)root.getLogger(StdErrLogTest.class.getName());
        Assert.assertThat("Log.isDebugEnabled()", log.isDebugEnabled(), is(false));
        assertLevel(log,StdErrLog.LEVEL_WARN); // as configured

        // Boot stomp it all to debug
        root.setDebugEnabled(true);
        Assert.assertThat("Log.isDebugEnabled()", log.isDebugEnabled(), is(true));
        assertLevel(log,StdErrLog.LEVEL_DEBUG); // as stomped

        // Restore configured
        root.setDebugEnabled(false);
        Assert.assertThat("Log.isDebugEnabled()", log.isDebugEnabled(), is(false));
        assertLevel(log,StdErrLog.LEVEL_WARN); // as configured
    }
