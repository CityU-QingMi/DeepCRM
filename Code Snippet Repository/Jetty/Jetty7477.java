    @Test
    public void testHttp11Response() throws IOException
    {
        StringBuffer rawResponse = new StringBuffer();
        rawResponse.append("HTTP/1.1 200 OK\n");
        rawResponse.append("Date: Mon, 08 Jun 2009 22:56:04 GMT\n");
        rawResponse.append("Content-Type: text/plain\n");
        rawResponse.append("Content-Length: 28\n");
        rawResponse.append("Last-Modified: Mon, 08 Jun 2009 17:06:22 GMT\n");
        rawResponse.append("Connection: close\n");
        rawResponse.append("Server: Jetty(7.0.y.z-SNAPSHOT\n");
        rawResponse.append("\n");
        rawResponse.append("ABCDEFGHIJKLMNOPQRSTTUVWXYZ\n");
        rawResponse.append("\n");

        HttpTester.Response response = HttpTester.parseResponse(rawResponse.toString());

        Assert.assertEquals("Response.version","HTTP/1.1",response.getVersion().asString());
        Assert.assertEquals("Response.status",200,response.getStatus());
        Assert.assertEquals("Response.reason","OK",response.getReason());

        Assert.assertEquals("Response[Content-Type]","text/plain",response.get(HttpHeader.CONTENT_TYPE));
        Assert.assertEquals("Response[Content-Length]",28,response.getLongField("Content-Length"));
        Assert.assertEquals("Response[Connection]","close",response.get("Connection"));

        String expected = "ABCDEFGHIJKLMNOPQRSTTUVWXYZ\n";

        Assert.assertEquals("Response.content",expected,response.getContent().toString());
    }
