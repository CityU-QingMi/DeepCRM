    @Test
    public void testResponseReasonIso8859_1() throws Exception
    {   
        ByteBuffer buffer = BufferUtil.toBuffer(
                "HTTP/1.1 302 déplacé temporairement\r\n"
                        + "Content-Length: 0\r\n" 
                        + "\r\n",StandardCharsets.ISO_8859_1);

        HttpParser.ResponseHandler handler = new Handler();
        HttpParser parser = new HttpParser(handler);
        parser.parseNext(buffer);
        Assert.assertEquals("HTTP/1.1", _methodOrVersion);
        Assert.assertEquals("302", _uriOrStatus);
        Assert.assertEquals("déplacé temporairement", _versionOrReason);
    }
