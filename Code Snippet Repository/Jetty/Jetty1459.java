    @Test
    public void testBadMethodEncoding() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "G\u00e6T / HTTP/1.0\r\nHeader0: value0\r\n\n\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);
        Assert.assertThat(_bad, Matchers.notNullValue());
    }
