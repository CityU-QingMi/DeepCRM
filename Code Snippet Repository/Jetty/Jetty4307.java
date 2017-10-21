    @Test
    public void testIsNotGzipCompressedSVGZ() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        tester.setContentServlet(DefaultServlet.class);
        tester.copyTestServerFile("test.svgz");
        
        try
        {
            tester.start();
            tester.assertIsResponseNotGzipFiltered("test.svgz","test.svgz.sha1","image/svg+xml","gzip");
        }
        finally
        {
            tester.stop();
        }
    }
