    @Test
    public void testHTTP2Preface() throws Exception
    {
        ByteBuffer buffer = BufferUtil.toBuffer(
                "PRI * HTTP/2.0\r\n" +
                        "\r\n" +
                        "SM\r\n" +
                        "\r\n");

        HttpParser.RequestHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parseAll(parser, buffer);

        Assert.assertTrue(_headerCompleted);
        Assert.assertTrue(_messageCompleted);
        Assert.assertEquals("PRI", _methodOrVersion);
        Assert.assertEquals("*", _uriOrStatus);
        Assert.assertEquals("HTTP/2.0", _versionOrReason);
        Assert.assertEquals(-1, _headers);
        Assert.assertEquals(null, _bad);
    }
