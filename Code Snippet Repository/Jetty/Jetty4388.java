    @Test
    public void testDosGzip() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir, GzipHandler.GZIP);
        
        // Add (DoSFilter-like) manip filter
        FilterHolder manipHolder = new FilterHolder(AsyncManipFilter.class);
        manipHolder.setAsyncSupported(true);
        tester.addFilter(manipHolder,"/*",EnumSet.of(DispatcherType.REQUEST,DispatcherType.ASYNC));
        
        // Add Gzip Filter first (in chain of DosFilter)
        FilterHolder gzipHolder = new FilterHolder(gzipFilterClass);
        gzipHolder.setAsyncSupported(true);
        tester.addFilter(gzipHolder,"*.txt",EnumSet.of(DispatcherType.REQUEST,DispatcherType.ASYNC));
        tester.addFilter(gzipHolder,"*.mp3",EnumSet.of(DispatcherType.REQUEST,DispatcherType.ASYNC));
        gzipHolder.setInitParameter("mimeTypes","text/plain");

        // Add content servlet
        tester.setContentServlet(contentServletClass);
        
        try
        {
            String testFilename = String.format("DosGzip-%s-%s",contentServletClass.getSimpleName(),fileName);
            File testFile = tester.prepareServerFile(testFilename,fileSize);
            
            tester.start();
            
            HttpTester.Response response = tester.executeRequest("GET","/context/" + testFile.getName(),5,TimeUnit.SECONDS);
            
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
