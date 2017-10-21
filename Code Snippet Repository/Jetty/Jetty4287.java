    private void testWithGzip(Class<? extends TestDirContentServlet> contentServlet) throws Exception
    {
        GzipTester tester = new GzipTester(testingdir, GzipHandler.GZIP);
        
        // Add AsyncGzip Configuration
        tester.getGzipHandler().setIncludedMimeTypes("text/plain");
        tester.getGzipHandler().setIncludedPaths("*.txt","*.mp3");

        // Add content servlet
        tester.setContentServlet(contentServlet);
        
        try
        {
            String testFilename = String.format("%s-%s", contentServlet.getSimpleName(), fileName);
            File testFile = tester.prepareServerFile(testFilename,fileSize);
            
            tester.start();
            
            HttpTester.Response response = tester.executeRequest("GET","/context/" + testFile.getName(),5,TimeUnit.SECONDS);
            
            if (response.getStatus()!=200)
                System.err.println("DANG!!!! "+response);
            
            assertThat("Response status", response.getStatus(), is(HttpStatus.OK_200));
            
            if (expectCompressed)
            {
                // Must be gzip compressed
                assertThat("Content-Encoding",response.get("Content-Encoding"),containsString(GzipHandler.GZIP));
            } else
            {
                assertThat("Content-Encoding",response.get("Content-Encoding"),not(containsString(GzipHandler.GZIP)));
            }
            
            // Uncompressed content Size
            ContentMetadata content = tester.getResponseMetadata(response);
            assertThat("(Uncompressed) Content Length", content.size, is((long)fileSize));
        }
        finally
        {
            tester.stop();
        }
    }
