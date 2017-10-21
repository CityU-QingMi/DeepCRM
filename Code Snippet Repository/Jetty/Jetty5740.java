    @Test
    public void testGetChildLogger_Deep()
    {
        String baseName = "jetty";
        StdErrLog log = new StdErrLog(baseName,new Properties());
        try (StacklessLogging stackless = new StacklessLogging(log))
        {
            Assert.assertThat("Logger.name", log.getName(), is("jetty"));

            Logger log2 = log.getLogger("child.of.the.sixties");
            Assert.assertThat("Logger.child.name", log2.getName(), is("jetty.child.of.the.sixties"));
        }
    }
