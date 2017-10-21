    @Test
    public void testIsGzipCompressedWithQ() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType,"something;q=0.1," + compressionType + ";q=0.5");

        int filesize = tester.getOutputBufferSize() / 4;
        tester.prepareServerFile("file.txt",filesize);

        tester.setContentServlet(org.eclipse.jetty.servlet.DefaultServlet.class);
        tester.getGzipHandler().setExcludedAgentPatterns();

        try
        {
            tester.start();
            HttpTester.Response http = tester.assertIsResponseGzipCompressed("GET","file.txt");
            Assert.assertEquals("Accept-Encoding",http.get("Vary"));
        }
        finally
        {
            tester.stop();
        }
    }
