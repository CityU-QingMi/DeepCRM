    @Test
    public void testIncludedPaths() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        // Configure Gzip Handler
        tester.getGzipHandler().setExcludedPaths("/bad.txt");
        tester.getGzipHandler().setIncludedPaths("*.txt");

        // Prepare server file
        int filesize = tester.getOutputBufferSize() * 4;
        tester.prepareServerFile("file.txt",filesize);
        tester.prepareServerFile("bad.txt",filesize);

        // Set content servlet
        tester.setContentServlet(DefaultServlet.class);

        try
        {
            tester.start();
            tester.assertIsResponseGzipCompressed("GET","file.txt");
        }
        finally
        {
            tester.stop();
        }
        
        try
        {
            tester.start();
            assertIsResponseNotGzipCompressed(tester,"GET","bad.txt",filesize,HttpStatus.OK_200);
        }
        finally
        {
            tester.stop();
        }
    }
