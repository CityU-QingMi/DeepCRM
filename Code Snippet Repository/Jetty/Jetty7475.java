    @Test
    public void testBasicHttp10Request() throws IOException
    {
        HttpTester.Request request = HttpTester.newRequest();
        request.setMethod("GET");
        request.setURI("/uri");
        request.setVersion("HTTP/1.0");
        request.put("Host","fakehost");

        ByteBuffer bBuff = request.generate();

        StringBuffer expectedRequest = new StringBuffer();
        expectedRequest.append("GET /uri HTTP/1.0\r\n");
        expectedRequest.append("Host: fakehost\r\n");
        expectedRequest.append("\r\n");

        Assert.assertEquals("Basic Request",expectedRequest.toString(),BufferUtil.toString(bBuff));
    }
