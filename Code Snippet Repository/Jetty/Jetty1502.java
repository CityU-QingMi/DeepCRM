    @Test
    public void testUriHost11() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET http://host/ HTTP/1.1\r\n"
                        + "Connection: close\r\n"
                        + "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertEquals("No Host", _bad);
        Assert.assertEquals("http://host/", _uriOrStatus);
        Assert.assertEquals(0, _port);
    }
