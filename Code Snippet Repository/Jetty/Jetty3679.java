    @Test
    @Stress("")
    public void testNonPersistent() throws Throwable
    {
        // TODO needs to be further investigated
        assumeTrue(!OS.IS_OSX);

        doThreads(20,20,false);
        Thread.sleep(1000);
        doThreads(200,10,false);
        Thread.sleep(1000);
        doThreads(200,200,false);
    }
