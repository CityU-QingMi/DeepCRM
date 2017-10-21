    @Test
    public void testEarlyEOF() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET /uri HTTP/1.0\r\n"
                        + "Content-Length: 20\r\n"
                        + "\r\n"
                        + "0123456789");
        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.atEOF();
        parseAll(parser, buffer);

        Assert.assertEquals("GET", _methodOrVersion);
        Assert.assertEquals("/uri", _uriOrStatus);
        Assert.assertEquals("HTTP/1.0", _versionOrReason);
        Assert.assertEquals("0123456789", _content);

        Assert.assertTrue(_early);
    }
