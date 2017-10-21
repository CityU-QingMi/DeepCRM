    @Test
    public void testIsNotGzipCompressedWithZeroQ() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType + "; q=0");

        // Configure Gzip Handler
        tester.getGzipHandler().addIncludedMimeTypes("text/plain");

        // Prepare server file
        int filesize = tester.getOutputBufferSize() / 4;
        tester.prepareServerFile("file.txt",filesize);

        // Add content servlet
        tester.setContentServlet(DefaultServlet.class);

        try
        {
            tester.start();
            HttpTester.Response http = assertIsResponseNotGzipCompressed(tester,"GET","file.txt",filesize,HttpStatus.OK_200);
            assertThat("Response[Vary]",http.get("Vary"),containsString("Accept-Encoding"));
        }
        finally
        {
            tester.stop();
        }
    }
