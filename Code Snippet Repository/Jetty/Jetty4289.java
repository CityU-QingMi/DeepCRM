    @Test
    public void testNotGzipHandlered_Default_AlreadyCompressed() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir, compressionType);

        copyTestFileToServer(alreadyCompressedFilename);

        tester.setContentServlet(TestStaticMimeTypeServlet.class);

        try
        {
            tester.start();
            tester.assertIsResponseNotGziped(alreadyCompressedFilename,alreadyCompressedFilename + ".sha1",expectedContentType);
        }
        finally
        {
            tester.stop();
        }
    }
