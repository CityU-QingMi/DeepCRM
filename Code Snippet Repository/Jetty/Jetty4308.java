    @Test
    public void testIsGzipCompressedEmpty() throws Exception
    {
        GzipTester tester = new GzipTester(testingdir,compressionType);

        // Configure Gzip Handler
        tester.getGzipHandler().addIncludedMimeTypes("text/plain");

        // Prepare server file
        tester.prepareServerFile("empty.txt",0);

        // Set content servlet
        tester.setContentServlet(DefaultServlet.class);

        try
        {
            tester.start();

            HttpTester.Response response;

            response = tester.executeRequest("GET","/context/empty.txt",5,TimeUnit.SECONDS);

            assertThat("Response status",response.getStatus(),is(HttpStatus.OK_200));
            assertThat("Content-Encoding",response.get("Content-Encoding"),not(containsString(compressionType)));

            String content = tester.readResponse(response);
            assertThat("Response content size",content.length(),is(0));
            String expectedContent = IO.readToString(testingdir.getPathFile("empty.txt").toFile());
            assertThat("Response content",content,is(expectedContent));
        }
        finally
        {
            tester.stop();
        }
    }
