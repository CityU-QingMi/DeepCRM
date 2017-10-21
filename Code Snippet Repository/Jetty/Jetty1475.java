    @Test
    public void testResponseParse0() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "HTTP/1.1 200 Correct\r\n"
                        + "Content-Length: 10\r\n"
                        + "Content-Type: text/plain\r\n"
                        + "\r\n"
                        + "0123456789\r\n");

        HttpParser.ResponseHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertEquals("HTTP/1.1", _methodOrVersion);
        Assert.assertEquals("200", _uriOrStatus);
        Assert.assertEquals("Correct", _versionOrReason);
        Assert.assertEquals(10, _content.length());
        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
    }
