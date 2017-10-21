    @Test
    public void testBasicHttp11Request() throws IOException
    {
        HttpTester.Request request = HttpTester.newRequest();
        request.setMethod("GET");
        request.setVersion(HttpVersion.HTTP_1_1);
        request.setURI("/uri");
        request.put("Host","fakehost");
        request.put("Connection", "close");
        request.setContent("aaa");
       

        ByteBuffer bBuff = request.generate();

        StringBuffer expectedRequest = new StringBuffer();
        expectedRequest.append("GET /uri HTTP/1.1\r\n");
        expectedRequest.append("Host: fakehost\r\n");
        expectedRequest.append("Connection: close\r\n");
        expectedRequest.append("Content-Length: 3\r\n");
        expectedRequest.append("\r\n");
        expectedRequest.append("aaa");
       

        Assert.assertEquals("Basic Request",expectedRequest.toString(),BufferUtil.toString(bBuff));
    }
