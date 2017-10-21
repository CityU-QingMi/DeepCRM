    @Test
    public void testParseRequest() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.1\r\n" +
                        "Host: localhost\r\n" +
                        "Header1: value1\r\n" +
                        "Connection: close\r\n" +
                        "Accept-Encoding: gzip, deflated\r\n" +
                        "Accept: unknown\r\n" +
                        "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);

        Assert.assertEquals("GET", _methodOrVersion);
        Assert.assertEquals("/", _uriOrStatus);
        Assert.assertEquals("HTTP/1.1", _versionOrReason);
        Assert.assertEquals("Host", _hdr[0]);
        Assert.assertEquals("localhost", _val[0]);
        Assert.assertEquals("Connection", _hdr[2]);
        Assert.assertEquals("close", _val[2]);
        Assert.assertEquals("Accept-Encoding", _hdr[3]);
        Assert.assertEquals("gzip, deflated", _val[3]);
        Assert.assertEquals("Accept", _hdr[4]);
        Assert.assertEquals("unknown", _val[4]);
    }
