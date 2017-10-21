    @Test
    public void testNoStatus() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "HTTP/1.1\r\n"
                        + "Content-Length: 0\r\n"
                        + "Connection: close\r\n"
                        + "\r\n");

        HttpParser.ResponseHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);

        parser.parseNext(buffer);
        Assert.assertEquals(null, _methodOrVersion);
        Assert.assertEquals("No Status", _bad);
        Assert.assertFalse(buffer.hasRemaining());
        Assert.assertEquals(HttpParser.State.CLOSE, parser.getState());
        parser.atEOF();
        parser.parseNext(BufferUtil.EMPTY_BUFFER);
        Assert.assertEquals(HttpParser.State.CLOSED, parser.getState());
    }
