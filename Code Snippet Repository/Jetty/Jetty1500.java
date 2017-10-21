    @Test
    public void testContentLengthThenTransferEncodingChunked()
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "POST /chunk HTTP/1.1\r\n"
                        + "Host: localhost\r\n"
                        + "Content-Length: 1\r\n"
                        + "Transfer-Encoding: chunked\r\n"
                        + "\r\n"
                        + "1\r\n"
                        + "X\r\n"
                        + "0\r\n"
                        + "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);

        Assert.assertEquals("POST", _methodOrVersion);
        Assert.assertEquals("/chunk", _uriOrStatus);
        Assert.assertEquals("HTTP/1.1", _versionOrReason);
        Assert.assertEquals("X", _content);

        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
    }
