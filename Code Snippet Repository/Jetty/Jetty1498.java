    @Test
    public void testDuplicateContentLengthWithCorrectThenLargerValue()
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "POST / HTTP/1.1\r\n"
                        + "Content-Length: 1\r\n"
                        + "Content-Length: 2\r\n"
                        + "Connection: close\r\n"
                        + "\r\n"
                        + "X");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);

        parser.parseNext(buffer);
        Assert.assertEquals("POST", _methodOrVersion);
        Assert.assertEquals("Duplicate Content-Length", _bad);
        Assert.assertFalse(buffer.hasRemaining());
        Assert.assertEquals(HttpParser.State.CLOSE, parser.getState());
        parser.atEOF();
        parser.parseNext(BufferUtil.EMPTY_BUFFER);
        Assert.assertEquals(HttpParser.State.CLOSED, parser.getState());
    }
