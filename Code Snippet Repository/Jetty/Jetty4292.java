    @Test
    public void testIsNotGzipCompressedByContentType() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        // Prepare server file
        int filesize = tester.getOutputBufferSize() * 4;
        tester.prepareServerFile("file.mp3",filesize);

        // Add content servlet
        tester.setContentServlet(DefaultServlet.class);

        try
        {
            tester.start();
            HttpTester.Response http = assertIsResponseNotGzipCompressed(tester,"GET","file.mp3",filesize,HttpStatus.OK_200);
            Assert.assertNull(http.get("Vary"));
        }
        finally
        {
            tester.stop();
        }
    }
