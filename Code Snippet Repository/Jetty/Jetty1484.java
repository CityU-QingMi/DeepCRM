    @Test
    public void testLineParse1_RFC2616() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer("GET /999\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler, HttpCompliance.RFC2616);
        parseAll(parser, buffer);

        Assert.assertNull(_bad);
        Assert.assertEquals("GET", _methodOrVersion);
        Assert.assertEquals("/999", _uriOrStatus);
        Assert.assertEquals("HTTP/0.9", _versionOrReason);
        Assert.assertEquals(-1, _headers);
        Assert.assertThat(_complianceViolation, Matchers.containsString("0.9"));
    }
