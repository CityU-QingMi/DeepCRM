    @Test
    public void testUserAgentExclusionByExcludedAgentPatterns() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);
        tester.setUserAgent("foo");

        // Configure Gzip Handler
        tester.getGzipHandler().setExcludedAgentPatterns("bar","fo.*");

        // Prepare server file
        int filesize = tester.getOutputBufferSize() * 4;
        tester.prepareServerFile("file.txt",filesize);

        // Set content servlet
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
