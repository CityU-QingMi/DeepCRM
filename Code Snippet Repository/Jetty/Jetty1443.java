    @Test
    public void testConnect() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer("CONNECT 192.168.1.2:80 HTTP/1.1\r\n" + "\r\n");
        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);
        Assert.assertEquals("CONNECT", _methodOrVersion);
        Assert.assertEquals("192.168.1.2:80", _uriOrStatus);
        Assert.assertEquals("HTTP/1.1", _versionOrReason);
        Assert.assertEquals(-1, _headers);
    }
