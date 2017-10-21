    @Test
    public void testLineParse2() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer("POST /222  \r\n");

        _versionOrReason = null;
        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);
        Assert.assertEquals("HTTP/0.9 not supported", _bad);
        Assert.assertNull(_complianceViolation);
    }
