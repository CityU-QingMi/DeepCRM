    @Test
    public void testAsyncReadsWithDelays() throws Exception
    {
        server.setHandler(new AsyncStreamHandler());
        server.start();

        asyncReadTest(64,4,4,20);
        asyncReadTest(256,16,16,50);
        asyncReadTest(256,1,128,10);
        asyncReadTest(128*1024,1,64,10);
        asyncReadTest(256*1024,5321,10,100);
        asyncReadTest(512*1024,32*1024,10,10);
    }
