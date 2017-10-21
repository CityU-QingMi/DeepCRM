    @Test
    public void testChunkEarlyEOF() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET /chunk HTTP/1.0\r\n"
                        + "Header1: value1\r\n"
                        + "Transfer-Encoding: chunked\r\n"
                        + "\r\n"
                        + "a;\r\n"
                        + "0123456789\r\n");
        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.atEOF();
        parseAll(parser, buffer);

        Assert.assertEquals("GET", _methodOrVersion);
        Assert.assertEquals("/chunk", _uriOrStatus);
        Assert.assertEquals("HTTP/1.0", _versionOrReason);
        Assert.assertEquals(1, _headers);
        Assert.assertEquals("Header1", _hdr[0]);
        Assert.assertEquals("value1", _val[0]);
        Assert.assertEquals("0123456789", _content);

        Assert.assertTrue(_early);
    }
