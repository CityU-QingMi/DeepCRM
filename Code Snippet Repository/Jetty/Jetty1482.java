    @Test
    public void testResponse101WithTransferEncoding() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "HTTP/1.1 101 switching protocols\r\n"
                        + "Transfer-Encoding: chunked\r\n"
                        + "\r\n");

        HttpParser.ResponseHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertEquals("HTTP/1.1", _methodOrVersion);
        Assert.assertEquals("101", _uriOrStatus);
        Assert.assertEquals("switching protocols", _versionOrReason);
        Assert.assertEquals(null, _content);
        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
    }
