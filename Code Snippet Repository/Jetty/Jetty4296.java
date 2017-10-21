    @Test
    public void testIsNotGzipCompressedByDeferredContentType() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        // Configure Gzip Handler
        tester.getGzipHandler().addIncludedMimeTypes("text/plain");

        // Prepare server file
        int filesize = tester.getOutputBufferSize() * 4;
        tester.prepareServerFile("file.mp3.deferred",filesize);

        // Add content servlet
        tester.setContentServlet(GetServlet.class);

        try
        {
            tester.start();
            HttpTester.Response response = assertIsResponseNotGzipCompressed(tester,"GET","file.mp3.deferred",filesize,HttpStatus.OK_200);
            assertThat("Response[Vary]", response.get("Vary"), isEmptyOrNullString());
        }
        finally
        {
            tester.stop();
        }
    }
