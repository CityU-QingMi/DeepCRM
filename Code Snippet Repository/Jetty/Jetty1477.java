    @Test
    public void testResponseParse2() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "HTTP/1.1 204 No-Content\r\n"
                        + "Header: value\r\n"
                        + "\r\n"

                        + "HTTP/1.1 200 Correct\r\n"
                        + "Content-Length: 10\r\n"
                        + "Content-Type: text/plain\r\n"
                        + "\r\n"
                        + "0123456789\r\n");

        HttpParser.ResponseHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertEquals("HTTP/1.1", _methodOrVersion);
        Assert.assertEquals("204", _uriOrStatus);
        Assert.assertEquals("No-Content", _versionOrReason);
        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);

        parser.reset();
        init();

        parser.parseNext(buffer);
        parser.atEOF();
        Assert.assertEquals("HTTP/1.1", _methodOrVersion);
        Assert.assertEquals("200", _uriOrStatus);
        Assert.assertEquals("Correct", _versionOrReason);
        Assert.assertEquals(_content.length(), 10);
        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
    }
