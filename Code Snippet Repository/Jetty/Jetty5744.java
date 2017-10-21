    @Test
    public void testGetChildLogger_NullParent()
    {
        AbstractLogger log = new StdErrLog(null,new Properties());

        Assert.assertThat("Logger.name", log.getName(), is(""));

        Logger log2 = log.getLogger("jetty");
        Assert.assertThat("Logger.child.name", log2.getName(), is("jetty"));
        Assert.assertNotSame("Should have returned same logger", log2, log);
    }
