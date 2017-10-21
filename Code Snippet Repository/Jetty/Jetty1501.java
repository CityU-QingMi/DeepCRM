    @Test
    public void testHost() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.1\r\n"
                        + "Host: host\r\n"
                        + "Connection: close\r\n"
                        + "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertEquals("host", _host);
        Assert.assertEquals(0, _port);
    }
