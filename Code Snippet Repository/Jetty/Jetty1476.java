    @Test
    public void testResponseParse1() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "HTTP/1.1 304 Not-Modified\r\n"
                        + "Connection: close\r\n"
                        + "\r\n");

        HttpParser.ResponseHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertEquals("HTTP/1.1", _methodOrVersion);
        Assert.assertEquals("304", _uriOrStatus);
        Assert.assertEquals("Not-Modified", _versionOrReason);
        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
    }
