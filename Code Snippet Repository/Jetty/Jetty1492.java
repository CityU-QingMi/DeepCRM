    @Test
    public void testBadCR() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.0\r\n"
                        + "Content-Length: 0\r"
                        + "Connection: close\r"
                        + "\r");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);

        parser.parseNext(buffer);
        Assert.assertEquals("Bad EOL", _bad);
        Assert.assertFalse(buffer.hasRemaining());
        Assert.assertEquals(HttpParser.State.CLOSE, parser.getState());
        parser.atEOF();
        parser.parseNext(BufferUtil.EMPTY_BUFFER);
        Assert.assertEquals(HttpParser.State.CLOSED, parser.getState());

        buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.0\r"
                        + "Content-Length: 0\r"
                        + "Connection: close\r"
                        + "\r");

        handler = new Handler();
        parser = new HttpParser(handler);

        parser.parseNext(buffer);
        Assert.assertEquals("Bad EOL", _bad);
        Assert.assertFalse(buffer.hasRemaining());
        Assert.assertEquals(HttpParser.State.CLOSE, parser.getState());
        parser.atEOF();
        parser.parseNext(BufferUtil.EMPTY_BUFFER);
        Assert.assertEquals(HttpParser.State.CLOSED, parser.getState());
    }
