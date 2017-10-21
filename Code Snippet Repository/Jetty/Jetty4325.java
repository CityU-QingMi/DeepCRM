    @Test
    public void testUnderMinSize() throws Exception
    {
        GzipTester tester = new GzipTester(testdir, compressionType);

        tester.setContentServlet(testServlet);
        // A valid mime type that we will never use in this test.
        // configured here to prevent mimeType==null logic
        tester.getGzipHandler().addIncludedMimeTypes("application/soap+xml");
        tester.getGzipHandler().setMinGzipSize(2048);

        tester.copyTestServerFile("small_script.js");

        try {
            tester.start();
            tester.assertIsResponseNotGziped("small_script.js",
                    "small_script.js.sha1",
                    "text/javascript; charset=utf-8");
        } finally {
            tester.stop();
        }
    }
