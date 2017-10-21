    @Test
    public void testIsDebugEnabled() throws Exception
    {
        StdErrLog log = new StdErrLog(StdErrLogTest.class.getName(),new Properties());
        try (StacklessLogging stackless = new StacklessLogging(log))
        {
            log.setLevel(StdErrLog.LEVEL_ALL);
            Assert.assertThat("log.level(all).isDebugEnabled", log.isDebugEnabled(), is(true));

            log.setLevel(StdErrLog.LEVEL_DEBUG);
            Assert.assertThat("log.level(debug).isDebugEnabled", log.isDebugEnabled(), is(true));

            log.setLevel(StdErrLog.LEVEL_INFO);
            Assert.assertThat("log.level(info).isDebugEnabled", log.isDebugEnabled(), is(false));

            log.setLevel(StdErrLog.LEVEL_WARN);
            Assert.assertThat("log.level(warn).isDebugEnabled", log.isDebugEnabled(), is(false));

            log.setLevel(StdErrLog.LEVEL_OFF);
            Assert.assertThat("log.level(off).isDebugEnabled", log.isDebugEnabled(), is(false));
        }
    }
