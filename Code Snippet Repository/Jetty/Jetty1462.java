    @Test
    public void testLineParse_Mock_IP() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer("POST /mock/127.0.0.1 HTTP/1.1\r\n" + "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);
        Assert.assertEquals("POST", _methodOrVersion);
        Assert.assertEquals("/mock/127.0.0.1", _uriOrStatus);
        Assert.assertEquals("HTTP/1.1", _versionOrReason);
        Assert.assertEquals(-1, _headers);
    }
