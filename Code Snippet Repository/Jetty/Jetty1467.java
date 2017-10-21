    @Test
    public void testChunkParseTrailer() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET /chunk HTTP/1.0\r\n"
                        + "Header1: value1\r\n"
                        + "Transfer-Encoding: chunked\r\n"
                        + "\r\n"
                        + "a;\r\n"
                        + "0123456789\r\n"
                        + "1a\r\n"
                        + "ABCDEFGHIJKLMNOPQRSTUVWXYZ\r\n"
                        + "0\r\n"
                        + "Trailer: value\r\n"
                        + "\r\n");
        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);

        Assert.assertEquals("GET", _methodOrVersion);
        Assert.assertEquals("/chunk", _uriOrStatus);
        Assert.assertEquals("HTTP/1.0", _versionOrReason);
        Assert.assertEquals(1, _headers);
        Assert.assertEquals("Header1", _hdr[0]);
        Assert.assertEquals("value1", _val[0]);
        Assert.assertEquals("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ", _content);
        Assert.assertEquals(1, _trailers.size());
        HttpField trailer1 = _trailers.get(0);
        Assert.assertEquals("Trailer", trailer1.getName());
        Assert.assertEquals("value", trailer1.getValue());

        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
    }
