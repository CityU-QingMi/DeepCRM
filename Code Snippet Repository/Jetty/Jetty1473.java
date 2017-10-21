    @Test
    public void testLineParse0() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer("POST /foo HTTP/1.0\r\n" + "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);
        Assert.assertEquals("POST", _methodOrVersion);
        Assert.assertEquals("/foo", _uriOrStatus);
        Assert.assertEquals("HTTP/1.0", _versionOrReason);
        Assert.assertEquals(-1, _headers);
    }
