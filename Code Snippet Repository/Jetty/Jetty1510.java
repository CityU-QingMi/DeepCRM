    @Test
    public void testHostBadPort() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.1\r\n"
                        + "Host: myhost:testBadPort\r\n"
                        + "Connection: close\r\n"
                        + "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertThat(_bad, Matchers.containsString("Bad Host"));
    }
