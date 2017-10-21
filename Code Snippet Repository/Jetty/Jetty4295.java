    @Test
    public void testGzipCompressedByContentTypeWithEncoding() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);
        tester.setContentServlet(HttpContentTypeWithEncoding.class);
        tester.getGzipHandler().addIncludedMimeTypes("text/plain");
        tester.getGzipHandler().setExcludedAgentPatterns();
        try
        {
            tester.start();
            HttpTester.Response http = tester.assertNonStaticContentIsResponseGzipCompressed("GET","xxx",HttpContentTypeWithEncoding.COMPRESSED_CONTENT);
            Assert.assertEquals("Accept-Encoding",http.get("Vary"));
        }
        finally
        {
            tester.stop();
        }
    }
