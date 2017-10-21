    @Test
    @Stress("")
    public void testPersistent() throws Throwable
    {
        // TODO needs to be further investigated
        assumeTrue(!OS.IS_OSX);
        doThreads(40,40,true);
        Thread.sleep(1000);
        doThreads(200,10,true);
        Thread.sleep(1000);
        doThreads(200,200,true);
    }
