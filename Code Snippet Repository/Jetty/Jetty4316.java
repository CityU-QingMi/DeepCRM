    public HttpTester.Response assertIsResponseNotModified(String method, String requestedFilename, long ifmodifiedsince) throws Exception
    {
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        request.setMethod(method);
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setHeader("Accept-Encoding",compressionType);
        if (ifmodifiedsince > 0)
            request.setHeader(HttpHeader.IF_MODIFIED_SINCE.asString(),DateGenerator.formatDate(ifmodifiedsince));
        if (this.userAgent != null)
            request.setHeader("User-Agent",this.userAgent);
        request.setURI("/context/" + requestedFilename);

        // Issue the request
        response = HttpTester.parseResponse(tester.getResponses(request.generate()));

        Assert.assertThat(response.getStatus(),Matchers.equalTo(304));
        Assert.assertThat(response.get("ETag"),Matchers.startsWith("W/"));

        return response;
    }
