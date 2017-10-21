    @Test
    public void testCaseSensitiveLegacy() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "gEt / http/1.0\r\n" +
                        "HOST: localhost\r\n" +
                        "cOnNeCtIoN: ClOsE\r\n" +
                        "\r\n");
        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler, -1, HttpCompliance.LEGACY);
        parseAll(parser, buffer);
        Assert.assertNull(_bad);
        Assert.assertEquals("gEt", _methodOrVersion);
        Assert.assertEquals("/", _uriOrStatus);
        Assert.assertEquals("HTTP/1.0", _versionOrReason);
        Assert.assertEquals("HOST", _hdr[0]);
        Assert.assertEquals("localhost", _val[0]);
        Assert.assertEquals("cOnNeCtIoN", _hdr[1]);
        Assert.assertEquals("ClOsE", _val[1]);
        Assert.assertEquals(1, _headers);
        Assert.assertThat(_complianceViolation, Matchers.containsString("case sensitive"));
    }
