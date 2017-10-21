    public HttpTester.Response assertIsResponseNotGzipCompressed(GzipTester tester, String method, String filename, int expectedFilesize, int status)
            throws Exception
    {
        HttpTester.Response response = tester.executeRequest(method,"/context/" + filename,5,TimeUnit.SECONDS);

        assertThat("Response status",response.getStatus(),is(status));
        assertThat("Content-Encoding",response.get("Content-Encoding"),not(containsString(compressionType)));

        assertResponseContent(tester,response,status,filename,expectedFilesize);

        return response;
    }
