    @Test
    public void testParseRealWorldResponse()
    {
        // Arbitrary Http Response Headers seen in the wild.
        // Request URI -> https://ssl.google-analytics.com/__utm.gif
        List<String> expected = new ArrayList<>();
        expected.add("HTTP/1.0 200 OK");
        expected.add("Date: Thu, 09 Aug 2012 16:16:39 GMT");
        expected.add("Content-Length: 35");
        expected.add("X-Content-Type-Options: nosniff");
        expected.add("Pragma: no-cache");
        expected.add("Expires: Wed, 19 Apr 2000 11:43:00 GMT");
        expected.add("Last-Modified: Wed, 21 Jan 2004 19:51:30 GMT");
        expected.add("Content-Type: image/gif");
        expected.add("Cache-Control: private, no-cache, no-cache=Set-Cookie, proxy-revalidate");
        expected.add("Age: 518097");
        expected.add("Server: GFE/2.0");
        expected.add("Connection: Keep-Alive");
        expected.add("");

        // Prepare Buffer
        ByteBuffer buf = ByteBuffer.allocate(512);
        for (String line : expected)
        {
            appendUtf8(buf,line + "\r\n");
        }

        BufferUtil.flipToFlush(buf,0);

        // Parse Buffer
        HttpResponseParseCapture capture = new HttpResponseParseCapture();
        HttpResponseHeaderParser parser = new HttpResponseHeaderParser(capture);
        assertThat("Parser.parse",parser.parse(buf),notNullValue());

        Assert.assertThat("Response.statusCode",capture.getStatusCode(),is(200));
        Assert.assertThat("Response.statusReason",capture.getStatusReason(),is("OK"));

        Assert.assertThat("Response.header[age]",capture.getHeader("age"),is("518097"));
    }
