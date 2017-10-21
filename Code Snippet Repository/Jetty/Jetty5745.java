    @Test
    public void testToString()
    {
        StdErrLog log = new StdErrLog("jetty",new Properties());

        log.setLevel(StdErrLog.LEVEL_ALL);
        Assert.assertThat("Logger.toString", log.toString(), is("StdErrLog:jetty:LEVEL=ALL"));

        log.setLevel(StdErrLog.LEVEL_DEBUG);
        Assert.assertThat("Logger.toString", log.toString(), is("StdErrLog:jetty:LEVEL=DEBUG"));

        log.setLevel(StdErrLog.LEVEL_INFO);
        Assert.assertThat("Logger.toString", log.toString(), is("StdErrLog:jetty:LEVEL=INFO"));

        log.setLevel(StdErrLog.LEVEL_WARN);
        Assert.assertThat("Logger.toString", log.toString(), is("StdErrLog:jetty:LEVEL=WARN"));

        log.setLevel(99); // intentionally bogus level
        Assert.assertThat("Logger.toString", log.toString(), is("StdErrLog:jetty:LEVEL=?"));
    }
