    @Test
    public void testNoColon7230() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.0\r\n" +
                        "Host: localhost\r\n" +
                        "Name\r\n" +
                        "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler,HttpCompliance.RFC7230);
        parseAll(parser, buffer);
        Assert.assertThat(_bad, Matchers.containsString("Illegal character"));
        Assert.assertNull(_complianceViolation);
    }
