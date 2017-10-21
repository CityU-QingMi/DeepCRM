    @Test
    public void testStdErrLogName()
    {
        StdErrLog log = new StdErrLog("test",new Properties());
        log.setPrintLongNames(true);
        StdErrCapture output = new StdErrCapture(log);

        Assert.assertThat("Log.name", log.getName(), is("test"));
        Logger next=log.getLogger("next");
        Assert.assertThat("Log.name(child)", next.getName(), is("test.next"));
        next.info("testing {} {}","next","info");

        output.assertContains(":test.next:tname: testing next info");
    }
