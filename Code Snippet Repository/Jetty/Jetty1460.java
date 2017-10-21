    @Test
    public void testBadVersionEncoding() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / H\u00e6P/1.0\r\nHeader0: value0\r\n\n\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);
        Assert.assertThat(_bad, Matchers.notNullValue());
    }
