    private void assertResponseContent(GzipTester tester, HttpTester.Response response, int status, String filename, int expectedFilesize) throws IOException,
            UnsupportedEncodingException
    {
        if (expectedFilesize >= 0)
        {
            assertThat("filename",filename,notNullValue());
            assertThat("Response contentBytes.length",response.getContentBytes().length,is(expectedFilesize));
            String contentLength = response.get("Content-Length");
            if (StringUtil.isNotBlank(contentLength))
            {
                assertThat("Content-Length",response.get("Content-Length"),is(Integer.toString(expectedFilesize)));
            }

            if (status >= 200 && status < 300)
            {
                assertThat("ETag",response.get("ETAG"),startsWith("W/"));
            }

            File serverFile = testingdir.getPathFile(filename).toFile();
            String expectedResponse = IO.readToString(serverFile);

            String actual = tester.readResponse(response);
            Assert.assertEquals("Expected response equals actual response",expectedResponse,actual);
        }
    }
