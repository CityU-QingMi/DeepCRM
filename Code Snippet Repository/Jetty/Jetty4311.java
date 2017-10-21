    @Test
    public void testGzipedIfModified() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        int filesize = tester.getOutputBufferSize() * 4;
        tester.prepareServerFile("file.txt",filesize);

        tester.setContentServlet(org.eclipse.jetty.servlet.DefaultServlet.class);

        try
        {
            tester.start();
            HttpTester.Response http = tester.assertIsResponseGzipCompressed("GET","file.txt",System.currentTimeMillis() - 4000);
            Assert.assertEquals("Accept-Encoding, User-Agent",http.get("Vary"));
        }
        finally
        {
            tester.stop();
        }
    }
