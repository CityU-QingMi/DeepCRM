    @Test
    public void testHeaderParseLF() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "GET / HTTP/1.0\n" +
                        "Host: localhost\n" +
                        "Header1: value1\n" +
                        "Header2:   value 2a value 2b  \n" +
                        "Header3: 3\n" +
                        "Header4:value4\n" +
                        "Server5: notServer\n" +
                        "HostHeader: notHost\n" +
                        "Connection: close\n" +
                        "Accept-Encoding: gzip, deflated\n" +
                        "Accept: unknown\n" +
                        "\n");
        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);

        Assert.assertEquals("GET", _methodOrVersion);
        Assert.assertEquals("/", _uriOrStatus);
        Assert.assertEquals("HTTP/1.0", _versionOrReason);
        Assert.assertEquals("Host", _hdr[0]);
        Assert.assertEquals("localhost", _val[0]);
        Assert.assertEquals("Header1", _hdr[1]);
        Assert.assertEquals("value1", _val[1]);
        Assert.assertEquals("Header2", _hdr[2]);
        Assert.assertEquals("value 2a value 2b", _val[2]);
        Assert.assertEquals("Header3", _hdr[3]);
        Assert.assertEquals("3", _val[3]);
        Assert.assertEquals("Header4", _hdr[4]);
        Assert.assertEquals("value4", _val[4]);
        Assert.assertEquals("Server5", _hdr[5]);
        Assert.assertEquals("notServer", _val[5]);
        Assert.assertEquals("HostHeader", _hdr[6]);
        Assert.assertEquals("notHost", _val[6]);
        Assert.assertEquals("Connection", _hdr[7]);
        Assert.assertEquals("close", _val[7]);
        Assert.assertEquals("Accept-Encoding", _hdr[8]);
        Assert.assertEquals("gzip, deflated", _val[8]);
        Assert.assertEquals("Accept", _hdr[9]);
        Assert.assertEquals("unknown", _val[9]);
        Assert.assertEquals(9, _headers);
    }
