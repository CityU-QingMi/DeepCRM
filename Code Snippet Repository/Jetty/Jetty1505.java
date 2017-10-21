    @Test
    public void testIPHost() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.1\r\n"
                        + "Host: 192.168.0.1\r\n"
                        + "Connection: close\r\n"
                        + "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertEquals("192.168.0.1", _host);
        Assert.assertEquals(0, _port);
    }
