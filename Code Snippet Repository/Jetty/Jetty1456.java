    @Test
    public void testQuoted() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.0\n" +
                        "Name0: \"value0\"\t\n" +
                        "Name1: \"value\t1\"\n" +
                        "Name2: \"value\t2A\",\"value,2B\"\t\n" +
                        "\n");
        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);

        Assert.assertEquals("GET", _methodOrVersion);
        Assert.assertEquals("/", _uriOrStatus);
        Assert.assertEquals("HTTP/1.0", _versionOrReason);
        Assert.assertEquals("Name0", _hdr[0]);
        Assert.assertEquals("\"value0\"", _val[0]);
        Assert.assertEquals("Name1", _hdr[1]);
        Assert.assertEquals("\"value\t1\"", _val[1]);
        Assert.assertEquals("Name2", _hdr[2]);
        Assert.assertEquals("\"value\t2A\",\"value,2B\"", _val[2]);
        Assert.assertEquals(2, _headers);
    }
