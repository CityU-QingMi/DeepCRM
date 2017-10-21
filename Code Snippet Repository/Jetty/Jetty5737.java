    @Test
    public void testSetGetLevel()
    {
        StdErrLog log = new StdErrLog(StdErrLogTest.class.getName(),new Properties());
        try (StacklessLogging stackless = new StacklessLogging(log))
        {
            log.setLevel(StdErrLog.LEVEL_ALL);
            Assert.assertThat("log.level(all).getLevel()", log.getLevel(), is(StdErrLog.LEVEL_ALL));

            log.setLevel(StdErrLog.LEVEL_DEBUG);
            Assert.assertThat("log.level(debug).getLevel()", log.getLevel(), is(StdErrLog.LEVEL_DEBUG));

            log.setLevel(StdErrLog.LEVEL_INFO);
            Assert.assertThat("log.level(info).getLevel()", log.getLevel(), is(StdErrLog.LEVEL_INFO));

            log.setLevel(StdErrLog.LEVEL_WARN);
            Assert.assertThat("log.level(warn).getLevel()", log.getLevel(), is(StdErrLog.LEVEL_WARN));

            log.setLevel(StdErrLog.LEVEL_OFF);
            Assert.assertThat("log.level(off).getLevel()", log.getLevel(), is(StdErrLog.LEVEL_OFF));
        }
    }
