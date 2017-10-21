    @Test
    public void testNotGzipedIfNotModified() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        int filesize = tester.getOutputBufferSize() * 4;
        tester.prepareServerFile("file.txt",filesize);

        tester.setContentServlet(org.eclipse.jetty.servlet.DefaultServlet.class);

        try
        {
            tester.start();
            tester.assertIsResponseNotModified("GET","file.txt",System.currentTimeMillis() + 4000);
        }
        finally
        {
            tester.stop();
        }
    }
