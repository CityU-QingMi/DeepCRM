    @Test
    public void testOverMinSize() throws Exception
    {
        GzipTester tester = new GzipTester(testdir, compressionType);

        tester.setContentServlet(testServlet);
        tester.getGzipHandler().addIncludedMimeTypes("application/soap+xml","text/javascript","application/javascript");
        tester.getGzipHandler().setMinGzipSize(2048);

        tester.copyTestServerFile("big_script.js");

        try {
            tester.start();
            tester.assertIsResponseGzipCompressed("GET","big_script.js");
        } finally {
            tester.stop();
        }
    }
