    @Test
    public void testSimple() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.0\r\n" +
                        "Host: localhost\r\n" +
                        "Connection: close\r\n" +
                        "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);

        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
        Assert.assertEquals("GET", _methodOrVersion);
        Assert.assertEquals("/", _uriOrStatus);
        Assert.assertEquals("HTTP/1.0", _versionOrReason);
        Assert.assertEquals("Host", _hdr[0]);
        Assert.assertEquals("localhost", _val[0]);
        Assert.assertEquals("Connection", _hdr[1]);
        Assert.assertEquals("close", _val[1]);
        Assert.assertEquals(1, _headers);
    }
