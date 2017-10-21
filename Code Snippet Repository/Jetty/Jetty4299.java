    @Test
    public void testUserAgentExclusion() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);
        tester.setUserAgent("foo");

        // Configure Gzip Handler
        tester.getGzipHandler().addIncludedMimeTypes("text/plain");
        tester.getGzipHandler().setExcludedAgentPatterns("bar","foo");
        
        // Prepare server file
        int filesize = tester.getOutputBufferSize() * 4;
        tester.prepareServerFile("file.txt",filesize);

        // Add content servlet
        tester.setContentServlet(DefaultServlet.class);

        try
        {
            tester.start();
            assertIsResponseNotGzipCompressed(tester,"GET","file.txt",filesize,HttpStatus.OK_200);
        }
        finally
        {
            tester.stop();
        }
    }
