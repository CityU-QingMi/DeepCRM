    @Test
    public void testBadRequestVersion() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HPPT/7.7\r\n"
                        + "Content-Length: 0\r\n"
                        + "Connection: close\r\n"
                        + "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);

        parser.parseNext(buffer);
        Assert.assertEquals(null, _methodOrVersion);
        Assert.assertEquals("Unknown Version", _bad);
        Assert.assertFalse(buffer.hasRemaining());
        Assert.assertEquals(HttpParser.State.CLOSE, parser.getState());
        parser.atEOF();
        parser.parseNext(BufferUtil.EMPTY_BUFFER);
        Assert.assertEquals(HttpParser.State.CLOSED, parser.getState());

        buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.01\r\n"
                        + "Content-Length: 0\r\n"
                        + "Connection: close\r\n"
                        + "\r\n");

        handler = new Handler();
        parser = new HttpParser(handler);

        parser.parseNext(buffer);
        Assert.assertEquals(null, _methodOrVersion);
        Assert.assertEquals("Unknown Version", _bad);
        Assert.assertFalse(buffer.hasRemaining());
        Assert.assertEquals(HttpParser.State.CLOSE, parser.getState());
        parser.atEOF();
        parser.parseNext(BufferUtil.EMPTY_BUFFER);
        Assert.assertEquals(HttpParser.State.CLOSED, parser.getState());
    }
