    @Test
    public void testUriHost10() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET http://host/ HTTP/1.0\r\n"
                        + "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertNull(_bad);
        Assert.assertEquals("http://host/", _uriOrStatus);
        Assert.assertEquals(0, _port);
    }
