    @Test
    public void testFoldedField2616() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.0\r\n" +
                        "Host: localhost\r\n" +
                        "Name: value\r\n" +
                        " extra\r\n" +
                        "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler, HttpCompliance.RFC2616);
        parseAll(parser, buffer);

        Assert.assertThat(_bad, Matchers.nullValue());
        Assert.assertEquals("Host", _hdr[0]);
        Assert.assertEquals("localhost", _val[0]);
        Assert.assertEquals("Name", _hdr[1]);
        Assert.assertEquals("value extra", _val[1]);
        Assert.assertEquals(1, _headers);
        Assert.assertThat(_complianceViolation, Matchers.containsString("folding"));
    }
