    @Test
    public void testIsNotGzipCompressedHttpStatus() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        // Configure Gzip Handler
        tester.getGzipHandler().addIncludedMimeTypes("text/plain");

        // Test error code 204
        tester.setContentServlet(HttpStatusServlet.class);

        try
        {
            tester.start();

            HttpTester.Response response = tester.executeRequest("GET","/context/",5,TimeUnit.SECONDS);

            assertThat("Response status",response.getStatus(),is(HttpStatus.NO_CONTENT_204));
            assertThat("Content-Encoding",response.get("Content-Encoding"),not(containsString(compressionType)));
        }
        finally
        {
            tester.stop();
        }

    }
