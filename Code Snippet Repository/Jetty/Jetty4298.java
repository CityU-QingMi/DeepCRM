    @Test
    public void testIsNotGzipCompressedHttpBadRequestStatus() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        // Configure Gzip Handler
        tester.getGzipHandler().addIncludedMimeTypes("text/plain");

        // Test error code 400
        tester.setContentServlet(HttpErrorServlet.class);

        try
        {
            tester.start();

            HttpTester.Response response = tester.executeRequest("GET","/context/",5,TimeUnit.SECONDS);

            assertThat("Response status",response.getStatus(),is(HttpStatus.BAD_REQUEST_400));
            assertThat("Content-Encoding",response.get("Content-Encoding"),not(containsString(compressionType)));

            String content = tester.readResponse(response);
            assertThat("Response content",content,is("error message"));
        }
        finally
        {
            tester.stop();
        }
    }
