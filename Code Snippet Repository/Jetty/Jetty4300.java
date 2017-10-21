    @Test
    public void testUserAgentExclusionDefault() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);
        tester.setContentServlet(DefaultServlet.class);
        tester.setUserAgent("Some MSIE 6.0 user-agent");

        int filesize = tester.getOutputBufferSize() * 4;
        tester.prepareServerFile("file.txt",filesize);

        try
        {
            tester.start();
            HttpTester.Response http = assertIsResponseNotGzipCompressed(tester,"GET","file.txt",filesize,HttpStatus.OK_200);
            Assert.assertEquals("Accept-Encoding, User-Agent",http.get("Vary"));
        }
        finally
        {
            tester.stop();
        }
    }
