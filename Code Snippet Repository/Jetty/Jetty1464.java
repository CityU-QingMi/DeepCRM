    @Test
    public void testCaseInsensitive() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "get / http/1.0\r\n" +
                        "HOST: localhost\r\n" +
                        "cOnNeCtIoN: ClOsE\r\n" +
                        "\r\n");
        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler, -1, HttpCompliance.RFC7230);
        parseAll(parser, buffer);
        Assert.assertNull(_bad);
        Assert.assertEquals("GET", _methodOrVersion);
        Assert.assertEquals("/", _uriOrStatus);
        Assert.assertEquals("HTTP/1.0", _versionOrReason);
        Assert.assertEquals("Host", _hdr[0]);
        Assert.assertEquals("localhost", _val[0]);
        Assert.assertEquals("Connection", _hdr[1]);
        Assert.assertEquals("close", _val[1]);
        Assert.assertEquals(1, _headers);
        Assert.assertNull(_complianceViolation);
    }
