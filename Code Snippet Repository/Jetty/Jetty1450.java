    @Test
    public void testNoColon2616() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.0\r\n" +
                        "Host: localhost\r\n" +
                        "Name\r\n" +
                        "Other: value\r\n" +
                        "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler,HttpCompliance.RFC2616);
        parseAll(parser, buffer);

        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
        Assert.assertEquals("GET", _methodOrVersion);
        Assert.assertEquals("/", _uriOrStatus);
        Assert.assertEquals("HTTP/1.0", _versionOrReason);
        Assert.assertEquals("Host", _hdr[0]);
        Assert.assertEquals("localhost", _val[0]);
        Assert.assertEquals("Name", _hdr[1]);
        Assert.assertEquals("", _val[1]);
        Assert.assertEquals("Other", _hdr[2]);
        Assert.assertEquals("value", _val[2]);
        Assert.assertEquals(2, _headers);
        Assert.assertThat(_complianceViolation, Matchers.containsString("name only"));
    }
