    @Test
    public void testGetChildLogger_EmptyNameSpaces()
    {
        String baseName = "jetty";
        StdErrLog log = new StdErrLog(baseName,new Properties());
        try (StacklessLogging stackless = new StacklessLogging(log))
        {
            Assert.assertThat("Logger.name", log.getName(), is("jetty"));

            // Pass empty name as child reference, should return parent logger
            Logger log2 = log.getLogger("      ");
            Assert.assertThat("Logger.child.name", log2.getName(), is("jetty"));
            Assert.assertSame("Should have returned same logger", log2, log);
        }
    }
