    @Test
    public void testBadIPv6Host() throws Exception
    {
        try(StacklessLogging s = new StacklessLogging(HttpParser.class))
        {
            ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.1\r\n"
                    + "Host: [::1\r\n"
                    + "Connection: close\r\n"
                    + "\r\n");

            HttpParser.RequestHandler handler = new Handler();
            HttpParser parser = new HttpParser(handler);
            parser.parseNext(buffer);
            Assert.assertThat(_bad, Matchers.containsString("Bad"));
        }
    }
