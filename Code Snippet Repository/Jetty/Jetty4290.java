    @Test
    public void testIsGzipByMethod() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        // Configure Gzip Handler
        tester.getGzipHandler().setIncludedMethods("POST","WIBBLE", "HEAD");

        // Prepare Server File
        int filesize = tester.getOutputBufferSize() * 2;
        tester.prepareServerFile("file.txt",filesize);

        // Content Servlet
        tester.setContentServlet(GetServlet.class);

        try
        {
            tester.start();
            HttpTester.Response response;

            //These methods have content bodies of the compressed response
            tester.assertIsResponseGzipCompressed("POST","file.txt");
            tester.assertIsResponseGzipCompressed("WIBBLE","file.txt");
            
            //A HEAD request should have similar headers, but no body
            response = tester.executeRequest("HEAD","/context/file.txt",5,TimeUnit.SECONDS);
            assertThat("Response status",response.getStatus(),is(HttpStatus.OK_200));
            assertThat("ETag", response.get("ETag"), containsString(CompressedContentFormat.GZIP._etag));
            assertThat("Content encoding", response.get("Content-Encoding"), containsString("gzip"));
            assertNull("Content length", response.get("Content-Length"));
   
            response = tester.executeRequest("GET","/context/file.txt",5,TimeUnit.SECONDS);

            assertThat("Response status",response.getStatus(),is(HttpStatus.OK_200));
            assertThat("Content-Encoding",response.get("Content-Encoding"),not(containsString(compressionType)));

            String content = tester.readResponse(response);
            assertThat("Response content size",content.length(),is(filesize));
            String expectedContent = IO.readToString(testingdir.getPathFile("file.txt").toFile());
            assertThat("Response content",content,is(expectedContent));
        }
        finally
        {
            tester.stop();
        }
    }
