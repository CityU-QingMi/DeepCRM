    @Test
    public void testGzippedIfSVG() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);
        tester.copyTestServerFile("test.svg");
        tester.setContentServlet(org.eclipse.jetty.servlet.DefaultServlet.class);

        tester.getGzipHandler().addIncludedMimeTypes("image/svg+xml");

        try
        {
            tester.start();
            HttpTester.Response http = tester.assertIsResponseGzipCompressed("GET","test.svg",System.currentTimeMillis() - 4000);
            Assert.assertEquals("Accept-Encoding, User-Agent",http.get("Vary"));
        }
        finally
        {
            tester.stop();
        }
    }
