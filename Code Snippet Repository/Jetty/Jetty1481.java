    @Test
    public void testResponse304WithContentLength() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "HTTP/1.1 304 found\r\n"
                        + "Content-Length: 10\r\n"
                        + "\r\n");

        HttpParser.ResponseHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertEquals("HTTP/1.1", _methodOrVersion);
        Assert.assertEquals("304", _uriOrStatus);
        Assert.assertEquals("found", _versionOrReason);
        Assert.assertEquals(null, _content);
        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
    }
