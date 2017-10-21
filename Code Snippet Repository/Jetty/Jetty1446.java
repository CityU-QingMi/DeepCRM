    @Test
    public void testFoldedField7230() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.0\r\n" +
                        "Host: localhost\r\n" +
                        "Name: value\r\n" +
                        " extra\r\n" +
                        "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler, 4096, HttpCompliance.RFC7230);
        parseAll(parser, buffer);

        Assert.assertThat(_bad, Matchers.notNullValue());
        Assert.assertThat(_bad, Matchers.containsString("Header Folding"));
        Assert.assertNull(_complianceViolation);
    }
