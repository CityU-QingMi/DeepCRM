    @Test
    public void testLineParse1() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer("GET /999\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);
        Assert.assertEquals("HTTP/0.9 not supported", _bad);
        Assert.assertNull(_complianceViolation);
    }
