    @Test
    public void testParseNotFound()
    {
        StringBuilder resp = new StringBuilder();
        resp.append("HTTP/1.1 404 Not Found\r\n");
        resp.append("Date: Fri, 26 Apr 2013 21:43:08 GMT\r\n");
        resp.append("Content-Type: text/html; charset=ISO-8859-1\r\n");
        resp.append("Cache-Control: must-revalidate,no-cache,no-store\r\n");
        resp.append("Content-Length: 38\r\n");
        resp.append("Server: Jetty(9.0.0.v20130308)\r\n");
        resp.append("\r\n");
        // and some body content
        resp.append("What you are looking for is not here\r\n");

        ByteBuffer buf = BufferUtil.toBuffer(resp.toString(),StandardCharsets.UTF_8);

        HttpResponseParseCapture capture = new HttpResponseParseCapture();
        HttpResponseHeaderParser parser = new HttpResponseHeaderParser(capture);
        assertThat("Parser.parse",parser.parse(buf),notNullValue());
        assertThat("Response.statusCode",capture.getStatusCode(),is(404));
        assertThat("Response.statusReason",capture.getStatusReason(),is("Not Found"));
        assertThat("Response.headers[Content-Length]",capture.getHeader("Content-Length"),is("38"));

        assertThat("Response.remainingBuffer",capture.getRemainingBuffer().remaining(),is(38));
    }
